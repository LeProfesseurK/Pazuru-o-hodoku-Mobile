package ccm.cours.nicolas.tiniki.Tools;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FichierOutils {
    private static String NAMEFILE = "pazuruohodoku.txt";

    public static void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(NAMEFILE, Context.MODE_APPEND));
            outputStreamWriter.append(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(Context context) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(NAMEFILE);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                int compt = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                    Log.i("LPK_File_Contenu", receiveString);
                    compt++;
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

    public static void verifDateJourFile(Context context){
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(NAMEFILE);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                receiveString = bufferedReader.readLine();
                //Log.i("LPK_FILE", "receiveString : " + receiveString);
                if (receiveString != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
                SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDate.format(new Date());
                if(ret.equals(date)){
                    // Même date
                    Log.i("LPK_FILE", "Même date");
                }else{
                    // Nouveau jour
                    changeDateFile(context);
                    Log.i("LPK_FILE", "New date");
                }
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    private static void changeDateFile(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(NAMEFILE, Context.MODE_PRIVATE));
            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
            String date = simpleDate.format(new Date());
            outputStreamWriter.write(date);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static int ajoutePuzzleReussit(String idPuzzle, Context context){
        int nbEchec = 0;
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(NAMEFILE);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                int compt = 0;
                boolean findLine = false;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    if(receiveString.startsWith(idPuzzle+";")){
                        String[] splitted = receiveString.split(";");
                        nbEchec = Integer.parseInt(splitted[2]);
                        splitted[1] = "1";
                        receiveString = splitted[0] + ";" + splitted[1] + ";" + splitted[2] + ";";
                        findLine = true;
                    }
                    stringBuilder.append(System.lineSeparator()+receiveString);
                    compt++;

                }
                inputStream.close();
                if (!findLine){
                    stringBuilder.append(System.lineSeparator()+idPuzzle+";1;0;");
                }
                ret = stringBuilder.toString();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(NAMEFILE, Context.MODE_PRIVATE));
                outputStreamWriter.write(ret);
                outputStreamWriter.close();

            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return nbEchec;
    }

    public static void ajoutePuzzleEchec(String idPuzzle, Context context){
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(NAMEFILE);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                int compt = 0;
                boolean findLine = false;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    if(receiveString.startsWith(idPuzzle+";")){
                        String[] splitted = receiveString.split(";");
                        splitted[2] = String.valueOf(Integer.parseInt(splitted[2]) + 1);
                        receiveString = splitted[0] + ";" + splitted[1] + ";" + splitted[2] + ";";
                        findLine = true;
                    }
                    stringBuilder.append(System.lineSeparator()+receiveString);
                    compt++;

                }
                inputStream.close();
                if (!findLine){
                    stringBuilder.append(System.lineSeparator()+idPuzzle+";0;1;");
                }
                ret = stringBuilder.toString();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(NAMEFILE, Context.MODE_PRIVATE));
                outputStreamWriter.write(ret);
                outputStreamWriter.close();

            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }
}
