package blatt04;

public abstract class Simplex {
	private Point[] points;
	private int dimension;
	
	public Simplex(int dimension, Point... points) {
		this.dimension = dimension;
		if(points.length != dimension + 1) {
			throw new IllegalArgumentException("Ungültige Anzahl Punkte");
		}else {
			this.points = points;
		}
	}
	
	public int dim() {
    	return dimension;
    }
        
    public Point get(int index){
    	if(index <= dimension) {
    		return points[index];
    	}else {
    		throw new IndexOutOfBoundsException("index: " + index + " is out of Dimension-Bounds: " + dimension);
    	}
    }
    
    public double perimeter() {
    	double sum = 0;
    	EuclidDistance dist = new EuclidDistance();
    	for(int i = 0; i < dimension; i++) {
    		sum += dist.distance(get(i), get(i+1));
    	}
    	sum += dist.distance(get(0), get(dim()));
    	return sum;
    }
    
	public abstract boolean validate();
}
