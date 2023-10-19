public class StudentPlayer extends Player{
    public StudentPlayer(int playerIndex, int[] boardSize, int nToConnect) {
        super(playerIndex, boardSize, nToConnect);
    }

    @Override
    public int step(Board board) {
    	int bestCol = 3;
    	int depth = 10;
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
    		var eval = eval(board, playerIndex);
    		return eval;
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
    private int eval(Board b, int pi) {
    	int enemyIndex;
    	if(this.playerIndex == 1) {
    		enemyIndex = 2;
    	} else {
    		enemyIndex = 1;
    	}
    	if(b.getWinner() == this.playerIndex) {
    		return 1000;
    	} else if (b.getWinner() == enemyIndex) {
    		return -1000;
    	} else {
    		if(b.getWinner() == 0) {
    			return 1;
    		} else {
    			return -1;
    		}
    	}
    }
}






