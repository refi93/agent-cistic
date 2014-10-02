import java.util.Iterator;


public class AgentStateIterator implements Iterator{

	AgentState current;
	int counter;
	
	public AgentStateIterator(AgentState first){
		super();
		current = first;
		counter = 0;
	}
	
	@Override
	public boolean hasNext() {
		return counter < 8;
	}

	@Override
	public AgentState next() {
		AgentState ret = current;
		if (counter++ % 2 == 1){ // on every even iteration we rotate the iterator
			current = current.rotate();
		}
		else{
			ret = current.getFW(); // on every odd iteration we return the move forward
		}
		return ret;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
