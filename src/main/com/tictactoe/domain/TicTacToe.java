package com.tictactoe.domain;

import com.tictactoe.console.Console;

public class TicTacToe {

  private Console inputOutput;

  public TicTacToe(Console inputOutput) {
    this.inputOutput = inputOutput;
  }

  public void start() {
    Player player1 = new Player(this.inputOutput.query("Name for player 1 (X): "), "X");
    Player player2 = new Player(this.inputOutput.query("Name for player 2 (O): "), "O");

    GameState gameState = new GameState();
    Player currentPlayer = player1;

    while (!gameState.isFinished()) {
      currentPlayer = currentPlayer.equals(player1) ? player2 : player1;

      String playerMove = this.inputOutput.query(currentPlayer.getName() + "'s move (eg. 1 1): ");
      String[] moveLocation = playerMove.split(" ");
      int moveLocationX = Integer.parseInt(moveLocation[0]) - 1;
      int moveLocationY = Integer.parseInt(moveLocation[1]) - 1;

      gameState.move(currentPlayer, moveLocationX, moveLocationY);
    }

    showResult(gameState);
  }

  private void showResult(GameState gameState) {
    if (gameState.getWinner() == null) {
      inputOutput.prompt("No moves left. It is a Draw!");
    } else {
      inputOutput.prompt(gameState.getWinner().getName() + " wins!");
    }
  }
}
