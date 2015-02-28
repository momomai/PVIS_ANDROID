package com.psu.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpHelper {
	public static String GetString(String url,final ArrayList<NameValuePair> data){
		String result = "";
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 4000);
		HttpClient client = new DefaultHttpClient(params);
		Log.i("URL",url);
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		try {
			if(data != null)post.setEntity(new UrlEncodedFormEntity(data,HTTP.UTF_8));
			response = client.execute(post);
			result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
			Log.i("LENGTH", result.length()+"");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="0";
			Log.i("HttpHelperError",e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="0";
			Log.i("HttpHelperError",e.getMessage());
		}
		Log.i("RESULT", result);
		return result;
	}
	public static void DownloadFile(String fileURL, File directory) {
		try {
			
			FileOutputStream f = new FileOutputStream(directory);
			URL u = new URL(fileURL);
			HttpURLConnection c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();

			InputStream in = c.getInputStream();

			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = in.read(buffer)) > 0) {
				f.write(buffer, 0, len1);
			}
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
