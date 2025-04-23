public enum CellState {
    OPENED ("O"),
    UNOPENED ("."),
    FLAGGED ("F");

    public final String symbol;

    private CellState(String symbol) {
        this.symbol = symbol;
    }
}
