package ast;

import visitor.NodeVisitor;

public class ZTree extends AST {

  @Override
  public String toString() {
    return "Z";
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visitZ(this);
  }

}
