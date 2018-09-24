package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;

import java.util.ArrayList;
import java.util.List;

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
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
