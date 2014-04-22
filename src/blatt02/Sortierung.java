package blatt02;

import java.util.Arrays;
import java.util.Random;


public class Sortierung {
	
	public static void main(String[] RunArgs) {
		// Kommandozeilenparameter auswerten
		try {
			for(int i = 1; i < 18; i++) {
				RunArgs[0] = String.valueOf(i * 10000); 
				RunArgs[1] = "insert"; 
				run(RunArgs);
				RunArgs[1] = "merge";
				run(RunArgs);
			}
//			run(RunArgs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void run(String[] RunArgs) {
		Random numberGenerator = new java.util.Random();
		if(RunArgs.length > 3) {
			System.err.println("Es können maximal 3 Parameter angegeben werden");
			throw new IllegalArgumentException();
		}
		if(RunArgs.length < 1) {
			System.err.println("Es muss mindestens ein Parameter angegeben werden");
			throw new IllegalArgumentException();
		}
		int feldGroesse = 0;
		// Erster Parameter, die FeldGröße
		try {
			feldGroesse = Integer.parseInt(RunArgs[0]);
		} catch (NumberFormatException ne) {
			System.err.println("Der erste Parameter ist kein integer");
			ne.printStackTrace();
		}
		if (feldGroesse < 2) {
			System.err.println("Die Feldgröße muss mindestens 2 betragen");
			throw new IllegalArgumentException();
		}
		int[] feld = new int[feldGroesse];
		boolean isMerge = true;
		// Optionale Parameter
		if (RunArgs.length > 1) {
			String sortParam = RunArgs[1];
			if(sortParam.equals("insert")) {
				isMerge = false;
			}else if(! sortParam.equals("merge")) {
				System.err.println("Der 2. Parameter kann nur \"insert\" oder \"merge\" sein");
				throw new IllegalArgumentException();
			}
			
		}
		if (RunArgs.length > 2) {
			String optParam = RunArgs[2];
			if(optParam.equals("rand")) {
				// zufällig füllen
				for(int i = 0; i < feldGroesse; i++) {
					feld[i] = numberGenerator.nextInt(feldGroesse);
				}
			}else if(optParam.equals("ab")) {
				// abfsteigend füllen
				for(int i = 0; i < feldGroesse; i++) {
					feld[i] = feldGroesse-i;
				}
			}else if(optParam.equals("auf")) {
				// aufsteigend füllen
				for(int i = 0; i < feldGroesse; i++) {
					feld[i] = i;
				}
			}else {
				System.err.println("Der 3. Parameter kann nur \"auf\", \"ab\" oder \"rand\" sein");
				throw new IllegalArgumentException();
			}
		}else {
			// Kein 2. Parameter, also zufällig füllen
			for(int i = 0; i < feldGroesse; i++) {
				feld[i] = numberGenerator.nextInt(feldGroesse);
			}
		}
		long tStart, tEnd, msecs;
		tmp = new int[feldGroesse];
		tStart = System.currentTimeMillis();
		if(isMerge) {
			// Sortierung mit MergeSort, bekannt aus der Vorlesung
			mergeSort(feld);
		} else {
			// Sortierung mit InsertionSort, bekannt aus der Vorlesung
			insertionSort(feld);
		}
		tEnd = System.currentTimeMillis();
		msecs = tEnd - tStart;
		if(isSorted(feld)) {
			System.out.println("Feld ist sortiert!");
		} else {
			System.out.println("Feld ist NICHT sortiert!");
		}
		if(isMerge) {
			System.out.println("Benötigte Zeit Merge: " + msecs + "ms");
		}else {
			System.out.println("Benötigte Zeit Insert: " + msecs + "ms");
		}
		if(feldGroesse <= 100) {
			System.out.println("Feld: " + Arrays.toString(feld));
		}
		
		
	}
	
	public static void insertionSort(int[] array) {
		if( array == null ) {
			System.err.println("Parameter array ist null");
			throw new IllegalArgumentException();
		}
		if ( array.length < 1 ) {
			System.err.println("Parameter array enthält keine Elemente");
			throw new IllegalArgumentException();
		}
		for(int j = 1; j < array.length; j++) {
			int key = array[j];
			int i = j-1;
			while(i >= 0 && array[i] > key ) {
				array[i+1] = array[i];
				i--;
			}
			array[i+1] = key;
		}
		assert isSorted(array) : "Der parameter array ist nicht sortiert";
	}
	
	public static boolean isSorted(int[] array) {
		if( array == null ) {
			System.err.println("Parameter array ist null");
			throw new IllegalArgumentException();
		}
		if ( array.length < 1 ) {
			System.err.println("Parameter array enthält keine Elemente");
			throw new IllegalArgumentException();
		}
		// Eine Folge mit nur einem Element ist per Definition sortiert
		if(array.length == 1) return true;
		// Durch das Feld iterieren
		for(int i = 0; i < array.length-1; i++) {
			// Ist der Wert mit dem index i+1 kleiner als der Wert mit dem index i
			// ist die Liste nicht aufsteigend sortiert
			if(array[i] > array[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	// Für Assertions von mergesort
	public static boolean isSorted(int[] array, int min, int max) {
		if( array == null ) {
			System.err.println("Parameter array ist null");
			throw new IllegalArgumentException();
		}
		if ( array.length < 1 ) {
			System.err.println("Parameter array enthält keine Elemente");
			throw new IllegalArgumentException();
		}
		int[] tmp = new int[max-min+1];
		int j = 0;
		for(int i = min; i <= max; i++) {
			tmp[j] = array[i];
			j++;
		}
		
		return isSorted(tmp);
		
	}
	
	static int[] tmp;
	
	public static void merge(int[] array, int min, int middle, int max) { 
		
		assert isSorted(array, min, middle) : "Linke Teilliste ist nicht sortiert";
		assert isSorted(array, middle+1, max) : "Rechte Teilliste ist nicht sortiert";
		// Alle Elemente in einen temporären Array kopieren
		for(int i = min; i <= max; i++) {
			tmp[i] = array[i];
		}
		int i = min;
		int j = middle+1;
		int k = min;
		// Den kleinsten Wert von entweder der rechten oder linken Teilliste
		// in den gegebenen array schreiben
		while(i <= middle && j <= max) {
			if(tmp[i] <= tmp[j]) {
				array[k] = tmp[i];
				i++;
			} else {
				array[k] = tmp[j];
				j++;
			}
			k++;
		}
		// Für den Fall, dass j über max kommt, i aber noch nicht == middle ist
		// müssen wir linken Teil noch nachtragen
		while(i <= middle) {
			array[k] = tmp[i];
			i++;
			k++;
		}
	}
	
	public static void mergeSort(int[] array, int min, int max) {
		if(min<max) {
			int middle = (min+max)/2;
			mergeSort(array, min, middle);
			mergeSort(array, middle+1, max);
			merge(array, min, middle, max);
		}
	}
	
	public static void mergeSort(int[] array) {
		mergeSort(array, 0, array.length-1);
	}
	
}
