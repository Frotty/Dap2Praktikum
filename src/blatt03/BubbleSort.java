package blatt03;

public class BubbleSort {
	
	public static void main (String[] args) {
		try {
			run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void bubbleSort (int[] array) {
		int n = array.length-1;
		for (int i = 0; i<=n; i++) {
			for (int j = n; j>i; j--) {
				if (array[j-1] > array[j]) {
					int tmp = array[j];
					array[j] = array[j-1];
					array[j-1] = tmp;
				}
			}
		}
	}
	public static void fill (int[] array) {	//Absteigende Befüllung des Arrays
		for (int i = 0; i< array.length; i++) {
			array[i] = array.length-i;
		}
	}
	public static void run (String[] args) {		
		float paramTime = 0;		
		try {
			if (args.length > 0) {
				paramTime = (Float.parseFloat(args[0]))*1000;
				if (paramTime<0) {
					System.err.println("Negative Werte sind nicht zulässig.");
					throw new IllegalArgumentException();
				}
				if (args.length > 1) {
					System.err.println("Es ist nur ein Kommandozeilenparameter erlaubt!");
					throw new IllegalArgumentException();
				}
			}
			else {
				System.err.println("Es wird mindestens ein Parameter benötigt!");
				throw new IllegalArgumentException();
			}
		}	
		catch (NumberFormatException e) {
			System.err.println("Eingabe vom Typ 'float' benötigt");
			e.printStackTrace();
		}
		// Parameter validiert
		
		int[] feld = new int[500];
	
		
		long tStart = 0, tEnd = 0, time = 0, diff = 0;
		do {
			feld = new int[feld.length*2];
			fill(feld);
			tStart = System.currentTimeMillis();
			bubbleSort(feld);
			tEnd = System.currentTimeMillis();
			time = tEnd-tStart;
			System.out.println("Laufzeit: "+time+" ms, Feldgroesse: "+feld.length);
	
		} while (time < paramTime);
		
		int min = feld.length / 2;
		int max = feld.length;
		
		while (max >= min) {
			// calculate the midpoint for roughly equal partition
			int mid = (min+max)/2;
			feld = new int[mid];
			fill(feld);
			tStart = System.currentTimeMillis();
			bubbleSort(feld);
			tEnd = System.currentTimeMillis();
			time = tEnd-tStart;
			System.out.println("Laufzeit: "+time+" ms, Feldgroesse: "+feld.length);
			diff = (long) Math.abs(time-paramTime);
			if(diff <= 100) {
				break;
			} else if (time < paramTime) {
				min = mid;
			} else {
				max = mid;
			}
		}
		System.out.println("Gefundene Feldgröße: " + feld.length + " mit der Laufzeit: " + time + "ms im Vergleich zur angegebenen Laufzeit von " + paramTime + "ms");
	}

}
