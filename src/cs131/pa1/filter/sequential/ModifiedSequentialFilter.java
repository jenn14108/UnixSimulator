package cs131.pa1.filter.sequential;

/**
 * This abstract class extends the SequentialFilter class and adds new fields necessary for 
 * implementing Filters needed for the assignment
 */
public abstract class ModifiedSequentialFilter extends SequentialFilter{
	protected String[] components; //array that stores the split sub command
	protected boolean cont; //determines whether to continue if the previous Filter prints an error
	protected boolean contForCat = true; //determines whether to continue of Cat Filter throws an error
	protected String subCommand; //stores the sub command String of the Filter
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
