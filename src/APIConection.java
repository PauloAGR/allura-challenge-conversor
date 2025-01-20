import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIConection {


    public void conectToAPI(String KEY, String fromCurrency, String toCurrency){
        // Setting URL
        String url_str = "https://v6.exchangerate-api.com/v6/"+KEY+"/latest/" + fromCurrency;

        // Making Request
        URL url;

        {
            try {
                url = new URL(url_str);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        HttpURLConnection request;

        {
            try {
                request = (HttpURLConnection) url.openConnection();
                request.connect();
                // Convert to JSON
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject jsonobj = root.getAsJsonObject();

                // Accessing object
                //String req_result = jsonobj.get("conversion_rates").getAsString();
                double rateValue = jsonobj.get("conversion_rates").getAsJsonObject().get(toCurrency).getAsDouble();
                System.out.println("La tasa de cambio de "+ fromCurrency +" es: " + toCurrency + "$ " + rateValue);
                Thread.sleep(2000);
            } catch (IOException e) {
                System.out.println("Ocurrio un error, valide la llave: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
