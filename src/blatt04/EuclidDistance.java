package blatt04;

public class EuclidDistance implements Distance{

	@Override
	public double distance(Point p1, Point p2) {
		if(p1.dim() != p2.dim()) {
			throw new IllegalArgumentException("Can only compute distance between Points with same dimension");
		} else {
			double sum = 0;
			for(int i = 0; i < p1.dim(); i++) {
				sum+= Math.pow((p1.get(p1.dim()-i-1) - p2.get(p1.dim()-i-1)), 2);
			}
			return Math.sqrt(sum);
		}
	}

}
