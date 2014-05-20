package blatt06;

public class Interval implements Comparable<Interval>{
	private int start, end;

	public Interval(int start, int end) {
		if(start >= end) {
			throw new IllegalArgumentException("start must be smaller than end");
		}
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String toString() {
		return "[" + start + ".." + end + "]";
	}

	public int getLength() {
		return end-start;
	}

	@Override
	public int compareTo(Interval o) {
		if(this.end > o.end) {
			return 1;
		} else if(this.end == o.end) {
			return 0;
		}else if (this.end < o.end){
			return -1;
		}
		return -1;
	}
	
	
}
