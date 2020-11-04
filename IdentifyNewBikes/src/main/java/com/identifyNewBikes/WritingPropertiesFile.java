package com.identifyNewBikes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class WritingPropertiesFile {
	public static void writingfile(ArrayList<String> models,String key) { // This is used to store the output.
		Properties prop1 = new Properties();
		OutputStream writeFile = null;
		try {
			writeFile = new FileOutputStream("Cars.properties");
	
			for (int i=0; i<models.size();i++)
			{
				int j = i+1;
				String keyString = Integer.toString(j);
				prop1.setProperty(keyString, models.get(i));
			}
			prop1.store(writeFile,null);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			if(writeFile != null) {
				try {
					writeFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
