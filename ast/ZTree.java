package ast;

import visitors.TreeVisitor;

public class ZTree extends AST {

  @Override
  public String toString() {
    return "Z Tree";
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitZTree(this);
  }

}
