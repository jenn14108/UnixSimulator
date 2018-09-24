package cs131.pa1.filter.sequential.filters;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * This class imitates the Grep command used in a shell 
 * @author Jenn
 */
public class GrepFilter extends SequentialFilter{
	
	private static String name;
	private static String searchTerm;
	
	/*
	 * Initializes a new instance of the grep filter with the 
	 * appropriate search term
	 */
	public GrepFilter(String searchTerm) {
		String[] splitCommandAndParam = searchTerm.split(" ");
		this.name = splitCommandAndParam[0];
		if (splitCommandAndParam.length == 1) {
			this.searchTerm = null;
		} else {
			this.searchTerm = splitCommandAndParam[1];
		}
	}
	
	@Override 
	/**
	 * This method overrides process() to print out the appropriate 
	 * error message if there is no input. 
	 */
	protected void process() {
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(this.name));
		}
		//proceed with the process method
		super.process();
	}
	
	
	@Override
	protected String processLine(String line) {
		if (this.searchTerm == null) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(this.name));
			return null;
		} 
		//if input line contains the searchterm - return the line
		if (line.contains(this.searchTerm)) {
			return line;
		}
		return null;
	}

}
