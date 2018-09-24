package cs131.pa1.filter.sequential;

import java.util.*;


import cs131.pa1.filter.sequential.filters.*;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		List
	}
	
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand (String subCommand){
		
		switch(subCommand) {
			case "cat":
				return new CatFilter();
			case "cd":
				return new CdFilter();
			case "grep":
				return new GrepFilter();
			case "ls":
				return new LsFilter();
			case "pwd":
				return new PwdFilter();
			case "redir":
				return new RedirFilter();
			case "uniq":
				return new UniqFilter();
			case "wc":
				return new WcFilter();
		}		
		return null;
	}

	private static void linkFilters(List<SequentialFilter> filters){
		Iterator<SequentialFilter> iterator = filters.iterator();
		SequentialFilter prev = null;
		
		if(iterator.hasNext()) {
			prev = iterator.next();
			while(iterator.hasNext()) {
				SequentialFilter curr = iterator.next();
				if (prev != null) prev.setNextFilter(curr);
				if (curr != null) curr.setPrevFilter(prev);
				prev = curr; 
			}
		}
	}
}
