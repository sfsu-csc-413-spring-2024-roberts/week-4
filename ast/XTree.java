package ast;

import visitor.TreeVisitor;

public class XTree extends AST {

  @Override
  public String toString() {
    return "X";
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitX(this);
  }

}
