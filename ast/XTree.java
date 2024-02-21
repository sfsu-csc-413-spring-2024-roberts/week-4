package ast;

import visitor.NodeVisitor;

public class XTree extends AST {

  @Override
  public String toString() {
    return "X";
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visitX(this);
  }
}
