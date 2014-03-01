package sample.ble.sensortag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Final_Page extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_final__page);
		 String finalScore =   getIntent().getStringExtra("score");
		 Button reportButton = (Button) findViewById(R.id.button1);
		 final TextView textView = (TextView)findViewById(R.id.textView1);
		 textView.setText("Your final score is : "+finalScore);
		 reportButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					String url = "http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=id%3Astomp&wt=json&indent=true";
					try{
					String resultString = getJsonFromUrl(url);
					System.out.println(resultString);
					String stomp_count= JsonToValue(resultString,"stomp");
					String rest_count = JsonToValue(resultString,"rest");
					textView.setText("Stomp :" + stomp_count);
					textView.append("Rest  :" + rest_count);
					}
					catch(Exception e)
					{
						textView.setText("Found exception while calling webservice to get report");
					}
				}
				});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.final__page, menu);
		return true;
	}
	public 	String getJsonFromUrl(String url) throws Exception
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			HttpGet httpPost = new HttpGet(url);
			response = httpclient.execute(httpPost);
			Log.i("httpresponse", response.toString());
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else{
				//Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
			throw new Exception();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println("rsponse::"+responseString);
		return responseString;
	}
	
public String JsonToValue(String response,String action) throws Exception
	{
		String count="";
	try {
		JSONObject jsonObj = new JSONObject	(response);
		JSONObject res_header=jsonObj.getJSONObject("responseHeader");
		Integer status = res_header.getInt("status");
		if (status == 0){
			JSONObject res_body=jsonObj.getJSONObject("response");
			JSONArray Results = res_body.getJSONArray("docs");
			if ((Results.getJSONObject(0).getString("id")).equalsIgnoreCase(action))
				count = Results.getJSONObject(0).getJSONArray("title").getString(0);
			else if ((Results.getJSONObject(1).getString("id")).equalsIgnoreCase(action))
				count = Results.getJSONObject(1).getJSONArray("title").getString(0);
		}

	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new Exception();
		
	}
	return count;
	}

}
