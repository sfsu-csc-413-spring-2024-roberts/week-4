package parser;

import ast.AST;
import ast.CharacterLiteralTree;
import ast.XTree;
import ast.ZTree;
import lexer.Lexer;
import visitors.PrintVisitor;
import visitors.TreeVisitor;

public class Parser {

  private Lexer lexer;
  private char currentToken;

  public Parser(String inputString) throws Exception {
    this.lexer = new Lexer(inputString);
    advance();
  }

  private void advance() throws Exception {
    this.currentToken = this.lexer.nextToken();
  }

  public AST parse() throws Exception {
    AST root = X();

    if (this.currentToken != '\0') {
      throw new Exception(String.format("Syntax error: unexpected token %c", this.currentToken));
    }

    return root;
  }

  /*
   * X → aX
   * X → bZX
   * X → c
   */
  private AST X() throws Exception {
    AST node = new XTree();

    switch (this.currentToken) {
      case 'a':
        node.addChild(new CharacterLiteralTree(this.currentToken));
        advance();
        node.addChild(X());
        break;
      case 'b':
        node.addChild(new CharacterLiteralTree(this.currentToken));
        advance();
        node.addChild(Z());
        node.addChild(X());
        break;
      case 'c':
        node.addChild(new CharacterLiteralTree(this.currentToken));
        advance();
        break;
      default:
        throw new Exception(String.format("Syntax error: unexpected token %c", this.currentToken));
    }

    return node;
  }

  /*
   * Z → dZ
   * Z → d
   */
  private AST Z() throws Exception {
    AST node = new ZTree();

    if (this.currentToken != 'd') {
      throw new Exception(String.format("Syntax error: unexpected token %c", this.currentToken));
    }

    node.addChild(new CharacterLiteralTree(this.currentToken));
    advance();

    if (this.currentToken == 'd') {
      node.addChild(Z());
    }

    return node;
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("usage: java parser.Parser <input string>");
      System.exit(1);
    }

    try {
      Parser parser = new Parser(args[0]);
      AST root = parser.parse();

      System.out.println("===== AST =====");
      TreeVisitor visitor = new PrintVisitor();
      root.accept(visitor);

      System.out.println(String.format("\n%s is a valid string.", args[0]));
    } catch (Exception e) {
      System.err.println(String.format("%s is not valid string:", args[0]));
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
