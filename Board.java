/**
 * The board object for the Knight's Tour Problem. 
 * 
 * @author Spencer A. Carr
 */
public class Board
{
	private int[][] board;												//chess board
	private int currentRow;												//current row knight is on
	private int currentCol;												//current column knight is on
	private int movesMade;												//counts moves made
	private final int[] sideways = 	{ 2, 2,  1,  1, -2, -2, -1, -1 };	//reference for knight jumps
	private final int[] upAndDown = {-1, 1, -2,  2, -1,  1, -2,  2 };	//reference for knight jumps
	String[] moveSolution;												//will print the solution in algebraic notation 
	
	/**
	 * Constructor for board to test the knight tour problem.
	 * @param startingRow - the starting row of the knight
	 * @param startingCol - the starting column of the knight.
	 */
	public Board(int startingRow, int startingCol)
	{
		this.currentRow = startingRow;		//initialize the starting row
		this.currentCol = startingCol;		//initialize the starting column
		movesMade = 0;						//initialize the amount of moves made
	
		
		board = new int[8][8];				//set up the board
		board[currentRow][currentCol]++;	//set the current row and current column on the board to be already visited
		
		moveSolution = new String[64];		//set up the move solution array
		moveSolution[movesMade] = moveToString(currentRow, currentCol);	//put the first move in the move solution array
		
	}
	
	/**
	 * Prints the board.
	 */
	public void print()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j] +" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints the board with a new line.
	 */
	public void println()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Makes a random move of for the knight. 
	 * @return true if a move was able to made, and was in fact made.
	 */
	public boolean makeMove()
	{
		boolean stillOptions = true;				//There are possible squares for the knight to jump to
		boolean notMoved = true;					//Whether the knight has moves or not
		int[] movesTriedArray = {8,8,8,8,8,8,8,8};	//Keeps track of the moves tried during the iterating
		int movesTried = 0;							//Keeps track of how many moves tried during iteration
		boolean duplicate = true;					//Used to help generate a random move that hasn't been tried
		int rand = 8;								//random variable for picking a random move
		
		while(notMoved && stillOptions) 			//keep looping if the knight hasn't moved and there are still moves to be tried 
		{
			duplicate = true;
			while(duplicate)
			{
				duplicate = false;
				rand = (int)(Math.random()*8); //choosing a random move
				for(int e : movesTriedArray)
				{
					if(e == rand)
					{
						duplicate = true;
					}
				}//check if the random move hasn't been tried. 
			} //random move has been chosen
			
			int r = currentRow + upAndDown[rand]; 	//will soon become the new row of the knight
			int c = currentCol + sideways[rand];	//will soon become the new column of the knight
			
			if(canMakeMove(r, c)) 
			{//if the move can be made
				currentRow = r;	//set the current row to the new row
				currentCol = c;	//set the current column to the new column
				
				board[currentRow][currentCol]++;	//show that the square has been moves to 
				
				movesMade++;
				
				moveSolution[movesMade] = moveToString(currentRow, currentCol);	//add move to the solution 
				notMoved = false;
			}
			
			else
			{//move cannot be made
				movesTriedArray[movesTried] = rand;		//add the move that was tried to the movesTried array
				movesTried++;							//a move has been tried
				stillOptions = movesTried < 8;			//there are still move options if less than 8 moves have been tried
			}
		}

		return (notMoved == false); //return if the move has been made 
		
	}
	
	/**
	 * Checks to see if the desired move can be made. 
	 * @param r - the desired row
	 * @param c	- the desired column
	 * @return true if the desired row and desired column is on the board and hasn't been visited. 
	 */
	public boolean canMakeMove(int r, int c)
	{
		return ( onBoard(r,c) && notVisited(r,c) );	//conditions to be true : move is on the board, and the square is not visited
	}
	
	/**
	 * Checks to see if the square on the 2D array is not out of bounds. 
	 * @param r - the desired row
	 * @param c - the desired column
	 * @return true if the desired row and desired column are on the board. 
	 */
	public boolean onBoard(int r, int c)
	{
		return ( (r<8 && r>-1) && (c<8 && c>-1) );	//conditions to be on the board :  (-1 < row < 8) and (-1 < column < 8)
	}
	
	/**
	 * Checks to see if the square on the 2D array has been visited or not. 
	 * @param r - the desired row
	 * @param c - the desired column
	 * @return true if the desired row and desired column have not been visited.
	 */
	public boolean notVisited(int r, int c)
	{
		return board[r][c] == 0;	//condition to be not visited : square's value is equal to 0
	}
	
	/**
	 * Determines if the knight's tour problem has been solved. 
	 * @return true if the knight's tour problem has been solved. 
	 */
	public boolean hasBeenSolved()
	{
		return moveSolution[63] != null;	//condition to be solved : the move solutions last element is not null
											//in other words, 64 moves have been made and stored in the move solution array
	}
	
	/**
	 * Resets the knight tour algorithm. Exact same code as the 
	 * body's constructor.
	 * @param startingRow - the row the knight will start on. 
	 * @param startingCol - the column the knight will start on.
	 */
	public void resetAlgorithm(int startingRow, int startingCol)
	{
		board = new int[8][8];				//reset board
		this.currentRow = startingRow;		//reset starting row	
		this.currentCol = startingCol;		//reset starting column
		movesMade = 0;						//reset moves made
		
		board[currentRow][currentCol]++;	//mark the starting square as visited
		
		moveSolution = new String[64];		//reset the move solution array
		moveSolution[movesMade] = moveToString(currentRow, currentCol);	//put the first move in the solution array
	}
	
	/**
	 * Prints the moves the knight took in the 
	 * solving of the knight's tour problem.
	 */
	public void printWinningMoveset()
	{
		for(String e : moveSolution)
		{
			System.out.println(e);
		}
	}	
	
	/**
	 * Converts the row and column move the knight made and converts 
	 * it to algebraic notation.
	 * @param r - row knight moved to.
	 * @param c - column knight moved to.
	 * @return the string representation of the move the knight in 
	 * algebraic notation.
	 */
	public String moveToString(int r, int c)
	{
		String name = "N";	//string representation of the move in algebraic notation
		
		//add the columns first to the name of the move
		//links the column number to the letter file
		if(c == 0)
			name += "a";
		else if(c == 1)
			name += "b";
		else if(c == 2)
			name += "c";
		else if(c == 3)
			name += "d";
		else if(c == 4)
			name += "e";
		else if(c == 5)
			name += "f";
		else if(c == 6)
			name += "g";
		else
			name += "h";
		
		//add the rows second to the name of the move
		//links the row number to the number file
		if(r == 0)
			name += "8";
		else if(r == 1)
			name += "7";
		else if(r == 2)
			name += "6";
		else if(r == 3)
			name += "5";
		else if(r == 4)
			name += "4";
		else if(r == 5)
			name += "3";
		else if(r == 6)
			name += "2";
		else
			name += "1";
		
		return name;	//return the move 
	}
}