public class Cell {
    private CellState state;
    private final boolean mine;
    private final int col;
    private final int row;
    private int neighboringMines;
    private char icon;

    public Cell(boolean hasMine, int col, int row) {
        this.mine = hasMine;
        this.col = col;
        this.row = row;
        this.state = CellState.UNOPENED;
    }

    public boolean hasMine() {
        return mine;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void setNeighboringMines(int neighboringMines) {
        this.neighboringMines = neighboringMines;
    }

    public int getNeighboringMines() {
        return neighboringMines;
    }

    public String getIcon(){
        if (state == CellState.UNOPENED || state == CellState.FLAGGED) {
            return state.symbol;
        } else if (hasMine()) {
            return "X";
        } else if (getNeighboringMines() != 0)  {
            return Integer.toString(getNeighboringMines());
        }

        return state.symbol;
    }

    public void openCell() {
        if (state == CellState.UNOPENED && !hasMine()) {
            state = CellState.OPENED;
        }
    }

    public void flagCell() {
        state = CellState.FLAGGED;
    }

}
