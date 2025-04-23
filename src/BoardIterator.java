public class BoardIterator {
    private final Board board;
    private int current;
    public BoardIterator(Board board) {
        this.board = board;
        this.current = 0;
    }

    public void first() {
        current = 0;
    }

    public void next() {
        current++;
    }

    public boolean isDone() {
        return current >= board.getHEIGHT() * board.getWIDTH();
    }

    public Cell currentItem() {
        int row = current / board.getHEIGHT();
        int col = current % board.getWIDTH();
        if (isDone()) {
            throw new IndexOutOfBoundsException("Out of bounds.");
        }
        return board.getCellAtCoords(col, row);
    }


}
