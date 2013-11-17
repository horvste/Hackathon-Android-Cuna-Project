package com.hackathon.cuna;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;

public class FIleReader {
	public static  void generateNoteOnSD(String sFileName, String sBody) {
		try {
			File root = new File(Environment.getExternalStorageDirectory(),
					"KUNA");
			if (!root.exists()) {
				root.mkdirs();
			}
			File gpxfile = new File(root, sFileName);
			FileWriter writer = new FileWriter(gpxfile);
			writer.append(sBody);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
