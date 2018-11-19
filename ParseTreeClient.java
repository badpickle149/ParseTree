/*
Parse tree checks to see if a passed phrase of sentential logic is well formed. 
A phrase of sentential logic is well formed if it can be broken down into atomic formula.
*/
import java.util.*;

public class ParseTreeClient {
   
   public final String[] OPERATERS = {"&","v.","~","->","<->"}; // sentential logic operators
   
   public static void main(String[] args) {
      ParseTree tree = new ParseTree("(A v. B v. C)");
      tree.checkWellFormed();
   }
}