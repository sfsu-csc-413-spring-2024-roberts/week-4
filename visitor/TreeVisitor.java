package visitor;

import ast.AST;

public abstract class TreeVisitor {

  public void visitChildren(AST ast) {
    for (int index = 0; index < ast.getChildCount(); index++) {
      ast.getChild(index).accept(this);
    }
  }

  public abstract void visitX(AST ast);

  public abstract void visitZ(AST ast);

  public abstract void visitCharLiteral(AST ast);

}
