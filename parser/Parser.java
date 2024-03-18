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
     * X → SE '>' SE
     * X → SE '>=' SE
     * X → 'if' E 'then' BLOCK
     * X → 'from' RANGE STEP BLOCK
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
            case 'i':
                // Handle 'if' statement
                node.addChild(new CharLiteralTree(this.current));
                consume();
                node.addChild(E());
                if (this.current != 't') syntaxError();
                consume();
                if (this.current != 'h') syntaxError();
                consume();
                if (this.current != 'e') syntaxError();
                consume();
                node.addChild(BLOCK());
                break;
            case 'f':
                // Handle 'from' iteration statement
                node.addChild(new CharLiteralTree(this.current));
                consume();
                node.addChild(RANGE());
                node.addChild(STEP());
                node.addChild(BLOCK());
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

    /*
     * E → SE '>' SE
     * E → SE '>=' SE
     */
    private AST E() throws Exception {
        AST node = new ETree();

        node.addChild(SE());
        if (this.current != '>') syntaxError();
        consume();
        node.addChild(SE());

        return node;
    }

    /*
     * S → 'if' E 'then' BLOCK
     * S → 'from' RANGE STEP BLOCK
     */
    private AST S() throws Exception {
        AST node = new STree();

        if (this.current == 'i') {
            node.addChild(new CharLiteralTree(this.current));
            consume();
            node.addChild(E());
            if (this.current != 't') syntaxError();
            consume();
            if (this.current != 'h') syntaxError();
            consume();
            if (this.current != 'e') syntaxError();
            consume();
            node.addChild(BLOCK());
        } else if (this.current == 'f') {
            node.addChild(new CharLiteralTree(this.current));
            consume();
            node.addChild(RANGE());
            node.addChild(STEP());
            node.addChild(BLOCK());
        } else {
            syntaxError();
        }

        return node;
    }

    /*
     * RANGE → '(' E 'to' E ')'
     */
    private AST RANGE() throws Exception {
        AST node = new RANGE_Tree();

        if (this.current != '(') syntaxError();
        consume();
        node.addChild(E());
        if (this.current != 't') syntaxError();
        consume();
        if (this.current != 'o') syntaxError();
        consume();
        node.addChild(E());
        if (this.current != ')') syntaxError();
        consume();

        return node;
    }

    /*
     * STEP → 'step' E
     */
    private AST STEP() throws Exception {
        AST node = new STEP_Tree();

        if (!Character.isDigit(this.current)) syntaxError();
        while (Character.isDigit(this.current)) {
            node.addChild(new CharLiteralTree(this.current));
            consume();
        }

        return node;
    }

    /*
     * BLOCK → '{' X '}'
     */
    private AST BLOCK() throws Exception {
        AST node = new BLOCK_Tree();

        if (this.current != '{') syntaxError();
        consume();
        node.addChild(X());
        if (this.current != '}') syntaxError();
        consume();

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
