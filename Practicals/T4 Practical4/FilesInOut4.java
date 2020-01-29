import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class FilesInOut4 {
	public static void main(String[] args) throws IOException, ParseException {

		String fileToConvert;

		// Creates Scanner Object for keyboard input

		Scanner keyboard = new Scanner(System.in);

		// get 1st file name

		System.out.print("Enter the input Filename : ");
		String filename1 = keyboard.nextLine();

		// get 2nd file name
		System.out.print("Enter the output Filename : ");
		String filename2 = keyboard.nextLine();

		// Make sure File does not exist

		File file2 = new File(filename2);
		if (file2.exists()) {
			System.out.println("The file " + filename2 + " already exist. ");
			System.exit(0);
		}

		// open file
		File file = new File(filename1);
		Scanner inputFile = new Scanner(file);
		PrintWriter outputFile = new PrintWriter(filename2);

		while (inputFile.hasNext()) {
			// Read the next line.
			fileToConvert = inputFile.nextLine();
			String[] tokens = fileToConvert.split(" "); // read the line and split it where are " " (spaces)
			fileToConvert = ""; // so now we have every word into an array of tokens from each line

			for (int i = 0; i < tokens.length - 1; i++) {
				char capLetter = Character.toUpperCase(tokens[i].charAt(0));
				fileToConvert += " " + capLetter + tokens[i].substring(1);
			} // take the each token (word) and capitalize the 1st char of each word

			int slashInterval = 2;

			String withSlashes = tokens[2].substring(0, slashInterval);
			for (int i = slashInterval; i < 4; i += slashInterval) {
				withSlashes += "/" + tokens[2].substring(i, i + slashInterval);
				// take the 3rd token which is the date and put slashes in every 2nd
			} // char except the last one

			String withSlashes2 = tokens[2].substring(8);
			withSlashes2 += "/" + tokens[2].substring(4);
			// put another slash to the 6th char separate from the array

			// Outputs to file
			outputFile.println(fileToConvert + " " + withSlashes + withSlashes2);

			// Code used to test the output
			System.out.println(fileToConvert + " " + withSlashes + withSlashes2);
		}
		// Closes input and output file
		inputFile.close();
		outputFile.close();
		keyboard.close();
	}
}
