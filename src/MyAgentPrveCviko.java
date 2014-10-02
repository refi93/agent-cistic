import java.util.Random;

public  class MyAgentPrveCviko extends Agent{	
	int[][] net;
		
	public MyAgentPrveCviko(int height, int width) {
		
	}
	
	private boolean lookUP(int[][] arr){
		if (arr[0][1] == 1) {
			return true;
		}
		return false;
	}
	
	private boolean lookDOWN(int[][] arr){
		if (arr[2][1] == 1) {
			return true;
		}
		return false;
	}
	
	private boolean lookLEFT(int[][] arr){
		if (arr[1][0] == 1) {
			return true;
		}
		return false;
	}
	
	private boolean lookRIGHT(int[][] arr){
		if (arr[1][2] == 1) {
			return true;
		}
		return false;
	}
	
	public void act(){			
		/* ZACIATOK MIESTA PRE VAS KOD */
		
		net = percept();	// aktualny percept
					// zadefinovane su konstanty CLEAN=0, DIRTY=1, WALL=2)		
		if (net.length == 1){
			// nasledujuci kod je len ukazkou, ktora sluzi na zoznamenie sa s metodami Agenta
	
			Random rand = new Random();
			
			if (net[0][0] == 1){
				suck();
				return;
			}
			
			switch(rand.nextInt(3)){ // aktualne natocenie agenta
			// zadefinovane su konstanty World.NORTH=0, World.EAST=1, World.SOUTH=2, World.WEST=3)
				case 0: //World.NORTH:
					moveFW(); 
					break;
				case 1: //World.EAST:
					turnLEFT();
					break;				
				case 2: //World.WEST:
					turnRIGHT();
					break;							
			}
			
			//if (Math.random() > 0.99)
			//	halt(); //  ukonci cinnost agenta. na konzole sa vypise pocet vykonanych akcii
			
			/* KONIEC MIESTA PRE VAS KOD */
		}
		else if (net.length == 3){
			if (net[1][1] == 1){
				suck();
				return;
			}
			{
				if (lookDOWN(net)){
					if (getOrientation() == World.SOUTH) moveFW();
					else turnLEFT();
					return;
				}
				if (lookUP(net)){
					if (getOrientation() == World.NORTH) moveFW();
					else turnLEFT();
					return;
				}
				if (lookLEFT(net)){
					if (getOrientation() == World.WEST) moveFW();
					else turnLEFT();
					return;
				}
				if (lookRIGHT(net)){
					if (getOrientation() == World.EAST) moveFW();
					else turnLEFT();
					return;
				}
				else{
					Random rand = new Random();
					switch(rand.nextInt(3)){ // aktualne natocenie agenta
					// zadefinovane su konstanty World.NORTH=0, World.EAST=1, World.SOUTH=2, World.WEST=3)
						case 0: //World.NORTH:
							moveFW(); 
							break;
						case 1: //World.EAST:
							turnLEFT();
							break;				
						case 2: //World.WEST:
							turnRIGHT();
							break;							
					}
				}
			
			}
		}
	}
	
}
