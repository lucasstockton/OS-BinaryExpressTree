
public class ENode {
	
	
    private char precedence_level[][] = { { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'}
    ,{ '+' , '-' }
    , { '*' , '/' }
    , { '^' } }; 

	
	public int count;
	//private int depth;
	public String value;
	ENode left,right;
	public String Inorder,Preorder, Postorder;
	
	
	ENode() {
		left =  right = null;
		count = 0;
	//	depth = 0;
		value = "";
	}
	
	ENode(String s){
		left =  right = null;
		count = 0;
	//	depth = 0;
		value = "";
		
		_create(s,this);
		

	    	printPostorder(); 
	    	printInorder();  
	    	printPreorder();  
	}
	


	private ENode _create(String s, ENode _head)
	{
		int _lowestprec = 9;
		int _location = -1;
		
	//	System.out.println("This is the input : " + s );
		for (int i = s.length() -1; i >= 0; i--)
		{
			
			char c = s.charAt(i);
			int p = precedence(c);
			if ((p == 1))
			{ // We found the first level split and move on .. 
				_head.value = Character.toString(c);
				_head.left = new ENode(s.substring(0, i));
			
				_head.right = new ENode(s.substring(i+1, s.length()));
				/*
				System.out.println("Left = " + s.substring(0, i));
				System.out.println("Right = " + s.substring(i+1, s.length()));
				*/
			return _head;
			} else if (p > 0 && p < _lowestprec)
			{
			   _lowestprec = p;
			   _location = i;
				
				
			}
			
		}
		
		if (_lowestprec == 9)
		{
			_head.value = s;
			return _head;
		} else {
	
	/*		System.out.println("Left = " + s.substring(0, _location));	
			System.out.println("Right = " + s.substring(_location, s.length())); */		
			_head.value = Character.toString(s.charAt(_location));
			_head.left = new ENode(s.substring(0, _location));	
			_head.right = new ENode(s.substring(_location+1, s.length()));	
			return _head;
			
			
		}
		
		
	//	System.out.println("'");
	//	return null;
	}
	

	
    private  int precedence(char op)
    {

        for (int i = 0; i < precedence_level.length;  i++)

            for (int j = 0; j < precedence_level[i].length; j++)

                if (precedence_level[i][j] == op) return i;  

        return -1;
    }	
	
    
    private void printPostorder(ENode node)
    {
        if (node == null)
            return;
 
        // first recur on left subtree
        printPostorder(node.left);
 
        // then recur on right subtree
        printPostorder(node.right);
 
        // now deal with the node
        System.out.print(node.value + " ");
        Postorder = Postorder + node.value + " ";
    }
 
    /* Given a binary tree, print its nodes in inorder*/
    private void printInorder(ENode node)
    {
        if (node == null)
            return;
 
        /* first recur on left child */
        printInorder(node.left);
 
        /* then print the data of node */
        System.out.print(node.value + " ");
        
        Inorder = Inorder + node.value + " ";
 
        /* now recur on right child */
        printInorder(node.right);
    }
 
    /* Given a binary tree, print its nodes in preorder*/
    private void printPreorder(ENode node)
    {
        if (node == null)
            return;
 
        /* first print data of node */
        System.out.print(node.value + " ");
 Preorder = Preorder + node.value + " ";
        
        /* then recur on left sutree */
        printPreorder(node.left);
 
        /* now recur on right subtree */
        printPreorder(node.right);
    }
       
  /*
   * These are wrapper functions to print out the orders
   *   
   */
    public void printPostorder()  {    
    	Postorder = "";
    	printPostorder(this);  }
    public  void printInorder()    {     
    	Inorder = "";
    	printInorder(this);   }
    public void printPreorder()   {    
    	Preorder = "";
    	printPreorder(this);  }
      

}
