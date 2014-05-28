package blatt06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
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
			sc.useDelimiter("\r\n|[\n\r\u2028\u2029\u0085]| |,");
		} catch (FileNotFoundException e) {
			System.err.println("Datei konnte nicht gefunden werden");
			e.printStackTrace();
		}
		// Welche Scheduling-Art
		if(args[0].equals("Interval")) {
			ArrayList<Interval> intervals = new ArrayList<>();
			
			while(sc.hasNext()) {
				try {
					intervals.add(new Interval(sc.nextInt(), sc.nextInt()));
				} catch (InputMismatchException e) {
				    //next
				}
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
			ArrayList<Job> jobs = new ArrayList<>();
			while(sc.hasNext()) {
				try {
					jobs.add(new Job(sc.nextInt(), sc.nextInt()));
				} catch (InputMismatchException e) {
				    //next
				}
			}
			StringBuilder sb = new StringBuilder();
			// Ausgabe der eingelesenen intervalle
			sb.append("Eingelesene Intervalle:\n[ ");
			for( Job i : jobs ){
				sb.append(i.toString() + ", ");
			}
			sb.deleteCharAt(sb.length()-2);
			sb.append("]");
			System.out.println(sb.toString());
			// Sortiere die ArrayListe
			Collections.sort(jobs);
			sb = new StringBuilder();
			sb.append("Sortiert:\n[ ");
			for( Job i : jobs ){
				sb.append(i.toString() + ", ");
			}
			sb.deleteCharAt(sb.length()-2);
			sb.append("]");
			System.out.println(sb.toString());
			// Schedulieren
			int[] svals = latenessScheduling(jobs);
			sb = new StringBuilder();
			sb.append("Latenessscheduling:\n");
			sb.append(Arrays.toString(svals) + "\n");
			sb.append("Maximale Verspätung: " + (svals[svals.length-1]+jobs.get(jobs.size()-1).getDuration()-jobs.get(jobs.size()-1).getDeadline()));
			System.out.println(sb.toString());
		}else {
			throw new IllegalArgumentException("Der erste parameter kann nur \"Interval\" oder \"Lateness\" sein.");
		}
		

	}
	
	public static boolean isSortedInterval(ArrayList<Interval> intervals)
	{
	    boolean sorted = true;        
	    for (int i = 1; i < intervals.size(); i++) {
	        if (intervals.get(i-1).compareTo(intervals.get(i)) > 0) sorted = false;
	    }

	    return sorted;
	}
	
	public static boolean isSortedLateness(ArrayList<Job> jobs)
	{
	    boolean sorted = true;        
	    for (int i = 1; i < jobs.size(); i++) {
	        if (jobs.get(i-1).compareTo(jobs.get(i)) > 0) sorted = false;
	    }

	    return sorted;
	}
	
	public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {
		assert isSortedInterval(intervals) : "Liste ist nicht sortiert";
		int n = intervals.size();
		ArrayList<Interval> A = new ArrayList<>();
		A.add(intervals.get(0));
		int j = 0;
		for(int i = 1; i < n; i++) {
			if( intervals.get(i).getStart() >= intervals.get(j).getEnd()) {
				A.add(intervals.get(i));
				j = i;
			}
		}
		return A;
		
	}
	
	public static int[] latenessScheduling(ArrayList<Job> jobs) {
		assert isSortedLateness(jobs) : "Liste nicht sortiert";
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
