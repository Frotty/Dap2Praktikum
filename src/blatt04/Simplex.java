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
    	if(index <= dimension && index >= 0) {
    		return points[index];
    	}else {
    		throw new IndexOutOfBoundsException("index: " + index + " is out of Dimension-Bounds: " + dimension);
    	}
    }
    
    public double perimeter() {
    	double sum = 0;
    	EuclidDistance dist = new EuclidDistance();
    	// Distanz von den Kanten
    	for(int i = 0; i <= dimension; i++) {
    		for(int j = i+1; j <= dimension; j++) {
    			sum += dist.distance(get(i), get(j));
    		}
    	}
    	return sum;
    }
    
	public abstract boolean validate();
}
