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
import java.util.List;

// class TreeNode definition
class TreeNode< T extends Comparable< T > > 
{
   // package access members
    TreeNode< T > parentNode; // parent node
    TreeNode< T > leftNode; // left node  
    T data; // node value
    TreeNode< T > rightNode; // right node
    private int frequency; // Track frequency of token occurrencesin Tree
    private int nodePosition;

   // constructor initializes data and makes this a leaf node
   public TreeNode( T nodeData )
   { 
      data = nodeData;
      leftNode = rightNode = null; // node has no children
      frequency = 1;
      parentNode = this; 
      setNodePosition(0);
   } // end TreeNode constructor

   // locate insertion point and insert new node; ignore duplicate values
   public void insert( T insertValue )
   {
      // insert in left subtree
      if ( insertValue.compareTo( data ) < 0 ) 
      {
         // insert new TreeNode
         if ( leftNode == null )
         {
             leftNode = new TreeNode<>( insertValue );
             leftNode.parentNode = this;
             leftNode.setNodePosition( -1 ); // Node object identified as a left child
         }
         else // continue traversing left subtree recursively
            leftNode.insert( insertValue ); 
      } // end if
      // insert in right subtree
      else if ( insertValue.compareTo( data ) > 0 ) 
      {
         // insert new TreeNode
         if ( rightNode == null )
         {
             rightNode = new TreeNode<>( insertValue );
             rightNode.parentNode = this;
             rightNode.setNodePosition( 1 ); // Node object identified as a right child
         }
         else // continue traversing right subtree recursively
            rightNode.insert( insertValue ); 
      } // end else if
      else // Increment counter
          frequency++;
   } // end method insert
   
   // Accessor method for binaryTree object's frequency of occurrance
   public int getFrequency()
   {
       return frequency;
   } // end getFrequency() method
   
   // Mutator method for binaryTree object's frequency of occurance
   protected void decrementFrequency()
   {
       frequency--;
   } // end decrementFrequency() method
   
   // Access method returns if a node is a left|right child
   public int getNodePosition()
   {
       return nodePosition;
   } // end getNodePosition() method
   
   // Mutator method sets a nodes left|right child position
   public void setNodePosition(int position)
   {
       nodePosition = position;
   } // end setNodePosition() method
   
} // end class TreeNode

// class Tree definition
public class Tree< T extends Comparable< T > >
{
   private TreeNode< T > root;

   // constructor initializes an empty Tree
   public Tree() 
   { 
      root = null; 
   } // end Tree no-argument constructor
   
