package ccm.cours.nicolas.tiniki.Database;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ccm.cours.nicolas.tiniki.Tools.BoiteAOutils;

public class MysqlDatabase extends AsyncTask {
    /* pour appeler cette classe
    * new MysqlDatabase(typeRequete).execute(Parans...);
    * où typeRequete est le nom donnée à la requete à faire. Il détermine la page php a appeler
    * Params... et la liste des parametres à envoyer à la requete mysql
    * */

    private String typeRequete;
    public MysqlDatabase(String typeRequete) {

    }

    protected void onPreExecute(){
        this.typeRequete = typeRequete;
    }

    protected String doInBackground(Object[] objects) {
        Log.v("titi", "test co");
        String url = "http://51.75.23.222:8585/index.php";
        if (typeRequete == "loginUtilisateur") {
            url = "http://51.75.23.222:8585/index.php";
        }

        InputStream is = null;
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
            return result;

        } catch (IOException e) {
            Log.v("titi", "non co");
            e.printStackTrace();
        } /*finally {
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.v("titi", "test");
                    e.printStackTrace();
                }
            }
        }*/


        return "non";
    }

    protected void onPostExecute(String result){
        Log.v("titi", result);
    }
}
