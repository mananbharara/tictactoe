package com.tictactoe.domain;

import com.tictactoe.console.Console;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TicTacToe {

  private Console inputOutput;

  public TicTacToe(Console inputOutput) {
    this.inputOutput = inputOutput;
  }

  public void start() {
    Player player1 = new Player(this.inputOutput.query("Name for player 1 (X): "), "X");
    Player player2 = player2OrComputer(this.inputOutput.query("Name for player 2 (O): "));

    GameState gameState = new GameState();
    Player currentPlayer = player1;

    while (!gameState.isFinished()) {
      String move = playerMoveOrComputerMove(gameState, currentPlayer);

      String[] moveLocation = move.split(" ");
      int moveLocationX = Integer.parseInt(moveLocation[0]) - 1;
      int moveLocationY = Integer.parseInt(moveLocation[1]) - 1;

      gameState.move(currentPlayer, moveLocationX, moveLocationY);

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
    if (player2Name.equals("")) {
      inputOutput.prompt("Playing against computer...");
      return new ComputerPlayer("O");
    } else {
      return new Player(player2Name, "O");
    }
  }

  private String playerMoveOrComputerMove(GameState gameState, Player currentPlayer) {
    String playerMove;
    if (currentPlayer.type().equals(PlayerType.COMPUTER)) {
      playerMove = computerMoveLocation(currentPlayer, gameState);
    } else {
      playerMove = this.inputOutput.query(currentPlayer.getName() + "'s move (eg. 1 1): ");
    }
    return playerMove;
  }

  private String computerMoveLocation(Player computer, GameState gameState) {
    final List<List<Integer>> unfilledLocations = gameState.allUnfilledLocations();
    final int moveIndex = new Random().nextInt(unfilledLocations.size());

    final String moveAsString = unfilledLocations.get(moveIndex)
        .stream()
        .map(i -> ++i)
        .map(Object::toString)
        .collect(Collectors.joining(" "));

    inputOutput.prompt(computer.getName() + " places " + computer.getSymbol() + " at " + moveAsString);

    return moveAsString;
  }
}
