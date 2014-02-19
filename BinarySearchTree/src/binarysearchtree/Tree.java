/* Filename:        BinarySearchTree.java
 * Last Modified:   15 Feb 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * Tree.java is ...
 */
package binarysearchtree;
import java.util.ArrayList;
// class TreeNode definition
class TreeNode< T extends Comparable< T > > 
{
   // package access members
   TreeNode< T > leftNode; // left node  
   T data; // node value
   TreeNode< T > rightNode; // right node
   private int frequency; // Track occurance of data in Tree

   // constructor initializes data and makes this a leaf node
   public TreeNode( T nodeData )
   { 
      data = nodeData;              
      leftNode = rightNode = null; // node has no children
      frequency = 1;
   } // end TreeNode constructor

   // locate insertion point and insert new node; ignore duplicate values
   public void insert( T insertValue )
   {
      // insert in left subtree
      if ( insertValue.compareTo( data ) < 0 ) 
      {
         // insert new TreeNode
         if ( leftNode == null )
            leftNode = new TreeNode<>( insertValue );
         else // continue traversing left subtree recursively
            leftNode.insert( insertValue ); 
      } // end if
      // insert in right subtree
      else if ( insertValue.compareTo( data ) > 0 ) 
      {
         // insert new TreeNode
         if ( rightNode == null )
            rightNode = new TreeNode<>( insertValue );
         else // continue traversing right subtree recursively
            rightNode.insert( insertValue ); 
      } // end else if
      else // Increment counter
          frequency++;
   } // end method insert
   
   public int getFrequency()
   {
       return frequency;
   }
   
} // end class TreeNode

// class Tree definition
public class Tree< T extends Comparable< T > >
{
   private TreeNode< T > root;

   // constructor initializes an empty Tree of integers
   public Tree() 
   { 
      root = null; 
   } // end Tree no-argument constructor

   // insert a new node in the binary search tree
   public void insertNode( T insertValue )
   {
      if ( root == null )
         root = new TreeNode<>( insertValue ); // create root node
      else
         root.insert( insertValue ); // call the insert method
   } // end method insertNode

   // begin preorder traversal
   public void preorderTraversal()
   { 
      preorderHelper( root ); 
   } // end method preorderTraversal

   // recursive method to perform preorder traversal
   private void preorderHelper( TreeNode< T > node )
   {
      if ( node == null )
         return;

      System.out.printf( "%s ", node.data ); // output node data
      preorderHelper( node.leftNode ); // traverse left subtree
      preorderHelper( node.rightNode ); // traverse right subtree
   } // end method preorderHelper

   // begin inorder traversal
   public void inorderTraversal()
   { 
      inorderHelper( root ); 
   } // end method inorderTraversal

   // recursive method to perform inorder traversal
   private void inorderHelper( TreeNode< T > node )
   {
      if ( node == null )
         return;

      inorderHelper( node.leftNode ); // traverse left subtree
      System.out.printf( "%s (%d)", node.data, node.getFrequency() ); // output node data
      inorderHelper( node.rightNode ); // traverse right subtree
   } // end method inorderHelper

   // begin postorder traversal
   public void postorderTraversal()
   { 
      postorderHelper( root ); 
   } // end method postorderTraversal

   // recursive method to perform postorder traversal
   private void postorderHelper( TreeNode< T > node )
   {
      if ( node == null )
         return;
  
      postorderHelper( node.leftNode ); // traverse left subtree
      postorderHelper( node.rightNode ); // traverse right subtree
      System.out.printf( "%s (%d)", node.data, node.getFrequency() ); // output node data
   } // end method postorderHelper
   
   public void outputTree()
   {        
        outputTreeHelper( root, 0 );
   }
   private void outputTreeHelper( TreeNode< T > node, int totalSpaces )
   {       
       while( node != null )
       {
           if( node.rightNode != null )
            outputTreeHelper( node.rightNode, totalSpaces + 5 );
           
           for( int i = 1; i < totalSpaces; i++ )
               System.out.print( " " );
           
           System.out.println( node.data );
           
           node = node.leftNode;
           totalSpaces += 5;
//           outputTreeHelper( node = node.leftNode, spaces + 10);
       }
   }
   
} // end class Tree
