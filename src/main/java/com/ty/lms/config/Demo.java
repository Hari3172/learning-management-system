package com.ty.lms.config;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

public class Demo {
	public static void main(String[]args) {
		HashMap<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, null);
		hashMap.put(null, "A");
		hashMap.put(null, "B");
		System.out.println(hashMap);
		
		HashMap<Integer, String> hashMap2 = new HashMap<>(23);  // 23 is fill ratio
		
		Hashtable<String, Integer> hashtable = new Hashtable<>();
		hashtable.put("nitin", null);
		
		System.out.println("hashtable"+hashtable);
//		
//		TreeMap<Integer, String> treeMap = new TreeMap<>();
//		
//		treeMap.put(null, null);

//		System.out.println(treeMap);
	}
}
