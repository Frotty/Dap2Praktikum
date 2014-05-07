package blatt04;

import java.util.Random;

import javax.naming.ldap.Rdn;

public class Application {
	public static void main(String[] args) {
		try {
			run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void run(String[] args) {
		Point[] points = new Point[3];
		if(args.length == 0) {
			Random rndGen = new Random();
			for(int i = 0; i < 6; i+=2) {
				points[i/2] = new Point(2, rndGen.nextInt(2000) -1000, rndGen.nextInt(2000) -1000);
			}
			
			
		} else if (args.length == 6) {
			try {
				for(int i = 0; i < 6; i+=2) {
					points[i/2] = new Point(2, Integer.parseInt(args[i]), Integer.parseInt(args[i+1]));
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				System.err.println("Parameter müssen vom typ int sein");
			}
		} else {
			throw new IllegalArgumentException("Nur 0 oder 6 Parameter erlaubt");
		}
		
		Triangle t = new Triangle(points);
		assert t.validate() : "Triangle t ist kein gültiges Dreieck";
		System.out.println("Umfange des Dreiecks: " + t.perimeter());
	}
}
