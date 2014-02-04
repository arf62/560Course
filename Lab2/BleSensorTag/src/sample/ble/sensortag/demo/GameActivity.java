package sample.ble.sensortag.demo;



import java.lang.reflect.Field;

import sample.ble.motion.DetectMotion;
import sample.ble.sensortag.R;
import sample.ble.sensortag.R.drawable;
import sample.ble.sensortag.sensor.TiAccelerometerSensor;
import sample.ble.sensortag.sensor.TiSensor;
import sample.ble.util.Logger;




import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends DemoSensorActivity {

	Field field  ;
	Integer score = 0;
	static Integer count =0;
	String nextImage=null;
	String nextText =null;
	DetectMotion motion = null;
	Integer stomp = 0;
	Boolean isStomp = false;
	Boolean isKick  = false;
	private Class<drawable> resource = R.drawable.class;
	GameLogic gameLogic = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        motion = new DetectMotion();
		setContentView(R.layout.activity_game);
		gameLogic = new GameLogic();
		onUserClick();
		
		
	}
/*	public void onStart(){
		super.onStart();
		if(detectStomp()){
			Toast.makeText(getApplicationContext(), "works", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplicationContext(), "need fix", Toast.LENGTH_SHORT).show();
		}
	}*/

	//not used
	public void timerCountDown(final int secondCount){

		final TextView mTextField = (TextView)findViewById(R.id.textView2);
		new CountDownTimer(30000, secondCount) {

			public void onTick(long millisUntilFinished) {
				mTextField.setText("seconds remaining: " + millisUntilFinished / 1000 + "." + millisUntilFinished % 1000);
			}

			public void onFinish() {
				mTextField.setText("done!");
			}
		}.start();
	}

	//not used
	public void userClickYes(){

		if(nextImage == nextText){
			TextView textView = (TextView)findViewById(R.id.textView2);
			++score;
			textView.setText(Integer.toString(score));
		}else if(nextImage != nextText){
			TextView textView = (TextView)findViewById(R.id.textView2);
			--score;
			textView.setText(Integer.toString(score));
			
		}
		onUserClick();
		
	}
	//not used
	public void userClickNo(){
		if(nextImage != nextText){
			TextView textView = (TextView)findViewById(R.id.textView2);
			score++;
			textView.setText(Integer.toString(score));
		}else{
			TextView textView = (TextView)findViewById(R.id.textView2);
			score--;
			textView.setText(Integer.toString(score));
			
		}
		onUserClick();

	}
	
	
	
	public void onUserClick(){


		// Start the count down for 8 seconds and iterate
		// based on the random number pick the image and text to be displayed
		// select a random number between 0,1,2 
		


		Integer randomNumber  = gameLogic.generateRandomNumber();
		
		if(randomNumber == 1 || randomNumber ==2){
			//timerCountDown(8000);
			// display a matching image and text
			//get a random image 
			 nextImage = gameLogic.getRandomImage();
			try {
				field = resource.getField(nextImage);
				ImageView imageView =  (ImageView)findViewById(R.id.imageView1);
				imageView.setImageResource(field.getInt(field));
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
			//get a matching text on screen
			// get a non matching text
				nextText = nextImage;
				TextView textView = (TextView) findViewById(R.id.textView1);
				textView.setText(nextText);
			
				
				//check for the button click
				/*if(detectMotion().equalsIgnoreCase("stomp") ){
					TextView textView1 = (TextView)findViewById(R.id.textView2);
					Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
					++score;
					textView1.setText(Integer.toString(score));
				}
				else if (detectkick()) {
					TextView textView1 = (TextView)findViewById(R.id.textView2);
					--score;
					textView1.setText(Integer.toString(score));
				}*/
			//if true continue - reduce halt time
			//else stop with an error message on screen
		}else if (randomNumber == 0) {
			//display a incorrect image and text
			//get a random image
			nextImage = gameLogic.getRandomImage();
			try {
				field = resource.getField(nextImage);
				ImageView imageView =  (ImageView)findViewById(R.id.imageView1);
				imageView.setImageResource(field.getInt(field));
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
			// get a non matching text
			nextText = gameLogic.getRandomText(nextImage);
			TextView textView = (TextView) findViewById(R.id.textView1);
			textView.setText(nextText);
			
			/*if(detectMotion().equalsIgnoreCase("stomp") ){
				Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
				TextView textView1 = (TextView)findViewById(R.id.textView2);
				++score;
				textView1.setText(Integer.toString(score));
			}*/
			//check for the button click 
			//if true continue - reduce halt time
			//else stop with an error message on screen
		}
if(count==0){
		TextView textView = (TextView)findViewById(R.id.textView2);
		textView.setText("0");
		count =1;
		}
		/*final int []imageArray={R.drawable.apple,R.drawable.kitty,R.drawable.ic_launcher};
 	final ImageView imageView = new ImageView(getBaseContext());
 	final Handler handler = new Handler();
 	         Runnable runnable = new Runnable() {
 	            int i=0;
 	            public void run() {
 	                imageView.setImageResource(imageArray[i]);
 	                i++;
 	                if(i>imageArray.length-1)
 	                {
 	                i=0;    
 	                }
 	             System.out.println("image view " + imageView.getDrawable() );  
 	                handler.postDelayed(this, 2000);  //for interval...
 	            }
 	        };
 	       Toast.makeText(this, "I am in new screen" , Toast.LENGTH_SHORT).show();
 	        handler.postDelayed(runnable, 2000); //for initial delay..
		 */	
	}

	private boolean detectkick() {
		// TODO Auto-generated method stub
		//detect =true;
		
		
		return false;
	}
	
	Integer temp=0;
public boolean detectStomp(){
	/*try{
		while(stomp-temp==0){
			Thread.currentThread().sleep(1000);
		}
	}catch(Exception e){
		
	}*/
	if(stomp-temp>0){
		return true;
	}
	else{
	return false;
	}
}
	
	public void detectMotion(View view) {
		// TODO Auto-generated method stub
		
		String tempString = "none";
		try{
		while(stomp-temp==0){
			Thread.currentThread().sleep(1000);
			Log.i("in loop",Integer.toString(stomp));
		}
		}catch(Exception e){
			
		}
		Log.i("out loop",Integer.toString(stomp));
		if(stomp-temp>1){
			tempString = "Stomp";
			Toast.makeText(getApplicationContext(), "At last", Toast.LENGTH_SHORT).show();
			stomp=0;
			onUserClick();
		}
		//return tempString;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gatt_scan, menu);
		return true;
	}


	@Override
	public void onDataRecieved(TiSensor<?> sensor, String text) {
		// TODO Auto-generated method stub

    	if (sensor instanceof TiAccelerometerSensor) {
            final TiAccelerometerSensor accSensor = (TiAccelerometerSensor) sensor;
            float[] values = accSensor.getData();
          // Log.i("Mytag",  "x"+ values[0] + "   y:" +values[1] + "   z:" +values[2]);
            //Call to check for stomp
             
           isStomp = motion.detectStomp(values[1]);
           isKick = motion.detectKick(values[1],values[2]);  
             //isKick = motion.detectKick();
            if(isStomp){
            	userClickYes();
            }else if (isKick) {
				userClickNo();
			}
            
            
           //renderer.setRotation(values);
            //viewText.setText(text);
        }
		
	}
	
	

}
