/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_Sudoku.html
 * https://javapointers.com/java/java-se/actionlistener/
 * https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
 * https://www.cs.purdue.edu/homes/bxd/java/Applets/wk7.pdf
 * https://www.oreilly.com/library/view/learning-java-4th/9781449372477/ch16s02.html
 */
package sudoku.with.constructor;
import java.awt.*;        // Uses AWT's Layout Managers
import java.awt.event.*;  // Uses AWT's Event Handlers
import javax.swing.*;     // Uses Swing's Container/Components
/**
 *
 * @author ekawamoto
 */
public class SudokuGame 
{
    
/**
 * The Sudoku game.
 * To solve the number puzzle, each row, each column, and each of the
 * nine 3×3 sub-grids shall contain all of the digits from 1 to 9
 */
    public static class Sudoku extends JFrame // declare this static in order to make this work
    {
       // Name-constants for the game properties
       public static final int GRID_SIZE = 9;    // Size of the board
       public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

       // Name-constants for UI control (sizes, colors and fonts)
       public static final int CELL_SIZE = 60;   // Cell width/height in pixels
       public static final int CANVAS_WIDTH  = CELL_SIZE * GRID_SIZE;
       public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
                                                 // Board width/height in pixels
       public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
       public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0);  // RGB
       public static final Color OPEN_CELL_TEXT_NO = Color.RED;
       public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
       public static final Color CLOSED_CELL_TEXT = Color.BLACK;
       public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

