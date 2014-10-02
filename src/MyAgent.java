import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public  class MyAgent extends Agent{	
	int[][] net;
	
	Tuple<Integer, Integer> dest = new Tuple(20,17);
		
	public MyAgent(int height, int width) {
		
	}
	
	private boolean lookUP(int[][] arr, Tuple<Integer, Integer> pos){
		if ((arr[pos.r - 1][pos.c] != 2) && (arr[pos.r - 1][pos.c] != arr[pos.r][pos.c])) {
			return true;
		}
		return false;
	}
	
	private Tuple<Integer, Integer> getUP(Tuple<Integer, Integer> pos){
		return new Tuple(pos.r - 1, pos.c);
	}
	
	private boolean lookDOWN(int[][] arr, Tuple<Integer, Integer> pos){
		if ((arr[pos.r + 1][pos.c] != 2) && (arr[pos.r + 1][pos.c] != arr[pos.r][pos.c])) {
			return true;
		}
		return false;
	}
	
	private Tuple<Integer, Integer> getDOWN(Tuple<Integer, Integer> pos){
		return new Tuple(pos.r + 1, pos.c);
	}
	
	private boolean lookLEFT(int[][] arr, Tuple<Integer, Integer> pos){
		if ((arr[pos.r][pos.c - 1] != 2) && (arr[pos.r][pos.c - 1] != arr[pos.r][pos.c])) {
			return true;
		}
		return false;
	}
	
	private Tuple<Integer, Integer> getLEFT(Tuple<Integer, Integer> pos){
		return new Tuple(pos.r, pos.c - 1);
	}
	
	private boolean lookRIGHT(int[][] arr, Tuple<Integer, Integer> pos){
		if ((arr[pos.r][pos.c + 1] != 2) && (arr[pos.r][pos.c + 1] != arr[pos.r][pos.c])) {
			return true;
		}
		return false;
	}
	
	private Tuple<Integer, Integer> getRIGHT(Tuple<Integer, Integer> pos){
		return new Tuple(pos.r, pos.c + 1);
	}
	
	
	private int BFS(int[][] arr, Tuple<Integer, Integer> source, int orientation, Tuple<Integer, Integer> destination){
		if (arr[source.r][source.c] == 2) return 1000000;
		
		Queue fronta = new LinkedList<Tuple<Integer, Integer> >();
		fronta.add(source);
		
		Set<Tuple<Integer, Integer> > seen = new HashSet<Tuple<Integer, Integer> >();
		seen.add(source);
		
		int dist = 0;
		
		while (!fronta.isEmpty()){
			Tuple<Integer, Integer> cur = (Tuple<Integer, Integer>) fronta.remove();
			

			
			if (cur.equals(destination)) return dist;
			
			
			
			if (lookUP(arr, cur) && (!seen.contains(getUP(cur)))){
				fronta.add(getUP(cur));
				seen.add(getUP(cur));
			}
			if (lookDOWN(arr, cur) && (!seen.contains(getDOWN(cur)))){
				fronta.add(getDOWN(cur));
				seen.add(getDOWN(cur));
			}
			if (lookLEFT(arr, cur) && (!seen.contains(getLEFT(cur)))){
				fronta.add(getLEFT(cur));
				seen.add(getLEFT(cur));
			}
			if (lookRIGHT(arr, cur) && (!seen.contains(getRIGHT(cur)))){
				fronta.add(getRIGHT(cur));
				seen.add(getRIGHT(cur));
			}
			dist += 1;
		}
		return 1000000;
	}
	
	
	public void act(){			
		/* ZACIATOK MIESTA PRE VAS KOD */
		
		net = percept();	// aktualny percept
					// zadefinovane su konstanty CLEAN=0, DIRTY=1, WALL=2)		
		
		if (dest.equals(new Tuple(20,20))) return;
		
		int up = BFS(net, new Tuple(19,20), 0, dest); // UP
		int down = BFS(net, new Tuple(21,20), 0, dest); // DOWN
		int left = BFS(net, new Tuple(20,19), 0, dest); // LEFT
		int right = BFS(net, new Tuple(20,21), 0, dest); // RIGHT
		
		System.out.println(up + " " + down + " " + left + " " + right);
		
		int max = Math.min(up, Math.min(down, Math.min(left, right)));
		System.out.println(max);
		
		
		if (down == max){
			if (getOrientation() == World.SOUTH){
				dest.r -= 1;
				moveFW();
				return;
			}
			else{
				turnLEFT();
				return;
			}
		}
		
		if (up == max){
			if (getOrientation() == World.NORTH){
				dest.r += 1;
				moveFW();
				return;
			}
			else{
				turnLEFT();
				return;
			}
		}
		
		if (right == max){
			if (getOrientation() == World.EAST){
				dest.c += 1;
				moveFW();
				return;
			}
			else{
				turnLEFT();
				return;
			}
		}
		
		if (left == max){
			if (getOrientation() == World.WEST){
				dest.c -= 1;
				moveFW();
				return;
			}
			else{
				turnLEFT();
				return;
			}
		}
	}
	
}
