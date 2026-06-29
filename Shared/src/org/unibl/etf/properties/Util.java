package org.unibl.etf.properties;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Util {
	
	private Util() {
		super();
	}
	
	public static Path generateStringToPath(String path) {
		String[] parts = path.split("/"); 
		return Paths.get(parts[0], Arrays.copyOfRange(parts, 1, parts.length));
	}
	
	public static String buildPath(String input) {
		// replacing all "/" and "\"with appropriate serarators for the current OS
        String separator = File.separator;
        String path = input.replaceAll("[\\\\/]", separator);
        
        // if the last character is a separator, remove it
        if (path.endsWith(separator)) {
            path = path.substring(0, path.length() - 1);
        }
        
        return path;
    }
}
