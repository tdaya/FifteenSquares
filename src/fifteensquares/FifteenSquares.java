package fifteensquares;

import java.util.*;
import java.io.*;


public class FifteenSquares {
Random rn = new Random();                     //this object allows for randomization
int [] blocks = new int[16];                 //this tells java I want to set up an integer array 
int[] neighboursArray = new int[4];         // this is creating an array to check up, down and side tile, moving the tile and replace the location with the number you're moving to 
static Scanner input = new Scanner (System.in);

//constructor
  public FifteenSquares(){                           // this is the constructor, it loads values 1-15 into positions 0-14
       for(int i = 1; i < blocks.length; i++) {     //.length counts how many indexs we have in the array when i = 1 and  i is less than the amount of indexs in the array, then add a space
       blocks [i-1]=i;                             // this line subtracts 1 for the for loop   
       //for (int i=0; i < 400; i++)              //2.3 this for loop is to construct moving the blank space using booleans tryAbove, tryBelow, tryLeft and tryRight
      //move( rn.nextInt(blocks.length-1) + 1 ); //2.3
       }
       scramble ();                            //this allows me to call the scramble method, make sure it's not in the for loop
    }

// moving the blank space 2.3
  
public void move( int number ) {            //this method is for finding the blank space in the grid, the game is played by moving this blank
    if (number >= blocks.length)
      return;
    int i;                                 // find the slot this number is in 
    for (i=0; i < blocks.length; i++)
      if (blocks[i] == number)
        break;
    if (tryAbove(i)) return;
    if (tryBelow(i)) return;
    if (tryLeft(i))  return;
    if (tryRight(i)) return;
  }


   private boolean tryAbove( int pos ) {      // moves the blank space up
    if (pos < 4){                            //if the blank is on the edge (position < 4), do nothing
      return false;                         //does not let you swap with position 0
    }
    if (blocks[pos-4] != 0){               // if the blank space is not found, do nothing - return false 
      return false;                       // the square above is blank
    }
    else{
    swap( pos, pos-4 );                 //if the blank space is found and not on the boundary, then swap positions with its above neighbour 
    return true;                       //pos-4 is swapping with the above 
    }
  }
 
 
  private boolean tryBelow( int pos ) {      //moves blank space down 
    if (pos > 11){                          //if the blank is on the edge (position < 11), do nothing
      return false;
    }
    if (blocks[pos+4] != 0){              // if the blank space is not found, do nothing - return false 
      return false;
      }
    else{                               //moves blank space, swap it with the below neighbour
    swap( pos, pos+4 );
    return true;
    }
  }
  
  private boolean tryLeft( int pos ) {     //moving the blank space left 
    if (pos%4 == 0)                        //if position divided by 4 and remainder = 0, that means blank space is too far left
      return false;                        //blank space is off the grid, do nothing  
    if (blocks[pos-1] != 0)                //if the blank space  is not found, do nothing 
      return false;
    else{
    swap( pos, pos-1 );                    //if the blank space is found and valid, swap it with the value on the left 
    return true;
    }
  }
  
  private boolean tryRight( int pos ) {     //moving the blank space left 
    if (pos%4 == 3)                        //if position divided by 4 and remainder = 3, that means blank space is too far right
      return false;
    if (blocks[pos+1] != 0)              //blank space is off the grid, do nothing 
      return false;                     //if the blank space  is not found, do nothing 
    else{
    swap( pos, pos+1 );               //if the blank space is found and valid, swap it with the value on the right
    return true;
    }
  }

