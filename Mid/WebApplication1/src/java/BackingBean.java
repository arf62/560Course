
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pv6xc
 */
public class BackingBean {
    private String latitude;
    private String longitude;
    public void print(){
        System.out.println("asd");
    }
    
    public BackingBean(){
        //weeklyData();
    }
    
    public void weeklyData(){
        String response="";
        try {
            
            String command= "curl http://172.16.142.133:8983/solr/new_core/select?q=*%3A*&wt=json&indent=true";
            response = executeCommand(command);
            JSONObject responeObj = new JSONObject(response);
            JSONObject response2 = (JSONObject) responeObj.get("response");
            JSONArray docs= (JSONArray) response2.get("docs");
            for(int i=0;i<docs.length();i++){
                JSONObject temp = (JSONObject) docs.get(i);
                System.out.println(temp.get("id")+"\t" + temp.get("numberOfTweet_s"));
            }
            
            
            //System.out.println(response);
            
            String locationData= executeCommand("curl http://maps.googleapis.com/maps/api/geocode/json?address=Missouri&sensor=false");
            //System.out.println(locationData);
            
                JSONObject responseData = new JSONObject(response);
                JSONObject location = new JSONObject(locationData);
                JSONArray results = (JSONArray)location.get("results");
                JSONObject arry = (JSONObject)results.get(0);
                JSONObject geometry = (JSONObject)arry.get("geometry");
                JSONObject locationValues = (JSONObject)geometry.get("location");
                String longitude="";
                String latitude="";
                latitude= String.valueOf(locationValues.get("lat"));
                longitude=String.valueOf(locationValues.get("lng"));
                this.latitude=latitude;
                this.longitude=longitude;
                
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(BackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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

    public String getLatitude() {
        return latitude;
    }

   

    public String getLongitude() {
        return longitude;
    }

   
     
}
