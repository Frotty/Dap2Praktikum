package blatt10;

import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
public class CountWords{
  
  // Festgelegte Menge der interessierenden Wörter
  static String[] words = {
	"a",
	"about",
	"all",
	"an",
	"and",
	"are",
	"around",
	"as",
	"at",
	"away",
	"back",
	"be",
	"beach",
	"beat",
	"black",
	"body",
	"brown",
	"but",
	"by",
	"can",
	"close",
	"come",
	"cut",
	"day",
	"did",
	"do",
	"door",
	"down",
	"eyes",
	"face",
	"find",
	"for",
	"from",
	"get",
	"gets",
	"go",
	"going",
	"gonna",
	"got",
	"has",
	"have",
	"he",
	"her",
	"here",
	"him",
	"his",
	"how",
	"i",
	"if",
	"in",
	"inside",
	"into",
	"is",
	"it",
	"jungle",
	"just",
	"know",
	"library",
	"like",
	"look",
	"looks",
	"lost",
	"main",
	"man",
	"matrix",
	"me",
	"mean",
	"my",
	"now",
	"of",
	"off",
	"oh",
	"on",
	"one",
	"open",
	"out",
	"over",
	"phone",
	"right",
	"room",
	"see",
	"sees",
	"she",
	"something",
	"sun",
	"tank",
	"that",
	"the",
	"their",
	"them",
	"then",
	"there",
	"they",
	"think",
	"this",
	"through",
	"to",
	"turns",
	"two",
	"up",
	"us",
	"walks",
	"wanna",
	"want",
	"was",
	"we",
	"well",
	"what",
	"when",
	"where",
	"who",
	"why",
	"with",
	"would",
	"yeah",
	"you",
	"your"
  };
  public static HashTable readAndHashWords (String path, int hashSize, int fileLength,String hashMode) {
	  System.out.println("start");
	  Scanner scanner;
	  HashTable hashTable = new HashTable(hashSize,hashMode);
	  int count = 0;
	  try {	
		  scanner = new Scanner(new File(path));
			scanner.useDelimiter("[.,-?!#+ \\s]+");
			String token;
			while (scanner.hasNext()) {
				token = scanner.next();
				hashTable.put(token);
				count++;
			}
			scanner.close();
	} catch (Exception ex) {	
		ex.printStackTrace();
	}
	return hashTable;
  }
  public static int fileLength(String path) {
 	File file = new File(path);
 	int count = 0;
 	try {
 		BufferedReader reader = new BufferedReader(new FileReader(file));
 		while ( reader.readLine() != null ) {
 			count++;
      		}
    	}
    	catch (Exception ex) {
      		System.err.println(ex);
    	}
    	return count;
  }
  public static void comp(HashTable table) {
  	for (int i = 0; i<words.length; i++) {
  		if (table.get(words[i]) != null) {
  			System.out.println(words[i]+" ist "+table.get(words[i]).getInfo()+" mal in der Datei vorhanden.");
  		}
  	}
  }
  	
  public static void main(String[] args){
    	String path = "";
    	int hashSize = 0;
    	boolean hashSizeCheck = false;
    	String hashMode = "";
    	HashTable hashTable;
    	if(args.length == 0) {
    		throw new IllegalArgumentException("Es muss mindestens ein Parameter angegeben werden");
    	}
    	if (args.length >= 1) {
    		path = args[0];
    	}
    	if (args.length >= 2) {
    		try {
    			hashSize = Integer.parseInt(args[1]);
    			hashSizeCheck = true;
    		}
    		catch (Exception ex) {
    			hashMode = args[1];
    			hashSize = 10;
    		}
    	}
    	if (args.length == 3) {
    		hashMode = args[2];
    	}
    	if (hashMode.equals("")) hashMode = "RSHash";
    	if (!hashSizeCheck) {
    		hashSize = 10;
    	}
    	if (hashSize > 0) {
    		hashTable = new HashTable(hashSize,hashMode);
    	}
    	try {
    		hashTable = readAndHashWords(path,hashSize,fileLength(path),hashMode);
    		comp(hashTable);
    		System.out.println("Anzahl der Kollisionen: "+hashTable.numberOfCollisions());
    	}
    	catch (Exception ex) {
    		System.err.println("Fehlerhafte Eingabe, HashSize muss positiv sein");
    	}
    	
  }
  
}
