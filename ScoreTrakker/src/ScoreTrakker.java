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

	public void loadDataFromFile(String fileName) throws FileNotFoundException{
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
			}
			catch(NumberFormatException n){
				System.out.println("Incorrect format for " + name + " not a valid score: " + n.getMessage());
			}
	}

	public void printInOrder(){
		Collections.sort(students);
		for(Student s: students)
			System.out.println(s);
		
	}
	
	public void processFiles(){
		try{
			loadDataFromFile("scores.txt");
		}catch(FileNotFoundException e){
			System.out.println("Can't open file: " + e.getMessage());
		}
		printInOrder();
	}
	
	public static void main(String[] args) {
		ScoreTrakker score = new ScoreTrakker();
		score.processFiles();
	}
}
