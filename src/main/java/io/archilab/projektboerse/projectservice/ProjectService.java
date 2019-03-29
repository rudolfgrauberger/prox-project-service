package io.archilab.projektboerse.projectservice;

import io.archilab.projektboerse.projectservice.module.ModuleService;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.List; 
  


@SpringBootApplication
@EnableFeignClients
public class ProjectService {

  public static void main(String[] args) {
	  
	  
	
	  
	  
	  
	  
    ConfigurableApplicationContext context = SpringApplication.run(ProjectService.class, args);

    context.getBean(ModuleService.class).importModules();
    
    
//	  CloseableHttpClient client = HttpClients.createDefault();
//	    HttpPost httpPost = new HttpPost("https://login.coalbase.io/auth/realms/prox/protocol/openid-connect/token");
//
//	    List<NameValuePair> params = new ArrayList<NameValuePair>();
//	    params.add(new BasicNameValuePair("client_id", "ptb-web-client"));
//	    params.add(new BasicNameValuePair("username", "Dozent"));
//
//	    params.add(new BasicNameValuePair("password", "dozent"));
//
//	    params.add(new BasicNameValuePair("grant_type", "password"));
//	   
//	    String result = null;
//	    try {
//			httpPost.setEntity(new UrlEncodedFormEntity(params));
//			 CloseableHttpResponse response = client.execute(httpPost);
//			 
//			 
//			 HttpEntity entity = response.getEntity();
//
//		        if (entity != null) {
//
//		            // A Simple JSON Response Read
//		            InputStream instream = entity.getContent();
//		            result = convertStreamToString(instream);
//		            // now you have the string representation of the HTML request
//		            System.out.println("RESPONSE: " + result);
//		            instream.close();
//		        }
//		        // Headers
//		        org.apache.http.Header[] headers = response.getAllHeaders();
//		        for (int i = 0; i < headers.length; i++) {
//		            System.out.println(headers[i]);
//		        }
//
//			 System.out.println("start test");
//			 System.out.println(response.getAllHeaders().toString());
//
//			 System.out.println(response.toString());
//
//			 System.out.println(response.getEntity().toString());
//			 client.close();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    
//    String ipAddress = "login.coalbase.io"; 
//    try {
//		sendPingRequest(ipAddress);
//	} catch (UnknownHostException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
  }

  
  public static void sendPingRequest(String ipAddress) 
          throws UnknownHostException, IOException 
{ 
InetAddress geek = InetAddress.getByName(ipAddress); 
System.out.println("Sending Ping Request to " + ipAddress); 
if (geek.isReachable(5000)) 
  System.out.println("Host is reachable"); 
else
  System.out.println("Sorry ! We can't reach to this host"); 
} 
  
  private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
  
}




