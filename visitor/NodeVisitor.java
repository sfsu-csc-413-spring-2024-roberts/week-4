package visitor;

import ast.AST;

public abstract class NodeVisitor {

  public void visitChildren(AST node) {
    for (AST child : node.getChildren()) {
      child.accept(this);
    }
  }

  public abstract void visitX(AST tree);

  public abstract void visitZ(AST tree);

  public abstract void visitCharacterLiteral(AST tree);
}
