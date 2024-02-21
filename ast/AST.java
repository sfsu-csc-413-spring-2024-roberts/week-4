package ast;

import java.util.ArrayList;
import java.util.List;

import visitor.TreeVisitor;

public abstract class AST {

  public static int NodeNumber = 0;

  private List<AST> children;
  private int nodeNumber;

  public AST() {
    this.children = new ArrayList<>();
    this.nodeNumber = AST.NodeNumber++;
  }

  public void addChild(AST child) {
    this.children.add(child);
  }

  public AST getChild(int index) {
    return this.children.get(index);
  }

  public int getChildCount() {
    return this.children.size();
  }

  public int getNodeNumber() {
    return this.nodeNumber;
  }

  public abstract String toString();

  public abstract void accept(TreeVisitor visitor);
}
