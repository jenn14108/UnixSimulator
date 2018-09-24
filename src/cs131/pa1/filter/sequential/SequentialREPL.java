package cs131.pa1.filter.sequential;
import cs131.pa1.filter.Message;
import java.util.*;

public class SequentialREPL {

	static String currentWorkingDirectory;

	public static void main(String[] args){
		//set current working directory
		currentWorkingDirectory = System.getProperty("user.dir");
		//System.out.println(currentWorkingDirectory);
		Scanner input = new Scanner(System.in);
		System.out.print(Message.NEWCOMMAND.toString() + Message.WELCOME.toString());
		System.out.print(Message.NEWCOMMAND.toString());
		
		//obtain user commands
		String commands = input.nextLine();
		//System.out.println("This is the command: " + commands);
		
		//shell runs until user command equates to "exit" 
		while (!commands.equals("exit")) {
			
			//1. make a new sequential filter
			//2. ensure commands are VALID (else, print error message) in order to continue
			//3. If valid, move on to the different filters that operates specific commands 
			
			//After completing current commands, ask for a new set of commands
			System.out.print(Message.NEWCOMMAND.toString());
			commands = input.nextLine();

		}
		
		//user commands to exit shell 
		input.close();
		System.out.println(Message.GOODBYE.toString());
		
		
		
		
		
		

		
		

	}

}