   public Tree(List<T> tokens)
   {
       root = null;
       createTree( tokens );
   }

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
       System.out.println("\nPreorderTraversal:\n");
       preorderHelper( root ); 
   } // end method preorderTraversal

   // recursive method to perform preorder traversal
   private void preorderHelper( TreeNode< T > node )
   {
      if ( node == null )
         return;

      System.out.printf( "%s(%d) ", node.data, node.getFrequency() ); // output node data
      preorderHelper( node.leftNode ); // traverse left subtree
      preorderHelper( node.rightNode ); // traverse right subtree
   } // end method preorderHelper

   // begin inorder traversal
   public void inorderTraversal()
   { 
       System.out.println("\nInorder Traversal:\n");
       inorderHelper( root ); 
   } // end method inorderTraversal

   // recursive method to perform inorder traversal
   private void inorderHelper( TreeNode< T > node )
   {
      if ( node == null )
         return;

      inorderHelper( node.leftNode ); // traverse left subtree
      System.out.printf( "%s(%d) ", node.data, node.getFrequency() ); // output node data
      inorderHelper( node.rightNode ); // traverse right subtree
   } // end method inorderHelper

   // begin postorder traversal
   public void postorderTraversal()
   { 
       System.out.println("\nPostorder Traversal:\n");
       postorderHelper( root ); 
   } // end method postorderTraversal

   // recursive method to perform postorder traversal
   private void postorderHelper( TreeNode< T > node )
   {
      if ( node == null )
         return;
  
      postorderHelper( node.leftNode ); // traverse left subtree
      postorderHelper( node.rightNode ); // traverse right subtree
      System.out.printf( "%s(%d) ", node.data, node.getFrequency() ); // output node data
   } // end method postorderHelper
   
   // Outputs binaryTree row-by-row, with binaryTree's rows aligned vertically
   public void outputTree()
   {        
        outputTreeHelper( root, 0 );
   } // End method outputTree
   
   // Recursive helper function for outputTree() method
   private void outputTreeHelper( TreeNode< T > node, int totalSpaces )
   {       
       while( node != null ) // While the currently passed object's node is not null
       {
           // Crawl right nodes recursively until a null right node is reached
           if( node.rightNode != null )
            outputTreeHelper( node.rightNode, totalSpaces + 5 );
           
           // After return from called object with null right node, output blank spaces
           for( int i = 1; i < totalSpaces; i++ )
               System.out.print( " " );
           
           // Output the value of the current node with said node's frequency of occurence
           System.out.println( node.data + "(" + node.getFrequency() + ")" );
           
           // Set reference to the current node to refer to the left subtree of the current node
           node = node.leftNode;
           totalSpaces += 5;
       } // end while( != null )
   } // end outputTreeHelper() method
   
    public void createTree( List<T> tokens )
    {
        ArrayList<T> leftBranch;
        ArrayList<T> rightBranch;
        int rootIndex = tokens.size() / 2; // get root index for binaryTree
        
        // If no elements to insert
        if( tokens.isEmpty() )
        {
            System.out.println( "ERROR: either [no|invalid] input was provided!" );
        }
        // Set root node of entire binaryTree
        else
        {
            insertNode( tokens.get( rootIndex ) );
            leftBranch = new ArrayList<>( tokens.subList( 0 , rootIndex) );
            createTreeHelper( leftBranch );
            rightBranch = new ArrayList<>( tokens.subList( rootIndex + 1, tokens.size()) );
            createTreeHelper( rightBranch );
        }
    } // end createTree   
   
       private void createTreeHelper ( ArrayList<T> tokens )
    {
        // If no more elements to insert
        if( tokens.isEmpty() )
        {
            // return control to caller
        }
        else if( tokens.size() <= 2)
        {
            insertNode( tokens.get( 0 ) );
            tokens.remove( 0 );
            createTreeHelper( tokens );
        }
        else
        {
            insertNode( tokens.get( tokens.size() / 2  ) );
            tokens.remove( tokens.size() / 2 );
            createTreeHelper( tokens );
        }
        // Set root node of entire binaryTree        
    }
   
   // Deletes a node from binaryTree by either decrementing the passed nodes
   // frequency, or by deleting the entire node if the frequency decrements to zero
   public void deleteNode( T deleteValue, boolean deleteAll )
   {
       // Get reference to node for deletion
       TreeNode< T > deleteNode = searchTreeUtility( deleteValue );
       
       // Node doesn't exist in binaryTree
       if( deleteNode == null )
           System.out.println( "\n\n\t Delete value not found.\n" );
       // Node is a leaf node
       else if ( (deleteNode.leftNode == null) && (deleteNode.rightNode == null) )
       {
           if( (deleteNode.getFrequency() > 1) && (!deleteAll)  )
           {
               deleteNode.decrementFrequency();
           }
           else
           {
               // Set parent nodes leftNode reference to null
               if( deleteNode.getNodePosition() < 0 )
               {
                   deleteNode.parentNode.leftNode = null;
                   deleteNode = null;
               }
               // Set parent nodes rightNode reference to null
               else
               {
                   deleteNode.parentNode.rightNode = null;
                   deleteNode = null;                 
               }
           }
       } // end else...if for leaf node
       // Node has a single, right, child
       else if ( ( deleteNode.leftNode == null ) )
       {           
           if( (deleteNode.getFrequency() > 1) && (!deleteAll) )
           {
               deleteNode.decrementFrequency();
           }
           else
           {
               // Set parent node leftNode reference to deleteNode's child
               if( deleteNode.getNodePosition() < 0 )
               {
                   // Set replacementNode parent to deleteNode parent
                   deleteNode.rightNode.parentNode = deleteNode.parentNode;
                   // Set the parentNode's leftNode to the replacement node
                   deleteNode.parentNode.leftNode = deleteNode.rightNode;
                   deleteNode = null;
               }
               // Set parent node rightNode reference to deleteNode's child
               else
               {
                   // Set replacementNode parent to deleteNode parent
                   deleteNode.rightNode.parentNode = deleteNode.parentNode;
                   // Set the parentNode's rightNode to the replacement node
                   deleteNode.parentNode.rightNode = deleteNode.rightNode;
                   deleteNode = null;                
               }              
           }           
       } // end else...if for node with single RIGHT child node
       // Node has a single, left, child
       else if ( ( deleteNode.rightNode == null ) )
       {
           if( (deleteNode.getFrequency() > 1) && (!deleteAll) )
           {
               deleteNode.decrementFrequency();
           }
           else
           {
               // Set parent nodes leftNode reference to deleteNode's child
               if( deleteNode.getNodePosition() < 0 )
               {
                   // Set replacementNode parent to deleteNode parent
                   deleteNode.leftNode.parentNode = deleteNode.parentNode;
                   // Set the parentNode's leftNode to the replacement node
                   deleteNode.parentNode.leftNode = deleteNode.leftNode;
                   deleteNode = null;
               }
               // Set parent nodes rightNode reference to deleteNode's child
               else
               {
                   // Set replacementNode parent to deleteNode parent
                   deleteNode.leftNode.parentNode = deleteNode.parentNode;
                   // Set the parentNode's rightNode to the replacement node
                   deleteNode.parentNode.rightNode = deleteNode.leftNode;
                   deleteNode = null;                   
               }              
           }            
       } // end else...if for node with single LEFT child node
       // Node node has two children
       else
       {           
           if( (deleteNode.getFrequency() > 1) && (!deleteAll) )
           {
               deleteNode.decrementFrequency();
           }
           else
           {
               // Store reference to the node being deleted
               TreeNode< T > tempNode = deleteNode;
               // Store reference to replacement node
               TreeNode< T > replacementNode = deleteNode.leftNode;
               // Loop control variable
               boolean foundReplacement = false;
               
               // Crawl down rightNodes of the first leftNode to the node being deleted
               do
               {                   
                   if( replacementNode.rightNode == null )
                   {
                       foundReplacement = true;
                   }
                   else
                       replacementNode = replacementNode.rightNode;
                   
               } while( !foundReplacement );
               
               // If the replacement node is a leaf node
               if( ( replacementNode.leftNode == null ) && ( replacementNode.rightNode == null ) )
               {
                   // Change parent node reference for node being deleted to the replacement node
                   if( deleteNode.getNodePosition() < 0 )
                   {
                       // Set deleteNode parent leftNode reference to replacementNode
                       deleteNode.parentNode.leftNode = replacementNode;                
                   }
                   // Set replacementNode's parent rightNode reference to null
                   else
                   {
                       // Set deleteNode parent rightNode reference to replacementNode
                       deleteNode.parentNode.rightNode = replacementNode;
                    }
                   
                   // Set parentNode reference in replacementNode to null
                   if( replacementNode.getNodePosition() < 0 )
                   {
                       // Set replacementNode parent leftNode reference to null
                       replacementNode.parentNode.leftNode = null;               
                   }
                   else
                   {
                       // Set replacementNode parent rightNode reference to null
                       replacementNode.parentNode.rightNode = null;                 
                   }
                   
                   // Set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );                   
                   replacementNode.parentNode = deleteNode.parentNode;
                   replacementNode.rightNode = deleteNode.rightNode;
                   replacementNode.leftNode = deleteNode.leftNode;
                   deleteNode = null;
                   
               }
               // Else the replacement node has a left child
               else
               {
                   // Change parent node reference for node being deleted to the replacement node
                   if( deleteNode.getNodePosition() < 0 )
                   {
                       // Set deleteNode parent leftNode reference to replacementNode
                       deleteNode.parentNode.leftNode = replacementNode;                
                   }
                   // Set replacementNode's parent rightNode reference to null
                   else
                   {
                       // Set deleteNode parent rightNode reference to replacementNode
                       deleteNode.parentNode.rightNode = replacementNode;
                    }
                   
                   if( replacementNode.parentNode != deleteNode)
                   {
                       replacementNode.parentNode.rightNode = replacementNode.leftNode;
                       replacementNode.leftNode = deleteNode.leftNode;
                   }
                   
                   // Set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );                   
                   // Set replacementNode parent rightNode to the left child of replacementNode
                   
                   replacementNode.parentNode = deleteNode.parentNode;
                   replacementNode.rightNode = deleteNode.rightNode;
               }           
           } // End else frequency = 1           
       } // End else node has two children 
   } // end deleteNode() method
   
   // Search the binaryTree for a value and return boolean result
   public int searchTree( T searchValue )
   {       
       return searchTreeHelper( root, searchValue );
   } // end searchTree() method
   
   private int searchTreeHelper( TreeNode<T> node, T value )
   {
       int result = 0;  // Determine search result
       
       // If current node is null, the item was not found in binaryTree
       if( node == null)
       {
           return result;
       }
       // Search for value in left node recursively
       else if( value.compareTo( node.data ) < 0 )
       {
           result = searchTreeHelper( node.leftNode, value );
       }
       // Search for value in right node recursively
       else if ( value.compareTo( node.data ) > 0 )
       {
           result = searchTreeHelper( node.rightNode, value );
       }
       // Match found. Return result
       else
       {
           result = node.getFrequency();
       }
       return result;
   } // end searchTreeHelper() method
   
   // Search the binaryTree for a value and return boolean result
   private TreeNode< T > searchTreeUtility( T searchValue )
   {       
       return searchTreeUtilityHelper( root, searchValue );
   } // end searchTree() method
   
   private TreeNode< T > searchTreeUtilityHelper( TreeNode<T> node, T value )
   {   
       // If current node is null, the item was not found in binaryTree
       if( node == null)
       {
           return null;
       }
       // Search for value in left node recursively
       else if( value.compareTo( node.data ) < 0 )
       {
           node = searchTreeUtilityHelper( node.leftNode, value );
       }
       // Search for value in right node recursively
       else if ( value.compareTo( node.data ) > 0 )
       {
           node = searchTreeUtilityHelper( node.rightNode, value );
       }
       // Match found. Return result
       else
       {
           node = node;
       }
       return node;
   } // end searchTreeHelper() method
   
} // end class Tree
