package ast;

import visitors.TreeVisitor;

public class XTree extends AST {

  @Override
  public String toString() {
    return "X Tree";
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitXTree(this);
  }

}
