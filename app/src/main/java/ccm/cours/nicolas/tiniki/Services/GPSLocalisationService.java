package ccm.cours.nicolas.tiniki.Services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import ccm.cours.nicolas.tiniki.Activity.CarteOSM;
import ccm.cours.nicolas.tiniki.Database.MysqlDatabase;
import ccm.cours.nicolas.tiniki.Entity.Zone;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;

public class GPSLocalisationService extends Service {

    private LocationListener listener;
    private LocationManager locationManager;

    private static CarteOSM context;

    public GPSLocalisationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("LPK_LOK_Listener", "onCreate");

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("LPK_LOK_Listener", "" + location.getLongitude() + " / " + location.getLatitude());
                GlobalVariable.getInstance().getConnectedUtilisateur().getMaPosition().setLongitude(location.getLongitude());
                GlobalVariable.getInstance().getConnectedUtilisateur().getMaPosition().setLatitude(location.getLatitude());

                if(GlobalVariable.getInstance().getZoneActuelle() == null || GlobalVariable.getInstance().getConnectedUtilisateur().getMaPosition().getDistanceWithOtherPosition(GlobalVariable.getInstance().getZoneActuelle().getPositionCentre()) > GlobalVariable.getInstance().getZoneActuelle().getRayon()*1000){
                    // Pas de zone ou en dehors de la zone actuelle
                // Verifier Si il y a changement de zone => getPointApparition / à la zone.
                    for(Zone laZone : GlobalVariable.getInstance().getListeDesZones()){
                        if(GlobalVariable.getInstance().getConnectedUtilisateur().getMaPosition().getDistanceWithOtherPosition(laZone.getPositionCentre()) <= laZone.getRayon()*1000){
                            // Dans la zone
                            GlobalVariable.getInstance().setZoneActuelle(laZone);

                            Object[] params = new Object[2];
                            params[0] = laZone.getId();
                            params[1] = context;

                            // TODO: Changer les affichages des points sur la cartes /!\ vérifier par rappord aux puzzles accomplis !
                            new MysqlDatabase("getPuzzleWithZone").execute(params);

                            break;
                        }
                    }
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent monIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                monIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(monIntent);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Si pas les permissions
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, listener);

        // TODO: Verifier dans quelle zone se trouve l'utilisateur => getPointApparition / à la zone.
        // TODO: Charger les points sur la cartes

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            locationManager.removeUpdates(listener);
        }
    }

    public static CarteOSM getContext() {
        return context;
    }

    public static void setContext(CarteOSM context) {
        GPSLocalisationService.context = context;
    }
}
