import java.util.Stack;
import java.util.Scanner;
import java.util.*;
import java.io.OutputStreamWriter;
import java.io.IOException;


/**
 * Write a description of class Prototype here.
 *
 * @author Lucsa Heuman
 * @version Prototype 1
 * First I imported the Scanner Class import java.util.Scanner; I use this to grab the input from the user.
 * 
 * operands  are the integers and operators are the math functions
 * 
 */
public class Prototype
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Prototype
     */

    /*
    static char precedence_level0[] = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9};
    static char precedence_level1[] = { '+' , '-' };
    static char precedence_level2[] = { '*' , '/' };
    static char precedence_level3[] = { '^' }; 
     */

    static char precedence_level[][] = { { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'}
        ,{ '+' , '-' }
        , { '*' , '/' }
        , { '^' } }; 


    

public static class Node {
 
    String value;
    Node left, right;
 
    Node(String item) {
        value = item;
        left = right = null;
    }
  
    
    
    void printPostorder(Node node)
    {
        if (node == null)
            return;
 
        // first recur on left subtree
        printPostorder(node.left);
 
        // then recur on right subtree
        printPostorder(node.right);
 
        // now deal with the node
        System.out.print(node.value + " ");
    }
 
    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(Node node)
    {
        if (node == null)
            return;
 
        /* first recur on left child */
        printInorder(node.left);
 
        /* then print the data of node */
        System.out.print(node.value + " ");
 
        /* now recur on right child */
        printInorder(node.right);
    }
 
    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(Node node)
    {
        if (node == null)
            return;
 
        /* first print data of node */
        System.out.print(node.value + " ");
 
        /* then recur on left sutree */
        printPreorder(node.left);
 
        /* now recur on right subtree */
        printPreorder(node.right);
    }
    
    
    
    void printPostorder()  {     printPostorder(this);  }
    void printInorder()    {     printInorder(this);   }
    void printPreorder()   {     printPreorder(this);  }
    
    
    
    
    public void printTree(OutputStreamWriter out) throws IOException {
        if (right != null) {
            right.printTree(out, true, "");
        }
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, "");
        }
    }
   
    private void printNodeValue(OutputStreamWriter out) throws IOException {
        
        out.write(value);
    
    out.write('\n');
}
// use string and not stringbuffer on purpose as we need to change the indent at each recursion
private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
    if (right != null) {
        right.printTree(out, true, indent + (isRight ? "        " : " |      "));
    }
    out.write(indent);
    if (isRight) {
        out.write(" /");
    } else {
        out.write(" \\");
    }
    out.write("----- ");
    printNodeValue(out);
    if (left != null) {
        left.printTree(out, false, indent + (isRight ? " |      " : "        "));
    }
}      
 
    
    
    
    
}
    
    
    public static class testNode {

        boolean isOp;
        char op;
        int value;
        testNode  next;
        int count = 0; 

        void add(char _op)
        {

            if (count == 0)
            {

                op = _op;
                count++;

            } else
            {
                 
                testNode nSearch = this; 
                while (nSearch.next != null)
                { 
                    nSearch = nSearch.next;
                }

                nSearch.next = new testNode();
                nSearch.next.op = _op;
                nSearch.next.isOp = true;
                count++;
            }

        }

        void add(int _value)
        {

            if (count == 0)
            {
                value = _value;
                count++;
            } else 
            {

                testNode nSearch = this; 
                while (nSearch.next != null)
                { 
                    nSearch = nSearch.next;
                }

                nSearch.next = new testNode();
                nSearch.next.value = _value;
                nSearch.next.isOp = false;
                count++;
            }
        }   

        void print()
        {
            testNode nSearch = this; 
            while (nSearch.next != null)
            { 
                if (nSearch.isOp) System.out.println(nSearch.op); else System.out.println(nSearch.value);

                nSearch = nSearch.next;
            }

            if (nSearch.isOp) System.out.println(nSearch.op); else System.out.println(nSearch.value);

        }   

                
    }
	public static void main(String[] args) {

        Deque<Character> operatorStack = new ArrayDeque<>();    
        String infix_input; // Input String hsa the INFIX input from the user to convert to Post Fix
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the express to evaluate :");
        infix_input = scan.nextLine();
        //infix_input = "3 * (5) / 4 - 3 + 7 ^2 * 6";
        System.out.println(" You are trying to process -> " + infix_input);

        
        // Here we are using a FOR Loop to look at each character
        String  temp_token = ""; // We are using this to store 
        boolean isNegative = false;

        // The Postfix expression
        String postfix = "";

        // Post Fix Linked List
        testNode postNode = new testNode();

        for (int i =0; i < infix_input.length(); i++)
        {
        	char chr = infix_input.charAt(i); 
        	
            System.out.println("Precedence of " + chr + " is " + precedence(chr));

            if   (chr == '(') {
                isNegative = true;
                continue;
            }

            if (precedence(chr) == 0)
            {
                temp_token = temp_token + chr;

            } else if (precedence(chr) > 0)
            {

                // IF temp_tocken empty then error  
                if (temp_token.equals(""))
                {
                    /// ERROR HERE
                } else
                {
                    // else convert temp token to int and push to stack. 
                    int newInt;
                    // Make sure you set if it is negative ..
                    if (isNegative)  newInt = Integer.parseInt(temp_token) * -1;
                    else newInt = Integer.parseInt(temp_token);

                    postNode.add(newInt);  
                    temp_token = "";
                    isNegative = false;

                    if (operatorStack.isEmpty())
                    {
                        // 1. if the operator stack is empty
                        // 2. Push the current operator onto the stack.                     
                        operatorStack.push(chr);

                    } else
                    {
                        // 3. Peek the operator stack and let topOp be the top operator.
                        char topOp = operatorStack.peek();
                        char currentOp = chr;
                        // 4. if the precedence of the current operator is greater than the precedence of topOp
                        if (precedence(topOp) < precedence(currentOp))
                        {
                            // 5. Push the current operator onto the stack.
                            operatorStack.push(currentOp);

                            
                            
                        } else
                        {
                            // 6. while the stack is not empty and the precedence of the current operator is less
                            // than or equal to the precedence of topOp

                            while (!operatorStack.isEmpty() && precedence(currentOp) <= precedence(topOp)) 
                            {
                                postNode.add(operatorStack.pop());

                                // 7. Pop topOp off the stack and append it to postfix.
                                // 8. if the operator stack is not empty
                                // 9. Peek the operator stack and let topOp be the top operator.
                                // 10 . Push the current operator onto the stack. 
                                if (!operatorStack.isEmpty())
                                topOp = operatorStack.peek();

                            }
                            operatorStack.push(currentOp);

                        }

                
                    }

                 
                
                }
                
                
            }

        } // end For loop
 
        int newInt;
        // Make sure you set if it is negative ..
        if (isNegative)  newInt = Integer.parseInt(temp_token) * -1;
        else newInt = Integer.parseInt(temp_token);

        postNode.add(newInt);    
        
        while (!operatorStack.isEmpty()) 
        {
            postNode.add(operatorStack.pop());
        }
        
        
        Stack<Node> st = new Stack();
        Node t, t1, t2;
 
        // Traverse through every character of
        // input expression
     //   for (int i = 0; i < postfix.length; i++) {
 testNode tempNode = postNode;
        while (tempNode != null)
        {
            // If operand, simply push into stack
            if (!tempNode.isOp) {
            	String as = Integer.toString(tempNode.value);
                t = new Node(as);
                st.push(t);
            } else // operator
            {
                t = new Node(Character.toString(tempNode.op));
 
                // Pop two top nodes
                // Store top
                t1 = st.pop();      // Remove top
                t2 = st.pop();
 
                //  make them children
                t.right = t1;
                t.left = t2;
 
                // System.out.println(t1 + "" + t2);
                // Add this subexpression to stack
                st.push(t);
            }
            
            tempNode = tempNode.next;
        } // for loop now while loop -lucas
 
        //  only element will be root of expression
        // tree
        t = st.peek();
        st.pop();
 
        // return t;
        
        postNode.print();
        
        
        OutputStreamWriter a = new OutputStreamWriter(System.out);
        try
        {
            
                System.out.println("");
        t.printTree(a);
        	a.flush();
     		} catch (IOException ex) {
			ex.printStackTrace();
		}
        
        
        System.out.println("Post Order : ");
        t.printPostorder();
        System.out.println("");
        System.out.println(" Inorder : ");
        t.printInorder();
        System.out.println("");
        System.out.println(" Preorder : "); 
        t.printPreorder();           
        
        
        
        
    }
        
    
    private static int precedence(char op)
    {

        for (int i = 0; i < precedence_level.length;  i++)

            for (int j = 0; j < precedence_level[i].length; j++)

                if (precedence_level[i][j] == op) return i;  

        return -1;
    }

}
