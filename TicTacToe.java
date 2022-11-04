import java.util.Scanner;
class TicTacToe
{
static char player = 'x', computer = 'o';
public static void main(String[] args) {
	
        char[][] board = new char[3][3]; //this is our board object
        //we initialize it as empty
        //as shown belo
        //make it empty
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    
        //player inputs
        Scanner in = new Scanner(System.in);
        System.out.println("Tic Tac Toe!");
        System.out.print("Enter your name please: ");
        //player input
        String p1 = in.nextLine();
        System.out.print("VS: Minimax");
        //assign p2 to the computer
        String p2 = "Minimax";
    
        boolean player1 = true;
    
    
        boolean gameEnded = false;
        while(!gameEnded) {			
            printgamestate(board);
            if(player1) {
                System.out.println(p1 + "'s Turn [x]:");
            } else {
                System.out.println(p2 + "'s Turn [o]:");
          
            }
    
            
            char c = '-';
            if(player1) {
                c = 'x';
            } else {
                c = 'o';
            }
    
            int row = 0;
            int col = 0;
    
        
            if(!player1) {
            while(true) {
                System.out.println("Player 1's Turn");
            
                System.out.print("Enter a row number [0,1,2]: ");
                row = in.nextInt();
                System.out.print("Enter a column number [0,1,2] ");
                col = in.nextInt();
    
                
                if(row > 2 || col < 0 || row < 0 ||  col > 2) {
                    System.out.println("Illegal Move");
                
                } else if(board[row][col] != '-') {
                    System.out.println("Illegaly move");
                
                } else {
                    break;
                }
            } 
        } else {
            while(true) {
           
                makeDecision opMove = optimize(board);
               
                System.out.println("Maximizer Move's Board:");
        for(int i = 0; i < 3; i++) {
        
            for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j]); //print out the board cause i'm an idiot and need to see what the compute sees
            }
        
            System.out.println();
        }
                row = opMove.row; 
                //let the optimizermover run on rows
                col = opMove.col;				
                //let it run on columns
                if(row < 0 || col < 0 || row > 2 || col > 2) {
                } else if(board[row][col] != '-') {
                    System.out.println("Invalid Move");
                    //make sure the optimizer isn't making a bad move
                } else {
                    break;
                }
            
            }
        }
    
            board[row][col] = 'o'; //assign optimizer
    
            if(winner(board) == 'x') {
                System.out.println(p1 + " has won!");
                gameEnded = true;
            } else if(winner(board) == 'o') {
                System.out.println(p2 + " has won!");
                gameEnded = true;
            } else {
    
                
                if(!gameOver(board)) {
                    System.out.println("Game has ended in a tie");
                    gameEnded = true;
                } else {
                    player1 = !player1;
                }
    
            }
    
        }
        
        printgamestate(board);
    
    }
static class makeDecision { //class object where a move will be made, to assign rows and cols
    int row, col;
}



static Boolean gameOver(char board[][]) {
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (board[i][j] == '-')
                return true;
    return false;
}
 


static int evaluate(char b[][]) {    
    for (int row = 0; row < 3; row++)
    {
        if (b[row][0] == b[row][1] &&
            b[row][1] == b[row][2])
        {
            if (b[row][0] == 'x') {
             //   System.out.println("polywog");
                return +10;
                
            }
            else if (b[row][0] == 'o') {
           // System.out.println("Enemy polywog");
                return -10;
            }
        }
    }
    
    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
    {
        if (b[0][2] == 'x')
            return +10;
        else if (b[0][2] == 'o')
            return -10;
    }
 
 
    for (int col = 0; col < 3; col++)
    {
        if (b[0][col] == b[1][col] &&
            b[1][col] == b[2][col])
        {
            if (b[0][col] == 'x'){
            //    System.out.println("Good Column polywog");
                return +10;
            }
            else if (b[0][col] == 'o'){
            //    System.out.println("Bad column polywog");
                return -10;
            }
        }
    }
 
    // Checking for Diagonals for X or O victory.
    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
    {
        if (b[0][0] == 'x'){
          //  System.out.println("Good Diag polywog");
            return +10;
        }
        else if (b[0][0] == 'o') {
         //   System.out.println("Bad Diag polywog");
            return -10;
        }
    }
 
   
    return 0;
}
 
static int minimax(char board[][], int depth, Boolean isMax) {
   // System.out.println("Minimax was called");
    int minmaxval = evaluate(board);
    if (minmaxval == -10)
        return minmaxval;
 
    if (minmaxval == 10)
        return minmaxval;
 

    if (gameOver(board) == false)
        return 0;
 

    if (isMax)
    {
        int best = -1000;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j]=='-')
                {
                    board[i][j] = player;                
                    best = Math.max(best, minimax(board, depth + 1, !isMax));
                    board[i][j] = '-';
                }
            }
        }
        return best;
    }
    else //minimizers move
    {
        int best = 1000;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == '-')
                {
                    board[i][j] = computer;
                    best = Math.min(best, minimax(board, depth + 1, !isMax));
                    // Undo the move
                    board[i][j] = '-';
                }
            }
        }
        return best;
    }
}
static makeDecision optimize(char board[][]) {
   // System.out.println("hello world");
    int bestVal = -1000;
    makeDecision opMove = new makeDecision();
    opMove.row = -1;
    opMove.col = -1;

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
           
            if (board[i][j] == '-')
            {
           
                board[i][j] = player;
 
              
                int moveVal = minimax(board, 0, false);
 
                
                board[i][j] = '-';
 
            
                if (moveVal > bestVal)
                {
                    opMove.row = i;
                    opMove.col = j;
                    bestVal = moveVal;
                }
            }
        }
    }
 

    return opMove;
}

public static char winner(char[][] board) {
		
		
    for(int i = 0; i < 3; i++) {
        if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
            return board[i][0];
        }
    }


    for(int j = 0; j < 3; j++) {
        if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
            return board[0][j];
        }
    }


    if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
        return board[0][0];
    }
    if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
        return board[2][0];
    }


    return ' ';

}

public static void printgamestate(char[][] board) {
		System.out.println("Board:");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
			}
		
			System.out.println();
		}
	}

}