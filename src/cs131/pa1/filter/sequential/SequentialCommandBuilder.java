package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;
import java.util.*;
import cs131.pa1.filter.sequential.filters.*;


public class SequentialCommandBuilder {
	private static String newCommand;
	public static List<ModifiedSequentialFilter> createFiltersFromCommand(String command){
		newCommand = command;
		List<ModifiedSequentialFilter> filters = new ArrayList<>();
		ModifiedSequentialFilter redFilter = null; 
		
		if (command.contains(">")) {
			String redir = determineRedirectFilter(command);
			redFilter = removeRedirectFilter(command, redir);
		}
		
		String[] commandSplit = newCommand.split("\\|");
		for (String inputStr : commandSplit) {
			inputStr = inputStr.trim();
			String[] actualCommand = inputStr.split(" ");
			String c = actualCommand[0];
			if (c.length() != 0 && !(c.equals("pwd") || c.equals("ls") || c.equals("cd") ||
					c.equals("cat") || c.equals("grep") || c.equals("wc") ||
					c.equals("uniq"))){
				System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(inputStr));
				return null;
			}
			filters.add(constructFilterFromSubCommand(inputStr));
		}
		
		if(filters == null || filters.size() == 0) {
			if (redFilter != null) {
				filters.add(redFilter);
				return filters;
			}
		}
		if (redFilter != null) {
			filters.add(redFilter);
		}
		linkFilters(filters);
		return filters;
	}
	
	private static String determineRedirectFilter(String command){
		return command.substring(command.lastIndexOf(">"), command.length());
	}

	private static ModifiedSequentialFilter removeRedirectFilter(String command, String redir){
		newCommand = command.substring(0, command.lastIndexOf(">"));
		return new RedirFilter(redir);
	}


	private static ModifiedSequentialFilter constructFilterFromSubCommand (String subCommand){
		String[] commandAndParam = subCommand.split(" ");

		switch(commandAndParam[0]) {
			case "cat":
				return new CatFilter(subCommand);
			case "cd":
				return new CdFilter(subCommand);
			case "grep":
				return new GrepFilter(subCommand);
			case "ls":
				return new LsFilter(subCommand);
			case "pwd":
				return new PwdFilter(subCommand);
			case "redir":
				return new RedirFilter(subCommand);
			case "uniq":
				return new UniqFilter(subCommand);
			case "wc":
				return new WcFilter(subCommand);
		}
		return null;
	}

	private static void linkFilters(List<ModifiedSequentialFilter> filters){
		Iterator<ModifiedSequentialFilter> iterator = filters.iterator();
		ModifiedSequentialFilter prev = null;

		if(iterator.hasNext()) {
			prev = iterator.next();
			//System.out.println("Outside whileloop prev: " + prev);
			while(iterator.hasNext()) {
				ModifiedSequentialFilter curr = iterator.next();
				System.out.println(prev);
				if (prev != null) prev.setNextFilter(curr);
				if (curr != null) curr.setPrevFilter(prev);
				prev = curr;
			}
		}
	}
}
