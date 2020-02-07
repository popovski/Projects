package com.memory.consuption;

import org.ehcache.sizeof.SizeOf;

public class App {
	public static void main(String[] args) {
    	String objectString = "";
    	SizeOf sizeOf = SizeOf.newInstance(); 
    	long shallowSize = sizeOf.sizeOf(objectString);
    	long deepSize = sizeOf.deepSizeOf(objectString);
    	// only String object without calculating all attributes
    	System.out.println("shallowSize: "+ shallowSize);
    	// everything that String objects contains is calculated
    	System.out.println("deepSize: " + deepSize);
	}
}