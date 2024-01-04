package interview;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class main {

	
	public static void main(String[] args) {
		HttpClient client = HttpClients.createDefault();
        
        int waitTime = 2000;
        Integer nextPage = 0;
        while(nextPage != null) {
        	System.out.println("beginning loop " + nextPage);
        	
	        try {
	        	System.out.println("big try");

	        	HttpResponse response = null;

	        	try {
	        		HttpGet httpGet = new HttpGet("https://wgcvq4480c.execute-api.us-west-2.amazonaws.com/dev?page="+nextPage);
	        		response = client.execute(httpGet);
		        	System.out.println("response is " + response);

	        	}catch (Exception e) {
	        		System.out.println("catch get");
	        		continue;
	        	}

	            // Check if the response status code is 200 (OK)
	            if (response.getStatusLine().getStatusCode() == 200) {
		        	System.out.println("200");

	                String jsonResponse = EntityUtils.toString(response.getEntity());
	                JSONObject jsonObject = new JSONObject(jsonResponse);
	                System.out.println("json object is " + jsonObject);
	                nextPage = jsonObject.getInt("nextPage");
	                
	                // Now you can work with the parsed JSON data in responseObj
	                System.out.println("next page: " + nextPage);
	                waitTime=2000;
	            } else {
	                System.err.println("lol HTTP GET request failed with status code: " + response.getStatusLine().getStatusCode());

	                System.out.println("sleeping for " + waitTime);
		        	Thread.sleep(waitTime);
		        	waitTime*=2;
	                System.out.println("nextSleep is " + waitTime);

	            }
	            
	        } catch (Exception e) {
	        	System.out.println("handling error");

	                System.out.println("sleeping for " + waitTime);
		                try {
			        	Thread.sleep(waitTime);
		                } catch (Exception exp) {
		                	System.out.println("error during sleeping" + exp);
		                }
		        	waitTime*=2;
	                System.out.println("nextSleep is " + waitTime);

                System.out.print("bad here, error is ");
                
	        	//e.printStackTrace();
	        }
	        System.out.println("end of loop");
        }
        
        System.out.println("done");

	}

}
