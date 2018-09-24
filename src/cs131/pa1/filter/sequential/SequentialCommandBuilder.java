package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;
import java.util.*;
import cs131.pa1.filter.sequential.filters.*;


public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		List<SequentialFilter> filters = new ArrayList<>();
		String[] commandSplit = command.split("\\|");
		for (String inputStr : commandSplit) {
			inputStr = inputStr.trim();
			String[] actualCommand = inputStr.split(" ");
			String c = actualCommand[0];
			if (!(c.equals("pwd") || c.equals("ls") || c.equals("cd") ||
					c.equals("cat") || c.equals("grep") || c.equals("wc") ||
					c.equals("uniq") || c.equals(">"))){
				System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(c));
				return null;
			}
			constructFilterFromSubCommand(inputStr);			
		}
		return filters;
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
