package visitor;

import ast.AST;

public class PrintVisitor extends NodeVisitor {
  private int indentation = 0;

  private void print(AST tree) {
    String formatString = "%" + ((indentation + 1) * 2) + "s %d: %s";
    System.out.println(String.format(formatString, "", tree.getNodeNumber(), tree.toString()));

    this.indentation++;
    visitChildren(tree);
    this.indentation--;
  }

  @Override
  public void visitX(AST tree) {
    print(tree);
  }

  @Override
  public void visitZ(AST tree) {
    print(tree);
  }

  @Override
  public void visitCharacterLiteral(AST tree) {
    print(tree);
  }

}
