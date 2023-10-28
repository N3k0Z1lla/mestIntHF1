public class StudentPlayer extends Player{
	private int[][] usefulnessTable = 
		{{3, 4, 5, 7, 5, 4, 3}, 
        {4, 6, 8, 10, 8, 6, 4},
        {5, 8, 11, 13, 11, 8, 5}, 
        {5, 8, 11, 13, 11, 8, 5},
        {4, 6, 8, 10, 8, 6, 4},
        {3, 4, 5, 7, 5, 4, 3}};
	
    public StudentPlayer(int playerIndex, int[] boardSize, int nToConnect) {
        super(playerIndex, boardSize, nToConnect);
    }

    @Override
    public int step(Board board) {
    	int bestCol = 3;
    	int depth = 11;
    	var maxEval = Integer.MIN_VALUE;
		for(int s : board.getValidSteps()) {
			Board b = new Board(board);
			b.step(playerIndex, s);
			int eval = minimax(b, depth - 1 , 3 - playerIndex, Integer.MIN_VALUE, Integer.MAX_VALUE);
			if(maxEval < eval) {
				maxEval = eval;
				bestCol = s;
			}
		}
		return bestCol;
    }
    
    private int minimax(Board board, int depth, int playerIndex, int alpha, int beta) {
    	if(depth == 0 || board.gameEnded()) {
    		return eval(board);
    	}
    	if(playerIndex == 2) {
    		var maxEval = Integer.MIN_VALUE;
    		for(int s : board.getValidSteps()) {
    			Board b = new Board(board);
    			b.step(playerIndex, s);
    			int eval = minimax(b, depth - 1 , 3 - playerIndex, alpha, beta);
    			maxEval = Math.max(eval, maxEval);
    			alpha = Math.max(alpha, eval);
    			if(beta <= alpha) {
    				break;
    			}
    			
    		}
    		return maxEval;
    	} else {
    		var minEval = Integer.MAX_VALUE;
    		for(int s : board.getValidSteps()) {
    			Board b = new Board(board);
    			b.step(playerIndex, s);
    			int eval = minimax(b, depth - 1 , 3 - playerIndex, alpha, beta);
    			minEval = Math.min(eval, minEval);
    			beta = Math.min(beta, eval);
    			if(beta <= alpha) {
    				break;
    			}
    		}
    		return minEval;
    	}
    }
    private int eval(Board b) {
    	int enemyIndex = 3 - playerIndex;
    	var currState = b.getState();
    	var winner = b.getWinner();
    	if(winner == this.playerIndex) {
    		return 1000;
    	} else if (winner == enemyIndex) {
    		return -1000;
    	} else if(winner == 0) {
    			return 0;
		} else {
		    int gotem = 0;
		    for (int i = 0; i < 6; i++) {
		    	for (int j = 0; j < 7; j++) {
		    		if (currState[i][j] == playerIndex) {
		    			gotem += usefulnessTable[i][j];
		    		} else if (currState[i][j] == enemyIndex) {
		    			gotem -= usefulnessTable[i][j];
		    		}
		        }
		    }       	
		    return gotem;
			
			
			
			/*
			int gotem = 0;
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 4; j++) {
					if(currState[i][j] == 0 && currState[i][j+1] == enemyIndex && currState[i][j+2] == enemyIndex && currState[i][j+3] == 0 ) {
						gotem -= 249;
					}
					if(currState[i][j] == 0 && currState[i][j+1] == playerIndex && currState[i][j+2] == playerIndex && currState[i][j+3] == 0 ) {
						gotem += 249;
					}
				}
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 7; j++) {
					if(currState[i][j] == 0 && currState[i+1][j] == enemyIndex && currState[i+2][j] == enemyIndex && currState[i+3][j] == 0 ) {
						gotem -= 249;
					}
					if(currState[i][j] == 0 && currState[i+1][j] == playerIndex && currState[i+2][j] == playerIndex && currState[i+3][j] == 0 ) {
						gotem += 249;
					}
				}
			}
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 4; j++) {
					if(currState[i][j] == 0 && currState[i+1][j+1] == enemyIndex && currState[i+2][j+2] == enemyIndex && currState[i+3][j+3] == 0 ) {
						gotem -= 249;
					}
					if(currState[i][j] == 0 && currState[i+1][j+1] == playerIndex && currState[i+2][j+2] == playerIndex && currState[i+3][j+3] == 0 ) {
						gotem += 249;
					}
				}
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 6; j > 4; j--) {
					if(currState[i][j] == 0 && currState[i+1][j-1] == enemyIndex && currState[i+2][j-2] == enemyIndex && currState[i+3][j-3] == 0 ) {
						gotem -= 249;
					}
					if(currState[i][j] == 0 && currState[i+1][j-1] == playerIndex && currState[i+2][j-2] == playerIndex && currState[i+3][j-3] == 0 ) {
						gotem += 249;
					}
				}
			}
			return gotem;*/
		}
    }
}