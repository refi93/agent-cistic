
/* stitches together all percepted data */
public class GlobalPerceptMap {
	int[][] map;
	int map_size_actual, percept_size;
	
	public GlobalPerceptMap(int percept_size){
		this.map_size_actual = 40;
		this.percept_size = percept_size;
		map = new int[map_size_actual * 2][map_size_actual * 2];
		
		//initialize map to walls
		for (int i = 0; i < map_size_actual * 2; i++){
			for (int j = 0; j < map_size_actual * 2; j++){
				map[i][j] = 2;
			}
		}
	}
	
	public int[][] get(){
		return map;
	}
	
	// based on current agent state and it's current percept we update the map
	public void update(AgentState curState, int[][] curPercept){
		for (int i = 0; i < 2 * percept_size; i++){
			for (int j = 0; j < 2 * percept_size; j++){
				map[curState.pos.r - percept_size + i][curState.pos.c - percept_size + j] = curPercept[i][j];
			}
		}
	}
	
	public String toString(){
		StringBuilder ret = new StringBuilder();
		for (int i = 0;i < map_size_actual * 2; i++){
			for (int j = 0; j < map_size_actual * 2; j++){
				if (map[i][j] == 0){
					ret.append('0');
				}
				else if (map[i][j] == 1){
					ret.append('1');
				}
				else{
					ret.append('-');
				}
			}
			ret.append('\n');
		}
		return ret.toString();
	}
}
