package cs131.pa1.filter.sequential;


public abstract class ModifiedSequentialFilter extends SequentialFilter{
	protected String[] components;
	protected boolean cont;
	protected boolean contForCat = true;
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
