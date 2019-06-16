package ccm.cours.nicolas.tiniki.Database;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ccm.cours.nicolas.tiniki.Activity.CarteOSM;
import ccm.cours.nicolas.tiniki.Entity.PointApparition;
import ccm.cours.nicolas.tiniki.Entity.Zone;
import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;
import ccm.cours.nicolas.tiniki.Tools.FabriquePuzzle;
import ccm.cours.nicolas.tiniki.Tools.GlobalVariable;
import io.grpc.internal.JsonParser;

public class MysqlDatabase extends AsyncTask {
    /* pour appeler cette classe
    * new MysqlDatabase(typeRequete).execute(Parans...);
    * où typeRequete est le nom donnée à la requete à faire. Il détermine la page php a appeler
    * Params... et la liste des parametres à envoyer à la requete mysql
    * */

    private String typeRequete;
    public MysqlDatabase(String typeRequete) {
        this.typeRequete = typeRequete;
    }

    protected String doInBackground(Object[] data) {
        String url;
        InputStream is;

        switch(typeRequete){
            case "getAllZone":
                url = "http://51.75.23.222/app.php/getAllZone/";

                is = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    is = conn.getInputStream();
                    // Read the InputStream and save it in a string
                    String result =  BoiteAOutils.readIt(is);
                    Log.v("LPK_JSON_all", result);

                    Type listType = new TypeToken<ArrayList<Zone>>(){}.getType();
                    ArrayList<Zone> zones = new Gson().fromJson(result, listType);

                    Log.i("LPK_JSON_1", "Size : " + zones.size());

                    for (Zone laZone : zones){
                        Log.i("LPK_JSON_ZONE", "id " + laZone.getId());
                        Log.i("LPK_JSON_ZONE", laZone.getLibelle());
                        Log.i("LPK_JSON_ZONE", "rayon " + laZone.getRayon());
                        Log.i("LPK_JSON_ZONE", "lat " + laZone.getPositionCentre().getLatitude());
                        Log.i("LPK_JSON_ZONE", "lon " + laZone.getPositionCentre().getLongitude());
                    }
                    GlobalVariable.getInstance().setListeDesZones(zones);

                } catch (IOException e) {
                    Log.v("titi", "non co");
                    e.printStackTrace();
                }
                break;

            case "getPuzzleWithZone":
                url = "http://vps.titi.space/app.php/getPuzzleWithZone/"+data[0];

                is = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    is = conn.getInputStream();
                    // Read the InputStream and save it in a string
                    String result =  BoiteAOutils.readIt(is);
                    Log.v("titi", result);

                    CarteOSM carte = (CarteOSM) data[1];
                    ArrayList<PointApparition> pointApps = new ArrayList<PointApparition>();
                   // TODO //////////////////////
                    JSONArray json = new JSONArray(result);

                    for(int i=0;i<json.length();i++){
                        JSONObject json_data = json.getJSONObject(i);
                        PointApparition lepointApp = new PointApparition();
                        lepointApp.setId(json_data.getInt("id"));
                        lepointApp.setNom(json_data.getString("nom"));
                        lepointApp.setDescription(json_data.getString("description"));

                        JSONObject jsonPos = json_data.getJSONObject("position");
                        lepointApp.getPosition().setLatitude(jsonPos.getDouble("latitude"));
                        lepointApp.getPosition().setLongitude(jsonPos.getDouble("longitude"));

                        JSONObject jsonPuzzle = json_data.getJSONObject("puzzleDuJour");
                        lepointApp.setPuzzleDuJour(FabriquePuzzle.fabriquePuzzle(jsonPuzzle.getString("type")));
                        lepointApp.getPuzzleDuJour().setType(jsonPuzzle.getString("type"));
                        lepointApp.getPuzzleDuJour().setDifficulte(jsonPuzzle.getString("difficulte"));
                        lepointApp.getPuzzleDuJour().setId(jsonPuzzle.getInt("id"));
                        lepointApp.getPuzzleDuJour().setNom(jsonPuzzle.getString("nom"));
                        lepointApp.getPuzzleDuJour().setEnonce(jsonPuzzle.getString("enonce"));
                        lepointApp.getPuzzleDuJour().setSolution(jsonPuzzle.getString("solution"));
                        lepointApp.getPuzzleDuJour().setContenu(jsonPuzzle.getString("contenu"));
                        lepointApp.getPuzzleDuJour().setIndices(jsonPuzzle.getString("indice"));
                        lepointApp.getPuzzleDuJour().setExp(jsonPuzzle.getInt("exp"));
                        lepointApp.getPuzzleDuJour().setPiece(jsonPuzzle.getInt("piece"));

                        Log.i("LPK_JSON_ARRAY","id : "+lepointApp.getPuzzleDuJour().getId());
                        Log.i("LPK_JSON_ARRAY","nom : "+lepointApp.getPuzzleDuJour().getNom());
                        Log.i("LPK_JSON_ARRAY","enonce : "+lepointApp.getPuzzleDuJour().getEnonce());
                        Log.i("LPK_JSON_ARRAY","type : "+lepointApp.getPuzzleDuJour().getType());
                        Log.i("LPK_JSON_ARRAY","dif : "+lepointApp.getPuzzleDuJour().getDifficulte());
                        Log.i("LPK_JSON_ARRAY","sol : "+lepointApp.getPuzzleDuJour().getSolution());
                        Log.i("LPK_JSON_ARRAY","con : "+lepointApp.getPuzzleDuJour().getContenu());
                        Log.i("LPK_JSON_ARRAY","indice : "+lepointApp.getPuzzleDuJour().getIndices());
                        Log.i("LPK_JSON_ARRAY","exp : "+lepointApp.getPuzzleDuJour().getExp());
                        Log.i("LPK_JSON_ARRAY","piece : "+lepointApp.getPuzzleDuJour().getPiece());

                        pointApps.add(lepointApp);
                    }

                    GlobalVariable.getInstance().setPointsApparitionDansZone(pointApps);
                    // TODO //////////////////////
                    // TODO : MAJ carte.

                } catch (IOException e) {
                    Log.v("titi", "non co");
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }

    protected void onPostExecute(String result){
        Log.v("titi", result);
    }

}
