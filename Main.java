import java.util.*;

public class Main {
    // A* functions go here
    public static void main(String[] args) {
        int[] board = { 6, 4, 9, 1, 2, 7, 5, 8, 3 };
        System.out.println(GenerateBoards.checkBoard(board));
        Puzzle puzzle = new Puzzle(board);
        puzzle.swapRight();
        puzzle.swapUp();
        puzzle.swapLeft();
        puzzle.swapDown();
        puzzle.swapRight();
        puzzle.swapRight();
        puzzle.swapUp();
        puzzle.swapLeft();
        puzzle.swapLeft();
        puzzle.swapUp();
        puzzle.swapRight();
        puzzle.swapRight();
        puzzle.swapDown();
        puzzle.swapLeft();
        puzzle.swapDown();
        puzzle.swapLeft();
        puzzle.swapUp();
        puzzle.swapRight();
        puzzle.swapUp();
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
