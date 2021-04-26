// Hussein's AVL Tree
// 2 April2017
// Hussein Suleman
import java.io.*;


class AccessAVLApp {
    public static class BinaryTreeNode<String> {
        String data;
        BinaryTreeNode<String> left;
        BinaryTreeNode<String> right;
        int height;

        public BinaryTreeNode(String d, BinaryTreeNode<String> l, BinaryTreeNode<String> r) {
            data = d;
            left = l;
            right = r;
            height = 0;
        }

        BinaryTreeNode<String> getLeft() {
            return left;
        }

        BinaryTreeNode<String> getRight() {
            return right;
        }
    }

    public static class BinaryTree<String> {
        BinaryTreeNode<String> root;

        public BinaryTree() {
            root = null;
        }

        public int getHeight() {
            return getHeight(root);
        }

        public int getHeight(BinaryTreeNode<String> node) {
            if (node == null)
                return -1;
            else
                return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }

        public int getSize() {
            return getSize(root);
        }

        public int getSize(BinaryTreeNode<String> node) {
            if (node == null)
                return 0;
            else
                return 1 + getSize(node.getLeft()) + getSize(node.getRight());
        }

        public void visit(BinaryTreeNode<String> node) {
            System.out.println(node.data);
        }

        public void preOrder() {
            preOrder(root);
        }

        public void preOrder(BinaryTreeNode<String> node) {
            if (node != null) {
                visit(node);
                preOrder(node.getLeft());
                preOrder(node.getRight());
            }
        }

        public void postOrder() {
            postOrder(root);
        }

        public void postOrder(BinaryTreeNode<String> node) {
            if (node != null) {
                postOrder(node.getLeft());
                postOrder(node.getRight());
                visit(node);
            }
        }

        public void inOrder() {
            inOrder(root);
        }

        public void inOrder(BinaryTreeNode<String> node) {
            if (node != null) {
                inOrder(node.getLeft());
                visit(node);
                inOrder(node.getRight());
            }
        }

        public void levelOrder() {
            if (root == null)
                return;
            BTQueue<String> q = new BTQueue<String>();
            q.enQueue(root);
            BinaryTreeNode<String> node;
            while ((node = q.getNext()) != null) {
                visit(node);
                if (node.getLeft() != null)
                    q.enQueue(node.getLeft());
                if (node.getRight() != null)
                    q.enQueue(node.getRight());
            }
        }
    }

    public static class BTQueueNode<String> {
        BinaryTreeNode<String> node;
        BTQueueNode<String> next;

        public BTQueueNode(BinaryTreeNode<String> n, BTQueueNode<String> nxt) {
            node = n;
            next = nxt;
        }
    }

    public static class BTQueue<String> {
        BTQueueNode<String> head;
        BTQueueNode<String> tail;

        public BTQueue() {
            head = null;
            tail = null;
        }

        public BinaryTreeNode<String> getNext() {
            if (head == null)
                return null;
            BTQueueNode<String> qnode = head;
            head = head.next;
            if (head == null)
                tail = null;
            return qnode.node;
        }

        public void enQueue(BinaryTreeNode<String> node) {
            if (tail == null) {
                tail = new BTQueueNode<String>(node, null);
                head = tail;
            } else {
                tail.next = new BTQueueNode<String>(node, null);
                tail = tail.next;
            }
        }
    }

    public static class AVLTree<String extends Comparable<? super String>> extends BinaryTree<String> {
        public int height(BinaryTreeNode<String> node) {
            if (node != null)
                return node.height;
            return -1;
        }

        public int balanceFactor(BinaryTreeNode<String> node) {
            return height(node.right) - height(node.left);
        }

        public void fixHeight(BinaryTreeNode<String> node) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }

        public BinaryTreeNode<String> rotateRight(BinaryTreeNode<String> p) {
            BinaryTreeNode<String> q = p.left;
            p.left = q.right;
            q.right = p;
            fixHeight(p);
            fixHeight(q);
            return q;
        }

        public BinaryTreeNode<String> rotateLeft(BinaryTreeNode<String> q) {
            BinaryTreeNode<String> p = q.right;
            q.right = p.left;
            p.left = q;
            fixHeight(q);
            fixHeight(p);
            return p;
        }

