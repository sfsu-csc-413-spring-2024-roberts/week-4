package visitors;

import ast.AST;

public class PrintVisitor extends TreeVisitor {

  private int indentation = 0;

  private void print(AST ast) {
    String formatString = "%" + ((indentation + 1) * 2) + "d: %s\n";

    System.out.printf(formatString, ast.getNodeNumber(), ast.toString());

    this.indentation++;
    visitChildren(ast);
    this.indentation--;
  }

  @Override
  public void visitXTree(AST ast) {
    print(ast);
  }

  @Override
  public void visitZTree(AST ast) {
    print(ast);
  }

  @Override
  public void visitCharacterLiteralTree(AST ast) {
    print(ast);
  }

}
