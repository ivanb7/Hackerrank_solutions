/*
 * Problem: Traveling sales man problem
 * Additional details: a vacuum cleaning dirty squares on a board.
 * Find an algorithm to perform the next move
 * 
 * Solution used: Greedy algorithm
*/

package AI;
import java.io.*;
import java.util.*;




public class BotClean {
	//Performs the next move on the board
    public static void next_move(int posr, int posc, String[] board){
    	//testing
    	//printBoard(board);
    	//List<int[]> dirtyPoints = listDirtyPoints(board);
    	
    	/*displays dirty points coordinates
    	for(int[] point : dirtyPoints){
    		System.out.println(point[0] + ", " +  point[1]);
    	}
    	*/
    	//Find destination
    	int[] dest = closest_dirty(posr,posc,board);
    	//Pathfinding
    	//Bot is already at destination
    	if(dest[0] == posr && dest[1] == posc){
    		System.out.println("CLEAN");
    		//Update Board
    		//board = cleanBoard(board,posr,posc);
    	} 
    	//Pathfinding logic
    	//Will prioritize going UP > RIGHT > DOWN > LEFT
    	//No obstacles
    	else {
    		if(dest[0] == posr){
    			if(dest[1]<posc){
    				//GO LEFT
    				System.out.println("LEFT");
    			}
    			if(dest[1]>posc){
    				//GO RIGHT
    				System.out.println("RIGHT");
    			}
    		}
    		if(dest[0]<posr){
    			//GO UP
    			System.out.println("UP");
    		}
    		if(dest[0]>posr){
    			//GO DOWN
    			System.out.println("DOWN");
    		}
    	}
    	//Update board
    }
    
    //Cleans the dirty point at the specified square posr,posc
    public static String[] cleanBoard(String[] board,int posr, int posc){
    	return board;
    }
    //Returns an integer array containing the coordinates of the closest dirty square
    //Takes in the current position
    //Coordinates are stored in [row, column] format
    public static int[] closest_dirty(int posr, int posc, String[] board){
    	int resr = 0;
    	int resc = 0;
    	List<int[]> points = listDirtyPoints(board);
    	int moves = 0;
    	int minimum = Integer.MAX_VALUE;
    	
    	//posr , posc are the coordinates of the bot
    	for(int[] point : points){
    		moves = (Math.abs(point[0] - posr) + Math.abs(point[1] - posc));
    		//
    		//System.out.println("Moves from (" + posr +", " + posc + ") to (" + point[0] + ", " + point[1] + "): " + moves);
    		if (moves < minimum){
    			resr = point[0];
    			resc = point[1];
    			minimum = moves;
    		}
    	}
    	//
    	//System.out.println("Smallest from (" + posr +", " + posc + ") to (" + resr + ", " + resc + "): " + minimum);
    	int[] results = {resr,resc};
		return results;
    }
    //Prints the current state of the board
    public static void printBoard(String[] board){
    	for(int i = 0; i<board.length;i++){
    		System.out.println(board[i]);
    	}
    }
    //Returns a list of the coordinates of all the dirty points
    //Coordinates are stored in [row, column] format
    public static List<int[]> listDirtyPoints(String[] board){
    	List<int[]> results = new ArrayList<int[]>();
    	String line;
    	int[] coords = new int[2];
    	//iterate through board
    	for(int i = 0;i<board.length;i++){
    		line = board[i];
    		for(int j = 0; j<line.length();j++){
    			////////System.out.println("Looking at : " + j + ", " + i);
    			if(line.charAt(j) == 'd'){
    				//add coordinates to results
    				coords[0] = i;
    				coords[1] = j;
    				results.add(coords);
    				//////////System.out.println("Coords: " + coords[0] + ", " + coords[1]);
    				coords = new int[2];
    			}
    		}
    	}
		return results;
    }
    
    //Calculates the score from the problem
    public static double calculateScore(int number_of_moves){
    	double score = (200-number_of_moves)/40;
    	return score;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/AI/input.txt"));
        int [] pos = new int[2];
        String board[] = new String[5];
        //Takes in the initial position of the bot
        for(int i=0;i<2;i++) pos[i] = in.nextInt();
        //Takes in the board
        for(int i=0;i<5;i++) board[i] = in.next();
        next_move(pos[0], pos[1], board);
        in.close();
    }
}
