package visitor;

import ast.AST;

public class PrintVisitor extends TreeVisitor {

  private int indentation = 0;

  private void print(AST ast) {

    String formatString = "%" + ((indentation + 1) * 2) + "d: %s";
    System.out.println(String.format(formatString, ast.getNodeNumber(), ast.toString()));

    this.indentation++;
    this.visitChildren(ast);
    this.indentation--;
  }

  @Override
  public void visitX(AST ast) {
    print(ast);
  }

  @Override
  public void visitZ(AST ast) {
    print(ast);
  }

  @Override
  public void visitCharLiteral(AST ast) {
    print(ast);
  }

}
