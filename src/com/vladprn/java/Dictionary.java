package com.vladprn.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
	private HashSet set;
	
	public Dictionary(CollisionMethod md) {
		switch (md) {
		case CHAINS_METHOD: set = new HashSet1();
		case OPEN_ADRESSING_METHOD: set = new HashSet2(1500);
		case DOUBLE_HASHING_METHOD: set = new HashSet3(1500);
		}
	}
	
	public void loadDictionary(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		while (br.ready()) {
			String word = br.readLine();
			set.insert(word);
		}
		
		br.close();
	}
	
	public boolean contains(String word) {
		return set.contains(word);
	}
}
