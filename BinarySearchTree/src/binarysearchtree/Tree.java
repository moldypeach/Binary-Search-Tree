/* Filename:        BinarySearchTree.java
 * Last Modified:   4 Mar 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * Tree.java is a multi-class file with some code adapted from the textbook. 
 * Generic class TreeNode extends Comparable to aid in node insertion, and its
 * fields track each created nodes left child, right child, tree position,
 * frequency of occurance, and parent node. TreeNode's constructor creates
 * the root node and initializes fields. TreeNode has accessor and mutator
 * methods for each nodes position, frequency, and parent node; and, method
 * insert() is used to manage each nodes creation and insertion into the tree.
 *
 * Class Tree does the bulk of the work for binary tree operations, and it has
 * two constructors, one for creating an empty tree with a null root and one
 * for creating a tree from a passed List<T>. Class tree posses many methods
 * such as the following:
 * Tree Traversals -- for post|pre|inorder
 * Tree Printing -- outputTree prints the tree to output
 * node deletion -- handles finding delete value in tree, updating any/all tree
 *                  nodes as necessary, or reporting if delete value was not 
 *                  found.
 * node searching -- searches the tree for a value, or returns not found
 * replacement node discovery -- get[Left|Right]SubtreeReplacement are each 
 * called variant upon whether the replacement node desired is the largest 
 * value smaller than the node being deleted, or the smallest value greater
 * than the node being deleted.
 */
package binarysearchtree;

import java.util.ArrayList;
import java.util.List;

// class TreeNode definition
class TreeNode< T extends Comparable< T > > 
{
   // package access members
    TreeNode< T > leftNode; // left node  
    T data; // node value
    TreeNode< T > rightNode; // right node
    
    private TreeNode< T > parentNode; // parent node
    private int frequency; // Track frequency of token occurrencesin Tree
    private int nodePosition;

