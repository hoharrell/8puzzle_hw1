import java.util.*;

public class Puzzle {
    public ArrayList<Integer> board;
    public int blankSpot;
    public int depth;

    public Puzzle(ArrayList<Integer> board, int depth) {
        this.board = new ArrayList<Integer>(board);
        this.depth = depth;
        for (int i = 0; i < 9; i++) {
            if (board.get(i) == 9) {
                this.blankSpot = i;
            }
        }
    }

    public Puzzle swapRight() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot % 3 != 0) {
            newPuzzle.board.set(newPuzzle.blankSpot, newPuzzle.board.get(this.blankSpot - 1));
            newPuzzle.board.set(newPuzzle.blankSpot - 1, 9);
            newPuzzle.blankSpot--;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle swapLeft() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot % 3 != 2) {
            newPuzzle.board.set(newPuzzle.blankSpot, newPuzzle.board.get(this.blankSpot + 1));
            newPuzzle.board.set(newPuzzle.blankSpot + 1, 9);
            newPuzzle.blankSpot++;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle swapDown() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot >= 3) {
            newPuzzle.board.set(newPuzzle.blankSpot, newPuzzle.board.get(this.blankSpot - 3));
            newPuzzle.board.set(newPuzzle.blankSpot - 3, 9);
            newPuzzle.blankSpot -= 3;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle swapUp() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot <= 5) {
            newPuzzle.board.set(newPuzzle.blankSpot, newPuzzle.board.get(this.blankSpot + 3));
            newPuzzle.board.set(newPuzzle.blankSpot + 3, 9);
            newPuzzle.blankSpot += 3;
            return newPuzzle;
        }
        return null;
    }
}