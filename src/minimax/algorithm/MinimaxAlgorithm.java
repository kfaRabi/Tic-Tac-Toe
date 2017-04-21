/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax.algorithm;

/**
 *
 * @author rabi
 */
public class MinimaxAlgorithm {

    public Action minimaxDecision(State initialState) {
        Action bestAction = null;
        int bestUtility = Integer.MIN_VALUE;

        for (Action a : initialState.getActions()) {
            int minUtility = minValue(initialState.getNextState(a), initialState.getCurrentPlayer(), 1);
            
            if (minUtility > bestUtility) {
                bestUtility = minUtility;
                bestAction = a;
            }
        }
        return bestAction;
    }

    public int minValue(State state, char player, int level) {
        char winner = state.getWinner();
        if (winner != 'U') {
            return state.getUtilityValue(player);
        }

        int bestUtility = Integer.MAX_VALUE;

        for (Action a : state.getActions()) {
            int utility = maxValue(state.getNextState(a), player, level + 1);

            if (utility < bestUtility) {
                bestUtility = utility;
            }
        }
        return bestUtility;
    }

    public int maxValue(State state, char player, int level) {
        char winner = state.getWinner();
        if (winner != 'U') {
            return state.getUtilityValue(player);
        }
        
        int bestUtility = Integer.MIN_VALUE;
        for (Action a : state.getActions()) {
            int utility = minValue(state.getNextState(a), player, level + 1);
            if (utility > bestUtility) {
                bestUtility = utility;
            }
        }
        
        return bestUtility;
    }

    public MinimaxAlgorithm() {
        long start, stop;
        char initialBoard[][] = {
            {'X', 'X', '.'},
            {'O', '.', '.'},
            {'O', '.', '.'},};
//        char initialBoard[][] = {
//            {'X', '.', 'X', '.'},
//            {'.', 'O', '.', '.'},
//            {'O', '.', '.', '.'},
//            {'.', '.', '.', '.'},
//        };
        State initialState = new State(initialBoard, 'X');
        
        System.out.println(initialState);
        System.out.println("Running minimax");
        start = System.currentTimeMillis();
        System.out.println("Suggested action " + minimaxDecision(initialState));
        stop = System.currentTimeMillis();
        System.out.printf("Time taken: %.3f seconds\n", (stop - start) / 1000.0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MinimaxAlgorithm();
    }

}
