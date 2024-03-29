import java.util.HashMap;
public class LatticePathRecursion {
	private static int n;
	private static HashMap<Coordinate, Long> pathMagnitudes;

	public static void main(String[] args){
		pathMagnitudes = new HashMap<>();
		//n = grid size (square)
		n=20;
		long moves = move(new Coordinate(0,0));
		System.out.println(moves);

		//print the size of the hashmap
		System.out.println(pathMagnitudes.keySet().size());
	}

	public static long move(Coordinate startPos){
		if(startPos.x >= n && startPos.y >= n){
			//If at the bottom right, return 1
			return 1;
		} else if(pathMagnitudes.get(startPos)!=null || pathMagnitudes.get(new Coordinate(startPos.y, startPos.x))!=null){
			//If a specific path has already been calculated, find it in the hashmap and return the value (this is essential to solving)
			//Also check for the reciprocal of the point - the number of paths equal: (4,5) == (5,4)
			return (pathMagnitudes.get(startPos)!=null) ? pathMagnitudes.get(startPos) : pathMagnitudes.get(new Coordinate(startPos.y, startPos.x));
		} else {
			if(startPos.x+1>n){
				//if the right spot does not exist, only count the number of paths from the spot to the bottom
				Coordinate bottomCoord = new Coordinate(startPos.x, startPos.y+1);
				long count = move(bottomCoord);

				//add the calculated point to the hashmap
				pathMagnitudes.put(bottomCoord, count);
				return count;
			} else if(startPos.y+1>n){
				//if below does not exist, only count the number of paths from the spot to the right
				Coordinate rightCoord = new Coordinate(startPos.x+1, startPos.y);
				long count = move(rightCoord);

				//add the calculated point to the hashmap
				pathMagnitudes.put(rightCoord, count);
				return count;
			} else {
				//if both spots (to the right and below) exist, then calculate the paths from each spot
				Coordinate rightCoord = new Coordinate(startPos.x+1, startPos.y);
				Coordinate bottomCoord = new Coordinate(startPos.x, startPos.y+1);

				long countRight = move(rightCoord);
				long countBottom = move(bottomCoord);


				//add the calculated points to the hashmap
				pathMagnitudes.put(rightCoord, countRight);
				pathMagnitudes.put(bottomCoord, countBottom);
				return countRight+countBottom;
			}
		}
	}
}