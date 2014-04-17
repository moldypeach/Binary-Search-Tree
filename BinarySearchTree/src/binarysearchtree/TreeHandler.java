/* Filename:        TreeHandler.java
 * Last Modified:   4 Mar 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * TreeHandler.java is the class that controls user interaction with Tree.java. 
 * It has four methods treeInterface(), treeMenu(), processNumericInput(), and
 * processStringInput(). treeMenu() prints the user menu and returns the choice
 * to treeInterface(); which uses a switch structure to handle the appropiate
 * actions. processNumericInput() and processStringInput() receive the user
 * entered values, strip special characters and whitespace, and return a token-
 * ized array to create the binary tree.
 */

package binarysearchtree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TreeHandler< T >
{
    // Scanner object to receive user input
    Scanner input = new Scanner( System.in );
    // Tree object to create and maintain a binary tree
    Tree< String > stringBinaryTree;
    Tree< Double > numericBinaryTree;
    // Prompt for input to binary tree as first action of program
    private int initialRun = 1;
    // determine if user-selected tree is numeric or string based
    private boolean isNumericTree;
    
    public void treeInterface()
    {
        Integer menuChoice = 0;
        String stringInput;
        Double doubleInput;
        
        // while user hasn't chosen to exit, print menu
        while( menuChoice.equals( 9 ) == false )
        {
            Student_Sig_Block.printSig();
            
            // prompt for input on first run instead of printing menu
            if( initialRun == 1 )
            {
                menuChoice = initialRun;
                initialRun--;
            }
            // get user's menu selection
            else
                menuChoice = treeMenu();

            switch( menuChoice )
            {
                // exit program
                case 9:
                    System.out.println( "\n\nProgram execution terminated\n\n" );
                    break;
                // Print text-based version of binary tree
                case 8:
                    System.out.print( "\n\n" );
                    if( isNumericTree )
                        numericBinaryTree.outputTree();
                    else
                        stringBinaryTree.outputTree();
                    break;
                // Print postorder traversal
                case 7:
                    if( isNumericTree )
                        numericBinaryTree.postorderTraversal();
                    else
                        stringBinaryTree.postorderTraversal();
                    break;
                // Print preorder traversal
                case 6:
                    if( isNumericTree )
                        numericBinaryTree.preorderTraversal();
                    else
                        stringBinaryTree.preorderTraversal();
                    break;
                // Print inorder traversal
                case 5:
                    if( isNumericTree )
                        numericBinaryTree.inorderTraversal();
                    else
                        stringBinaryTree.inorderTraversal();
                    break;
                // Prompt for and delete value from tree, if it exists
                case 4:
                    System.out.print( "\n\nEnter a value for deletion: " );
                    if( isNumericTree )
                    {
                        // declare and initialize double variable
                        doubleInput = new Double(0);
                        
                        // catch invalid delete entry type
                        try
                        {
                            doubleInput = input.nextDouble();
                            input.nextLine();
                            numericBinaryTree.deleteNode( doubleInput , true);                            
                        }
                        catch( NumberFormatException s )
                        {
                            System.out.println( "\n\tERROR: please enter a number.\n\n" );
                        } // end try...catch
                    }
                    else
                    {
                        stringInput = input.next();
                        input.nextLine();
                        stringBinaryTree.deleteNode(stringInput, true);
                    }
                    break;
                // Prompt for and delete all values from tree, if it exists
                case 3:
                   System.out.print( "\n\nEnter a value for deletion: " );
                    if( isNumericTree )
                    {
                        // declare and initialize double variable
                        doubleInput = new Double(0);
                        
                        // catch invalid delete entry type
                        try
                        {
                            doubleInput = input.nextDouble();
                            input.nextLine();
                            numericBinaryTree.deleteNode( doubleInput , false);                            
                        }
                        catch( NumberFormatException s )
                        {
                            System.out.println( "\n\tERROR: please enter a number.\n\n" );
                        } // end try...catch
                    } // end if deleting a number
                    else
                    {
                        // declare and initialize string variable
                        stringInput = new String("");
                        
                        // catch invalid delete entry
                        stringInput = input.next();
                        input.nextLine();
                        stringBinaryTree.deleteNode(stringInput, false);
                    } // end else deleting a string          
                    break;
                // Prompt for value to search tree for
                case 2:
                    int searchResult = 0;
                   System.out.print( "\n\nEnter a search value: " );
                   if( isNumericTree )
                   {
                       doubleInput = input.nextDouble();
                       input.nextLine();
                       searchResult = numericBinaryTree.searchTree(doubleInput);
                       System.out.println( searchResult >= 1 ? "\n\t\"" + doubleInput + "\" occurs (" + searchResult +") times in the tree\n" : "\n\t\"" + doubleInput + "\" was not found in the tree\n");                       
                   }
                   else
                   {
                       stringInput = input.next();
                       input.nextLine();
                       searchResult = stringBinaryTree.searchTree(stringInput);
                       System.out.println( searchResult >= 1 ? "\n\t\"" + stringInput + "\" occurs (" + searchResult +") times in the tree\n" : "\n\t\"" + stringInput + "\" was not found in the tree\n");                         
                   }
                    break;
                // Create new String or Numeric binary tree
                case 1:
                    int treeType;
                    
                    do
                    {
                        treeType = 0;
                        
                        System.out.println( "\nWould you like to enter Numeric or String values?\n" );
                        System.out.print( "\t1 - Numeric\n"
                                        + "\t2 - String\n"
                                        + "Choice: " );
                        // Determine if user wishes to create a numeric or string tree
                        try
                        {
                            treeType = Integer.parseInt( input.nextLine() );
                            if( treeType == 1 )
                                isNumericTree = true;
                            else
                                isNumericTree = false;
                        }
                        catch(Exception e)
                        {
                            System.out.println( "\nERROR: invalid input, please try again." );
                        }                        
                    } while( (treeType < 1) || (treeType > 2) );
                    
                    if( isNumericTree )
                    {
                        System.out.println( "\nPlease enter some numbers:\n" );
                        stringInput = input.nextLine();
                        List<Double> tokenizedText = Arrays.asList( processNumericInput( stringInput ) );
                        numericBinaryTree = new Tree<>( tokenizedText );
                    }
                    else
                    {
                        System.out.println( "\nPlease enter some text:\n" );
                        stringInput = input.nextLine();
                        List<String> tokenizedText = Arrays.asList( processStringInput(stringInput) );
                        stringBinaryTree = new Tree<>( tokenizedText );     
                    }
                    
                    break;
                // Default case, should not be reachable
                default:
                    System.out.println( "Default case" );
                    
            } // end switch
        } // end while
        
    } // end treeInterface() method    
    
    // Determine which type of binary tree to create
    private int treeMenu()
    {
        int menuChoice;
        
        do
        {
            menuChoice = 0;
            
            System.out.println( "\n\nThis program simulates a Binary Search Tree, wherein"
            + " a user enters values, they are inserted into a tree, then several"
            + " features are ran against the created tree.");

            System.out.println( "\nPlease choose one of the following: " );
            System.out.print( "\t1 - Create a tree\n"
                            + "\t2 - Search tree for a value (returns boolean)\n"
                            + "\t3 - Delete a single value\n"
                            + "\t4 - Delete all occurences of a value\n"
                            + "\t5 - Print inorder traversal\n"
                            + "\t6 - Print preorder traversal\n"
                            + "\t7 - Print postorder traversal\n"
                            + "\t8 - Print Binary Tree object\n"
                            + "\t9 - Exit\n" );
            System.out.print("Choice: ");            
            
            try
            {
                menuChoice = Integer.parseInt( input.nextLine() );
            }
            catch( Exception e )
            {
                System.out.println("\n\tInvalid option, please try again.\n");
            }            
        } while( (menuChoice < 1) || (menuChoice > 9) );
        
        return menuChoice;
    } // end createTreeMenu() method
    
    // Process string input, removing punctuation and whitespaces; and sort list in ascending order
    private String[] processStringInput( String userInput)
    {
        // Remove all punctuation
        userInput = userInput.replaceAll( "[^A-Za-z\\s]" , "" );
        // Tokenize string by whitespace characters
        String[] userInputTokens = userInput.split( "\\s" );
        
//        Arrays.sort( userInputTokens ); // Sort tokens in ascending order
        
        return userInputTokens;
    } // end processStringInput
    
    // Process numeric string input into double values, removing erroneous entries, punctuation, and whitespaces; and sort list in ascending order
    private Double[] processNumericInput( String userInput)
    {
        // Remove all punctuation
        userInput = userInput.replaceAll( "[^0-9\\s]" , "" );
        // Tokenize string by whitespace characters
        String[] userInputTokens = userInput.split( "\\s" );
        Double[] numericTokens = new Double[userInputTokens.length];
        
        for( int i = 0; i < userInputTokens.length; i++ )
        {
            try
            {
                numericTokens[i] = Double.parseDouble( userInputTokens[ i ] ); 
            }
            catch( Exception e )
            {
                return numericTokens = new Double[0];
            }
        }
        
//        Arrays.sort( numericTokens ); // Sort tokens in ascending order
        
        return numericTokens;
    } // end processNumericInput    
    
} // end treeHandler class
