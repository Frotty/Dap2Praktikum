package blatt03;

public class BubbleSort {
	
	public static void main (String[] args) {
		try {
			run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Aus der Vorlesung bekannt
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
	
	//Absteigende Bef�llung des Arrays
	public static void fill (int[] array) {	
		for (int i = 0; i< array.length; i++) {
			array[i] = array.length-i;
		}
	}
	
	public static boolean isSorted(int[] array) {
		if( array == null ) {
			System.err.println("Parameter array ist null");
			throw new IllegalArgumentException();
		}
		if ( array.length < 1 ) {
			System.err.println("Parameter array enth�lt keine Elemente");
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
	
	public static void run (String[] args) {		
		float paramTime = 0;		
		if (args.length == 1) {
			try {
				paramTime = (Float.parseFloat(args[0]))*1000;
			} catch (NumberFormatException e) {
				System.err.println("Eingabe vom Typ 'float' ben�tigt");
				e.printStackTrace();
			}
		}
		else {
			System.err.println("Es wird genau ein Parameter ben�tigt!");
			throw new IllegalArgumentException();
		}
		// Parameter ist nun validiert
		
		// Workaround f�r m�glichst wenig Codezeilen
		int[] feld = new int[500];	
		// Variablen f�r die Zeitmessung
		long tStart = 0, tEnd = 0, time = 0, diff = 0;
		do {
			feld = new int[feld.length*2];
			fill(feld);
			tStart = System.currentTimeMillis();
			bubbleSort(feld);
			assert isSorted(feld) : "Array nicht sortiert";
			tEnd = System.currentTimeMillis();
			time = tEnd-tStart;
			System.out.println("Laufzeit: "+time+" ms, Feldgroesse: "+feld.length);
	
		} while (time < paramTime);
		// Bubblesort ben�tigt l�nger als die angegebene Zeit
		
		// Anfang der bin�ren Suche
		int min = feld.length / 2;
		int max = feld.length;
		while (max >= min) {
			// Mitte berechnen
			int mid = (min+max)/2;
			// Buublesort durchf�hren mit Feldgr��e = mid
			feld = new int[mid];
			fill(feld);
			tStart = System.currentTimeMillis();
			bubbleSort(feld);
			assert isSorted(feld) : "Array nicht sortiert";
			tEnd = System.currentTimeMillis();
			time = tEnd-tStart;
			System.out.println("Laufzeit: "+time+" ms, Feldgroesse: "+feld.length);
			// Differenz zwischen gebrauchter Zeit und angegebener Zeit
			diff = (long) Math.abs(time-paramTime);
			if(diff <= 1) {
				// Ist die Differenz geringer als 100ms wird die Suche (erfolgreich) beendet
				break;
			} else if (time < paramTime) {
				// Ist die ben�tigte Zeit geringer als die Angegebene, suchen wir weiter im Bereich von mid-max
				min = mid;
			} else {
				// Ist die ben�tigte Zeit gr��er als die Angegebene, suchen wir weiter im Bereich von min-mid
				max = mid;
			}
		}
		// Die Suche ist beendet, das Ergebnis wird ausgegeben
		System.out.println("Gefundene Feldgr��e: " + feld.length + " mit der Laufzeit: " + time + "ms im Vergleich zur angegebenen Laufzeit von " + paramTime + "ms");
	}

}
