package com.sc.alleserver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.alleserver.security.Token;

@RestController
public class QueryController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "Hello world";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String getMainCategories() throws IOException, JSONException {
		String destUrl = "https://api.allegro.pl.allegrosandbox.pl/sale/categories";
		
		URL url = new URL(destUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "Bearer " + Token.getInstance().getToken());
		con.setRequestProperty("Accept", "application/vnd.allegro.public.v1+json");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		
		con.disconnect();

		
		return content.toString();
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String getCategory(@RequestParam String name) throws IOException, JSONException {
		String destUrl = "https://api.allegro.pl.allegrosandbox.pl/sale/categories";
		String query = "parent.id=" + name;
		URL url = new URL(destUrl + "?" + query);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "Bearer " + Token.getInstance().getToken());
		con.setRequestProperty("Accept", "application/vnd.allegro.public.v1+json");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		
		con.disconnect();

		
		return content.toString();
	}

}
