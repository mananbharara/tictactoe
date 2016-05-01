package com.tictactoe.domain;

import com.tictactoe.console.Console;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PlayerMoveStrategy {

  public static List<Integer> getMoveLocation(GameState gameState, Player currentPlayer, Console inputOutput) {
    if (currentPlayer.type().equals(PlayerType.COMPUTER)) {
      final List<Integer> move = computerMoveLocation(gameState);
      inputOutput.prompt(currentPlayer.getName() + " places " + currentPlayer.getSymbol() + " at location: " + (move.get(0) + 1) + " " + (move.get(1) + 1));
      return move;
    }

    String playerMoveString = inputOutput.query(currentPlayer.getName() + "'s move (eg. 1 1): ");
    return Arrays.stream(playerMoveString.split(" "))
        .map(Integer::parseInt)
        .map(i -> --i)
        .collect(Collectors.toList());
  }

  private static List<Integer> computerMoveLocation(GameState gameState) {
    final List<List<Integer>> unfilledLocations = gameState.allUnfilledLocations();
    final int moveIndex = new Random().nextInt(unfilledLocations.size());

    return unfilledLocations.get(moveIndex);
  }
}
