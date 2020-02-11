package com.memory.consumption;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.ehcache.sizeof.SizeOf;

import it.unimi.dsi.fastutil.bytes.ByteArrayList;

public class LZW {
    /** Compress a string to a list of output symbols. */
    public static byte[] compress(String uncompressed) {
        // Build the dictionary.
        byte dictSize = 127;
        Map<String,Byte> dictionary = new HashMap<String,Byte>();
        for (byte i = 0; i < 127; i++)
            dictionary.put("" + (char)i, i);
 
        String w = "";
        ByteArrayList result = new ByteArrayList(uncompressed.length());
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
        return result.toByteArray();
    }
 
    /** Decompress a list of output ks to a string. */
    public static String decompress(byte[] compressed) {
        // Build the dictionary.
        byte dictSize = 127;
        Map<Byte,String> dictionary = new HashMap<Byte,String>();
        for (byte i = 0; i < 127; i++)
            dictionary.put(i, "" + (char)i);
 
        String w = "" + (char)(byte)compressed[0];
        compressed = Arrays.copyOfRange(compressed, 1, compressed.length);
        
        StringBuffer result = new StringBuffer(w);
        for (byte k : compressed) {
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
    	String text = "To create an outstanding value for our customers and to empower them to outsmart their competition";
    	
    	SizeOf sizeOf = SizeOf.newInstance();

    	System.out.println("ORIGINAL: " + text);
    	
        long deepSizeText = sizeOf.deepSizeOf(text);
        System.out.println("ORIGINAL CONTENT MEMORY FOOT PRINT - BYTES: " + deepSizeText);
    	
    	byte[] compressed = compress(text);
        
        String decompressed = decompress(compressed);
        System.out.println("TEXT AFTER DECOMPRESS: " + decompressed);
        
        long deepSize = sizeOf.deepSizeOf(compressed);
        System.out.println("Compress CONTENT MEMORY FOOT PRINT - BYTES: " + deepSize);

    }
}
