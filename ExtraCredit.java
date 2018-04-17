import java.util.*;

/**
 * Write a description of class ExtraCredit here.
 *
 * @author Lucas Heuman
 * @version (a version number or a date)

(Optional – Extra 10 credits goes to your next Exam grade)
Consider a maze made up of a rectangular array of squares, 
such as the following one:

The Xs represent a blocked square and form the walls of the maze. Let’s consider mazes that have only one entrance and one exit 
on opposite sides of the maze, as in our example. Beginning at the entrance at the top left side of the maze, find a path to the 
exit at the bottom right side. You can move only up, down, left, or right. 

Each square in the maze can be in one of four states: clear, blocked, path, or visited. Initially, each square is either 
clear or blocked. If a square lies on a successful path, mark it with a period. If you enter a square but it does not lead to 
a successful path, mark the square as visited. 

Let a two-dimensional array represent the maze. Use a stack-based algorithm to find a path through the maze. Some mazes might 
have more than one successful pat, while others have no path.

[Note: To earn the extra 10 credits, you have to show and explain your code to the instructor. The instructor will grade it based 
on your coding and explanations.]

 */
public class ExtraCredit
{
    // instance variables - replace the example below with your own
    //  private int x;

    /**
     * Constructor for objects of class ExtraCredit
     */
        final static char visited = '.';
        final static char unexplored = ' ';
        
        
        
    public static class MapPoint {
        int x;
        int y;

        MapPoint ()
        {
            x = 0;
            y= 0;
        }

        MapPoint ( int _x , int _y) // The Constructor for the Map Location. .
        {
            x = _x;
            y = _y; 
        }

    } // MapPoint
    
    
    public static void main(String[] args)
    {
        MapPoint currentloc = new MapPoint(1,0); //; Create a location

        Stack<MapPoint> history = new Stack();  // Make a breadcrum path to follow

        int height = 7;
        int width = 13;
        //With times Height

        //        int currentx = 1;
        //        int currenty = 0;

        int finishx = 12;
        int finishy = 5;



        char[][] mazeArray =  //new char[width][height]; 
            {
                {'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X'} ,
                {' ' , ' ' , ' ' , 'X' , ' ' , ' ' , ' ' , 'X' , 'X' , 'X' , 'X' , ' ' , 'X'} ,
                {'X' , 'X' , ' ' , ' ' , ' ' , 'X' , ' ' , ' ' , ' ' , ' ' , 'X' , ' ' , 'X'} ,
                {'X' , 'X' , ' ' , 'X' , 'X' , ' ' , ' ' , ' ' , 'X' , ' ' , 'X' , ' ' , 'X'} ,
                {'X' , 'X' , ' ' , ' ' , 'X' , ' ' , ' ' , ' ' , 'X' , ' ' , ' ' , ' ' , 'X'} ,
                {'X' , 'X' , ' ' , 'X' , 'X' , 'X' , 'X' , 'X' , ' ' , ' ' , ' ' , ' ' , ' '} ,
                {'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X' , 'X'}
            };
        System.out.println("Starting the Programing ...");

        PrintArray(height,width,mazeArray);
        //  Populate the Starting Possition                 
        mazeArray[currentloc.x][currentloc.y] = visited;                     
        PrintArray(height,width,mazeArray);

        // Credit Vars to use ..            
        int x = 0;
        int y = 0;         

        //while (tru
     

        int counter = 40; 
        while (!((finishx == currentloc.x)  && (finishy == currentloc.y)))
        {

            
            if (counter-- < 0) break;
            System.out.println(" Currently Location x y : " + currentloc.x + "|" + currentloc.y + " History > " + history.size());            
        

            //try right
            x = currentloc.x ;
            y = currentloc.y+1;

//            if  (TryNext(x,y,mazeArray)) System.out.println("Try Next True");
            
            if  (TryNext(x,y,mazeArray))  
            {
  
                System.out.println(" We can move right");
                history.push(currentloc);
                currentloc = new MapPoint(x,y);
                mazeArray[x][y] = visited;   
                continue;
     
            }
           //  else System.out.println(" We can not move right"+ x + ":" + y + "<" +mazeArray[x][y] +">");

//             System.out.println(" > " );     

            // try down            
            x = currentloc.x + 1;
            y = currentloc.y;
            if  ((TryNext(x,y,mazeArray)) && unexplored == mazeArray[x][y])  {

                System.out.println(" We can move down");
                history.push(currentloc);
                currentloc = new MapPoint(x,y);
                mazeArray[x][y] = visited;   
                continue;   
            }
          //  else System.out.println(" We can not move down"+ x + ":" + y + "<");

            //  System.out.println(" > " + x + ":" + y + "<");           

            // try up
            x = currentloc.x- 1;
            y = currentloc.y;

            if  ((TryNext(x,y,mazeArray))  && unexplored == mazeArray[x][y]) 
            {
                System.out.println(" We can move up");
                history.push(currentloc);
                currentloc = new MapPoint(x,y);
                mazeArray[x][y] = visited;   
                continue;     
            }
         //   else  System.out.println(" We can not move up"+ x + ":" + y + "<");
            // System.out.println(" > " + x + ":" + y + "<");  

            // try left

            x = currentloc.x ;
            y = currentloc.y - 1;            
            if  ((TryNext(x,y,mazeArray)) && unexplored == mazeArray[x][y]) 
            { 
                System.out.println(" We can move this right");         
                history.push(currentloc);
                currentloc = new MapPoint(x,y);
                mazeArray[x][y] = visited;   
                continue;    
            }
          //  else  System.out.println(" We can not move right"+ x + ":" + y + "<"); 

            System.out.println(" NO EXIT > " + x + ":" + y + "<");  
            
            if (history.empty()) break; else                      
            currentloc = history.pop();

        }
        
        
        PrintArray(height,width,mazeArray);  

        
        String path = " (" + currentloc.x + "," + currentloc.y + ")";
        while (!history.empty())
        {
        currentloc = history.pop();
        path = " (" + currentloc.x + "," + currentloc.y + ")"+ path ;
            }
        
        System.out.print("Path from exit " + path);
        
        
        }   // Main Array

    public ExtraCredit()
    {                    

        //    PrintArray(height,width,mazeArray);   
        //   for (int w = 0; w < width; w++)
        //     for (int h = 0; h < height; h++) mazeArray[w][h] = ' ';

        // initialise instance variables

    }

    public static boolean IsValid(int x , int y)
    {
     //    { System.out.println(" IsValid > " + x + ":" + y + "<");   
        
        if (x < 0) { System.out.println(" x < 0"); return false;}
        if (y < 0) { System.out.println(" y < 0 "); return false;}
        if (7 <= x) { System.out.println(" 13 <= x"); return false;}
        if (13 <= y) { System.out.println(" 7 <= Y"); return false;}
        return true; 
    }

    public static char findNextMove(int height, int width, char mazeArray[][])
    {

        return 's';
    }

    public static boolean TryNext( int width , int height, char mazeArray[][])
    {

        if (!IsValid(width,height)) return false ;
        if (mazeArray[width][height] == unexplored) return true;
        return false; 
    }

    public static void  PrintArray(int height, int width, char mazeArray[][])
    {

        for (int h = 0; h < height; h++)                     

        {
            for (int w = 0; w < width; w++) System.out.print(mazeArray[h][w]);   
            System.out.println(' ');
        }
    }

}