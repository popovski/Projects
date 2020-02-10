package com.memory.consumption;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.ehcache.sizeof.SizeOf;

public class HuffmanCodeSolution {
	private static Map<Character, String> charPrefixHashMap = new HashMap<>();
	static HuffmanNode root;

	public static void main(String[] args) {
		// Huffman coding
		String test = "To create an outstanding value for our customers "
				+ "and to empower them to outsmart their competition";
		
		System.out.println("Original Text = " + test);
		String s = encode(test);
		// calculate bytes
		SizeOf sizeOf = SizeOf.newInstance(); 
		byte[] lista = stringToBytesASCII(s);
		
		long deepSize3 = sizeOf.deepSizeOf(lista);
		
		System.out.println("ORIGINAL deepSize3: " + deepSize3);
		
		decode(s);

	}
	
	public static byte[] stringToBytesASCII(String str) {
		byte[] b = new byte[str.length()];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) str.charAt(i);
		}
		return b;
	}

	private static HuffmanNode buildTree(Map<Character, Integer> freq) {

		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
		Set<Character> keySet = freq.keySet();
		for (Character c : keySet) {

			HuffmanNode huffmanNode = new HuffmanNode();
			huffmanNode.data = c;
			huffmanNode.frequency = freq.get(c);
			huffmanNode.left = null;
			huffmanNode.right = null;
			priorityQueue.offer(huffmanNode);
		}
		assert priorityQueue.size() > 0;

		while (priorityQueue.size() > 1) {

			HuffmanNode x = priorityQueue.peek();
			priorityQueue.poll();

			HuffmanNode y = priorityQueue.peek();
			priorityQueue.poll();

			HuffmanNode sum = new HuffmanNode();

			sum.frequency = x.frequency + y.frequency;
			sum.data = '-';

			sum.left = x;

			sum.right = y;
			root = sum;

			priorityQueue.offer(sum);
		}

		return priorityQueue.poll();
	}

	private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

		if (node != null) {
			if (node.left == null && node.right == null) {
				charPrefixHashMap.put(node.data, prefix.toString());

			} else {
				prefix.append('0');
				setPrefixCodes(node.left, prefix);
				prefix.deleteCharAt(prefix.length() - 1);

				prefix.append('1');
				setPrefixCodes(node.right, prefix);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}

	}

	private static String encode(String test) {
		Map<Character, Integer> freq = new HashMap<>();
		for (int i = 0; i < test.length(); i++) {
			if (!freq.containsKey(test.charAt(i))) {
				freq.put(test.charAt(i), 0);
			}
			freq.put(test.charAt(i), freq.get(test.charAt(i)) + 1);
		}

		System.out.println("Character Frequency Map = " + freq);
		root = buildTree(freq);

		setPrefixCodes(root, new StringBuilder());
		System.out.println("Character Prefix Map = " + charPrefixHashMap);
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < test.length(); i++) {
			char c = test.charAt(i);
			s.append(charPrefixHashMap.get(c));
		}

		return s.toString();
	}

	private static void decode(String s) {

		StringBuilder stringBuilder = new StringBuilder();

		HuffmanNode temp = root;

		System.out.println("Encoded: " + s);

		for (int i = 0; i < s.length(); i++) {
			int j = Integer.parseInt(String.valueOf(s.charAt(i)));

			if (j == 0) {
				temp = temp.left;
				if (temp.left == null && temp.right == null) {
					stringBuilder.append(temp.data);
					temp = root;
				}
			}
			if (j == 1) {
				temp = temp.right;
				if (temp.left == null && temp.right == null) {
					stringBuilder.append(temp.data);
					temp = root;
				}
			}
		}

		System.out.println("Decoded string is " + stringBuilder.toString());

	}
}

class HuffmanNode implements Comparable<HuffmanNode> {
	int frequency;
	char data;
	HuffmanNode left, right;

	public int compareTo(HuffmanNode node) {
		return frequency - node.frequency;
	}
}
