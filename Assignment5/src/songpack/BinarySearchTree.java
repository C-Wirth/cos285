package songpack;

import java.util.ArrayList;

/**
 * 
 */
public class BinarySearchTree {

    Node root;

    public BinarySearchTree(){
        this.root = null;
    }

    public class Node{
        Song data;
        Node left;
        Node right;

        public Node(Song data){
            this.data= data;
            left = right = null;
        }
    }

        private boolean isValidBST(Node root){

            if(root == null)
                return true;

            if(root.left == null && root.right == null)
                return true;
            
            if(root.left != null && root.data.compareTo(root.left.data) <=0)
                return false;
            
            if(root.right != null && root.data.compareTo(root.right.data) >0)
                return false;

            //Finish me?!

            // if((
            //     root.left.data.compareTo(root.data) > 0)
            //     &&
            //     root.data.compareTo(root.data) > 0
            //     )

            //     return false;
            
            return (isValidBST(root.left) && isValidBST(root.right));
 
        }

        //implement in-order traverse
        public ArrayList<Song> toSortedArrayList(){

            ArrayList<Song> list = new ArrayList<>();

            toSortedArrayList(list, root);

            return list;
        }

            private ArrayList<Song> toSortedArrayList(ArrayList<Song> list, Node root){

                if(root == null)
                    return list;

                list.addAll(toSortedArrayList(list, root.left));
                list.add(root.data);
                list.addAll(toSortedArrayList(list, root.right));

                return list;
            }
        }