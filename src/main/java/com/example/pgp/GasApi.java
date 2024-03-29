package com.example.pgp;
/*
 * Updated 4/13/23
 *
 * Class accesses remote API.
 * William Vaughan
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class GasApi {

    /**
     * Method sends a POST request to the API address, with the latitude and longitude values included in the
     * JSON payload. The method then extracts the "Price" field from the JSON response as a double, and returns it.
     *
     * @param lat Latitude of gas price
     * @param lng Longitude of gas price
     * @return gas price as a double
     * @throws IOException
     */
    public static double getGasPrice(double lat, double lng) throws IOException {
        String url = "https://www.gasbuddy.com/gaspricemap/county";
        String payload = String.format("{\"lat\": %.4f, \"lng\": %.4f, \"usa\": true}", lat, lng);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner in = new Scanner(con.getInputStream());
            String response = in.useDelimiter("\\A").next();
            in.close();

            // Parse the JSON response and extract the "Price" field as a double
            return Double.parseDouble(response.split("\"Price\":")[1].split(",")[0]);
        } else {
            throw new IOException("Failed to retrieve gas prices from GasBuddy API");
        }
    }
}
