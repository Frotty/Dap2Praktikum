package blatt06;

public class Job implements Comparable<Job>{
	private int duration, deadline;

	public Job(int duration, int deadline) {
		if(duration >= deadline) {
			throw new IllegalArgumentException("start must be smaller than end");
		}
		this.duration = duration;
		this.deadline = deadline;
	}

	public int getDuration() {
		return duration;
	}

	public int getDeadline() {
		return deadline;
	}

	public String toString() {
		return "[" + duration + ".." + deadline + "]";
	}


	@Override
	public int compareTo(Job o) {
		if(this.deadline > o.deadline) {
			return 1;
		} else if(this.deadline == o.deadline) {
			return 0;
		}else if (this.deadline < o.deadline){
			return -1;
		}
		return -1;
	}
	
	
}
