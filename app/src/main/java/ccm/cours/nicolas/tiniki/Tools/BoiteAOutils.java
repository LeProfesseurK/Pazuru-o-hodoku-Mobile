package ccm.cours.nicolas.tiniki.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BoiteAOutils {
    public static String crypteMotDePasse(String mdp){
        return mdp;
    }


    public static String readIt(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            response.append(line).append('\n');
        }
        return response.toString();
    }

    public static ArrayList<String> AleatoireElementsListe(List<String> listeChoix) {
        return (ArrayList<String>) listeChoix;
    }
}