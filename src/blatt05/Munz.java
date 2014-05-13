package blatt05;

import java.util.Arrays;

public class Munz {

	public static void main(String[] runArgs) {
		try {
			run(runArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void run(String[] runArgs) {
		if(runArgs.length != 2) {
			System.err.println("Es müssen genau 2 Parameter angegeben werden");
			throw new IllegalArgumentException();
		}else {
			int[] w;
			if( runArgs[0].equals("Euro")) {
				w = new int[]{200,100,50,20,10,5,2,1};
			}else if ( runArgs[0].equals("Alternative")) {
				w = new int[]{200,100,50,20,10,5,4,2,1};
			}else{
				System.err.println("Der 2. Parameter muss Euro oder Alternative sein");
				throw new IllegalArgumentException();
			}
			int b = 0;
			try {
				b = Integer.parseInt(runArgs[1]);
			} catch(Exception e) {
				System.err.println("Der 2. Parameter muss ein Integer sein");
				e.printStackTrace();
			}
			if(b > 0) {
				// Wechselgeld berechnen
				System.out.println("Der Betrag " + b + " wurde wie folgt aufgeteilt: " + Arrays.toString(change(b, w)));
			}else{
				System.err.println("Wechselgeld muss mehr als 0 betragen");
				throw new IllegalArgumentException();
			}
		}
		
	}
	
	public static int[] change(int b, int[] w) {
		int[] r = new int[w.length];
		for(int i = 0; i< w.length; i++) {
			if(b >= w[i]) {
				r[i] = b/w[i];
				b -= r[i] * w[i];
			}
		}
		return r;
	}
}
