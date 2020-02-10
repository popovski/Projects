package com.memory.consumption;

import java.util.ArrayList;

import org.ehcache.sizeof.SizeOf;

public class App {
	public static void main(String[] args) {
		// measure memory consumption in bytes of every object
    	String objectString = "";
    	SizeOf sizeOf = SizeOf.newInstance(); 
    	long shallowSize = sizeOf.sizeOf(objectString);
    	long deepSize = sizeOf.deepSizeOf(objectString);
    	// only String object without calculating all attributes
    	System.out.println("shallowSize: "+ shallowSize);
    	// everything that String objects contains is calculated
    	System.out.println("deepSize: " + deepSize);
    	
    	ArrayList<String> lista = new ArrayList<>();
    	lista.add("NIKOLA REMOVE1");
    	lista.add("NIKOLA REMOVE2");
    	
    	System.out.println(lista.remove(0));;
    	
	}
}