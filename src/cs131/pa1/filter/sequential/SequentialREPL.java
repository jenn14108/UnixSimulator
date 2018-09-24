package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;
import java.util.*;

public class SequentialREPL {

	public static String currentWorkingDirectory;

	public static void main(String[] args){
		//set current working directory
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner input = new Scanner(System.in);
		System.out.print(Message.NEWCOMMAND.toString() + Message.WELCOME.toString());
		System.out.print(Message.NEWCOMMAND.toString());
		
		//obtain user commands
		String commands = input.nextLine();
		//System.out.println("This is the command: " + commands);
		
		//shell runs until user command equates to "exit" 
		while (!commands.equals("exit")) {
			
			//Make a new sequential filter
			List<SequentialFilter> filters = SequentialCommandBuilder.createFiltersFromCommand(commands);
//			for (SequentialFilter f : filters) {
//				System.out.println(f);
//			}
			
			//After completing current commands, ask for a new set of commands
			System.out.print(Message.NEWCOMMAND.toString());
			commands = input.nextLine();

		}
		
		//user commands to exit shell 
		input.close();
		System.out.println(Message.GOODBYE.toString());
	}
}