       // The game board composes of 9x9 JTextFields,
       // each containing String "1" to "9", or empty String
       private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];

       // Puzzle to be solved and the mask (which can be used to control the
       //  difficulty level).
       // Hardcoded here. Extra credit for automatic puzzle generation
       //  with various difficulty levels.
       private int[][] puzzle =
          {{5, 3, 4, 6, 7, 8, 9, 1, 2},
           {6, 7, 2, 1, 9, 5, 3, 4, 8},
           {1, 9, 8, 3, 4, 2, 5, 6, 7},
           {8, 5, 9, 7, 6, 1, 4, 2, 3},
           {4, 2, 6, 8, 5, 3, 7, 9, 1},
           {7, 1, 3, 9, 2, 4, 8, 5, 6},
           {9, 6, 1, 5, 3, 7, 2, 8, 4},
           {2, 8, 7, 4, 1, 9, 6, 3, 5},
           {3, 4, 5, 2, 8, 6, 1, 7, 9}};
       // For testing, open only 2 cells.
       private boolean[][] masks =
          {{false, false, false, false, false, true, false, false, false},
           {false, false, false, false, false, false, false, false, true},
           {false, false, false, false, false, false, false, false, false},
           {false, false, false, false, false, false, false, false, false},
           {false, false, false, false, false, false, false, false, false},
           {false, false, false, false, false, false, false, false, false},
           {false, false, false, false, false, false, false, false, false},
           {false, false, false, false, false, false, false, false, false},
           {false, false, false, false, false, false, false, false, false}};

       /**
        * Constructor to setup the game and the UI Components
        */
       public Sudoku() 
       {
          System.out.println("start default constructor ...");
          
          Container cp = getContentPane();
          cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // 9x9 GridLayout

          // Allocate a common listener as the ActionEvent listener for all the
          //  JTextFields
          // ... [TODO 3] (Later) ....

          // Construct 9x9 JTextFields and add to the content-pane
          for (int row = 0; row < GRID_SIZE; ++row) {
             for (int col = 0; col < GRID_SIZE; ++col) {
                tfCells[row][col] = new JTextField(); // Allocate element of array
                cp.add(tfCells[row][col]);            // ContentPane adds JTextField
                if (masks[row][col]) {
                   tfCells[row][col].setText("");     // set to empty string
                   tfCells[row][col].setEditable(true);
                   tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);

                   // Add ActionEvent listener to process the input
                   // ... [TODO 4] (Later) ...
                } else {
                   tfCells[row][col].setText(puzzle[row][col] + "");
                   tfCells[row][col].setEditable(false);
                   tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
                   tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
                }
                // Beautify all the cells
                tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                tfCells[row][col].setFont(FONT_NUMBERS);
             }
          }

          // Set the size of the content-pane and pack all the components
          // under this container.
          cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
          pack();

          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
          setTitle("Sudoku");
          setVisible(true);
          System.out.println("end default constructor ...");
       }

       // https://www.geeksforgeeks.org/constructor-overloading-java/
       public Sudoku(boolean[][] masks2) 
       {
          System.out.println("start parameterized constructor ...");
          
          Container cp = getContentPane();
          cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // 9x9 GridLayout

          // Allocate a common listener as the ActionEvent listener for all the
          //  JTextFields
          // ... [TODO 3] (Later) ....

          // Construct 9x9 JTextFields and add to the content-pane
          for (int row = 0; row < GRID_SIZE; ++row) {
             for (int col = 0; col < GRID_SIZE; ++col) {
                tfCells[row][col] = new JTextField(); // Allocate element of array
                cp.add(tfCells[row][col]);            // ContentPane adds JTextField
                if (masks2[row][col]) {
                   tfCells[row][col].setText("");     // set to empty string
                   tfCells[row][col].setEditable(true);
                   tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);

                   // Add ActionEvent listener to process the input
                   // ... [TODO 4] (Later) ...
                } else {
                   tfCells[row][col].setText(puzzle[row][col] + "");
                   tfCells[row][col].setEditable(false);
                   tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
                   tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
                }
                // Beautify all the cells
                tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                tfCells[row][col].setFont(FONT_NUMBERS);
             }
          }

          // Set the size of the content-pane and pack all the components
          // under this container.
          cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
          pack();

          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
          setTitle("Sudoku");
          setVisible(true);
          System.out.println("end parameterized constructor ...");
       }       
       
       public boolean GetMaskElem(int i, int j)
       {
           boolean b = false;
           
           if (i < GRID_SIZE && j < GRID_SIZE)
           {
               b = masks[i][j];
               System.out.println("mask element row " + i + ", col " + j + " is " + b);
               return b;
           }
           else
           {
               System.out.println("mask element in row " + i + ", col " + j + " does not exist.");
               return b;
           }
       }
       
       public void SetMaskElem(int i, int j, boolean b)
       {
           boolean old_b;
           if (i < GRID_SIZE && j < GRID_SIZE)
           {
               old_b = masks[i][j];
               masks[i][j] = b;
               System.out.println("mask element row " + i + ", col " + j + " was " + old_b + " but is now set to " + masks[i][j]);
           }
           else
           {
               System.out.println("mask element in row " + i + ", col " + j + " does not exist.");
           }
           
           return;
       }
       
       // https://www.softwaretestinghelp.com/pass-return-array-in-java/
       public boolean[][] GetMask()
       {
           return masks;
       }
       
       public void PrintMask()
       {
           String rowStr;
           for(int i = 0; i < GRID_SIZE; i++)
           {
               rowStr = "";
               for(int j = 0; j < GRID_SIZE; j++)
               {
                   if (masks[i][j])
                        rowStr += "1 ";
                   else
                        rowStr += "0 ";
               }
               System.out.println(rowStr);
           }
       }

       // : https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
       public void CloseSudoku()
       {
           setVisible(false);
           dispose();
       }
       // Define the Listener Inner Class
       // ... [TODO 2] (Later) ...
       private class InputListener implements ActionListener {
 
      @Override
      public void actionPerformed(ActionEvent e) {
         // All the 9*9 JTextFileds invoke this handler. We need to determine
         // which JTextField (which row and column) is the source for this invocation.
         int rowSelected = -1;
         int colSelected = -1;
 
         // Get the source object that fired the event
         JTextField source = (JTextField)e.getSource();
         // Scan JTextFileds for all rows and columns, and match with the source object
         boolean found = false;
         for (int row = 0; row < GRID_SIZE && !found; ++row) {
            for (int col = 0; col < GRID_SIZE && !found; ++col) {
               if (tfCells[row][col] == source) {
                  rowSelected = row;
                  colSelected = col;
                  found = true;  // break the inner/outer loops
               }
            }
         }
 
         /*
          * [TODO 5]
          * 1. Get the input String via tfCells[rowSelected][colSelected].getText()
          * 2. Convert the String to int via Integer.parseInt().
          * 3. Assume that the solution is unique. Compare the input number with
          *    the number in the puzzle[rowSelected][colSelected].  If they are the same,
          *    set the background to green (Color.GREEN); otherwise, set to red (Color.RED).
          */
 
         /* 
          * [TODO 6] Check if the player has solved the puzzle after this move.
          * You could update the masks[][] on correct guess, and check the masks[][] if
          * any input cell pending.
          */
      }
   }
    }
    
    /** The entry main() entry method */
    public static void main(String[] args) 
    {
       // [TODO 1] (Now)
       // Check Swing program template on how to run the constructor
       boolean[][] newmasks;
       Sudoku mySudoku = new Sudoku(); // invokes constructor
       mySudoku.PrintMask();
       mySudoku.GetMaskElem(0, 5);
       mySudoku.GetMaskElem(0, 7);
       mySudoku.GetMaskElem(10, 12);
       //System.out.println("Hello, world!");
       
       // change an element of the mask
       mySudoku.SetMaskElem(0, 7, true);
       mySudoku.SetMaskElem(3, 3, true);
       mySudoku.PrintMask();
       newmasks = mySudoku.GetMask();
       
       // close the original Sudoku window
       mySudoku.CloseSudoku();
       mySudoku = new Sudoku(newmasks);
    }
}
