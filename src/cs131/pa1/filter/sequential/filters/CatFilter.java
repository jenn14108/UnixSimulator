package cs131.pa1.filter.sequential.filters;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.*;
import java.io.*;

public class CatFilter extends ModifiedSequentialFilter {
	
	private String[] files;
	private String subCommand;
	
	public CatFilter(String commandAndParam) {
		input = new LinkedList<>();
		output = new LinkedList<>();
		subCommand = commandAndParam;
		cont = false;
		contForCat = true;
		String[] splitCommandAndParam = commandAndParam.split(" ");
		
		files = new String[splitCommandAndParam.length -1];
		//add all files into an array
		for (int i = 1; i < splitCommandAndParam.length; i++) {
			this.files[i-1] = splitCommandAndParam[i];
		}

	}
	
	@Override
	public void process() {
		boolean error = false;
		//if there is input, which there shouldn't be, throw error
		if (!input.isEmpty()) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(subCommand));
			return;
		//if there are no parameters, throw error
		} else if (files.length == 0) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(subCommand));
			return;
		} else {
			
			for (String f : files) {
				File file = new File(f);
				if (!file.exists() && !error) {
					System.out.print(Message.FILE_NOT_FOUND.with_parameter(subCommand));
					error = true;
					contForCat = false;
				} else {
					writeFileToOutput(file);
				}
			}
		}
		cont = true;
	}

	public void writeFileToOutput(File file) {
		try {
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				output.add(line);
			}
			fileScanner.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String processLine(String line) {
		return null;
	}

}
