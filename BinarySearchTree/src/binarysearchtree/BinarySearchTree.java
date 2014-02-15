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

import java.util.*;

public class BinarySearchTree
{
    public static void main(String[] args)
    {
        TreeMap< String, Integer > binaryTree = new TreeMap<>();
        
        createTree( binaryTree );
        displayTree( binaryTree );
        
    }
    
    private static void createTree(TreeMap< String, Integer > tree)
    {
        Scanner input = new Scanner( System.in );
        System.out.println( "Enter a string: ");
        String textIn = input.nextLine();
        
        String[] tokenizedText = processText( textIn );
        
        for( String token : tokenizedText )
        {
            token = token.toLowerCase(); // Convert tokens to lowercase
            
            // if the word already exists in binaryTree update frequency
            if( tree.containsKey( token ) )
            {
                int frequency = tree.get( token );
                tree.put( token ,  frequency + 1 );
            }
            // else add word to binaryTree and set initial frequency
            else
                tree.put( token , 1 );
        } // end for add and/or update frequency of binaryTree tokens
    } // end createTree
    
    private static void displayTree( TreeMap< String, Integer > tree )
    {
        Set< String > keys = tree.keySet();
        
        for( String key : keys )
            System.out.printf( "%-15s%-10s\n" , key, tree.get( key ) );
        
        Collection c = tree.navigableKeySet();
        Iterator itr = c.iterator();
        
        while(itr.hasNext())
            System.out.println(itr.next());
        
    } // end display tree
    
    private static String[] processText( String textIn)
    {
        // Remove all punctuation
        textIn = textIn.replaceAll( "[^A-Za-z\\s]" , "" );
        // Tokenize string by whitespace characters
        String[] textInTokens = textIn.split( "\\s" );
        
        return textInTokens;
    } // end processText
    
} // BinarySearchTree class
