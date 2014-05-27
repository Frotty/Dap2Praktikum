package blatt06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Anwendung {

	public static void main(String[] args) {
		if(args.length != 2) {
			throw new IllegalArgumentException("Es müssen genau 2 Parameter angegeben werden");
		}
		File f = new File(args[1]);
		Scanner sc = null;
		// Scanner erstellen und gucken ob die Datei existiert
		try {
			sc = new Scanner(f);
			sc.useDelimiter(" |\\n|=");
		} catch (FileNotFoundException e) {
			System.err.println("Datei konnte nicht gefunden werden");
			e.printStackTrace();
		}
		// Welche Scheduling-Art
		if(args[0].equals("Interval")) {
			ArrayList<Interval> intervals = new ArrayList<>();
			while(sc.hasNext()) {
				intervals.add(new Interval(sc.nextInt(), sc.nextInt()));
			}
			StringBuilder sb = new StringBuilder();
			// Ausgabe der eingelesenen intervalle
			sb.append("Eingelesene Intervalle:\n[ ");
			for( Interval i : intervals ){
				sb.append(i.toString() + ", ");
			}
			sb.deleteCharAt(sb.length()-2);
			sb.append("]");
			System.out.println(sb.toString());
			// Sortiere die ArrayListe
			Collections.sort(intervals);
			sb = new StringBuilder();
			sb.append("Sortiert:\n[ ");
			for( Interval i : intervals ){
				sb.append(i.toString() + ", ");
			}
			sb.deleteCharAt(sb.length()-2);
			sb.append("]");
			System.out.println(sb.toString());
			// Schedulieren
			ArrayList<Interval> svals = intervalScheduling(intervals);
			sb = new StringBuilder();
			sb.append("Intervallscheduling:\n[ ");
			for( Interval i : svals ){
				sb.append(i.toString() + ", ");
			}
			sb.deleteCharAt(sb.length()-2);
			sb.append("]");
			System.out.println(sb.toString());
		}else if(args[0].equals("Lateness")) {
			ArrayList<String> jobs = new ArrayList<>();
			while(sc.hasNext()) {
				if(sc.next().equals("integer"))
					jobs.add(sc.next());
			}
			StringBuilder sb = new StringBuilder();
			// Ausgabe der eingelesenen intervalle
			sb.append("init:\n");
			int count = 0;
			for( String i : jobs ){
				sb.append("\torderStrings[" +count + "] = \"" + i + "\"\n");
				count++;
			}
			System.out.println(sb.toString());
			// Sortiere die ArrayListe
			Collections.sort(jobs);
			sb = new StringBuilder();
			sb.append("Sortiert:\n[ ");
			sb = new StringBuilder();
			sb.append("Latenessscheduling:\n");
			System.out.println(sb.toString());
		}else {
			throw new IllegalArgumentException("Der erste parameter kann nur \"Interval\" oder \"Lateness\" sein.");
		}
		

	}
	
	public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {
		int n = intervals.size();
		ArrayList<Interval> A = new ArrayList<>();
		int j = 0;
		for(int i = 1; i < n; i++) {
			if( intervals.get(i).getEnd() >= intervals.get(j).getStart()) {
				A.add(intervals.get(i));
				j = i;
			}
		}
		return A;
		
	}
	
	public static int[] latenessScheduling(ArrayList<Job> jobs) {
		int n = jobs.size();
		int[] A = new int[n];
		int z = 0;
		for(int i = 0; i < n; i++) {
			A[i] = z;
			z += jobs.get(i).getDuration();
		}
		return A;
		
	}

}
