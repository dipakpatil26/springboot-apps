package com.filter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * This is a utility class that provides methods used for Http connection usage.
 * 
 * @author Dipak Patil
 */
public class HttpConnectionHelper {
	
	/**
	 * This function creates a <code>HttpURLConnection</code> for a given url.
	 * @param url URL string to make HttpConnection.
	 * @returns <code>HttpURLConnection</code>
	 * @throws IOException 
	 * @throws ProtocolException
	 */
	public static HttpURLConnection createHttpConnection(URL url) throws IOException, ProtocolException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		return con;
	}
	
	/**
	 * This function is used to invoke and read responze to provided <code>HttpURLConnection</code>
	 * @param con <code>HttpURLConnection</code> object
	 * @return Http request response. 
	 * @throws IOException
	 */
	public static StringBuffer invokeAndReadHttpResponse(HttpURLConnection con) throws IOException {
		StringBuffer content;
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		return content;
	}
	
	/**
	 * Close the connection object <code>HttpURLConnection</code>.
	 * @param con
	 */
	public static void closeHttpConnection(HttpURLConnection con) {
		con.disconnect();
	}
}
