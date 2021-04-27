/**
 * AVL Tree for String insertion and sorting
 * @author Palesa Khoali, KHLPAL002
 * source: Hussein Suleman's AVL Tree
 */

import java.io.*;

public class AccessAVLApp {

    /**
     * BinaryTreeNode is used to get the right ond the left node to be compared to the next key being entered, because its an AVL tree Height is also accounted for
     * */
    public static class BinaryTreeNode<String> {
        String data;
        BinaryTreeNode<String> left;
        BinaryTreeNode<String> right;
        int height;

        /**
         *
         * @param d used to store data
         * @param l used to store left node
         * @param r used to store right node
         */
        public BinaryTreeNode(String d, BinaryTreeNode<String> l, BinaryTreeNode<String> r) {
            data = d;
            left = l;
            right = r;
            height = 0;
        }

        /**
         * @return returns the left node
         */
        BinaryTreeNode<String> getLeft() {
            return left;
        }

        /**
         * @return returns the right node
         */
        BinaryTreeNode<String> getRight() {
            return right;
        }
    }

    /**
     * BinaryTree prints out the data in a specific node once its been sorted
     */
    public static class BinaryTree<String> {
        BinaryTreeNode<String> root;

        /**
         * Initailizing the Binary Tree
         */
        public BinaryTree() {
            root = null;
        }

        /**
         * visit prints out all exact data in the node
         * @param node stores the actual word in the node
         */
        public void visit(BinaryTreeNode<String> node) {
            System.out.println(node.data);
        }

        /**
         * inOrder performs an in order traversal
         * @param node
         */
        public void inOrder(BinaryTreeNode<String> node) {
            if (node != null) {
                inOrder(node.getLeft());
                visit(node);
                inOrder(node.getRight());
            }
        }

    }

    /**
     * AVL Tree keeps track of the height of the tree, balances the tree through rotations and is reposible for the program keeping its AVL tree properties while insert and searching or any other request
     * There is also the opCount used to count every instance of an operation during a find or search and print that out
     */
    public static class AVLTree<String extends Comparable<? super String>> extends BinaryTree<String> {

        int opCount = 0;

        /**
         * height keeps track of the height of the tree
         * @param node stores a word from the list
         * @return returns the height the node is on
         */
        public int height(BinaryTreeNode<String> node) {
            if (node != null)
                return node.height;
            return -1;
        }

        /**
         * balanceFactor balances the tree by comparing left to right
         * @param node stores a word from the list
         * @return returns the difference in height between the left node and the right node
         */
        public int balanceFactor(BinaryTreeNode<String> node) {
            //opCount++;
            //System.out.println(opCount);
            return height(node.right) - height(node.left);
        }

        /**
         * fixHeight adjusts the height of the current node
         * @param node stores the actual word from the list
         */
        public void fixHeight(BinaryTreeNode<String> node) {
            //opCount++;
            //System.out.println(opCount);
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }

        /**
         * rotateRight rotates the tree right to allow an adjusted height
         * @param p the current node to be rotated
         * @return the newly rotated node
         */
        public BinaryTreeNode<String> rotateRight(BinaryTreeNode<String> p) {
            BinaryTreeNode<String> q = p.left;
            p.left = q.right;
            q.right = p;
            fixHeight(p);
            fixHeight(q);
            return q;
        }

        /**
         * rotateRight rotates the tree right to allow an adjusted height
         * @param q the current node to be rotated
         * @return the newly rotated node
         */
        public BinaryTreeNode<String> rotateLeft(BinaryTreeNode<String> q) {
            BinaryTreeNode<String> p = q.right;
            q.right = p.left;
            p.left = q;
            fixHeight(q);
            fixHeight(p);
            return p;
        }

        /**
         * BinaryTreeNode ensures there is always a balance through rotations
         * @param p the word in the current node
         * @return the new current node after the balance
         */
        public BinaryTreeNode<String> balance(BinaryTreeNode<String> p) {
            fixHeight(p);
            if (balanceFactor(p) == 2) {
                //opCount++;
                if (balanceFactor(p.right) < 0) {
                    //opCount++;
                    p.right = rotateRight(p.right);
                }
                //System.out.println(opCount);
                return rotateLeft(p);
            }
            if (balanceFactor(p) == -2) {
                if (balanceFactor(p.left) > 0) {
                    //opCount++;
                    p.left = rotateLeft(p.left);
                }
                //System.out.println(opCount);
                return rotateRight(p);
            }
            return p;
        }

