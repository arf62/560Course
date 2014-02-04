package sample.ble.sensortag.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameLogic {

	Map<Integer, String> imageData = null;
	Random random = null;
public GameLogic(){

	  random = new Random();
	 imageData = new HashMap<Integer,String>();
	 
}



private   Integer bufferTime = 8000;

	
//returns a random number in 0,1,2	
public  int generateRandomNumber(){
	
	return random.nextInt(2);
}

//returns random image name from the hashmap imagedata
public  String getRandomImage(){
	imageData.put(0, "apple");
	imageData.put(1, "kitty");
	imageData.put(2, "pepsi");
	imageData.put(3, "blackberry");
	return imageData.get(random.nextInt(imageData.size())) ;
}

public  String getRandomText(String imageValue){
	
		String textValue = imageData.get(random.nextInt(imageData.size())); 
		while( textValue == imageValue){
			textValue = imageData.get(random.nextInt(imageData.size()));
		}
	
	return textValue;
	
}
public  void showQuestion(){
	
	
	
}

public  void checkIfAnswerTrue(){
	
	
}

public  void exitApplication(){
	
	
}


public  Integer getBufferTime() {
	return bufferTime;
}

public  void setBufferTime(Integer bufferTime) {
	this.bufferTime = bufferTime;
}



	
}
