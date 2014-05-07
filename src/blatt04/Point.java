package blatt04;


public class Point
{
     private int[] values;
     private int dimension;
    
    
    public Point(int dimension, int... values){
    	this.dimension = dimension;
    	if(values.length != dimension) {
    		throw new IllegalArgumentException("Invalid amount of parameters");
    	}
        this.values = values;
        
    }
    
    public int dim() {
    	return dimension;
    }
        
    public int get(int index){
    	if(index < dimension) {
    		return values[index];
    	}else {
    		throw new IndexOutOfBoundsException("index: " + index + "is out of Dimension-Bounds: " + dimension);
    	}
    }
    
    public String toString() {
    	String s = "[";
    	for(int i = 0; i < dimension; i++) {
    		s+= get(i) + ",";
    	}
    	s = s.substring(0, s.length()-1);
    	s+="]";
    	return s;
    }
    
}
