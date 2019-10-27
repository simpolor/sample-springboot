package com.simpolor.app.util;

public class FileUtil {

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

}
