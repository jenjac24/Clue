
package clueGame;

import java.io.FileWriter;
import java.io.IOException;

public class BadConfigFormatException extends Exception {
	
	public BadConfigFormatException(int length){
		FileWriter fout;
		try {
			fout = new FileWriter("BadConfigFormatExceptionLog.txt");
			fout.write("An error was thrown for the input file.  The expected number of rows or columns for the layout of your board was incorrect.");
			fout.write("Your file had " + length + " columns or rows, which is incorrect");
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public BadConfigFormatException(char badChar){
		try {
			FileWriter fout = new FileWriter("BadConfigFormatExceptionLog.txt");
			fout.write("Your file had an incorrect room character.  The room character " + badChar + " is not a valid room.");
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//error reason: all rows don't have same number of columns
	//row of the legend file doesnt have exactly 2 items
	//room that has an initial is not a valid room
	
	//extra credit, write the message to a log file
}
