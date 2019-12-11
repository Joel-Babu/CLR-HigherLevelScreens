package au.wow.wlp.utils;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class SAPWebServiceClient {
	public static void main(String[] args) {
		File file = new File("C:\\New\\StoresCentralProject\\TestData\\CT\\Price.txt");
//		DefaultHttpClient 
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://ncdlctssct0001.wowcorp.com.au/api/v1/inbound/data");
		FileEntity entity = new FileEntity(file);
		try {
			System.out.println(EntityUtils.toString(entity));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		post.setEntity(entity);
		
////		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("WM_DEV", "Wd3R_poLi3");
		CredentialsProvider cp = new BasicCredentialsProvider();
		cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("WM_DEV", "Wd3R_poLi3"));
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(cp);
//		
//		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		post.addHeader("Authorization", "Basic V01fREVWOldkM1JfcG9MaTM=");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			CloseableHttpResponse response = httpclient.execute(post, context);
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity1 = response.getEntity();
			String output = EntityUtils.toString(entity1);
			System.out.println(output);
		} catch (IOException e) {
			System.out.println("Not working");
		}
		
		
		
		
	}
}
