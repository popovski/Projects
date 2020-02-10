package com.memory.consumption;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import org.ehcache.sizeof.SizeOf;

public class ZippingString {
	public static void main(String[] args) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
			ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut);
			objectOut.writeObject("To create an outstanding value for our "
					+ "customers and to empower them to outsmart their competition"); 
					
			objectOut.close();
			
			byte[] bytes = baos.toByteArray();
			SizeOf sizeOf = SizeOf.newInstance();
			
			long deepSize3 = sizeOf.deepSizeOf(bytes);
			
			System.out.println("ORIGINAL deepSize3: " + deepSize3);
			
		} catch (Exception e) {

		}
	}
}