import java.util.Random;

public class Board {
    private final int WIDTH;
    private final int HEIGHT;
    private Cell[][] cells;

    public Board(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.cells = new Cell[WIDTH][HEIGHT];
        populateBoard();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void populateBoard() {
        Random random = new Random();
        for (int row = 0; row < HEIGHT; row++) {
            int mineCol = random.nextInt(WIDTH);
            for (int col = 0; col < WIDTH; col++) {
                // generate a mine if matched with random
                boolean hasMine = col == mineCol;
                Cell cell = new Cell(hasMine, col, row);
                cells[col][row] = cell;
            }
        }
        populateCells();
    }

    public void populateCells() {
        BoardIterator iterator = new BoardIterator(this);
        while (!iterator.isDone()) {
            Cell[] neighbors = getNeighboringCells(iterator.currentItem());
            int mineCount = 0;
            for (Cell c : neighbors) {
                if (c != null && c.hasMine()) mineCount ++;
            }
            iterator.currentItem().setNeighboringMines(mineCount);
            iterator.next();
        }
    }

    public Cell getCellAtCoords(int x, int y) {
        return cells[x][y];
    }

    public void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < WIDTH; j++) {
                Cell cell = getCellAtCoords(j, i);
                line.append(String.format("%-2s", cell.getIcon()));
            }
            System.out.printf("%s\n", line);
        }
    }

    public Cell[] getNeighboringCells(Cell cell) {
        Cell[] neighbors = new Cell[8];
        int neighborIndex = 0;
        int row = cell.getRow();
        int col = cell.getCol();
        for (int i = row-1; i <= row+1; i++) {
            if (i < 0 || i >= HEIGHT) {
                continue;
            }
            for (int j = col-1; j <= col+1; j++) {
                // skip self
                if (j < 0 || j >= WIDTH || (i == row && j == col)) {
                    continue;
                }
                neighbors[neighborIndex] = getCellAtCoords(j, i);
                neighborIndex ++;
            }
        }
        return neighbors;
    }
}
