package com.example.amipal_2;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import com.example.amipal_2.R.drawable;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Timed_Images extends Activity {
	Field field  ;
	private Class<drawable> resource = R.drawable.class;
	public static Boolean press = false;
	public static Long arcadeTotalTime = System.currentTimeMillis();
	public static Long startTime = System.currentTimeMillis();
	public static Integer score = 0;
	GameLogic gameLogic =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timed__images);
		 gameLogic = new GameLogic();
		startThread();
		//showImageAndStartTime();
	}

	private void startThread() {
		// TODO Auto-generated method stub
		final Timer t = new Timer();
	    t.scheduleAtFixedRate(new TimerTask() {
	    	
	    	
	        @Override
	        public void run() {
	        	Long currentTime = System.currentTimeMillis();
	            // Download your stuff
	        	Log.i("current time", Long.toString(currentTime-startTime));
	        	if (currentTime-arcadeTotalTime < 60000) {
	        		if(currentTime-startTime > 4000){
	        		
	        		Log.i("break", "break");
	        		final TextView text = (TextView) findViewById(R.id.textView1);
	        		final TextView scoreText = (TextView)findViewById(R.id.textView2);
	        		final Button b = (Button)findViewById(R.id.button1);
	        		final ImageView image = (ImageView)findViewById(R.id.imageView1);
	        		final TextView scores = (TextView)findViewById(R.id.textView2);
	        		if(text.toString().equalsIgnoreCase("apple"))
	        		{
	        			++score;
	        			
	        		}
	        		else{
	        			--score;
	        		}
	        		scores.post(new Runnable(){
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							scores.setText(Integer.toString(score));
						}
					});
	        		
	        		text.post(new Runnable() {
	        		    public void run() {
	        		        
	        		        String imageName = gameLogic.getRandomImage();
	        				try {
								field = resource.getField(imageName);
								startTime = System.currentTimeMillis();
								ImageView image = (ImageView)findViewById(R.id.imageView1);
								image.setImageResource(field.getInt(field));
								text.setText(imageName);
							} catch (NoSuchFieldException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        		        
	        		       } 
	        		});
	        		
	        		
					
	        		}
				}else{
					final TextView text = (TextView) findViewById(R.id.textView1);
	        		final TextView scoreText = (TextView)findViewById(R.id.textView2);
	        		final Button b = (Button)findViewById(R.id.button1);
	        		final ImageView image = (ImageView)findViewById(R.id.imageView1);
	        		text.post(new Runnable() {
	        		    public void run() {
	        		        text.setText("Your time is up !!! Your final score " + score);
	        		        text.setVisibility(View.VISIBLE);
	        		        scoreText.setVisibility(View.GONE);
	        		        b.setVisibility(View.GONE);
	        		        image.setVisibility(View.GONE);
	        		    } 
	        		});
	        		t.cancel();
				}
	        	Log.i("Timer", Long.toString(SystemClock.currentThreadTimeMillis()));
	        }

	    }, 0, 1000);
	}

	public void showImageAndStartTime(View view) {
		reset();        
		
	
	}
	private void reset() {
		try {
			MediaPlayer mp1 = MediaPlayer.create(getBaseContext(), R.raw.kill);;   
	       
			String imageName = gameLogic.getRandomImage();
			field = resource.getField(imageName);
			TextView text = (TextView)findViewById(R.id.textView1);
			if(text.getText().toString().equalsIgnoreCase("apple")){
				AssetFileDescriptor afd = getAssets().openFd("save.mp3");
				mp1.stop();
				mp1.reset();
				mp1.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
				afd.close();
				mp1.prepare();
				mp1.start();
				--score;
				
			}else{
				++score;
			}
			Log.i("field resource", field.toString());
			startTime = System.currentTimeMillis();
			ImageView image = (ImageView)findViewById(R.id.imageView1);
			image.setImageResource(field.getInt(field));
			text.setText(imageName);
			
			mp1.start();
			TextView scoreText = (TextView)findViewById(R.id.textView2);
			scoreText.setText(Integer.toString(score));
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timed__images, menu);
		return true;
	}

}
