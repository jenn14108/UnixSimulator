package cs131.pa1.filter.sequential.filters;

<<<<<<< HEAD
import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.*;
import cs131.pa1.filter.sequential.*;
=======
import java.util.LinkedList;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;
>>>>>>> origin/master

public class CdFilter extends SequentialFilter {

	private String[] components;
	private String name;
<<<<<<< HEAD
	private String newDirc;
	private String currDirc = SequentialREPL.currentWorkingDirectory;
=======
	private String directory;
>>>>>>> origin/master
	
	public CdFilter(String subCommand) {
		this.components = subCommand.split(" ");	
		this.name = components[0];
	}
	
	public void process() {
<<<<<<< HEAD
		if(next != null) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(name));
			return; 
		} else if (prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(name));
			return; 
=======
		if(prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(name));
			return; 
		} else if (next != null) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(name));
			return; 
>>>>>>> origin/master
		} else if (components.length < 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(name));
			return; 
		}
<<<<<<< HEAD
		newDirc = components[1];
		if (newDirc.equals(".")) {
			return;
		} else if (newDirc.equals("..")) {
			int firstSepr = currDirc.indexOf(Filter.FILE_SEPARATOR);
			int lastSepr = currDirc.lastIndexOf(Filter.FILE_SEPARATOR);
			
			if(!currDirc.equals(Filter.FILE_SEPARATOR)){
				SequentialREPL.currentWorkingDirectory = currDirc.substring(0, lastSepr); 
			}
			return;
		}
		currDirc += Filter.FILE_SEPARATOR + newDirc; 
		File dirc = new File(newDirc);
		
		if (!dirc.isDirectory()) {
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(newDirc));
		} else {
			SequentialREPL.currentWorkingDirectory = dirc.getAbsolutePath();
		}
	}
=======
		directory = components[1];
		if (directory.equals(".")) {
			return;
		} else if (directory.equals("..")) {
			int firstSepr = SequentialREPL.currentWorkingDirectory.indexOf(Filter.FILE_SEPARATOR);
			int lastSepr = SequentialREPL.currentWorkingDirectory.lastIndexOf(Filter.FILE_SEPARATOR);
			
		}
	}
	
>>>>>>> origin/master
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}