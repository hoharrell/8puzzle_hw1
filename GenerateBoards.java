import java.util.*;

public class GenerateBoards {

    /**
     * Generates a random permutation of 8 numbers
     * to represent the 8-puzzle
     */

    public static void shuffle(int[] arr, int n) {
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
    public static boolean checkBoard(int[] board) {
        int cycleCounter = 0;
        int[] permBoard = new int[8];
        int j = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] != 9) {
                permBoard[j] = board[i];
                j++;
            }
        }
        int[] explored = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int i = 1; i < 9; i++) {
            if (explored[i] == 0) {
                explored[i] = 1;
                int startingNode = i;
                int nextNode = permBoard[startingNode - 1];
                while (nextNode != startingNode) {
                    explored[nextNode] = 1;
                    nextNode = permBoard[nextNode - 1];
                }
                cycleCounter++;
            }
        }
        if (cycleCounter % 2 == 0) {
            return true;
        }
        return false;
    }

    public static int h1Calculator(ArrayList<Integer> board) {
        int h1 = 0;
        for (int i = 0; i < 9; i++) {
            if (((i + 1) != board.get(i)) && (board.get(i) != 9)) {
                h1++;
            }
        }
        return h1;
    }

    public static int h2Calculator(ArrayList<Integer> board) {
        int h2 = 0;
        for (int i = 0; i < 9; i++) {
            if (board.get(i) != 9) {
                int hDist = (board.get(i) % 3) - (i + 1 % 3);
                h2 += Math.abs(hDist);

                int myRow = board.get(i) / 3;
                int goodRow = (i + 1) / 3;
                int vDist = myRow - goodRow;
                h2 += Math.abs(vDist);
            }
        }
        return h2;
    }
}