        public BinaryTreeNode<String> balance(BinaryTreeNode<String> p) {
            fixHeight(p);
            if (balanceFactor(p) == 2) {
                if (balanceFactor(p.right) < 0)
                    p.right = rotateRight(p.right);
                return rotateLeft(p);
            }
            if (balanceFactor(p) == -2) {
                if (balanceFactor(p.left) > 0)
                    p.left = rotateLeft(p.left);
                return rotateRight(p);
            }
            return p;
        }

        public void insert(String d) {
            root = insert(d, root);
        }

        public BinaryTreeNode<String> insert(String d, BinaryTreeNode<String> node) {
            if (node == null)
                return new BinaryTreeNode<String>(d, null, null);
            if (d.compareTo(node.data) <= 0)
                node.left = insert(d, node.left);
            else
                node.right = insert(d, node.right);
            return balance(node);
        }

        public void delete(String d) {
            root = delete(d, root);
        }

        public BinaryTreeNode<String> delete(String d, BinaryTreeNode<String> node) {
            if (node == null) return null;
            if (d.compareTo(node.data) < 0)
                node.left = delete(d, node.left);
            else if (d.compareTo(node.data) > 0)
                node.right = delete(d, node.right);
            else {
                BinaryTreeNode<String> q = node.left;
                BinaryTreeNode<String> r = node.right;
                if (r == null)
                    return q;
                BinaryTreeNode<String> min = findMin(r);
                min.right = removeMin(r);
                min.left = q;
                return balance(min);
            }
            return balance(node);
        }

        public BinaryTreeNode<String> findMin(BinaryTreeNode<String> node) {
            if (node.left != null)
                return findMin(node.left);
            else
                return node;
        }

        public BinaryTreeNode<String> removeMin(BinaryTreeNode<String> node) {
            if (node.left == null)
                return node.right;
            node.left = removeMin(node.left);
            return balance(node);
        }

        public BinaryTreeNode<String> find(String d) {
            if (root == null)
                return null;
            else
                return find(d, root);
        }

        public BinaryTreeNode<String> find(String d, BinaryTreeNode<String> node) {
            if (d.compareTo(node.data) == 0)
                return node;
            else if (d.compareTo(node.data) < 0)
                return (node.left == null) ? null : find(d, node.left);
            else
                return (node.right == null) ? null : find(d, node.right);
        }

        public void treeOrder() {
            treeOrder(root, 0);
        }

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

    public static void printAllStudents ()throws Exception{
        AVLTree<String> bt = new AVLTree<String>();

        // reading the file and keeping an buffer to tell me what my position is
        String line;
        FileReader read = new FileReader("../data/oklist.txt");
        BufferedReader buff = new BufferedReader(read);
        while ((line = buff.readLine()) != null) {
            bt.insert(line);
            System.out.println(line);
        }

    }

    public static void printStudent(String studentID) throws Exception {
        AVLTree<String> bt = new AVLTree<String>();

        // reading the file and keeping an buffer to tell me what my position is
        FileReader read = new FileReader("../data/oklist.txt");
        BufferedReader buff = new BufferedReader(read);

        String line;
        String others = "";



        //String[] wordChecker = new String[];
        //String[] line = new String[];
        //wordChecker = line.split(" ", 0);

        while ((line = buff.readLine()) != null) {
            String letters = "";
            String[] wordChecker;
            wordChecker = line.split(" ", 0);
            letters = wordChecker[0];
            bt.insert(letters);
            if (bt.find(studentID) != null) {

                others = wordChecker[1] + " " + wordChecker[2];
                //System.out.println(others);
                break;
            }
            //System.out.println(line);

         /*if (bt.find(word[0]) != null)
         {
            //System.out.println("yes");
            //System.out.println(word[1] +" "+word[2]);
         }*/
        }
        System.out.println(others);
    }

    public static void main(String[] args) throws Exception {



        if (args.length == 0) {
            printAllStudents();
            }
         else {
            printStudent(args[0]);
        }

        }
    }




