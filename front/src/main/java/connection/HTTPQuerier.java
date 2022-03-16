package connection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HTTPQuerier {
    private final String PROTOCOL = "http";
    private String targetURL;
    public boolean isHaveError;
    public String errorMessage = "";
    private HttpURLConnection connection = null;


    public HTTPQuerier(String host, int port){
        this.targetURL = this.PROTOCOL + "://" + host + ":" + port + "/api/";
    }

    
    public String writeCommand(String path, String data) {
        clearLastError();

        try {
            //Create connection
            URL url = new URL(this.targetURL + path);
            this.connection = (HttpURLConnection) url.openConnection();
            this.connection.setRequestMethod("POST");
            this.connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            this.connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
            this.connection.setRequestProperty("Content-Language", "ru-RU");

            this.connection.setUseCaches(false);
            this.connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(this.connection.getOutputStream());
            wr.writeBytes("data=" + URLEncoder.encode(data, "UTF-8"));
            wr.close();
            System.out.println("data=" + URLEncoder.encode(data, "UTF-8"));

            //Get Response  
            InputStream is = this.connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            generateError("Error передачу информации серверу", e.getMessage() );
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void generateError(String message, String javaMessage){
        isHaveError = true;
        errorMessage = message + ". Error: \"" + javaMessage +"\"";
    }

    private void clearLastError(){
        isHaveError = false;
        errorMessage = "";
    }

    public String closeSocket(String message){
        writeCommand("exit_" + message + "_server", "");
        return isHaveError ? errorMessage : "";
    }

}
