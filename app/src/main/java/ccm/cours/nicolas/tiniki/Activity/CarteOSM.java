package ccm.cours.nicolas.tiniki.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import ccm.cours.nicolas.tiniki.R;

import org.mapsforge.map.layer.download.tilesource.TileSource;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class CarteOSM extends AppCompatActivity implements LocationListener {

    MapView map = null;
    LocationManager locationManager;
    private double latitude;
    private double longitude;
    private double altitude;
    private String idmarquer;
    private static final int id_demande_permission = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_osm);

        verifperm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifperm();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
    // permission
    private void verifperm(){
        if (
                !(
                        ActivityCompat.checkSelfPermission(
                                this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                &&
                                ActivityCompat.checkSelfPermission(
                                        this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                &&
                                ActivityCompat.checkSelfPermission(
                                        this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ))
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,id_demande_permission );
            return;
        }

        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000,0, this);
        mapOSM();
    }

    private  void mapOSM(){

        // Affichage map
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
//        map.setTileSource(TileSource.);
        IMapController mapController = map.getController();
        mapController.setZoom(6.0);
        map.setMultiTouchControls(true); // tactile pour zoom +/-


    }

    // implementation of Location listener
    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        altitude = location.getAltitude();
        Toast.makeText(this, "Latitude : " +latitude+" - Longitude : "+longitude, Toast.LENGTH_SHORT).show();
        IMapController mapController = map.getController();
        position(mapController);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void position(IMapController mapController){
        // position
        GeoPoint positionActuelle = new GeoPoint(latitude, longitude, altitude);
        //Toast.makeText(this, "Latitude : " +latitude+" - Longitude : "+longitude, Toast.LENGTH_SHORT).show();
        mapController.setCenter(positionActuelle);
        map.getOverlayManager().clear();

        // marker
        Marker marquer = new Marker(map);
        marquer.setPosition(positionActuelle);
        marquer.setIcon(getResources().getDrawable(R.drawable.direction_arrow));
        //marquer.setAnchor(Marker.ANCHOR_TOP,Marker.ANCHOR_TOP);
        marquer.setId("46568");
        idmarquer = marquer.getId();
        map.getOverlays().add(marquer);
//        Toast.makeText(this, "Latitude : " + idmarquer , Toast.LENGTH_SHORT).show();

        // test
        GeoPoint posTEST = new GeoPoint(0, 0, 0);
        final Marker marquerTEST = new Marker(map);
        marquerTEST.setPosition(posTEST);
        //marquer.setIcon(getResources().getDrawable(R.drawable.direction_arrow));
        marquerTEST.setAnchor(Marker.ANCHOR_TOP,Marker.ANCHOR_TOP);
        marquerTEST.setId("Marquer test ");
        marquerTEST.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(mapView.getContext(), "Id event : " + marker.getId() , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        map.getOverlays().add(marquerTEST);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

}