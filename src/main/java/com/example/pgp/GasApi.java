package com.example.pgp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class GasApi {
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
