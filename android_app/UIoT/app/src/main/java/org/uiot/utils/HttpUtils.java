/**
 * Created by Alex Alexandre <alex.alexandre@uiot.org>
 * on 05/10/16.
 */

package org.uiot.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


/**
 *  This class is responsible for creating a new REST request
 */
public class HttpUtils {

    public static String makeRequest(String address, String methodRequest, HashMap<String, String> parameters) throws IOException {
        String dataReturn = "";

        URL url = new URL(address);
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(methodRequest);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(manipulationParameters(parameters));

            writer.flush();
            writer.close();
            outputStream.close();


            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    dataReturn+=line;
                }
            }
            else {
                dataReturn="";

            }

        }catch (MalformedURLException urlEx){
            urlEx.printStackTrace();
        }

        return  dataReturn;
    }

    /**
     *  This method is responsible for manipulate the parameter for send in the request body
     */
    private static String manipulationParameters(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first) {
                first = false;
            }else {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}