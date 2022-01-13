// @Author: Vincenzo D'Aria
// Parses and interprets Covid-19 data from a file

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Tracker {
	//Main method
	public static void main(String[] args) {
		try {
			//import in the CSV data file
			Scanner data = new Scanner(new File("CovidData2021.csv"));
			//Header For Program Display
			System.out.println("\n\t\t\t\t\t\t       Covid-19 Data Dashboard");
			System.out.println("\t\t\t\t\t\t       -----------------------\n");
			//Variables for # of positive & negative individuals
			int posCount = 0;
			int negCount = 0;
			//Perform the following actions while the file has another line
			while (data.hasNextLine()) {
				//Each line is split up, delimited, and put into an array for parsing
				String line = data.nextLine();
				String covidData[] = line.split(",");
				//The third column contains the test results
				String testResult = covidData[2];
				//Increments the # of positive and negative people based on what the result is in testResult
				for (int i = 0; i < testResult.length(); i++) {
					if (testResult.equals("P")) {
						posCount++;
					} else if (testResult.equals("N")) {
						negCount++;
					} else {
						//Do Nothing
					}
				}
			}
			//@formatter:off
			//Formats a decimal number to 2 decimal places
			DecimalFormat df = new DecimalFormat("###.##");
			//Variable for the total amount of people tested
			double totalCount = posCount + negCount;
			//Column headers
			System.out.println("\t\t\t# Positive\t\t" + "# Negative\t\t" + "% Negative\t\t" + "% Positive");
			System.out.println("");
			//Data results printed out
			System.out.println("\t\t\t" + "  " + posCount + "\t\t\t   " + negCount + "\t\t\t  "+ df.format((negCount / totalCount) * 100) + "%\t\t  "+ df.format((posCount / totalCount) * 100) + "%");
			System.out.println("\n\n");
			
		//If a file for parsing isn't present, catch the exception and print error message
		} catch (FileNotFoundException notFound) {
			//Error Message for missing File
			System.out.println("Error 0x01: File Not Found");
		}
	}
}