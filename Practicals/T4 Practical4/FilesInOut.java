import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tutor
 */

// get 1st file name
/*
 * System.out.print("Supply the filename for input: "); try { String filename =
 * keyboard.nextLine(); inputFile = new File(filename); Scanner filename2 = new
 * Scanner(inputFile); } catch (IOException e) {
 * System.err.println("IOException: " + e.getMessage() + " not found"); }
 * 
 * System.out.println("Supply the filename for input: "); try {
 * 
 * filename1 = new PrintWriter(filename1); } catch (FileNotFoundException e) {
 * System.err.println("FileNotFoundException: " + e.getMessage() +
 * " not openable"); System.exit(0); }
 */

public class FilesInOut {
	public static void main(String[] args) throws IOException {

		String upperCase;
		String fileToConvert;
		String filename1 = null;
		String filename2 = null;
		PrintWriter outputFile;

		// Creates Scanner Object for keyboard input

		Scanner keyboard = new Scanner(System.in);

		// get 1st file name

		System.out.print(" Enter the Filename : ");
		//String filename1 = keyboard.nextLine();

		// get 2nd file name
		
		try {
			filename1 = keyboard.nextLine();
		File file = new File(filename1);
		Scanner inputFile = new Scanner (file);
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage() + " not found");
		}
		
		System.out.println(" Enter the Filename : ");
		try { 
			outputFile = new PrintWriter(filename1);
		}
		catch 
			(FileNotFoundException e)
			{ 
				System.err.println("FileNotFoundExcetion: " + e.getMessage() + " not openable");// Make sure File does not exist
			System.exit(0);
			
		}		
		
		System.out.print(" Enter the Filename : ");
		File file2 = new File(filename2);
		if (file2.exists()) {
			System.out.println(" The file " + filename2 + " already exist. ");
			System.exit(0);
		}

		// open file
		File file = new File(filename1);		
		Scanner inputFile = new Scanner(file);
		outputFile = new PrintWriter(filename2);

		fileToConvert = inputFile.nextLine();
		upperCase = fileToConvert.toUpperCase();

		while (inputFile.hasNext()) {
			// Read the next line.
			fileToConvert = inputFile.nextLine();
			// Convert to upper case
			upperCase = fileToConvert.toUpperCase();
			// Outputs to file
			outputFile.println(upperCase);

			// Code used to test the output
			System.out.println(upperCase);
		}
		// Closes input and output file
		inputFile.close();
		outputFile.close();
		keyboard.close();

	}
}
