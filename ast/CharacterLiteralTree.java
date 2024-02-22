package ast;

import visitors.TreeVisitor;

public class CharacterLiteralTree extends AST {

  private char literalValue;

  public CharacterLiteralTree(char literalValue) {
    this.literalValue = literalValue;
  }

  @Override
  public String toString() {
    return this.literalValue + "";
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitCharacterLiteralTree(this);
  }

}
