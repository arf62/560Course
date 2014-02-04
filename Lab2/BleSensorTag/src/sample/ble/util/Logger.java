package sample.ble.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;

public class Logger {

	static final File externalStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	private static File myFile = new File(externalStorageDir , "mysdfile.txt");;
	
	
	public static void writeLogToFile(String s){
		   try {	   
		   if(!myFile.exists())
				myFile.createNewFile();

				BufferedWriter buf = new BufferedWriter(new FileWriter(myFile, true));
				buf.append(s);
				buf.newLine();
				buf.flush();
				buf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	
	   
}
