package me.kaimu.hastebin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class Hastebin {

	public String post(String text, boolean raw) throws IOException {
		byte[] postData = text.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		String requestURL = "https://www.toptal.com/developers/hastebin/documents";
		URL url = new URL(requestURL);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Hastebin Java Api");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);

		String response = null;
		DataOutputStream wr;
		try {
			wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			response = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (response.contains("\"key\"")) {
			response = response.substring(response.indexOf(":") + 2, response.length() - 2);
		
			String postURL = raw ? "https://www.toptal.com/developers/hastebin/raw/" : "https://www.toptal.com/developers/hastebin/";
			response = postURL + response;
		}
		
		return response;
	}

}
