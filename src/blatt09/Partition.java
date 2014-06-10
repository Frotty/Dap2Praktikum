package blatt09;
import java.util.ArrayList;
import java.util.Arrays;

public class Partition {

	public static void main(String[] args) {
		if( args.length < 2) {
			throw new IllegalArgumentException("Mindestens 2 Parameter benötigt");
		}else {
			ArrayList<Integer> ints = new ArrayList<Integer>();
			// Eingabe parsen
			for(int i = 0; i < args.length; i++) {
				try {
					ints.add(Integer.parseInt(args[i]));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("Die Parameter müssen von Typ int sein");
				}
			}
			// Partition Testen
			if(partition(ints)) {
				System.out.println("Die Zahlen: " + Arrays.asList(ints) + " sind partionierbar.");
			}else{
				System.out.println("Die Zahlen: " + Arrays.asList(ints) + " sind nicht partionierbar.");
			}
		}
		
	}
	
	public static boolean partition(ArrayList<Integer> m) {
		int w = 0;
		// Addiere alle zahlen
		for( int i : m ) {
			w += i;
		}
		// Wenn die Summe der zahlen gerade ist wird geprüft ob sie
		// partionierbar sind
		if((w & 1) == 0) {
			boolean[][] g = new boolean[m.size()+1][w+1];
			for(int i = 0; i <= m.size(); i++) {
				for(int j = 0; j <= w/2; j++) {
					if(j == 0) {
						g[i][j] = true;
					}else if (i == 0) {
						g[i][j] = false;
					}else if (g[i-1][j] == true || (m.get(i-1) <= j && g[i-1][j-m.get(i-1)] == true)) {
						g[i][j] = true;
					}else {
						g[i][j] = false;
					}
				}
			}
			return g[m.size()][w/2];
		}
		return false;
	}
}
