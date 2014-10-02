public class Tuple<X, Y> { 
  public  X r; 
  public  Y c; 
  public Tuple(X r, Y c) { 
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
   Tuple p=(Tuple)obj;
   return (p.r == this.r && p.c == this.c);
  }
  
  public int hashCode(){
	  return 0;
  }
} 