   // constructor initializes data and makes this a leaf node
   public TreeNode( T nodeData )
   { 
      data = nodeData;
      leftNode = rightNode = null; // node has no children
      frequency = 1;
      setNodePosition(0);
      setParentNode( null );
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
             leftNode.setParentNode( this );
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
             rightNode.setParentNode( this );
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
   
   // Accessor method returns if a node is a left|right child
   public int getNodePosition()
   {
       return nodePosition;
   } // end getNodePosition() method
   
   // Mutator method sets a nodes left|right child position
   public void setNodePosition(int position)
   {
       nodePosition = position;
   } // end setNodePosition() method
   
   // Accessor method returns parent node
   public TreeNode< T > getParentNode()
   {
       return parentNode;
   } // end getParentNode() method
   
   // Mutator method sets a nodes parent node
   public void setParentNode( TreeNode< T > parent )
   {
       parentNode = parent;
   } // end setParentNode() method
   
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
   
   // constructor creates tree from list of nodes
   public Tree(List<T> tokens)
   {
       root = null;
//       createTree( tokens );
               // If no elements to insert
        if( tokens.isEmpty() )
        {
            System.out.println( "ERROR: either [no|invalid] input was provided!" );
        }
        else
        {
            for(T node : tokens)
            {
                insertNode( node );
            }
        } // end else
   } // end constuctor for list of nodes

   // insert a new node in the binary search tree
   public void insertNode( T insertValue )
   {
      if ( root == null )
      {
         root = new TreeNode<>( insertValue ); // create root node
      }
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
   
   // Deletes a node from binaryTree by either decrementing the passed nodes
   // frequency, or by deleting the entire node if the frequency decrements to zero
   public void deleteNode( T deleteValue, boolean deleteAll )
   {
       // Get reference to node for deletion
       TreeNode< T > deleteNode = searchTreeUtility( deleteValue );
       // Store reference to replacementNode
       TreeNode< T > replacementNode = null;
       // Store reference to parentNode
       TreeNode< T > parentNode = null;
       
       // Node doesn't exist in binaryTree
       if( deleteNode == null )
           System.out.println( "\n\n\t Delete value not found.\n" );
/*******************************************************************************       
                    NODE FOR DELETION IS A LEAF NODE
*******************************************************************************/       
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
                   deleteNode.getParentNode().leftNode = null;
                   deleteNode = null;
               }
               // Set parent nodes rightNode reference to null
               else
               {
                   deleteNode.getParentNode().rightNode = null;
                   deleteNode = null;                 
               }
           }
       } // end else...if for leaf node
/*******************************************************************************       
            NODE FOR DELETION HAS A SINGLE, RIGHT, CHILD
*******************************************************************************/ 
       else if ( ( deleteNode.leftNode == null ) )
       {
           parentNode = deleteNode.getParentNode();
           
           if( (deleteNode.getFrequency() > 1) && (!deleteAll) )
           {
               deleteNode.decrementFrequency();
           }
           else
           {                
                // if deleteNode is the root node
                if( deleteNode.getNodePosition() == 0 )
                {
                    // If the right child of the node being deleted has a left child
                    // replacement node is in left subtree of that node
                    if( deleteNode.rightNode.leftNode != null )
                    {
                        // Store reference to replacement node
                        replacementNode = getRightSubtreeReplacement( deleteNode.rightNode );
                        
                        // if replacementNode is a leaf node
                        if( replacementNode.rightNode == null )
                        {
                            replacementNode.getParentNode().leftNode = null;
                        }
                        // if the replacement node has a right child
                        else
                        { 
                            // set replacementNode's parent leftNode to replacementNode's right child
                            replacementNode.getParentNode().leftNode = replacementNode.rightNode;
                            // set replacementNode's right child parent to replacementNode's parent
                            replacementNode.rightNode.setParentNode( replacementNode.getParentNode() );
                        }
                        replacementNode.rightNode = deleteNode.rightNode;
                    }
                    // if the right child of the node being deleted does not have a left child
                    // then said right child is the replacement node
                    else
                        replacementNode = deleteNode.rightNode;
                    
                    // set replacementNode position to deleteNode position
                    replacementNode.setNodePosition( deleteNode.getNodePosition() );
                    // Set replacementNode parent to deleteNode parent
                    replacementNode.setParentNode( parentNode );                    
                    // set root equal to the replacement node
                    root = replacementNode;
                    root.rightNode.setParentNode( replacementNode );
                }
               // Set parent node leftNode reference to deleteNode's child
                else if( deleteNode.getNodePosition() < 0 )
               {
                   replacementNode = deleteNode.rightNode;
                   // Set the parentNode's leftNode to the replacement node
                   parentNode.leftNode = replacementNode;
                   // set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );
                   // Set replacementNode parent to deleteNode parent
                   replacementNode.setParentNode( parentNode );                   
               }
               // Set parent node rightNode reference to deleteNode's child
               else
               {
                   replacementNode = deleteNode.rightNode;
                   // Set the parentNode's rightNode to the replacement node
                   parentNode.rightNode = replacementNode;
                   // set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );
                   // Set replacementNode parent to deleteNode parent
                   replacementNode.setParentNode( parentNode );                   
               }
                
               deleteNode = null;
           }           
       } // end else...if for node with single RIGHT child node
/*******************************************************************************       
            NODE FOR DELETION HAS A SINGLE, LEFT, CHILD
*******************************************************************************/ 
       else if ( ( deleteNode.rightNode == null ) )
       {
           parentNode = deleteNode.getParentNode();
           
           if( (deleteNode.getFrequency() > 1) && (!deleteAll) )
           {
               deleteNode.decrementFrequency();
           }
           else
           {
                // Store reference to replacement node
                replacementNode = deleteNode.leftNode;
                
               // Set parent nodes leftNode reference to deleteNode's child
               if( deleteNode.getNodePosition() < 0 )
               {
                   // Set replacementNode parent to deleteNode parent
                   replacementNode.setParentNode( parentNode );
                   // Set the parentNode's leftNode to the replacement node
                   parentNode.leftNode = replacementNode;
                   deleteNode = null;
               }
               // Set parent nodes rightNode reference to deleteNode's child
               else
               {
                   // Set replacementNode parent to deleteNode parent
                   replacementNode.setParentNode( parentNode );
                   // Set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );
                   // Set the parentNode's rightNode to the replacement node
                   parentNode.rightNode = replacementNode;
                   deleteNode = null;                   
               }              
           }            
       } // end else...if for node with single LEFT child node
/*******************************************************************************       
            NODE HAS TWO CHILDREN
*******************************************************************************/ 
       else
       {
           parentNode = deleteNode.getParentNode();
           
           if( (deleteNode.getFrequency() > 1) && (!deleteAll) )
           {
               deleteNode.decrementFrequency();
           }
           else
           {
               // Store reference to replacement node
               replacementNode = getLeftSubtreeReplacement( deleteNode.leftNode );

               // **************************************************************
               // REPLACEMENT NODE IS A LEAF NODE
               // **************************************************************
               if( ( replacementNode.leftNode == null ) && ( replacementNode.rightNode == null ) )
               {
                   // Set parentNode reference in replacementNode to null
                   if( replacementNode.getNodePosition() < 0 )
                       // Set replacementNode parent leftNode reference to null
                       replacementNode.getParentNode().leftNode = null;               
                   else
                       // Set replacementNode parent rightNode reference to null
                       replacementNode.getParentNode().rightNode = null;                
                   
                   // set replacementNode left and right node references
                   if( replacementNode.getParentNode().data.compareTo( deleteNode.data ) == 0 )
                       replacementNode.leftNode = null;
                   else
                        replacementNode.leftNode = deleteNode.leftNode;                   
                   replacementNode.rightNode = deleteNode.rightNode;
                   
                   // Set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );
                   // Set replacementNode parent node to deleteNode parent node
                   replacementNode.setParentNode( deleteNode.getParentNode() );                   
                   
                   // Node for deletion is the root node
                   if( deleteNode.getNodePosition() == 0 )
                   {
                       // set root to replacementNode reference
                       root = replacementNode;
                       // update parent node reference in root's left and right children
                       if( root.leftNode != null)
                           root.leftNode.setParentNode( replacementNode );
                       if( root.rightNode != null )
                           root.rightNode.setParentNode( replacementNode );
                   }
                   // Change parent node reference for node being deleted to the replacement node
                   else if( deleteNode.getNodePosition() < 0 )
                   {
                       // Set parentNode leftNode reference to replacementNode
                       parentNode.leftNode = replacementNode;
                       // update parentNode reference in deleteNode's children
                       if( deleteNode.leftNode != null )
                           deleteNode.leftNode.setParentNode( replacementNode );
                       if( deleteNode.rightNode != null )
                           deleteNode.rightNode.setParentNode( replacementNode );
                   }
                   // Set parentNode rightNode reference to replacementNode
                   else
                   {
                       // Set deleteNode parent rightNode reference to replacementNode
                       parentNode.rightNode = replacementNode;
                       // update parentNode reference in deleteNode's children
                       if( deleteNode.leftNode != null )
                           deleteNode.leftNode.setParentNode( replacementNode );
                       if( deleteNode.rightNode != null )
                           deleteNode.rightNode.setParentNode( replacementNode );                     
                    }

                   deleteNode = null;
               }
               // **************************************************************
               // REPLACEMENT NODE HAS A LEFT CHILD
               // **************************************************************
               else
               {
                   // Set parentNode reference in replacementNode to null
                   if( replacementNode.getNodePosition() < 0 )
                   {
                       // update replacementNode's left child node position
                       replacementNode.leftNode.setNodePosition( replacementNode.getNodePosition() );
                       // update parent node of replacementNode's child
                       replacementNode.leftNode.setParentNode( replacementNode.getParentNode() );
                       // Set replacementNode parent leftNode reference to replacementNode's left child
                       replacementNode.getParentNode().leftNode = replacementNode.leftNode;
                       
                   }
                   else
                   {
                       // update replacementNode's left child node position
                       replacementNode.leftNode.setNodePosition( replacementNode.getNodePosition() );
                       // update parent node of replacementNode's child
                       replacementNode.leftNode.setParentNode( replacementNode.getParentNode() );
                       // Set replacementNode parent rightNode reference to replacementNode left child
                       replacementNode.getParentNode().rightNode = replacementNode.leftNode;                 
                   }                   
                   
                   // set replacementNode left and right node references
                   if( replacementNode.getParentNode() == deleteNode )
                       replacementNode.leftNode = null;
                   else
                       replacementNode.leftNode = deleteNode.leftNode;
                   
                   replacementNode.rightNode = deleteNode.rightNode;
                   // Set replacementNode position to deleteNode position
                   replacementNode.setNodePosition( deleteNode.getNodePosition() );
                   // Set replacementNode parent node to deleteNode parent node
                   replacementNode.setParentNode( deleteNode.getParentNode() );                   
                   
                   // Node for deletion is the root node
                   if( deleteNode.getNodePosition() == 0 )
                   {
                       // set root to replacementNode reference
                       root = replacementNode;
                       // update parent node reference in root's left and right children
                       if( root.leftNode != null)
                           root.leftNode.setParentNode( replacementNode );
                       if( root.rightNode != null )
                           root.rightNode.setParentNode( replacementNode );
                   }
                   // Change parent node reference for node being deleted to the replacement node
                   else if( deleteNode.getNodePosition() < 0 )
                   {
                       // Set deleteNode's parent leftNode reference to replacementNode
                       parentNode.leftNode = replacementNode;
                       // update parentNode reference in deleteNode's children
                       if( deleteNode.leftNode != null )
                           deleteNode.leftNode.setParentNode( replacementNode );
                       if( deleteNode.rightNode != null )
                           deleteNode.rightNode.setParentNode( replacementNode );
                   }
                   // Set parentNode rightNode reference to replacementNode
                   else
                   {
                       // Set deleteNode's parent rightNode reference to replacementNode
                       parentNode.rightNode = replacementNode;
                       // update parentNode reference in deleteNode's children
                       if( deleteNode.leftNode != null )
                           deleteNode.leftNode.setParentNode( replacementNode );
                       if( deleteNode.rightNode != null )
                           deleteNode.rightNode.setParentNode( replacementNode );                     
                    }

                   deleteNode = null;                   
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
       TreeNode<T> deleteNode = node;
       
       // If current node is null, the item was not found in binaryTree
       if( node == null)
       {
           return null;
       }
       // Search for value in left node recursively
       else if( value.compareTo( node.data ) < 0 )
       {
           deleteNode = searchTreeUtilityHelper( node.leftNode, value );
       }
       // Search for value in right node recursively
       else if ( value.compareTo( node.data ) > 0 )
       {
           deleteNode = searchTreeUtilityHelper( node.rightNode, value );
       }
       // Match found. Return result
       else
       {
           deleteNode = node;
       }
       return deleteNode;
   } // end searchTreeHelper() method
   
   private TreeNode< T > getLeftSubtreeReplacement( TreeNode<T> node )
   {
       // Loop control variable
       boolean foundReplacement = false;
       
       // Crawl down rightNodes of the first leftNode to the node being deleted
       do
       {            
           if( node.rightNode == null )
           {
               foundReplacement = true;
           }
           else
               node = node.rightNode;
       } while( !foundReplacement );

   return node;
       
   } // end getReplacementNode() method
   
   private TreeNode< T > getRightSubtreeReplacement( TreeNode<T> node )
   {
       // Loop control variable
       boolean foundReplacement = false;
       
       // Crawl down rightNodes of the first leftNode to the node being deleted
       do
       {            
           if( node.leftNode == null )
           {
               foundReplacement = true;
           }
           else
               node = node.leftNode;
       } while( !foundReplacement );

   return node;
       
   } // end getReplacementNode() method   
   
} // end class Tree
