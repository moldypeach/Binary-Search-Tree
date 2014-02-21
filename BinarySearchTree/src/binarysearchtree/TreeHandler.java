/* Filename:        TreeHandler.java
 * Last Modified:   21 Feb 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * TreeHandler.java is ...
 */

package binarysearchtree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TreeHandler
{
    Scanner input = new Scanner( System.in );
    Tree< String > binaryTree;    
    private int initialRun = 1;
    
    public void treeInterface()
    {
        Integer menuChoice = 0;
        String textIn = "";
        
        while( menuChoice.equals( 9 ) == false )
        {
            Student_Sig_Block.printSig();
            
            if( initialRun == 1 )
            {
                menuChoice = initialRun;
                initialRun--;
            }
            else
                menuChoice = treeMenu();

            switch( menuChoice )
            {
                case 9:
                    System.out.println( "\n\nProgram execution terminated\n\n" );
                    break;
                case 8:
                    System.out.print( "\n\n" );
                    binaryTree.outputTree();
                    break;
                case 7:
                    binaryTree.postorderTraversal();
                    break;
                case 6:
                    binaryTree.preorderTraversal();
                    break;
                case 5:
                    binaryTree.inorderTraversal();
                    break;
                case 4:
                    System.out.print( "\n\nEnter a value for deletion: " );
                    textIn = input.next();
                    input.nextLine();
                    binaryTree.deleteNode(textIn, true);
                    break;
                case 3:
                   System.out.print( "\n\nEnter a value for deletion: " );
                    textIn = input.next();
                    input.nextLine();
                    binaryTree.deleteNode(textIn, false);                    
                    break;
                case 2:
                   System.out.print( "\n\nEnter a search value: " );
                    textIn = input.next();
                    input.nextLine();
                    int searchResult = binaryTree.searchTree(textIn);
                    System.out.println( searchResult >= 1 ? "\n\t\"" + textIn + "\" occurs (" + searchResult +") times in the tree\n" : "\n\t\"" + textIn + "\" was not found in the tree\n");                    
                    break;
                case 1:
                    System.out.println( "\nPlease type what you would like inserted into a tree:\n" );
                    textIn = input.nextLine();
                    List<String> tokenizedText = Arrays.asList( processText(textIn) );
                    binaryTree = new Tree<>( tokenizedText );
                    System.out.println( "\n\t\tTree created successfully\n\n" );
                    break;
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
    
    private String[] processText( String textIn)
    {
        // Remove all punctuation
        textIn = textIn.replaceAll( "[^A-Za-z\\s]" , "" );
        // Tokenize string by whitespace characters
        String[] textInTokens = textIn.split( "\\s" );
        
        Arrays.sort( textInTokens ); // Sort tokens in ascending order
        
        return textInTokens;
    } // end processText    
    
} // end treeHandler class
