/**
 * The main class to test the Knight's Tour Algorithm
 * Tested on the console.
 * 
 * @author Spencer A. Carr
 */
public class KnightsTour
{
	public static void main(String[] args)
	{
		Board board = new Board(7,7);	//start the knight on h1
		
		while(!board.hasBeenSolved())
		{
			if(board.makeMove() == false)
			{
				board.resetAlgorithm((int)(Math.random()*8), (int)(Math.random()*8));
			}//if there are no moves to be made, reset the algorithm
		}//while the problem has not been solves, make moves
		
		board.println();				//print the board 
		board.printWinningMoveset();	//print the winning move list.
	}
}