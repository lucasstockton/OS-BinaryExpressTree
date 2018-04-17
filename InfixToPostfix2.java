/*

	Write a java program to covert a valid infix expression to a postfix expression.
	The expression can have the following operators: '+', '-', '%','*', '/' and alphabets from a to z .
	The precedence of the operators (+,-) is lesser than precedence of operators (*,/,%) . Parenthesis have the highest precedence and the expression
	inside it must be converted first . 
	 	
	Examples
	Infix				Postfix
	a%b				ab%
	a+b*(c+d) 			abcd+*+   
	a+b*c+d			abc*+d+
	
	Use the stack data structure to solve the problem. The stack is used to store the operators and parenthesis to enforce the precedence 
	Start parsing the expression from left to right. 
	
	If the current character is ,
	an alphabet , append it to the temporary output string 
	an operator , pop all the operators (to the temporary output string ) that have precedence equal to or higher than current operator and then push the current operator 
	a right paranthesis, pop all operators (to the temporary output string ) until a left parenthesis is found in the stack
	a left parenthesis , simply push it onto the stack

	In the end pop all the operators in the stack to the temporary output string


	A stack is a data structure based on the principle of Last In First Out (LIFO). 
	It is a container of nodes and has two basic operations: push and pop. 
	Push adds a given node to the top of the stack leaving previous nodes below. 
	Pop removes and returns the current top node of the stack. 
	peek , returns the value at the top of the stack , but doesnt remove it
	isEmpty check whether stack is empty or not , generally called before calling pop/peek

	A frequently used metaphor is the idea of a stack of plates in a spring loaded cafeteria stack. 
	In such a stack, only the top plate is visible and accessible to the user, all other plates remain hidden. 
	As new plates are added, each new plate becomes the top of the stack, hiding each plate below, pushing the stack of plates down.

	Create a separate Stack class to implement the operator stack using arrays. 
	
*/

import java.io.*;

	class Stack
	{
	char a[]=new char[100];
	int top=-1;
	
	void push(char c)
	{
	try
	{
	a[++top]= c;
	}
	catch(StringIndexOutOfBoundsException e)
	{
	System.out.println("Stack full , no room to push , size=100");
	System.exit(0);
	}
	}

	char pop()
	{
	return a[top--];
	}

	boolean isEmpty()
	{
	return (top==-1)?true:false;
	}

	char peek()
	{
	return a[top];
	}

	}	


	public class InfixToPostfix2
	{

	static Stack operators = new Stack();
		
	public static void main(String argv[]) throws IOException
	{
		String infix;
	
		//create an input stream object
		BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));
		
		//get input from user
		System.out.print("\nEnter the algebraic expression in infix: ");
		infix = keyboard.readLine();
		
		//output as postfix
		System.out.println("The expression in postfix is:" + toPostfix(infix));
		
	}
	
	private static String toPostfix(String infix)
	//converts an infix expression to postfix
	{
		char symbol;
		String postfix = "";
		
                for(int i=0;i<infix.length();++i)
					//while there is input to be read
		{
			symbol = infix.charAt(i);
			//if it's an operand, add it to the string
                        if (Character.isLetter(symbol))
				postfix = postfix + symbol;
			else if (symbol=='(')
			//push (
			{
				
				operators.push(symbol);
			}
			else if (symbol==')')
			//push everything back to (
			{
				while (operators.peek() != '(')
				{
					postfix = postfix + operators.pop();
				}
				operators.pop();		//remove '('
			}
			else
			//print operators occurring before it that have greater precedence
			{
                                while (!operators.isEmpty() && !(operators.peek()=='(') && prec(symbol) <= prec(operators.peek()))
					postfix = postfix + operators.pop();
				
				operators.push(symbol);
			}
		}
		while (!operators.isEmpty())
			postfix = postfix + operators.pop();
		return postfix;
	}
	
	
	static int prec(char x)
	{
		if (x == '+' || x == '-')
			return 1;
		if (x == '*' || x == '/' || x == '%')
			return 2;
				if (x == '^')
			return 3;	
			
		return 0;
	}
}
