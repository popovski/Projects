package com.memory.consumption;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.ehcache.sizeof.SizeOf;

public class DeflateAlgortihm {
	public static void main(String[] args) {
		try {
			// Encode a String into bytes
			String inputString = "To create an outstanding value for our "
					+ "customers and to empower them to outsmart their competition";
			System.out.println(inputString);
			byte[] input = inputString.getBytes("UTF-8");

			// Compress the bytes
			byte[] output = new byte[100];
			Deflater compresser = new Deflater();
			compresser.setInput(input);
			compresser.finish();
			int compressedDataLength = compresser.deflate(output);

			// Decompress the bytes
			Inflater decompresser = new Inflater();
			decompresser.setInput(output, 0, compressedDataLength);
			byte[] result = new byte[inputString.length()];
			int resultLength = decompresser.inflate(result);
			decompresser.end();

			// calculate size of the list
			SizeOf sizeOf = SizeOf.newInstance();

			long deepSize3 = sizeOf.deepSizeOf(result);
			System.out.println("ORIGINAL deepSize3: " + deepSize3);

			// Decode the bytes into a String
			String outputString = new String(result, 0, resultLength, "UTF-8");
			System.out.println(outputString);
		} catch (java.io.UnsupportedEncodingException ex) {
			// handle
		} catch (java.util.zip.DataFormatException ex) {
			// handle
		}
	}
}
