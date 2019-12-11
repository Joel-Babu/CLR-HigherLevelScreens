/**
 * This class represents the webservice client.
 * It has its inputs in xml and executes a post request in webservice
 *  
 *  
 *  To post an xml, call the below method
 *  HttpWebClient webclient = new HttpWebClient(status, data, getContext());
 *  webclient.pushBatch(RequestXML, appname);
 */

package au.wow.wlp.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpWebClient {

	String request;
	CloseableHttpClient httpclient;
	HttpPost post;
	CredentialsProvider cp;
	HttpClientContext context;
	private TestStatus status;
	private XMLDataReader testdata;
	private TestContext tstcontext;
	
	public HttpWebClient(TestStatus status, XMLDataReader testdata, TestContext tstcontext){
		this.status = status;
		this.testdata = testdata;
		this.tstcontext = tstcontext;
	}
	
	/**
	 * Reads the input from a xml file
	 * @param filename
	 */
	private void readFromFile(String filename){
		File file = new File(filename);
		try {
			request = FileUtils.readFileToString(file);
			System.out.println("Request before update: " + request);
		} catch (IOException e) {
			status.fail("Error in reading the request file");
		}
	}
	
	/**
	 * Updates the request xml
	 * @param filename
	 * @param appname
	 */
	private void modifyRequest(String filename, String appname){
		readFromFile(filename);
		if(request!=null){
			switch (appname) {
			case "pricing":
				request = updatePricingEntites();
				break;

			default:
				break;
			}
		}else
			status.fail(appname+" request is null");
		
	}
	
	/**
	 * Updates the entities of Request XML
	 * @return updatedRequest
	 */
	private String updatePricingEntites(){
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
//		System.out.println("Date format of date: " + date);
		return request.replace("CREATIONDATE", date)
		.replace("BUSINESSUNIT", "9395")
		.replace("PRODUCT", testdata.getText("Product"))
		.replace("EFFECTIVEDATE", date)
		.replace("CATALOGPRICE", testdata.getText("Catalogprice"));
	}

	/**
	 * Creates a post request along with credentials
	 * @param webserviceurl
	 * @param username
	 * @param password
	 */
	private void createHttpPostRequest(String webserviceurl, String username, String password){
		try{
			httpclient = HttpClients.createDefault();
			post = new HttpPost(webserviceurl);
			cp = new BasicCredentialsProvider();
			cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
			context = HttpClientContext.create();
			context.setCredentialsProvider(cp);
			post.addHeader("Authorization", "Basic V01fREVWOldkM1JfcG9MaTM=");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		}catch(Exception e){
			status.fail("Error in creating the post request");
		}
		
		
	}
	
	/**
	 * Creates a string entity of the modified request
	 */
	private void setEntity(){
		StringEntity str=null;
		try {
			str = new StringEntity(request);
		} catch (UnsupportedEncodingException e) {
			status.fail("Error in creating the string entity: " + str);
		}
		post.setEntity(str);
		
	}
	
	/**
	 * Executes a post request in webservice
	 */
	private void executeHttpPostRequest(){
		try {
			CloseableHttpResponse response = httpclient.execute(post, context);
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity1 = response.getEntity();
			String output = EntityUtils.toString(entity1);
			System.out.println(output);
		} catch (IOException e) {
			status.fail("Error in executing the post request");
		}
	}
	
	/**
	 * Reads the request xml, updates the entities, creates execute a post request in webservice
	 * @param filename
	 * @param appname
	 */
	public void pushBatch(String filename, String appname){
		
		modifyRequest(System.getProperty("user.dir")+"\\TestData\\CT\\"+filename+".txt", appname);
		createHttpPostRequest(tstcontext.getStringProperty("webserviceurl"), tstcontext.getStringProperty("wbsrvcusername"), tstcontext.getStringProperty("wbsrvcpassword"));
		setEntity();
		executeHttpPostRequest();
	}

		
	
}
