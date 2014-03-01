package sample.ble.sensortag.demo;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sample.ble.motion.DetectMotion;
import sample.ble.sensortag.Final_Page;
import sample.ble.sensortag.R;
import sample.ble.sensortag.R.drawable;
import sample.ble.sensortag.sensor.TiAccelerometerSensor;
import sample.ble.sensortag.sensor.TiSensor;


import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Timed_Images extends DemoSensorActivity {
	Field field  ;
	Float  isStomp = -2.0f;
	Float  isKick = -2.0f;
	DetectMotion motion = null;
	private Class<drawable> resource = R.drawable.class;
	public static Boolean press = false;
	public static Long arcadeTotalTime = System.currentTimeMillis();
	public static Float score = 0.0f;
	GameLogic gameLogic =null;
	String imageName ;
	Boolean match = false;
	final long startTime = System.currentTimeMillis();
	ProgressBar pb;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timed__images);
		motion = new DetectMotion();
		gameLogic = new GameLogic();
		//startThread();
		changeImage();
		//changeProgressBar();
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
	        		//changing the time for next image randomly with 1sec minimum
	        		if(currentTime-startTime > (new Random().nextInt(4)+1)){
	        		changeImage();
	        		final TextView scores = (TextView)findViewById(R.id.textView1);
	        			score--;
	        			scores.post(new Runnable(){
						@Override
						public void run() {
							// TODO Auto-generated method stub
							scores.setText(Float.toString(score));
						}
					});
	        		}
				}else{
					final TextView scoreText = (TextView) findViewById(R.id.textView1);
	        		//final Button b = (Button)findViewById(R.id.button1);
	        		final ImageView image = (ImageView)findViewById(R.id.imageView1);
	        		scoreText.post(new Runnable() {
	        		    public void run() {
	        		        scoreText.setText("Your time is up !!! Your final score " + score);
	        		        scoreText.setVisibility(View.VISIBLE);
	        		        //b.setVisibility(View.GONE);
	        		        image.setVisibility(View.GONE);
	        		    } 
	        		});
	        		t.cancel();
	        		finalPage();
	        		
				}
	        	Log.i("Timer", Long.toString(SystemClock.currentThreadTimeMillis()));
	        }
	    }, 0, 100);
	}

	public void changeImage(){
		imageName = gameLogic.getRandomImage();
		try {
			field = resource.getField(imageName);
			ImageView image = (ImageView)findViewById(R.id.imageView1);
			image.setImageResource(field.getInt(field));
			//updateScore();
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
	public void changeProgressBar(){
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setProgress((int) ((System.currentTimeMillis()-startTime)/1000));
		if(pb.getProgress()>= 1){
			finalPage();
		}
		/*Runnable pbrun = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100; i++) {
					pb.setProgress(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};new Thread(pbrun).start();*/
		
	}
	/*	Runnable runnable = new Runnable() {
		      @Override
		      public void run() {
		        for (int i = 0; i <= 100; i++) {
		          final int value = i;
		           doFakeWork();
		          pb.post(new Runnable() {
		            @Override
		            public void run() {
		              //text.setText("Updating");
		              pb.setProgress(value);
		            }
		          });
		        }
		      }
		    };
		    new Thread(runnable).start();
		  }
	private void doFakeWork() {
	    try {
	      Thread.sleep(500);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }*/
	
		/*new AsyncTask<Void, Integer, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                int progressStatus = 0;
                while (progressStatus < 100000) {
                    progressStatus++;
                    publishProgress(progressStatus);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;  
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                pb.setProgress(values[0]);

            }
        }.execute();
*///	}
	public void playSound(String stompType){
		
		MediaPlayer mp1 = MediaPlayer.create(getBaseContext(), R.raw.save);
		if((imageName.contains("friend") && stompType.equalsIgnoreCase("kick")) || (imageName.contains("bug") && stompType.equalsIgnoreCase("stomp"))){
			match = true;
			try{
			AssetFileDescriptor afd = getAssets().openFd("kill.mp3");
			
			mp1.stop();
			mp1.reset();
			mp1.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			afd.close();
			mp1.prepare();
			mp1.start();
			}catch(Exception e){
				
			}
		}else{
			match=false;
			mp1.start();
		}
	}
	public void showImageAndStartTime(View view) {
		//reset();        
		
	
	}
	
	public void finalPage(){
		Intent finalP = new Intent(this,Final_Page.class);
		finalP.putExtra("score", Float.toString(score));
		startActivity(finalP);
	}
	public void reset(Float peakValue,String stompType) {
		playSound(stompType);	
		changeImage();
		changeScore(peakValue,stompType);
		changeProgressBar();
	}

	private void changeScore(Float peakValue,String stompType) {
		// TODO Auto-generated method stub
		TextView text = (TextView)findViewById(R.id.textView1);
		if(match){
			score = score+peakValue;
		}else{
			score =score-peakValue;
		}
		text.setText(score.toString());	
	}

	@Override
	public void onDataRecieved(TiSensor<?> sensor, String text) {
		// TODO Auto-generated method stub
		
		if (sensor instanceof TiAccelerometerSensor) {
            final TiAccelerometerSensor accSensor = (TiAccelerometerSensor) sensor;
            float[] values = accSensor.getData();

            //Call to check for stomp, adding 1 to Y data for scoring
            isStomp = motion.detectStomp(values[0],values[1],values[2]);
            //isKick = motion.detectRightKick(values[0],values[1],values[2]);  
            
            if(isStomp==-2.0f){
            Log.i("rest", ((Math.round(values[0]*100.0)/100.0)) + "," + ((Math.round(values[1]*100.0)/100.0))+ "," + ((Math.round(values[2]*100.0)/100.0)));
            }
        	
            	if(isStomp!=-2.0f){
            		Log.i("Stomp", ((Math.round(values[0]*100.0)/100.0)) + "," + ((Math.round(values[1]*100.0)/100.0))+ "," + ((Math.round(values[2]*100.0)/100.0)));
                	
            		reset(isStomp,"stomp");	
            	}else if(isKick !=-2.0f){
            		reset(isKick,"kick");
            	}
		}
        
		
		
	}

	

}
