package ast;

import visitor.TreeVisitor;

public class ZTree extends AST {

  @Override
  public String toString() {
    return "Z";
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitZ(this);
  }

}
