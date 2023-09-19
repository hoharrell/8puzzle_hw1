import java.util.*;

public class Main {
    // A* functions go here
    public static void main(String[] args) {
        int[] board = { 9, 4, 1, 2, 3, 7, 6, 5, 8 };
        Puzzle puzzle = new Puzzle(board, 0);
        puzzle.swapUp();
        System.out.println(Arrays.toString(board));
        puzzle.swapLeft();
        System.out.println(Arrays.toString(board));
        // int validBoards = 0;
        // for (int i = 0; i < 100; i++) {
        // int[] newBoard = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // GenerateBoards.shuffle(newBoard, 9);
        // if (GenerateBoards.checkBoard(newBoard)) {
        // validBoards++;
        // }
        // }
        // System.out.println(validBoards);
        // if (checkBoard(board)) {
        // int[] permutation = boards.getPermutation();
        // for (int i = 0; i < 1200; i++) {
        // // Puzzle p = new Puzzle();
        // // shuffl
        // }
    }
}

// Wolfram Alpha for effective branching factor calculator
