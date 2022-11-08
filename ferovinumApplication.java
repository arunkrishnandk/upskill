package ferovinum_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import com.opencsv.*;
import java.util.regex.Pattern;

public class ferovinumApplication {

	public static void main(String[] args) {

		readCsvFile();

	}

	private static void readCsvFile() {
		// This can be done by adding opencsv dependency as well
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		final File[] uploader = new File[1];
		Scanner sc = new Scanner(System.in);
		System.out.println("please provide a file");
		// Reading file from command line
		String filepath = sc.nextLine();
		File file = new File(filepath);
		uploader[0] = file;
		if (null != file) {
			try {
				Scanner fileScanner = new Scanner(file);
				fileScanner.useDelimiter(",");
				while (fileScanner.hasNext()) {
					String cell = sc.next();
					char[] chars = cell.toCharArray();
					String value = "";
					// Number only values
					if (!cell.contains(" ") && isNumeric(cell, pattern)) {
						value = cell;
						// Numbers without arithmetic operators
					} else if (cell.contains(" ") && isNumeric(cell, pattern)) {
						value = "###";
						// With Numbers and operators
					} else if (checkArithmetic(cell) && isNumeric(cell.substring(0, cell.length() - 2), pattern)) {
						String sub = cell.substring(0, cell.length() - 2);
						String[] numbers = sub.split("[' ']");
						String stripped = cell.strip();
						char op = stripped.charAt(stripped.length() - 1);
						int total = 0;
						for (String num : numbers) {
							// Additio
							if ("+".equals(op)) {
								total = total + Integer.valueOf(num);
							} else if ("-".equals(op)) {
								total = total - Integer.valueOf(num);
							} else if ("*".equals(op)) {
								total = total * Integer.valueOf(num);
							} else {
								total = total / Integer.valueOf(num);
							}
						}
						value = String.valueOf(total);
					}

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	// To check string contains Arithmetic operator
	private static boolean checkArithmetic(String cell) {
		String stripped = cell.strip();
		char op = stripped.charAt(stripped.length() - 1);
		return String.valueOf(op).matches("[+,-,*,/]");
	}

	// To check Numeric
	public static boolean isNumeric(String strNum, Pattern pattern) {

		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}
}
