package ast;

import visitor.NodeVisitor;

public class CharLiteralTree extends AST {

  private char character;

  public CharLiteralTree(char character) {
    this.character = character;
  }

  @Override
  public String toString() {
    return this.character + "";
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visitCharacterLiteral(this);
  }

}
