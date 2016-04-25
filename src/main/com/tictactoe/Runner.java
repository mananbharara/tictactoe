package com.tictactoe;

import com.tictactoe.console.Console;
import com.tictactoe.domain.TicTacToe;

public class Runner {
  public static void main(String[] args) {
    Console console = new Console();

    new TicTacToe(console).start();
  }
}
