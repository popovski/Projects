package com.memory.consumption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ehcache.sizeof.SizeOf;

import it.unimi.dsi.fastutil.shorts.ShortArrayList;

public class LZW2 {
    /** Compress a string to a list of output symbols. */
    public static ShortArrayList compress(String uncompressed) {
        // Build the dictionary.
        short dictSize = 127;
        Map<String,Short> dictionary = new HashMap<String,Short>();
        for (short i = 0; i < 127; i++)
            dictionary.put("" + (char)i, i);
 
        String w = "";
        ShortArrayList result = new ShortArrayList();
       // List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }
 
        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }
 
    /** Decompress a list of output ks to a string. */
    public static String decompress(ShortArrayList compressed) {
        // Build the dictionary.
        int dictSize = 127;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 127; i++)
            dictionary.put(i, "" + (char)i);
 
        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result.append(entry);
 
            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result.toString();
    }
 
    public static void main(String[] args) {
    	String test = "To create an outstanding value for our customers "
    			+ "and to empower them to outsmart their competition";
    	SizeOf sizeOf = SizeOf.newInstance(); 
    	System.out.println(test.length());
    	
    	ShortArrayList compressed = compress("To create an outstanding value for our customers "
        		+ "and to empower them to outsmart their competition");
        
        System.out.println(compressed.size());
        System.out.println(compressed);
        
        long deepSize = sizeOf.deepSizeOf(compressed);

        System.out.println("deepSize: " + deepSize);
        String decompressed = decompress(compressed);
        System.out.println(decompressed);
    }
}
