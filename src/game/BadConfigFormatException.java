package game;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() {}
	public BadConfigFormatException(int i) throws FileNotFoundException{
		String msg = "";
		switch(i){
		case 0:
			msg = "Error, not all rows have same number of columns";
			break;
		case 1:
			msg = "Error, row of legend file doesn't have exactly 2 items";
			break;
		case 2:
			msg = "Error, room has inital that is not a valid room";
			break;
		case 3:
			msg = "Error, hallway has a door";
		}
		PrintWriter ErrorLog = new PrintWriter("logfile.txt");
		ErrorLog.println(msg);
		ErrorLog.close();
	}
}
