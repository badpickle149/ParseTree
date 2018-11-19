/*
ParseTree class includes methods to handle checking if the overall statement is a well formed statement of sentential
logic
*/
import java.util.*;

public class ParseTree {
   
   public ParseTreeNode overallRoot; // remember to change to private later
   private char[] operators; // converted binary operators
   // stores subformulae that can no longer be broken down as booleans
   // true - well formed
   // false - not well formed
   private Stack<Boolean> wellFormed;
   
   // creates a new ParseTree with given statement of sentential logic
   public ParseTree(String phrase) {
      this.overallRoot = new ParseTreeNode(phrase); 
      char[] operators = {'&','b','v','>'};
      this.operators = operators;
      this.wellFormed = new Stack<Boolean>();   
   }
   
   // checks if expression of sentential logic is well formed
   public void checkWellFormed() {
      this.format();
      overallRoot = this.buildTree(overallRoot);
      addExpressions(overallRoot);
      boolean wellFormed = true;
      boolean isTrue = false;
      while(!this.wellFormed.isEmpty()) {
         isTrue = this.wellFormed.pop();
         if (!isTrue) {
            wellFormed = false;
         }
      }
      overallRoot = this.buildTree(overallRoot);
      if (!this.checkBrackets(overallRoot.phrase)) {
         System.out.println("The formula is not well formed. Brackets are not correctly formed.");
      } else {
        if (wellFormed) {
          System.out.println("The formula is well formed!");
        } else {
          System.out.println("The formula is not well formed.");
        }  
      }
      
   }
   
   // replaces operators with representative symbols
   private void format() {
      String phrase = overallRoot.phrase;
      phrase = phrase.replaceAll("\\s+","");
      phrase = phrase.replace("<->","b"); // "b" for biconditional
      phrase = phrase.replace("->",">");  
      phrase = phrase.replace("v.","v");
      overallRoot.phrase = phrase;
   }
   
   // checks if brackets are well formed for a given formula/subformula
   private boolean checkBrackets(String phrase) {
      int openBracket = 0;
      for (int i = 0; i < phrase.length(); i++) {
         if (phrase.charAt(i) == '(') {
            openBracket++;
         } else if (phrase.charAt(i) == ')') {
            openBracket--;
         }
      }
      if (openBracket == 0) {
         return true;
      }
      return false;
   }
   
   // builds a parse tree. Always pass in overallRoot
   private ParseTreeNode buildTree(ParseTreeNode curr) {
      String phrase = curr.phrase;
      if (checkBrackets(phrase) && !phrase.matches("^~{0,1}[A-Z]$")) {
         int openBracket = 0;
         for (int i = 0; i < phrase.length(); i++) {
            
            // keep track of which parens counter is in
            if (phrase.charAt(i) == '(') {
               openBracket++;
            } else if (phrase.charAt(i) == ')') {
               openBracket--;
            }
            
            // recursive part  
            if (openBracket == 1) {
               char charAtI = phrase.charAt(i);   
               if (contains(charAtI)) {               
                  String withoutOuterParens = phrase.substring(1, phrase.length() - 1);
                  ParseTreeNode left = new ParseTreeNode(withoutOuterParens.substring(0, i - 1));
                  ParseTreeNode right = new ParseTreeNode(withoutOuterParens.substring(i, withoutOuterParens.length()));
                  curr.left = buildTree(left);
                  curr.right = buildTree(right);
               }
            }
         }
         return curr;
      } else if (phrase.matches("^~{0,1}[A-Z]$")) {
         return new ParseTreeNode(phrase);
      } else {
         return null;
      }
   }
   
   // Adds subformulae that can no longer be broken down
   // to the Stack
   private void addExpressions(ParseTreeNode curr) {
      if (curr != null) {
         if (curr.left == null && curr.right == null) {
            checkExpression(curr.phrase);
         } else if (curr.left == null || curr.right == null) {
            checkExpression(curr.phrase);
         } else {
            addExpressions(curr.left);
            addExpressions(curr.right);
         }
      }
   }
   
   // helper method for addExpressions. Checks for matches
   private void checkExpression(String phrase) {
      if (phrase.matches("^~{0,1}[A-Z]$")) {
            wellFormed.push(true);
         } else {
            wellFormed.push(false);
         }
   }
   
   // checks if given character is in list of operators
   private boolean contains(char c) {
      for (int i = 0; i < operators.length; i++) {
         if (operators[i] == c) {
            return true;
         }
      }
      return false;
   }
   
}