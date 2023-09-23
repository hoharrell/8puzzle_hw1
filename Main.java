import java.util.*;

public class Main {
    // A* functions go here
    public static void main(String[] args) {
        int[] arraysAtDepths = new int[12];
        ArrayList<int[]> solutionList = new ArrayList<int[]>();
        for (int i = 0; i < 12; i++) {
            arraysAtDepths[i] = 0;
        }
        int goodDepths = 0;
        while (goodDepths < 12) {
            int[] board = { 4, 1, 3, 2, 9, 6, 7, 5, 8 };
            // GenerateBoards.shuffle(board, 9);
            // throw out board if seen -- maybe not

            // ensure goal state is reachable and edit board if not

            if (!GenerateBoards.checkBoard(board)) {
                if (board[0] == 9) {
                    int temp = board[1];
                    board[1] = board[2];
                    board[2] = temp;
                } else if (board[1] == 9) {
                    int temp = board[0];
                    board[0] = board[2];
                    board[2] = temp;
                } else {
                    int temp = board[0];
                    board[0] = board[1];
                    board[1] = temp;
                }
            }

            int[] solution = findSolution(new Puzzle(board, 0));
            int depth = solution[1];
            if (depth % 2 == 0 && depth < 24) {
                if (arraysAtDepths[depth / 2 - 1] < 99) {
                    arraysAtDepths[depth / 2 - 1]++;
                    solutionList.add(solution);
                } else if (arraysAtDepths[depth / 2 - 1] == 99) {
                    arraysAtDepths[depth / 2 - 1]++;
                    solutionList.add(solution);
                    goodDepths++;
                }
            }
        }
    }

    public static void findSuccessors(Puzzle board, PriorityQueue<Puzzle> puzzleStates) {
        addToQueue(board.swapUp(), puzzleStates);
        addToQueue(board.swapRight(), puzzleStates);
        addToQueue(board.swapDown(), puzzleStates);
        addToQueue(board.swapLeft(), puzzleStates);
    }

    public static void addToQueue(Puzzle board, PriorityQueue<Puzzle> puzzleStates) {
        if (board != null) {
            puzzleStates.add(board);
        }
    }

    public static int[] findSolution(Puzzle initialBoard) {
        Set<int[]> visitedStates = new HashSet<int[]>();
        PriorityQueue<Puzzle> puzzleStates = new PriorityQueue<>(new CustomComparator());

        // add initial board to priority queue
        puzzleStates.add(initialBoard);

        while (puzzleStates.size() > 0) {
            // pop node from priority queue only keep going if its new

            Puzzle currentBoard = puzzleStates.poll();
            if (checkGoal(currentBoard.board)) {
                int[] success = { visitedStates.size() + 1, currentBoard.depth };
                return success;
            }
            if (!visitedStates.contains(currentBoard.board)) {
                // find successors & increment number of nodes
                findSuccessors(currentBoard, puzzleStates);
                // for each successsor
                // depth = g of parent ++
                // Can add all nodes to pq without worrying about if priority is better
                // if goal state, return g (depth) & number of nodes
                // add node to pq
            }
            visitedStates.add(currentBoard.board);
        }
        int[] fail = { -1, -1 };
        return fail;
    }

    public static boolean checkGoal(int[] board) {
        int[] permBoard = new int[8];
        int j = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] != 9) {
                permBoard[j] = board[i];
                j++;
            }
        }

        for (int i = 1; i < permBoard.length; i++) {
            if (permBoard[i] < permBoard[i - 1]) {
                return false;
            }
        }
        return true;
    }
}

class CustomComparator implements Comparator<Puzzle> {

    @Override
    public int compare(Puzzle board1, Puzzle board2) {

        // elements are sorted in reverse order
        int value1 = board1.depth + GenerateBoards.h1Calculator(board1.board); // change between h1Calculator and
                                                                               // h2Calculator
        int value2 = board2.depth + GenerateBoards.h1Calculator(board2.board); // change between h1Calculator and
                                                                               // h2Calculator

        if (value1 > value2) {
            return 1;
        } else if (value2 > value1) {
            return -1;
        } else {
            return 0;
        }
    }
}

// Wolfram Alpha for effective branching factor calculator
