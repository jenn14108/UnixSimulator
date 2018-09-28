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
		
		//handle redirect separately from other commands
		if (command.contains(">")) {
			String redir = determineRedirectFilter(command);
			command = removeRedirectFilter(command);
			redFilter = createRedirectFilter(redir);
		}
		
		String[] commandSplit = command.split("\\|");
		if (!(commandSplit[0].equals(""))) {
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
		}
		
		if (redFilter != null) {
			filters.add(redFilter);
		}
		linkFilters(filters);
		return filters;
	}
	
	/**
	 * This method determines the chunk of string that contains 
	 * the command and parameters for redirect
	 * @param the full command string typed in by the user
	 * @return the string that contains the command and parameters
	 * for redirect
	 */
	private static String determineRedirectFilter(String command){
		return command.substring(command.lastIndexOf(">"), command.length());
	}

	/**
	 * This method returns the new command string from the user 
	 * with the redirect command and parameters removed
	 * @param the full command string typed in by the user
	 * @return new command string for other filters 
	 */
	private static String removeRedirectFilter(String command){
		return command.substring(0, command.lastIndexOf(">"));
	}
	
	/**
	 * This method creates a filter from the redirect command string
	 * @param string that contains redirect command and parameter
	 * @return a redirect filter
	 */
	private static ModifiedSequentialFilter createRedirectFilter(String redir) {
		return new RedirFilter(redir);
	}

	/**
	 * this methods determines with filter to create based on 
	 * the user command(s)
	 * @param subCommand
	 * @return filters 
	 */
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

	/**
	 * This method links all the filters together
	 * @param filters
	 */
	private static void linkFilters(List<ModifiedSequentialFilter> filters){
		Iterator<ModifiedSequentialFilter> iterator = filters.iterator();
		ModifiedSequentialFilter prev = null;

		if(iterator.hasNext()) {
			prev = iterator.next();
			while(iterator.hasNext()) {
				ModifiedSequentialFilter curr = iterator.next();
				if (prev != null) prev.setNextFilter(curr);
				if (curr != null) curr.setPrevFilter(prev);
				prev = curr;
			}
		}
	}
}
