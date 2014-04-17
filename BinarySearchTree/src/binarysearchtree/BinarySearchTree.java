/* Filename:        BinarySearchTree.java
 * Last Modified:   04 Mar 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * BinarySearchTree.java allows a user to enter either text or numbers and then 
 * creates a binary tree from the entered data. Once running, a command line
 * menu interface is presented to the user to control operations, such as adding
 * a new tree, searching the tree for values, deleting values, and printing
 * both textual and in/post/pre-order traversals.
 */

package binarysearchtree;

public class BinarySearchTree
{
    public static void main(String[] args)
    {  
        TreeHandler tree = new TreeHandler();
        tree.treeInterface();
    }
} // BinarySearchTree class



