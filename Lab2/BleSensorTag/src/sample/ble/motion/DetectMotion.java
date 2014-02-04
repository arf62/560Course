package sample.ble.motion;



public class DetectMotion {
	
	int count = 0;
	int tempCount =0;

	
//This method detects a stomp
public Boolean detectStomp(float value){
	
	Boolean stampDetected=false;
	if(value >= -0.1 && count ==0 ){
		stampDetected = true;
		count =1;
	}

	else{
	//to skip a time frame if we detect a stomp	
		if(count==1 && tempCount > 11){
			count=0;
			tempCount =0;
		}
		tempCount++;
		}
	
	//Logger.writeLogToFile("in the sensor : count : " + count +  "tempCoount   : " + tempCount +  "value : " + value);
	return stampDetected;
}

int count1=0;
int tempCount1 = 0;
public Boolean detectKick(float yaxis, float zaxis) {
	Boolean stampDetected=false;
	if(yaxis <= -0.5 && zaxis <= -0.8 && count1 ==0 ){
		stampDetected = true;
		count1 =1;
	}

	else{
	//to skip a time frame if we detect a stomp	
		if(count1==1 && tempCount1 > 12){
			count1=0;
			tempCount1 =0;
		}
		tempCount1++;
		}
	
	return stampDetected;
}

}
