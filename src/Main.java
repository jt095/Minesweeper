public class Main {
    public static void main(String[] args) {
        final int BOARDWIDTH = 10;
        final int BOARDHEIGHT = 10;

        Board board = new Board(BOARDWIDTH, BOARDHEIGHT);
        Controller controller = new Controller(board);

        while (!controller.isGameOver()) {
            controller.getPlayerInput();
            board.printBoard();
        }

        System.out.println("GAME OVER");
    }
}
