import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ScoreTrakker {
	private ArrayList<Student> students;

	public ScoreTrakker() {
		// TODO Auto-generated constructor stub
	}

	public void loadDataFromFile(String fileName){
		students = new ArrayList<Student>();

			FileReader read = null;
			Scanner scan = null;
			Scanner scanInt = null;
			String strNum = "", name = "";
			int score = 0;
			try {
				read = new FileReader(fileName);
				scan = new Scanner(read);
				while(scan.hasNextLine()){
					name = scan.nextLine();
					strNum = scan.nextLine();
					score = Integer.parseInt(strNum);
					students.add(new Student(name, score));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("You have not entered a valid file name");
			}
	}

	public void printInOrder(){
		Collections.sort(students);
		for(Student s: students)
			System.out.println(s);
		
	}
	
	public void processFiles(){
		loadDataFromFile("scores.txt");
		printInOrder();
	}
	
	public static void main(String[] args) {
		ScoreTrakker score = new ScoreTrakker();
		score.processFiles();
	}
}
