
public class Student implements Comparable<Student> {

	private String name;
	private int score;

	public Student(String n, int sc){
		name = n;
		score = sc;
	}

	@Override
	public int compareTo(Student arg0) {
		if(this.score > arg0.score)
			return 1;
		else if(this.score < arg0.score)
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return name + " "  + score;
	}

}
