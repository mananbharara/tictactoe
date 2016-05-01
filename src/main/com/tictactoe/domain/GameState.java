package com.tictactoe.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class GameState {
  private boolean finished;
  private String gameBoard[][];
  private Player winner;

  public GameState() {
    this.gameBoard = new String[3][3];
  }

  public boolean move(Player player, int moveLocationX, int moveLocationY) {
    if (gameBoard[moveLocationX][moveLocationY] != null) {
      throw new RuntimeException("Slot already filled. Invalid move");
    }

    gameBoard[moveLocationX][moveLocationY] = player.getSymbol();

    if (gameWon()) {
      winner = player;
      finished = true;
    }

    if (noMovesLeft()) {
      finished = true;
    }
    return true;
  }

  private boolean gameWon() {
    for (int i = 0; i < 3; ++i) {
      if (symbolEquals(gameBoard[i][0], gameBoard[i][1]) && symbolEquals(gameBoard[i][1], gameBoard[i][2]) ||
          symbolEquals(gameBoard[0][i], gameBoard[1][i]) && symbolEquals(gameBoard[1][i], gameBoard[2][i])) {
        return true;
      }
    }

    if (symbolEquals(gameBoard[0][0], gameBoard[1][1]) && symbolEquals(gameBoard[1][1], gameBoard[2][2]) ||
        symbolEquals(gameBoard[0][2], gameBoard[1][1]) && symbolEquals(gameBoard[1][1], gameBoard[2][0])) {
      return true;
    }

    return false;
  }

  private boolean noMovesLeft() {
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        if (gameBoard[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean symbolEquals(String symbolAtPos1, String symbolAtPos2) {
    return symbolAtPos1 != null && symbolAtPos1.equals(symbolAtPos2);
  }

  public boolean isFinished() {
    return finished;
  }

  public Player getWinner() {
    return winner;
  }

  public List<List<Integer>> allUnfilledLocations() {
    final List<List<Integer>> unfilledLocations = new ArrayList<>();

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        if (gameBoard[i][j] == null) {
          unfilledLocations.add(asList(i, j));
        }
      }
    }

    return unfilledLocations;
  }
}
