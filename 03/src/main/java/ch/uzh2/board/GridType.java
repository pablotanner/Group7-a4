package main.java.ch.uzh2.board;


public enum GridType {
  PLAYER1_GRID,
  PLAYER2_GRID;


  public String toString() {
    String title = "Unknown";
    switch (this) {
      case PLAYER1_GRID:
        title = "PLAYER1 GRID";
        break;
      case PLAYER2_GRID:
        title = "PLAYER2 GRID";
        break;
    }
    return title;
  }
}
