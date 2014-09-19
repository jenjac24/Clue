import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ScoreTrakker {
	private ArrayList<Student> students;
	private String[] files = {"scores.txt", "badscore.txt", "nofile.txt", "badname.txt" };

	public ScoreTrakker() {
		// TODO Auto-generated constructor stub
	}

	public void loadDataFromFile(String fileName) throws FileNotFoundException, Exception{
		students = new ArrayList<Student>();

		FileReader read = new FileReader(fileName);
		Scanner scan = new Scanner(read);
		String strNum = "", name = "";
		int score = 0;
		while(scan.hasNextLine()){
			try {
				name = scan.nextLine();
				boolean space = false;
				for(int i = 0; i<name.length(); i++){
					char c = name.charAt(i);
					if(c == ' ')
						space = true;
				}
				if(!space){
					throw new Exception("'" + name + "'" + " does not include a first and last name");
					
					//System.out.println(name + " does not include a first and a last name");
				}
				space = false;
				strNum = scan.nextLine();
				score = Integer.parseInt(strNum);
				students.add(new Student(name, score));
			}

			catch(NumberFormatException n){
				System.out.println("Incorrect format for " + name + " not a valid score: " + n.getMessage());
				System.out.println();
			}
		}
	}

	public void printInOrder(){
		System.out.println("Student Score List");
		Collections.sort(students);
		for(Student s: students)
			System.out.println(s);
		System.out.println();

	}

	public void processFiles(){
		for(String file: files){
			try{
				loadDataFromFile(file);
				printInOrder();
			}catch(FileNotFoundException e){
				System.out.println("Can't open file: " + e.getMessage());
			}catch(Exception ex){
				System.out.println(ex.getMessage());
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		ScoreTrakker score = new ScoreTrakker();
		score.processFiles();
	}
}
