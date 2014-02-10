package com.example.amipal_2;



import java.lang.reflect.Field;

import com.example.amipal_2.R.drawable;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends Activity {

	Field field  ;
	Integer score = 0;
	static Integer count =0;
	String nextImage=null;
	String nextText =null;
	private Class<drawable> resource = R.drawable.class;
	GameLogic gameLogic = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		gameLogic = new GameLogic();
		onUserClick();
		
	}


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
	public void userClickYes(View view){

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
	public void userClickNo(View view){
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

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
*/
}
