package ferovinum_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import com.opencsv.*;

public class ferovinumApplication {
	
	public static void main (String[] args) {
		
		
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
				while(fileScanner.hasNext()) {
					System.out.println(sc.next());
					
				}
				  
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }

	}
}