        /**
         * inserts the roots in the tree
         * @param d root
         */
        public void insert(String d) {
            root = insert(d, root);
        }

        /**
         * Insets the data in the tree
         * @param d the data to be inserted
         * @param node the node wherein the data will be inserted
         * @return the node after balancing
         */
        public BinaryTreeNode<String> insert(String d, BinaryTreeNode<String> node) {
            if (node == null)
                return new BinaryTreeNode<String>(d, null, null);
            if (d.compareTo(node.data) <= 0) {
                //opCount++;
                node.left = insert(d, node.left);
                //System.out.println(opCount);
            }
            else {
                //opCount++;
                node.right = insert(d, node.right);
                //System.out.println(opCount);
            }
            return balance(node);
        }

        /**
         * Searches for the string that has been parsed
         * @param d the string that we are searching for
         * @return returns the position of the data in the tree if it is found
         */
        public BinaryTreeNode<String> find(String d) {
            opCount++;
            System.out.println(opCount);
            if (root == null)
                return null;
            else
                return find(d, root);
        }

        /**
         * find searches for the string which has been requested
         * @param d the string we are searching for
         * @param node node containg the data from the list
         * @return the node
         */
        public BinaryTreeNode<String> find(String d, BinaryTreeNode<String> node) {
            if (d.compareTo(node.data) == 0) {
                opCount++;
                System.out.println(opCount);
                return node;
            }
            else if (d.compareTo(node.data) < 0) {
                opCount++;
                System.out.println(opCount);
                return (node.left == null) ? null : find(d, node.left);
            }
            else {
                opCount++;
                System.out.println(opCount);
                return (node.right == null) ? null : find(d, node.right);
            }
        }

        /**
         * initializing the treeOrder();
         */
        public void treeOrder() {
            treeOrder(root, 0);
        }

        /**
         * @param node where the data is stored
         * @param level the nevel of the current node
         */
        public void treeOrder(BinaryTreeNode<String> node, int level) {
            if (node != null) {
                for (int i = 0; i < level; i++)
                    System.out.print(" ");
                System.out.println(node.data);
                treeOrder(node.left, level + 1);
                treeOrder(node.right, level + 1);
            }
        }
    }

    /**
     * printAllStudents reads in the List of students provided, inserts it in an AVL tree and calls an inOrder traver
     * @throws Exception throws an exception if Filereader is not working
     */
    public static void printAllStudents ()throws Exception{
        AVLTree<String> bt = new AVLTree<String>();

        // reading the file and keeping an buffer to tell me what my position is
        String line;
        FileReader read = new FileReader("../data/oklist.txt");
        BufferedReader buff = new BufferedReader(read);
        while ((line = buff.readLine()) != null) {
            bt.insert(line);
            //System.out.println(line);
        }
        bt.treeOrder ();
    }

    /**
     * printStudent takes in the name of a student one wishes to search for, calls the find function and prints out the name and surname of the corresponding student or Access Denied if no student is found
     * @param studentID passes in the student ID to be seacrhed
     * @throws Exception throws an exception if Filereader is not working
     */
    public static void printStudent(String studentID) throws Exception {
        AVLTree<String> bt = new AVLTree<String>();

        // reading the file and keeping an buffer to tell me what my position is
        FileReader read = new FileReader("../data/oklist.txt");
        BufferedReader buff = new BufferedReader(read);

        String line;
        String others = "";


        boolean check = false;
        while ((line = buff.readLine()) != null) {
            String letters = "";
            String[] wordChecker;
            wordChecker = line.split(" ", 0);
            letters = wordChecker[0];
            bt.insert(letters);
            //check = false;
            if (bt.find(studentID) != null) {

                others = wordChecker[1] + " " + wordChecker[2];
                check = true;
                //System.out.println(others);
                break;
            }
        }
        if (check) {

            System.out.println("Student name: "+others);
        } else {
            System.out.println("Access denied!");
        }
    }


    /**
     * Main calls the printAllStudents() if ran without arguments or printStudent using the argument at position 0
     * @param args arguments which can be entered on the command line
     * @throws Exception throws an exception if Filereader is not working
     */
    public static void main(String[] args) throws Exception {

        if (args.length == 0) {

            System.out.println("The results for printing all Students from the AVL tree inOrder traversal");
            System.out.println("\n");
            printAllStudents();
            }
         else {
            System.out.println("The results for searching for student ID "+args[0]+" in the AVL tree");
            printStudent(args[0]);
        }

        }
    }




