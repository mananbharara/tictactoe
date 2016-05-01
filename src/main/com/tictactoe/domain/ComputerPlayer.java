package com.tictactoe.domain;

public class ComputerPlayer extends Player {

  public static final String COMPUTER = "Computer";

  public ComputerPlayer(String symbol) {
    super(COMPUTER, symbol);
  }

  @Override
  public PlayerType type() {
    return PlayerType.COMPUTER;
  }
}
