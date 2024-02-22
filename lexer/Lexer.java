package lexer;

import java.util.Set;

public class Lexer {
  private String inputString;
  private int currentPosition;

  private Set<Character> validTokens = Set.of('a', 'b', 'c', 'd');

  public Lexer(String inputString) {
    this.inputString = inputString;
    this.currentPosition = 0;
  }

  public char nextToken() throws Exception {
    if (this.currentPosition >= this.inputString.length()) {
      return '\0';
    }

    char currentCharacter = this.inputString.charAt(this.currentPosition);

    if (!validTokens.contains(currentCharacter)) {
      throw new Exception(
          String.format("%c at position %d is an invalid character", currentCharacter, this.currentPosition));
    }

    this.currentPosition++;
    return currentCharacter;
  }
}