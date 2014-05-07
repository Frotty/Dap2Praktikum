package blatt04;

import java.util.List;
import java.util.Random;

import javax.naming.ldap.Rdn;

public class Application_Convex {
	public static void main(String[] args) {
		try {
			run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void run(String[] args) {
		if(args.length != 0) {
			throw new IllegalArgumentException();
		}
		Point[] points = new Point[1000];
		Random rndGen = new Random();
		for(int i = 0; i < points.length; i++) {
			points[i] = new Point(2, rndGen.nextInt(2000) -1000, rndGen.nextInt(2000) -1000);
		}
		List<Point> hullPoints = ConvexHull.simpleConvex(points);
		System.out.println("Liste: ");
		for(int i = 0; i < hullPoints.size(); i++) {
			System.out.print(hullPoints.get(i).toString() + ", ");
		}
	}
}
