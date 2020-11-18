package com.objectiva.pilot.util;

public class CommonUtils {

	public static String toHash(String key) {
		int arraySize = 11113;
		int hashCode = 0;
		for (int i = 0; i < key.length(); i++) {
			int letterValue = key.charAt(i) - 96;
			hashCode = ((hashCode << 5) + letterValue) % arraySize;
		}
		return "***" + hashCode;
	}

}
