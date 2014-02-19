/* Filename:        BinarySearchTree.java
 * Last Modified:   15 Feb 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * BinarySearchTree.java is ...
 */

package binarysearchtree;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearchTree
{
    static Tree< String > binaryTree = new Tree<>();
        
    public static void main(String[] args)
    {
        Scanner input = new Scanner( System.in );
        System.out.println( "Enter a string: ");
//        String textIn = "apple cat dog";
        String textIn = "cat apple dog bird snake mango zebra zonk cronk";
//        String textIn = input.nextLine();
//        String textIn = "DURING the whole of a dull, dark, and soundless day in the autumn of the year, when the clouds hung oppressively low in the heavens, I had been passing alone, on horseback, through a singularly dreary tract of country; and at length found myself, as the shades of the evening drew on, within view of the melancholy House of Usher.";        
        List<String> temp = Arrays.asList( processText(textIn) );
        ArrayList<String> tokenizedText = new ArrayList<>(temp);
        createTree( tokenizedText, tokenizedText.size() );
//        binaryTree.preorderTraversal();
//        binaryTree.inorderTraversal();
//        binaryTree.postorderTraversal();
        binaryTree.outputTree();
        binaryTree.inorderTraversal();
        System.out.println();
        binaryTree.preorderTraversal();
        System.out.println();
        binaryTree.postorderTraversal();
        
    }
    
    private static void createTree( ArrayList<String> tokens, int setRoot)
    {
        // If no more elements to insert
        if( tokens.isEmpty() )
        {
            // return control to caller
        }
        // Set root node of entire binaryTree
        else if (setRoot != 0)
        {
            setRoot /= 2;
            binaryTree.insertNode( tokens.get( setRoot ) );
            tokens.remove( setRoot );
            createTree( tokens, 0 );
        }
        // If list size is three elements or less just add first list item
        else if( tokens.size() <= 2 )
        {
            binaryTree.insertNode( tokens.get( 0 ) );
            tokens.remove( 0 );
            createTree(tokens, 0);
        }
        // else determine if list size is even||odd, and balance insertions 
        else
        {
            if( tokens.size() % 2 == 0 )
                setRoot = (tokens.size() - 2);
            else
                setRoot = ( (tokens.size() / 2) - 1 );
            binaryTree.insertNode( tokens.get( setRoot ) );
            tokens.remove(setRoot );
            createTree( tokens, 0 );
        }
//        System.out.println("Size of tokens is: " + tokens.size() );
//        binaryTree.insertNode( tokens.get( 1 ));
//        tokens.remove( 1 );
//        System.out.println("Size after removal is: " + tokens.size() );
        
        
//        for( String token : tokens )
//        {
//            if( setRoot.equals( 0 ) )
//            {
//                if( tokens.size() % 2 == 0 )
//                    setRoot = tokens.size() / 2;
//                else
//                    setRoot = ( tokens.size() / 2 ) + 1;
//                
//                binaryTree.insertNode( tokens.get( setRoot ) );
//                tokens.remove( setRoot );
//            }
//            else
//            {
//                binaryTree.insertNode( token );              
//            }
//        } // end for add and/or update frequency of binaryTree tokens
    } // end createTree
    
    private static String[] processText( String textIn)
    {
        // Remove all punctuation
        textIn = textIn.replaceAll( "[^A-Za-z\\s]" , "" );
        // Tokenize string by whitespace characters
        String[] textInTokens = textIn.split( "\\s" );
//        
//        for( String token : textInTokens )
//        {
//            token = token.toLowerCase(); // Convert tokens to lowercase
//        }
        
        Arrays.sort( textInTokens ); // Sort tokens in ascending order
        
        return textInTokens;
    } // end processText
    
} // BinarySearchTree class
