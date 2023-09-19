import java.util.Random;

public class GenerateBoards {
    private int[] permutation;

    /**
     * Generates a random permutation of 8 numbers
     * to represent the 8-puzzle
     */
    public GenerateBoards() {
        int[] permutation = {};
        for (int i = 0; i < 9; i++) {
            permutation[i] = (i + 1);
        }
        shuffle(this.permutation, 9);
        this.permutation = permutation;
    }

    static void shuffle(int[] arr, int n) {
        Random rand = new Random();
        for (int i = n - 1; i >= 1; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * 
     * @param board
     * @return
     */
    public boolean checkBoard(int[] board) {
        int cycleCounter = 0;
        int[] permBoard = new int[8];
        int j = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] != 9) {
                permBoard[j] = board[i];
                j++;
            }
        }
        int[] explored = { 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < 8; i++) {
            if (explored[i] == 0) {
                explored[i] = 1;
                int startingNode = i;
                int nextNode = permBoard[startingNode];
                while (nextNode != startingNode) {
                    explored[nextNode] = 1;
                    nextNode = permBoard[nextNode];
                }
                cycleCounter++;
            }
        }
        if (cycleCounter % 2 == 0) {
            return true;
        }
        return false;
    }

    public int[] getPermutation() {
        return this.permutation;
    }

    public int h1Calculator(int[] board){
        int h1 = 0;
        for(i = 0; i < 9; i++){
            if(((i + 1) != board[i])) && (board[i] != 9)){
                h1++;
            }
        }
        return h1;
    }
}
