import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class FilesInOut3 {
	public static void main(String[] args) throws IOException, ParseException {

		String fileToConvert;

		// Creates Scanner Object for keyboard input

		Scanner keyboard = new Scanner(System.in);

		// get 1st file name

		System.out.print(" Enter the Filename : ");
		String filename1 = keyboard.nextLine();

		// get 2nd file name
		System.out.print(" Enter the Filename : ");
		String filename2 = keyboard.nextLine();

		// Make sure File does not exist

		File file2 = new File(filename2);
		if (file2.exists()) {
			System.out.println(" The file " + filename2 + " already exist. ");
			System.exit(0);
		}

		// open file
		File file = new File(filename1);
		Scanner inputFile = new Scanner(file);
		PrintWriter outputFile = new PrintWriter(filename2);
		

		// add another while loop (or the same) to read the 8 digit int
		// and add "/" to the 2nd and 4th char

		while (inputFile.hasNext()) {
			// Read the next line.
			fileToConvert = inputFile.nextLine();
			String[] tokens = fileToConvert.split("\\s");
			fileToConvert = "";

			for (int i = 0; i < tokens.length - 1; i++) {
				char capLetter = Character.toUpperCase(tokens[i].charAt(0));
				fileToConvert += " " + capLetter + tokens[i].substring(1);

			}
			System.out.println(tokens[2]);
			
			

			// Convert to upper case

			// Outputs to file
			outputFile.println(fileToConvert);

			// Code used to test the output
			System.out.println(fileToConvert);
		}
		// Closes input and output file
		inputFile.close();
		outputFile.close();
		keyboard.close();
	}
}
