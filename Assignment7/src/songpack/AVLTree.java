package songpack;

import java.lang.classfile.components.ClassPrinter;

/**
 * Author: Colby Wirth 
 * Version: 25 October 2024 
 * Course: COS 285 
 * Class: AVLTree.java
 */
public class AVLTree extends BinarySearchTree {

    private int leftRotation, rightRotation, leftRightRotation, rightLeftRotation;
    public Node root;

    /**
     * Constructor for a AVLTree
     */
    public AVLTree(){
        this.root = null;
        leftRotation = 0;
        rightRotation = 0;
        leftRightRotation = 0;
        rightLeftRotation = 0; 

    }


    /**
     * Helper method to update the height of a node when a node is added
     * @param n
     */
    private void updateHeight(Node n){
        n.height=1+Math.max(height(n.left),height(n.right));
    }

    /**
     * completes a left rotation on a AVLTree
     * @param current the node to root node
     * @return replace - the new root node
     */
    private Node leftRotate(Node root){

        Node replace = root.right;
        Node replaceLeft = replace.left;

        replace.left = root;
        root.right = replaceLeft;

        updateHeight(root);
        updateHeight(replace);

        return replace; //the new root rode
    }

    private Node rightRotate(Node root){

        System.out.println("Complete me");

        return null;
    }

    public Node insert(Song data){

    }

    private Node insert(Song data){

    }

    /**
     * helper method that checks the balance factor of a Tree
     * @param N the node being checked
     * @return int: -1 if left heavy | 2 if left heavy by 2 nodes | 0 if tree is balanced | 1 if right heavy | 2 if right heavy by 2 nodes
     */
    private int balanceFactor(Node N){

        if(N==null)
            return 0;

        return height(N.right) - height(N.left);
    }

    /**
     * This handles the logic for rebalancing an AVLTree
     * @param root the root node
     * @return root the root node - after rebalancing the tree
     */
    private Node rebalance(Node root){
        
        updateHeight(root);
        int bf = balanceFactor(root);

        //2L-L Case
        if(bf <-1 && balanceFactor(root.left)<0){
            rightRotation+=1;

            return rightRotate(root);
        }

        //2R-R Case
        if(bf >1 && balanceFactor(root.right) > 0){
            leftRotation+=1;

            return leftRotate(root);
        }
        
        //Left-Right Case
        if(bf <-1 & balanceFactor(root.left)>0){
            leftRightRotation+=1;
            root.left = leftRotate(root.left);

            return rightRotate(root);
        }

        //Right-Left Case
        if(bf >1 & balanceFactor(root.right)>0){

            rightLeftRotation+=1;
            root.right = rightRotate(root.right);

            return leftRotate(root);
        }
    
        return root;

    }
    

}
