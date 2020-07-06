package com.sc.alleserver.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

public class Authentication {

	public static String getToken() throws IOException, JSONException {
		String authUrl = "https://allegro.pl.allegrosandbox.pl/auth/oauth/token?grant_type=client_credentials";
		String clientId = "12347993ab074528baa9c297ac209a4d";
		String clientSecret = "8Lhj2lrTyCANktd5clNexPhd1sXaKJ9Nlih9LqnEBNq9pF6HWVjoFK5B6aOnrlD8";

		
		//Standard Java way
		URL url = new URL(authUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("username", clientId);
		con.setRequestProperty("password", clientSecret);
		con.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		
		con.disconnect();
		
		JSONObject json = new JSONObject(content.toString());
    	return json.getString("access_token");

	}
}
