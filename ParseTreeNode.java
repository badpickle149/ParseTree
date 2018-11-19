/*
Parse Tree object has two fields:
left and right
*/

public class ParseTreeNode {
   
   public String phrase;
   public ParseTreeNode left;
   public ParseTreeNode right;
   
   // phrase: a String representing a statement of sentential logic
   // left and right nodes assigned to null
   public ParseTreeNode (String phrase) {
      this.phrase = phrase;
      this.left = null;
      this.right = null;
   }

   // phrase: a String phrase of sentential logic
   // left/right: a ParseTreeNode object
   // creates a new parse tree node
   public ParseTreeNode (String phrase, ParseTreeNode left, ParseTreeNode right) {
      this.phrase = phrase;
      this.left = left;
      this.right = right;
   }
}