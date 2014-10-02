import java.util.HashMap;
import java.util.Map;


public class AgentState {
	int orientation;
	Position pos;
	int[][] map;
	String prevAction;
	AgentState prevState;
	
	Map<Integer, Integer> dir_r, dir_c;
	
	public AgentState(Position pos, int orientation, int[][] map, AgentState prevState, String prevAction){
		this.pos = pos;
		this.orientation = orientation;
		this.map = map;
		this.prevAction = prevAction;
		this.prevState = prevState;
		
		dir_r = new HashMap<Integer, Integer>();
		dir_c = new HashMap<Integer, Integer>();
		
		dir_r.put(World.NORTH, -1);
		dir_r.put(World.SOUTH, 1);
		dir_r.put(World.EAST, 0);
		dir_r.put(World.WEST, 0);
		
		dir_c.put(World.NORTH, 0);
		dir_c.put(World.SOUTH, 0);
		dir_c.put(World.EAST, -1);
		dir_c.put(World.WEST, 1);
	}
	
	public AgentState rotate(){
		return new AgentState(this.pos, (orientation + 1) % 4, map, this, "rotate");
	}
	
	public boolean canFW(){
		return ((map[pos.r + dir_r.get(orientation)][pos.c + dir_c.get(orientation)] != 2) && // not a stone 
				(map[pos.r][pos.c] != map[pos.r + dir_r.get(orientation)][pos.c + dir_c.get(orientation)])); // we can transit only between different tiles
	}
	
	public AgentState getFW(){
		if (!canFW()) return null;
		return new AgentState(new Position(pos.r + dir_r.get(orientation), pos.c + dir_c.get(orientation)), orientation, map, this, "forward");
	}
	
	@Override
	  public boolean equals(Object obj) {
	   if(this==obj)
	    return true;
	   AgentState s = (AgentState)obj;
	   return ((s.pos.equals(this.pos)) && (s.orientation == this.orientation));
	  }
	  
	  public int hashCode(){
		  return 0;
	  }
}
