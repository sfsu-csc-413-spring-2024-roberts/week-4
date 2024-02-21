package ast;

import visitor.TreeVisitor;

public class CharLiteralTree extends AST {

  private char literal;

  public CharLiteralTree(char literal) {
    this.literal = literal;
  }

  @Override
  public String toString() {
    return this.literal + "";
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitCharLiteral(this);
  }

}
