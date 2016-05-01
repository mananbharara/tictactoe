package com.tictactoe.domain;

public class Player {

  private final String name;
  private final String symbol;

  public Player(String player1Name, String symbol) {
    this.name = player1Name;
    this.symbol = symbol;
  }

  public PlayerType type() {
    return PlayerType.HUMAN;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }
}
