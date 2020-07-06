package com.sc.alleserver.security;

import java.io.IOException;

import org.json.JSONException;

public class Token {
	
	private static Token instance;
	private String accessToken;
	
	private Token()
	{
		
	}

	public static Token getInstance()
	{
		if(instance == null)
		{
			return new Token();
		}
		else
		{
			return instance;
		}
	}
	
	public String getToken()
	{
		if(this.accessToken != null)
		{
			return this.accessToken;
		}
		else
		{
			try {
				return Authentication.getToken();
			} catch (IOException | JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
