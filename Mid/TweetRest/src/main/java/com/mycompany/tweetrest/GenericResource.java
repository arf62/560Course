/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tweetrest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * REST Web Service
 *
 * @author prakash
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.tweetrest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@Context UriInfo info) {
        //TODO return proper representation object
        String idString = info.getQueryParameters().getFirst("id");
        Integer id = 285;
        if(idString !=null){
         id = Integer.parseInt(idString);
        }
        String longitude="";
        String latitude="";
        String response="";
        String number_of_tweets="";
        String timeZone_s="";
        String tweetType_s="";
        JSONObject returnData = new JSONObject();
        try {
            
            String command= "curl http://172.16.202.129:8983/solr/new_core/select?q=id%3A"+id+"&wt=json&indent=true";
            response = executeCommand(command);
            JSONObject responeObj = new JSONObject(response);
            JSONObject response2 = (JSONObject) responeObj.get("response");
            JSONArray docs= (JSONArray) response2.get("docs");
            if(docs.length()>0){
                JSONObject temp = (JSONObject) docs.get(0);
                number_of_tweets = (String) temp.get("numberOfTweet_s");
                timeZone_s = temp.getString("timeZone_s");
                tweetType_s = temp.getString("tweetType_s");
            }
            String locationData= executeCommand("curl http://maps.googleapis.com/maps/api/geocode/json?address="+timeZone_s+"&sensor=false");
                      
                JSONObject responseData = new JSONObject(response);
                JSONObject location = new JSONObject(locationData);
                JSONArray results = (JSONArray)location.get("results");
                JSONObject arry = (JSONObject)results.get(0);
                JSONObject geometry = (JSONObject)arry.get("geometry");
                JSONObject locationValues = (JSONObject)geometry.get("location");
                latitude= String.valueOf(locationValues.get("lat"));
                longitude=String.valueOf(locationValues.get("lng"));
            returnData.put("numberOfTweet_s", number_of_tweets);
            returnData.put("tweetType_s", tweetType_s);
            returnData.put("latitude", latitude);
            returnData.put("longitude", longitude);
        } catch (Exception ex) {
            System.out.println("error!!");
            ex.printStackTrace();
        }
        System.out.println(returnData.toString());
            return returnData.toString();  
    }
    
     private String executeCommand(String command) {
   	 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
                         String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}
        
        
    }

   

