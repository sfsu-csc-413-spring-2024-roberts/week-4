package parser;

import ast.AST;
import ast.CharLiteralTree;
import ast.XTree;
import ast.ZTree;
import lexer.Lexer;
import visitor.PrintVisitor;

public class Parser {
  private Lexer lexer;
  private char current;

  public Parser(String inputString) {
    this.lexer = new Lexer(inputString);
  }

  /**
   * Starting symbol is X
   */
  public AST execute() throws Exception {
    this.current = this.lexer.nextToken();

    AST root = X();

    if (this.current != '\0') {
      error();
    }

    return root;
  }

  private void advance() {
    this.current = this.lexer.nextToken();
  }

  /**
   * X → aX
   * X → bZX
   * X → c
   *
   * @throws Exception
   */
  private AST X() throws Exception {
    AST node = new XTree();

    switch (this.current) {
      case 'a':
        node.addChild(new CharLiteralTree(this.current));
        advance();
        node.addChild(X());
        break;
      case 'b':
        node.addChild(new CharLiteralTree(this.current));
        advance();
        node.addChild(Z());
        node.addChild(X());
        break;
      case 'c':
        node.addChild(new CharLiteralTree(this.current));
        advance();
        break;
      default:
        error();
    }

    return node;
  }

  /**
   * Z → dZ
   * Z → d
   *
   * @throws Exception
   */
  private AST Z() throws Exception {
    AST node = new ZTree();

    if (this.current != 'd') {
      error();
    }

    node.addChild(new CharLiteralTree(this.current));
    advance();

    while (this.current == 'd') {
      node.addChild(new CharLiteralTree(this.current));
      advance();
    }

    return node;
  }

  private void error() throws Exception {
    throw new Exception(
        String.format("Invalid character: %c", this.current));
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("An input string must be provided.");
      System.exit(1);
    }

    Parser parser = new Parser(args[0]);
    try {
      AST ast = parser.execute();

      System.out.println("===== AST =====");
      PrintVisitor visitor = new PrintVisitor();
      visitor.visitX(ast);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }

    System.out.println(String.format("\n\n%s is a valid string.", args[0]));
  }
}
