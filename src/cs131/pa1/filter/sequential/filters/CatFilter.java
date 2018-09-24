package cs131.pa1.filter.sequential.filters;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.*;
import java.io.*;

public class CatFilter extends SequentialFilter {
	
	private static String name;
	private static String[] files;
	
	public CatFilter(String commandAndParam) {
		input = new LinkedList<>();
		output = new LinkedList<>();
		String[] splitCommandAndParam = commandAndParam.split(" ");
		this.name = splitCommandAndParam[0];
		files = new String[splitCommandAndParam.length -1];
		//add all files into an array
		for (int i = 1; i < splitCommandAndParam.length; i++) {
			this.files[i-1] = splitCommandAndParam[i];
		}
	}
	
	@Override
	public void process() {
		//if there is input, which there shouldn't be, throw error
		if (!input.isEmpty()) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(this.name));
		//if there are no parameters, throw error
		} else if (files.length == 0) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(this.name));
		} 
		
		for (String f : files) {
			if (!f.contains(".txt")) {
				System.out.print(Message.INVALID_PARAMETER.with_parameter(this.name));
			} else {
				writeFileToOutput(f);
			}
		}
	}

	public void writeFileToOutput(String f) {
		try {
			File file = new File(f);
			if (!file.exists()) {
				output.add(Message.FILE_NOT_FOUND.with_parameter(this.name));
			} else {
				Scanner fileScanner = new Scanner(file);
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					output.add(line);
				}
				fileScanner.close();
				output.add("------------");
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String processLine(String line) {
		return null;
	}

}
