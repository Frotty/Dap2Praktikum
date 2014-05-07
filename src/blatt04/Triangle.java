package blatt04;

public class Triangle extends Simplex {

	public Triangle(Point[] points) {
		super(2, points);
	}

	@Override
	public boolean validate() {
		if(dim() != 3) {
			return false;
		}
		for(int i = 0; i < dim(); i++) {
			if(get(i).dim() != 2) {
				return false;
			}
		}
		return true;
	}

}
