public class Position { 
  public  int r; 
  public  int c; 
  public Position(int r, int c) { 
    this.r = r; 
    this.c = c; 
  } 
  
  public String toString(){
	  return r + " " + c;
  }
  @Override
  public boolean equals(Object obj) {
   if(this==obj)
    return true;
   Position p = (Position)obj;
   return ((p.r == this.r) && (p.c == this.c));
  }
  
  public int hashCode(){
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + (int) (this.r ^ (this.r >>> 32));
	  result = prime * result + (int) (this.c ^ (this.c >>> 32));
      return result;
  }
} 