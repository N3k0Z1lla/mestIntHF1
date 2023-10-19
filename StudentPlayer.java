public class StudentPlayer extends Player{
    public StudentPlayer(int playerIndex, int[] boardSize, int nToConnect) {
        super(playerIndex, boardSize, nToConnect);
    }

    @Override
    public int step(Board board) {
    	int bestCol = 3;
    	int depth = 9;
    	var maxEval = Integer.MIN_VALUE;
		for(int s : board.getValidSteps()) {
			Board b = new Board(board);
			b.step(playerIndex, s);
			int eval = minimax(b, depth - 1 , 3 - playerIndex);
			if(maxEval < eval) {
				maxEval = eval;
				bestCol = s;
			}
		}
		return bestCol;
    }
    
    private int minimax(Board board, int depth, int playerIndex) {
    	if(depth == 0 || board.gameEnded()) {
    		var eval = eval(board, playerIndex);
//    		for(int i = 0; i < 8-depth; i++) {
//    			System.out.print("# ");
//    		}
//    		System.out.println(eval);
    		return eval;
    	}
    	if(playerIndex == 2) {
    		var maxEval = Integer.MIN_VALUE;
    		for(int s : board.getValidSteps()) {
    			Board b = new Board(board);
    			b.step(playerIndex, s);
    			int eval = minimax(b, depth - 1 , 3 - playerIndex);
    			maxEval = Math.max(eval, maxEval);
    		}
//    		for(int i = 0; i < 8-depth; i++) {
//    			System.out.print("# ");
//    		}
//    		System.out.println(maxEval);
    		return maxEval;
    	} else {
    		var minEval = Integer.MAX_VALUE;
    		for(int s : board.getValidSteps()) {
    			Board b = new Board(board);
    			b.step(playerIndex, s);
    			int eval = minimax(b, depth - 1 , 3 - playerIndex);
    			minEval = Math.min(eval, minEval);
    		}
//    		for(int i = 0; i < 8-depth; i++) {
//    			System.out.print("# ");
//    		}
//    		System.out.println(minEval);
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
    		//System.out.println("nyomjad pillanat van");
    		return 1000;
    	} else if (b.getWinner() == enemyIndex) {
    		//System.out.println("1/nyomjad pillanat van");
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






