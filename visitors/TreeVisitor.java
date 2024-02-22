package visitors;

import ast.AST;

public abstract class TreeVisitor {

  public void visitChildren(AST ast) {
    for (int index = 0; index < ast.getChildCount(); index++) {
      ast.getChild(index).accept(this);
    }
  }

  public abstract void visitXTree(AST ast);

  public abstract void visitZTree(AST ast);

  public abstract void visitCharacterLiteralTree(AST ast);
}
