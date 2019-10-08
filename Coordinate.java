public class Coordinate{
	public int x;
	public int y;

	public Coordinate(int x, int y){
		this.x=x;
		this.y=y;
	}

	public String toString(){
		return "["+x+","+y+"]";
	}

	@Override
	public boolean equals(Object o){
		if(o==this){
			return true;
		}

		if(!(o instanceof Coordinate)){
			return false;
		}

		Coordinate c = (Coordinate) o;

		return(c.x==x && c.y==y);
	}

	@Override
	public int hashCode(){
		int result = (int) ((Double)(Math.pow(x,4) * Math.pow(y,2))).hashCode();
		return result;
	}
}