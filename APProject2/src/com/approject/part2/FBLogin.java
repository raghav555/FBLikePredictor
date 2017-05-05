package com.approject.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FBLogin {
	public static final String FB_APP_ID = "329654297204895";
	public static final String FB_APP_SECRET = "8ac1dc31b6ad86941187128fafd57d7f";
	public static final String REDIRECT_URI = "http://localhost:8080/Facebook_Login/fbhome";

	static String accessToken = "";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FBLogin.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBLogin.REDIRECT_URI, "UTF-8")
					+ "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FBLogin.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBLogin.REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = "CAACEdEose0cBAOVulwNJgTbaw6iHzuuaZAk2ron84cR4AT6B95vYZAQy9p1PDutDqjcajG5D910wy5cBioKRQeZBypA7ZCyDZAseM59p2hIHK86VM2xrcvzDFeocnW0T6ISBYOZAKeGXxFcib86cONW9jKgCyxZChQeGXJVxJjva1J2KAmJrEv88stVO967uIevZCNGEwXYBnCuJfnrxmhAy";
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}
}
