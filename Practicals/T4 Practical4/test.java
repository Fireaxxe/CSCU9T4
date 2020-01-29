import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;


public class test {
	
	static String inputFileName = null;
	static PrintWriter outputFile;
	static String filename = null;


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	

	System.out.println("supply filename for input:");
	try {
	inputFileName= in.nextLine();
	File inputFile = new File(inputFileName) ;
	Scanner inFile = new Scanner(inputFile);
	} catch (IOException e) {
	System.err.println("IOException: " +
	e.getMessage() + " not found");
	}
	System.out.println("supply filename for input:");
	try {
	outputFile = new PrintWriter(filename);
	} catch (FileNotFoundException e) {
	System.err.println("FileNotFoundException: " +
	e.getMessage() + " not openable");
	System.exit(0);
	}
	}
	
}
