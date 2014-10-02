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
	  return 0;
  }
} 