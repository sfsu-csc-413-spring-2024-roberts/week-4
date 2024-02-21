package lexer;

public class Lexer {
  private String inputString;
  private int index;

  public Lexer(String inputString) {
    this.inputString = inputString;
    this.index = 0;
  }

  public char nextToken() {
    if (this.index >= this.inputString.length()) {
      return '\0';
    }

    return this.inputString.charAt(this.index++);
  }

  public int getCurrentIndex() {
    return this.index;
  }
}
