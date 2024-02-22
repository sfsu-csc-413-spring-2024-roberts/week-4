package ast;

import java.util.ArrayList;
import java.util.List;

import visitors.TreeVisitor;

public abstract class AST {

  public static int NodeCount;

  protected List<AST> children;
  protected int nodeNumber;

  public AST() {
    this.nodeNumber = NodeCount++;
    this.children = new ArrayList<>();
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

  public List<AST> getChildren() {
    return List.copyOf(this.children);
  }

  public int getNodeNumber() {
    return this.nodeNumber;
  }

  public abstract String toString();

  public abstract void accept(TreeVisitor visitor);
}
