package boyer.moore.algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static final int SIZE = 256;
	private int[] badCharacterSkips = new int[SIZE];
	private char[] pattern, text;

	public static void main(String[] args) {
		char[] pattern, text;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the text: ");

		text = scanner.nextLine().trim().toUpperCase().toCharArray();

		System.out.print("Enter the pattern: ");

		pattern = scanner.nextLine().trim().toUpperCase().toCharArray();

		scanner.close();

		System.out.println("\nMatch found at indices = " + new Main(pattern, text).search());
	}
	
	public Main(char[] pattern, char[] text) {
		int i;
    	
		this.pattern = pattern;
		this.text = text;

		for (i = 0; i < badCharacterSkips.length; i++) {
			badCharacterSkips[i] = -1;
		}

		for (i = 0; i < pattern.length; i++) {
			badCharacterSkips[pattern[i]] = i;
		}
	}

	public ArrayList<Integer> search() {
		int i, j, skip;

		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		for (i = 0; i < text.length - pattern.length + 1; i += skip) {
			skip = 0;

			for (j = pattern.length - 1; j >= 0; j--) {
				if (pattern[j] != text[i + j]) {
					skip = Math.max(1, j - badCharacterSkips[text[i + j]]);

					break;
				}
			}

			if (skip == 0) {
				skip++;

				arrayList.add(i);
			}
		}

		return arrayList;
	}

}