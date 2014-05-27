package blatt07;

import java.util.Arrays;
import java.util.Random;

public class Subsequence {

	public static void main(String[] args) {
		// Es muss genau ein Parameter übergeben werden
		if(args.length != 1) {
			throw new IllegalArgumentException("Es die Länge der Teilfolgen angegeben werden");
		} else {
			int length = 0;
			// Länge parsen
			try {
				length = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("Die Länge muss vom Typ int sein");
			}
			Random rnd = new Random();
			// Zufällige Zeichenketten erstellen
			String ss1 = randStr(length, rnd);
			String ss2 = randStr(length, rnd);
			long tStart, tEnd, msecs;
			tStart = System.currentTimeMillis();
			// Algorithmus ausführen
			int[][] lcs = lcs(ss1, ss2);
			tEnd = System.currentTimeMillis();
			msecs = tEnd - tStart;
			// Optional
			if(length <= 25) {
				System.out.println(ss1);
				System.out.println(ss2);
				for(int i = 0; i < lcs.length; i++)
				{
				    for(int j = 0; j < lcs[i].length; j++)
				    {
				        System.out.print(lcs[i][j]);
				        if(j < lcs[i].length - 1) System.out.print(" ");
				    }
				    System.out.println();
				}
			}
			// Ergebnis ausgeben
			System.out.println("Berechnung der längsten gemeinsamen Teilfolge mit Länge " + length + " dauert: " + msecs + "ms");
		}

	}
	
	// Algorithmus bekannt aus Vorlesung
	public static int[][] lcs(String x, String y) {
		int m = x.length();
		int n = y.length();
		int[][] c = new int[m][n];
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n; j++) {
				lengthCalc(x, y, c, i, j);
			}
		}
		return c;
	}
	
	private static void lengthCalc(String x, String y, int[][] c, int i, int j) {
		if(x.charAt(i) == (y.charAt(j))) {
			c[i][j] = c[i-1][j-1] + 1;
		} else {
			if(c[i-1][j] >= c[i][j-1]) {
				c[i][j] = c[i-1][j];
			} else {
				c[i][j] = c[i][j-1];
			}
		}
		
	}

	public static String randStr(int n, Random r) {
		String alphabet ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while (--n>=0) {
		res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return res.toString();
	}

}
