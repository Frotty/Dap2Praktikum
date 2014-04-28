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
	
	//Absteigende Befüllung des Arrays
	public static void fill (int[] array) {	
		for (int i = 0; i< array.length; i++) {
			array[i] = array.length-i;
		}
	}
	public static void run (String[] args) {		
		float paramTime = 0;		
		if (args.length == 1) {
			try {
				paramTime = (Float.parseFloat(args[0]))*1000;
			} catch (NumberFormatException e) {
				System.err.println("Eingabe vom Typ 'float' benötigt");
				e.printStackTrace();
			}
		}
		else {
			System.err.println("Es wird genau ein Parameter benötigt!");
			throw new IllegalArgumentException();
		}
		// Parameter ist nun validiert
		
		// Workaround für möglichst wenig Codezeilen
		int[] feld = new int[500];	
		// Variablen für die Zeitmessung
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
		// Bubblesort benötigt länger als die angegebene Zeit
		
		// Anfang der binären Suche
		int min = feld.length / 2;
		int max = feld.length;
		while (max >= min) {
			// Mitte berechnen
			int mid = (min+max)/2;
			// Buublesort durchführen mit Feldgröße = mid
			feld = new int[mid];
			fill(feld);
			tStart = System.currentTimeMillis();
			bubbleSort(feld);
			tEnd = System.currentTimeMillis();
			time = tEnd-tStart;
			System.out.println("Laufzeit: "+time+" ms, Feldgroesse: "+feld.length);
			// Differenz zwischen gebrauchter Zeit und angegebener Zeit
			diff = (long) Math.abs(time-paramTime);
			if(diff <= 100) {
				// Ist die Differenz geringer als 100ms wird die Suche (erfolgreich) beendet
				break;
			} else if (time < paramTime) {
				// Ist die benötigte Zeit geringer als die Angegebene, suchen wir weiter im Bereich von mid-max
				min = mid;
			} else {
				// Ist die benötigte Zeit größer als die Angegebene, suchen wir weiter im Bereich von min-mid
				max = mid;
			}
		}
		// Die Suche ist beendet, das Ergebnis wird ausgegeben
		System.out.println("Gefundene Feldgröße: " + feld.length + " mit der Laufzeit: " + time + "ms im Vergleich zur angegebenen Laufzeit von " + paramTime + "ms");
	}

}
