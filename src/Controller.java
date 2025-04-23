import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    private final Board board;
    private boolean gameOver = false;
    public Controller(Board board) {
        this.board = board;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean clickOnCell(int row, int col, boolean flag) {
        Cell cell = board.getCellAtCoords(col, row);
        if (cell.getState() == CellState.OPENED) {
            System.out.println("That cell is already open");
            return false;
        } else {
            if (cell.hasMine() && !flag) {
                cell.setState(CellState.OPENED);
                gameOver = true;
            }
            if (flag) {
                cell.flagCell();
                return true;
            }
            cell.openCell();
            openNeighbors(cell);
            return true;
        }
    }

    private void openNeighbors(Cell cell) {
        Cell[] neighbors = board.getNeighboringCells(cell);
        for (Cell c : neighbors) {
            // still unopened remaining
            if (c != null && c.getState() == CellState.UNOPENED) {
                c.openCell();
                if (c.getNeighboringMines() == 0) {
                    openNeighbors(c);
                }
            }
        }
    }

    public void getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        boolean validCellPicked = false;
        while (!validCellPicked) {
            int row = -1;
            int col = -1;
            String flag = "";
            while (row < 0 || row >= board.getWIDTH()) {
                System.out.print("Enter Row between 0 and " + (board.getWIDTH() - 1) + ": ");
                row = scanner.nextInt();
            }

            while (col < 0 || col >= board.getHEIGHT()) {
                System.out.print("Enter Column between 0 and " + (board.getHEIGHT() - 1) + ": ");
                col = scanner.nextInt();
            }

            while (!Objects.equals(flag, "y") && !Objects.equals(flag, "n")) {
                System.out.print("Flag this cell? (y/n): ");
                try {
                    flag = scanner.next("\\w");
                } catch (InputMismatchException e) {
                    System.out.println("Flag must be either 'y' or 'n'.");
                }
            }

            validCellPicked = clickOnCell(row, col, flag.equals("y"));
        }
    }
    
    public void updateBoard() {

    }

    public boolean isGameOver() {
        if (getGameOver()) {
            return true;
        }
        for (int row = 0; row < board.getHEIGHT(); row++) {
            for (int col = 0; col < board.getWIDTH(); col++) {
                if (board.getCellAtCoords(col, row).getState() == CellState.UNOPENED) return false;
            }
        }
        // all cells opened or flagged
        System.out.println("YOU WIN!");
        return true;
    }
}