  private void swap( int one, int two ) {
    int   temp = blocks[one];
    blocks[one] = blocks[two];
    blocks[two] = temp;
  }
  
  
  // using scramble, randomization and neighbours array to scramble 15squares 2.2 
    private void scramble(){                       //this method is scambing the 15 tiles 200 times, it is moving the blank space around 200 times 
    int[] neighbours = new int [4];               //in case blank space falls in the four corners
    int numNeighbours, temp, moveTo, blank = 15;        //creating same data-type variables of the same type, and they all have the value of 15 b/c we assigned it, ex right hand, left hand, right foot = 5
    for (int i=0; i < 200; i++){                        //everything inside this for loop is going, 
    numNeighbours = findNeighbours (blank, neighbours); //findNeighbours scarmbes the position of the blank in neighbours array 
    moveTo = neighbours [rn.nextInt(numNeighbours)];    //this randomizes what index goes where because of MoveTO, using the randomnumber object to come up with a value in one of the (4) indexes from neighbours give it a value from 1-15
    temp = blocks [moveTo];                             //whatever random value MoveTo has been assigned, hold the position there on the original array 
    blocks[moveTo] = blocks[blank];                     //recall blank= 15 so it is the blank space, setting the value of temp into the blank space 
    blocks[blank] = temp;                               // blank space = temp = 15, this number is reset 
    blank = moveTo;                                     // resetting to 15            
}
} 
    
private int findNeighbours ( int blank, int [] array){    //this neighbours checks where the blank is in my neighbours array, not original 
    //if the blank is on the top row, bottom row, left column and right column; there is no neighbour respectively, in other words when the blank is at the cornors there are no neighbours
   // 12 accountss for the above neighbours, there are only 12 positions that have an above neighbour, below, left and right 
    int numNeighbours = 0;                                
    if (blank > 3)  array[numNeighbours++] = blank - 4;   //the value from the random number object is passed through these conditions to keep the values in the boundaries 
    if (blank < 12) array[numNeighbours++] = blank + 4;   //12 is the value because there are 12 possible neighbours 
    if (blank % 4 != 0) array[numNeighbours++] = blank -1;  //% dives blank by 4 and leaves a remainder
    if (blank % 4 != 3) array[numNeighbours++] = blank +1;  
    return numNeighbours;                                   
} 

// creating the grid and formattiing the spaces in between the numbers  2.1
public String format (int number){                          //this method is going to give us a space for every 0 made 
        if (number == 0) return " x ";                     //this puts a space at the end of the grid 
        return ((number < 10) ? " " : "") + number + " "; // ? : is a conditional operator. if the number is 1-9, put a space there to keep the number in line
                                                         // the + number + puts a space after the condition is met, basically it puts spaces before and after numbers in the grid
       }
  
    public void display (){                                 // this method is going to show me what is happening in the code 
        System.out.println();                              //the for loops are used for displaying it in 2D, this runs throught the first for loop and fills the row, then the loop goes to j and fills the column
         for (int i=0; i < 4; i++){                       // nesting a for loop as long as i<4 because we have 4 rows
           for (int j=0; j < 4; j++)                     // nesting another for loop because we have 4 columns
           System.out.print(format(blocks[i * 4 + j])); //populates in a 4 by 4 grid ex i=0, j = 1: 0x4+1 = 1, 0x4+2 = 2 populates the grid by using i and j
             System.out.println();  
         }
    }
    
 
    public static int inputCheck() {                   //this method checks if the input is within the acceptable range, error method
       try{
        
        int number = input.nextInt();               //number is the integer variable that stores the input 
            
       
        if (number>=0){                            // if number is > 0 then the number is returned 
                return number; 
            }  
            
            else {                                 // else we send the not valid message 
                System.out.println("Not Valid"); 
                return inputCheck();                
            }
       }
       
       catch(InputMismatchException e ){          // the catch is looking for characters anything other than integers                     
           input.next();
           System.out.println("Invalid Input: Try Again");
           return inputCheck();           
           
       }
                
    }
   
    
    public static void main(String[] args) {
        FifteenSquares puzzle = new FifteenSquares ();    //making an object out of the fifteensquares method
        //create a new object so I can call it instead of using IOutils line  
        //int number = inputCheck();
       // inputClass num = new inputClass();
       
        while (true){                                         
        puzzle.display ();                             //show the puzzle
            System.out.print("\nMove: ");             //ask for a move
            int number = inputCheck();               // calling error method
      
        
            if (number==0){                         //if user enters 0, end the game 
            System.out.println("Game Over");
            break;
            }
            else {
            puzzle.move(number);                   //calling move method 
       
       }    
    }
   }

    

//I want to create a method for error handling
//in the else statement I want to have it call the error method I create with the try and catch 


// 2.1 so far I have created the grid and allowed for user input for number of moves 
// 2.2 I need to use an output array parameter for the list of neighbors, and the return value of the function for the number of neighbors that were written to the array parameter.
// 2.2 I created the scramble method that uses the original array and a numneighbours array to scramble the numbers in the grid
// 2.2 I used the findneighbours - if blank statements to account for the scrambles blank at the corners/boundary statements 
//2.3 Now I''m adding the move functionality, what the user inputs to move the blank space to play the game    
// I used else statements in tryAbove, tryBelow, tryLeft and tryRight to move the blank in different directions 
//now I want to error handle, I want to write if number is between 1 and 15 play the game, else if number = 0 end the game, else number = error method
//when else number = error mthod, it will check for any other values/strings/symbols etc 

    