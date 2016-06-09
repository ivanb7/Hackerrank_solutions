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




public class BotCleanPartiallyObservable {
	//Performs the next move on the board
    public static void next_move(int posr, int posc, String[] board){
    	String[] lastBoard = loadBoard();
    	if(lastBoard == null){
    		saveBoard(board);
    		lastBoard = loadBoard();
    	}
    	board = mergeBoards(board, lastBoard);
    	
    	//printing
    	/*
    	printBoard(lastBoard);
    	System.out.println("Merged Board: ");
    	printBoard(board);
    	*/
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
    		//Update clean square Board
    		cleanBoard(board, posr,posc);
    	} 
    	//Pathfinding logic
    	//Will prioritize going LEFT > RIGHT and UP > DOWN
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
    	
    	saveBoard(board);
    }
    
    //The Bot remembers the state of the board from the previous moves with this method
    public static String[] mergeBoards(String[] board, String[] lastBoard) {
		String[] res = new String[board.length];
		String line;
		if(lastBoard != null){
			for(int i=0;i<lastBoard.length;i++){
				line = lastBoard[i];
				for(int j=0;j<line.length();j++){
					if(line.charAt(j) == 'o' && board[i].charAt(j) != 'o'){
						line = replaceCharAt(line,j,board[i].charAt(j));
					}
					if(line.charAt(j) == 'd' && board[i].charAt(j) != 'd'){
						line = replaceCharAt(line,j,board[i].charAt(j));
					}
				}
				res[i] = line;
			}
			return res;
		} else {
			return null;
		}
	}
    
    //Code to replace a character at a certain location by another character.
    public static String replaceCharAt(String s,int pos, char chr){
    	s = s.substring(0,pos) + chr + s.substring(pos+1);
    	return s;
    }
    
	//Cleans the dirty point at the specified square posr,posc
    //Places a 'b' because the bot cleans it's own location.
   
    public static String[] cleanBoard(String[] board,int posr, int posc){
    	if(board[posr].charAt(posc) == 'd'){
    		board[posr] = board[posr].substring(0,posc) + 'b' + board[posr].substring(posc+1);
    		
    	}
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
    	//If there are dirty points nearby, the bot will go clean them
    	if(!points.isEmpty()){
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
    	}
    	//else the bot will explore the environment.
    	else
    	{
    		return explore(posr, posc, board);
    	}
    	//
    	//System.out.println("Smallest from (" + posr +", " + posc + ") to (" + resr + ", " + resc + "): " + minimum);
    	int[] results = {resr,resc};
		return results;
    }
    //Returns an integer array containing the coordinates of the closest unobserved square to be explored
    //Takes in the current position
    //Coordinates are stored in [row, column] format
    public static int[] explore(int posr, int posc, String[] board){
    	int resr = 0;
    	int resc = 0;
    	List<int[]> points = listUnobservedPoints(board);
    	int moves = 0;
    	int minimum = Integer.MAX_VALUE;
    	
    	//logic
    	if(!points.isEmpty()){
	    	for(int[] point : points){
	    		moves = (Math.abs(point[0] - posr) + Math.abs(point[1] - posc));
	    		
	    		//System.out.println("Moves from (" + posr +", " + posc + ") to (" + point[0] + ", " + point[1] + "): " + moves);
	    		if (moves < minimum){
	    			resr = point[0];
	    			resc = point[1];
	    			minimum = moves;
	    		}
	    	}
    	} else {
    		//If there are no more unobserved points the game should end, it should never reach this code.
    		System.out.println("There has been a mistake");
    	}
    	
    	int[] results = {resr,resc};
    	return results;
    }
    
    
    
    
    //Prints the current state of the board
    public static void printBoard(String[] board){
    	for(int i = 0; i<board.length;i++){
    		System.out.println(board[i]);
    	}
    }
    //Saves the board to a file, acting like the memory of the bot
    public static void saveBoard(String[] board){
    	try {
			//This one cannot append
			//PrintWriter writer2 = new PrintWriter("src/midterm/textfile.txt");
			//True will keep the old text, false will erase the old text
    		//old file path "src/AI/savedBoard.txt"
			PrintWriter writer = new PrintWriter(new FileOutputStream("savedBoard.txt", false));
			for(String line : board){	
			writer.println(line);
			}
			writer.close();

			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
			e.printStackTrace();
		}
    }
    //Loads the board from the savedBoard.txt file
    public static String[] loadBoard(){
    	// 5x5 is board size
		String[] board = new String[5];
		try{
			//old file path "src/AI/savedBoard.txt"
			File fileName = new File("savedBoard.txt" );
	        if( !fileName.exists() )
	        {
	            //System.out.println( "this file doesn't exist " );
	            try
	            {
	                fileName.createNewFile();
	                FileWriter fileWrite = new FileWriter( fileName );
	                fileWrite.close();
	                return null;
	            } catch ( IOException e )
	            {
	                System.out.println(e);
	            }
	        } else {
			Scanner in = new Scanner(new FileReader("savedBoard.txt"));
			//5 comes from board size
			for(int i=0;i<5;i++) board[i] = in.next();
			in.close();
	        }
		} catch (Exception e) {
			System.out.println(e);
		} 
    	return board;
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
    			//System.out.println("Looking at : " + j + ", " + i);
    			if(line.charAt(j) == 'd'){
    				//add coordinates to results
    				coords[0] = i;
    				coords[1] = j;
    				results.add(coords);
    				//System.out.println("Coords: " + coords[0] + ", " + coords[1]);
    				coords = new int[2];
    			}
    		}
    	}
		return results;
    }
    //Returns a list of the coordinates of all the Unobserved points
    //Coordinates are stored in [row, column] format
    public static List<int[]> listUnobservedPoints(String[] board){
    	List<int[]> results = new ArrayList<int[]>();
    	String line;
    	int[] coords = new int[2];
    	//iterate through board
    	for(int i = 0;i<board.length;i++){
    		line = board[i];
    		for(int j = 0; j<line.length();j++){
    			//System.out.println("Looking at : " + j + ", " + i);
    			if(line.charAt(j) == 'o'){
    				//add coordinates to results
    				coords[0] = i;
    				coords[1] = j;
    				results.add(coords);
    				//System.out.println("Coords: " + coords[0] + ", " + coords[1]);
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
        Scanner in = null;
    	try{
	    	in = new Scanner(new FileReader("src/AI/input2.txt"));
	        int [] pos = new int[2];
	        //5x5 = board size
	        String board[] = new String[5];
	        //Takes in the initial position of the bot
	        for(int i=0;i<2;i++) pos[i] = in.nextInt();
	        //Takes in the board
	        for(int i=0;i<5;i++) board[i] = in.next();
	        
	        
	        
	        next_move(pos[0], pos[1], board);
        } catch (Exception e){
        	
        } finally {
	        in.close();
        }
    }
}
