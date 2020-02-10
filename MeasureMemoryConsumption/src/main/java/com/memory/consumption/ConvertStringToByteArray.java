package com.memory.consumption;

import org.ehcache.sizeof.SizeOf;

public class ConvertStringToByteArray {
	public static void main(String[] args) {
		// measure memory consumption in bytes
		String objectString1 = "To create an outstanding value for our customers "
				+ "and to empower them to outsmart their competition";
		String objectString = "1create an outstanding value for our customers "
				+ "and 1empower them 1outsmart their competition";
		// replace to with one character
		// find all works that accure multiple times, save then into array and replace them
		// with a number that will reference the position of the real word
		SizeOf sizeOf = SizeOf.newInstance();
		long shallowSize = sizeOf.sizeOf(objectString);
		long deepSize2 = sizeOf.deepSizeOf(objectString1);
		long deepSize = sizeOf.deepSizeOf(objectString);
		// only String object without calculating all attributes
		System.out.println(" Shallow Size: " + shallowSize);
		// everything that String objects contains is calculated
		System.out.println("ORIGINAL String Deep Size: " + deepSize2);
		System.out.println("CONVERTED String Deep Size1: " + deepSize);

		byte[] lista = stringToBytesASCII(objectString);
		long deepSize1 = sizeOf.deepSizeOf(lista);
		
		System.out.println("CONVERTED deepSize1: " + deepSize1);
		
		byte[] lista1 = stringToBytesASCII(objectString1);
		long deepSize3 = sizeOf.deepSizeOf(lista1);
		
		System.out.println("ORIGINAL deepSize3: " + deepSize3);
		
	}

	public static byte[] stringToBytesASCII(String str) {
		byte[] b = new byte[str.length()];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) str.charAt(i);
		}
		return b;
	}
}