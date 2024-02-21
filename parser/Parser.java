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
    this.current = this.lexer.nextToken();
  }

  public AST parse() throws Exception {
    AST tree = X();

    if (this.current != '\0') {
      syntaxError();
    }

    return tree;
  }

  private void consume() {
    this.current = this.lexer.nextToken();
  }

  private void syntaxError() throws Exception {
    throw new Exception(String.format(
        "Invalid character: %c at column %d", this.current, this.lexer.getCurrentIndex()));
  }

  /*
   * X → aX
   * X → bZX
   * X → c
   */
  private AST X() throws Exception {
    AST node = new XTree();

    switch (this.current) {
      case 'a':
        node.addChild(new CharLiteralTree(this.current));
        consume();
        node.addChild(X());
        break;
      case 'b':
        node.addChild(new CharLiteralTree(this.current));
        consume();
        node.addChild(Z());
        node.addChild(X());
        break;
      case 'c':
        node.addChild(new CharLiteralTree(this.current));
        consume();
        break;
      default:
        syntaxError();
        break;
    }

    return node;
  }

  /*
   * Z → dZ
   * Z → d
   */
  private AST Z() throws Exception {
    AST node = new ZTree();

    if (this.current != 'd') {
      syntaxError();
    }

    node.addChild(new CharLiteralTree(this.current));
    consume();

    if (this.current == 'd') {
      node.addChild(Z());
    }

    return node;
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("An input string must be provided.");
      System.exit(1);
    }

    try {
      Parser parser = new Parser(args[0]);
      AST ast = parser.parse();

      PrintVisitor visitor = new PrintVisitor();
      System.out.println("===== AST =====");
      visitor.visitX(ast);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }

    System.out.println(String.format("\n\n%s is a valid string for this grammar", args[0]));
  }

}
