package com.tictactoe.domain;

import com.tictactoe.console.Console;

import java.util.List;

public class TicTacToe {

  public static final String O = "O";
  public static final String X = "X";
  private Console inputOutput;

  public TicTacToe(Console inputOutput) {
    this.inputOutput = inputOutput;
  }

  public void start() {
    Player player1 = new Player(this.inputOutput.query("Name for player 1 (X): "), X);
    Player player2 = player2OrComputer(this.inputOutput.query("Name for player 2 (O): "));

    GameState gameState = new GameState();
    Player currentPlayer = player1;

    while (!gameState.isFinished()) {
      final List<Integer> moveLocation = PlayerMoveStrategy.getMoveLocation(gameState, currentPlayer, inputOutput);

      gameState.move(currentPlayer, moveLocation.get(0), moveLocation.get(1));

      currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
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

  private Player player2OrComputer(String player2Name) {
    if (player2Name.isEmpty()) {
      inputOutput.prompt("Playing against computer...");
      return new ComputerPlayer(O);
    } else {
      return new Player(player2Name, O);
    }
  }

}
