package blatt03;

class Bubblesort {
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
	public static void main (String[] args) {		
		boolean pruefung = false;	//Fehlerkontrolle, wird bei 1.) auf true gesetzt, wenn Programm ausgeführt werden kann
		float vgl = 0;		
		try {
			if (args.length > 0) {
				vgl = (Float.parseFloat(args[0]))*1000;
				pruefung = true;	// 1.)
				if (vgl<0) {
					System.out.println("Negative Werte sind nicht zulässig. Programm wird mit positivem Wert ausgeführt...");
					vgl = vgl *(-1); //Negative Variable für Ausführung anpassen
				}
				if (args.length > 1) {
					System.out.println("Nur ein Wert wird benötigt. Programm ignoriert weitere Parameter...");
				}
			}
			else {
				System.out.println("Parameter benötigt!");
			}
		}	
		catch (NumberFormatException e) {
			System.out.println("Eingabe vom Typ 'float' benötigt");
		}	 
		if (pruefung) {
			int[] feld = new int[1000];
			fill(feld);
			//print(feld);
			long tStart = System.currentTimeMillis();	//Zeitmessung
			bubbleSort(feld);
			long tEnd = System.currentTimeMillis();
			long time = tEnd-tStart;
			//System.out.println();		
			//System.out.println("Laufzeit: "+time+" ms");			
			float timeDif = vgl-time;	//Differenz der eingegebenen Zeit und der Ausführungszeit
			while (timeDif > 100) {		//wird ausgeführt, solange Bubblesort schneller als die Vorgabe arbeitet
				int feldL = feld.length;				
				feld = new int[feldL*2];
				fill(feld);
				tStart = System.currentTimeMillis();
				bubbleSort(feld);
				tEnd = System.currentTimeMillis();
				time = tEnd-tStart;
				timeDif = vgl-time;
				System.out.println("Laufzeit: "+time+" ms, Feldgroesse: "+feld.length);
		
			}	
		}
	}
	public static void print(int[] array) { //für Aufgabenstellung unwichtig
		System.out.println();		
		int formatCounter = 0;		
		for(int i = 0; i<array.length; i++) {
			System.out.print(array[i] +", ");
			formatCounter++;
			if (formatCounter > 10) {
				System.out.println();
				formatCounter = 0;
			}
		}
		System.out.println();
	}
}
