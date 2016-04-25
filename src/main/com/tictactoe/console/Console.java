package com.tictactoe.console;

import java.util.Scanner;

public class Console {

  private Scanner reader;

  public Console() {
    this.reader = new Scanner(System.in);
  }

  public String query(String query) {
    System.out.println(query);

    return reader.nextLine();
  }

  public void prompt(String prompt) {
    System.out.println(prompt);
  }
}
