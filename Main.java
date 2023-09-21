import java.util.*;

public class Main {
    // A* functions go here
    public static void main(String[] args) {
        int[] arraysAtDepths = new int[12];
        for(int i = 0; i < 12; i++){
            arraysAtDepths[i] = 0;
        }
        int goodDepths = 0;
        while(goodDepths < 12) {
            int[] board = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            GenerateBoards.shuffle(board, 9);
            //throw out board if seen -- maybe not
            if(!GenerateBoards.checkBoard(board)){
                if(board[0] == 9){
                    int temp = board[1];
                    board[1] = board[2];
                    board[2] = temp;
                }
                else if (board[1] == 9){
                    int temp = board[0];
                    board[0] = board[2];
                    board[2] = temp;
                }
                else {
                    int temp = board[0];
                    board[0] = board[1];
                    board[1] = temp;
                }
                //add initial board to priority queue
                PriorityQueue<int[]> puzzleStates = new PriorityQueue<>();
                puzzleStates.add(board);
                //if pq empty, throw failure excpetion
                if(puzzleStates.size() == 0) {
                  throw new Error();
                }
                
                //pop node from priority queue only keep going if its new
                //find sucesssors & increment number of nodes
                //for each successsor
                    //depth = g of parent ++
                    //Can add all nodes to pq without worrying about if priority is better
                    //if goal state, return g (depth) & number of nodes
                        //add node to pq
            
            
        }
    }
}

// Wolfram Alpha for effective branching factor calculator
