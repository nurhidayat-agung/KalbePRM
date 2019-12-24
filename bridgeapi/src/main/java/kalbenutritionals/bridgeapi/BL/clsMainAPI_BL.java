package kalbenutritionals.bridgeapi.BL;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Scanner;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class clsMainAPI_BL {

    public JSONObject callPushDataReturnJson(String link,String txtMethod, String txtTimeOut, String txtJson) {
        JSONObject _JSONObject = null;
        java.net.URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        String urlToRead = link + txtMethod;

        try {
            url = new java.net.URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(Integer.valueOf(txtTimeOut));
            conn.setRequestProperty("Accept", "*/*");
            String param = "txtParam=" + txtJson.toString();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setFixedLengthStreamingMode(param.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.close();
            String response = "";
            Scanner inStream = new Scanner(conn.getInputStream());
            while (inStream.hasNextLine()) {
                response += (inStream.nextLine());
            }
            conn.disconnect();
            result = response;
            _JSONObject = new JSONObject(result);
        } catch (IOException e) {
        } catch (Exception e) {
        }

        return _JSONObject;
    }

    public Boolean ValidJSON(JSONObject JsonRes){
        if(JsonRes == null){
            return false;
        }else{
            if(JsonRes.length()==0){
                return false;
            }else{
                if(JsonRes.toString().equals("{}")==false){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
}
