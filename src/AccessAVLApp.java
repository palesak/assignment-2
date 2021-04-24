/* Palesa Khoali
KHLPAL002
19 April 2021*/

import java.io.*;
import java.util.*;

/*public class Students implements Comparable<Students>, Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;
    private String fName;
    String lName;
    public Students getFistName;

    Students(char std_ID, String std_fName, String std_lName) {
        ID = std_ID;
        fName = std_fName;
        lName = std_lName;

    }

    public void print() {
        System.out.println(ID);
        System.out.println(fName);
        System.out.println(lName);

    }

    class AVLNode {
        AVLNode left, right;
        char ID;
        String fName;
        String lName;

        public AVLNode() {
            left = null;
            right = null;
            ID = 0;
            fName = null;
        }

        public AVLNode(char n) {
            left = null;
            right = null;
            ID = n;
            fName = null;
        }
    }

    class AVLTree {
        private AVLNode root;

        public AVLTree() {
            root = null;
        }

        public boolean isEmpty() {
            return root == null;
        }

        public void makeEmpty() {
            root = null;
        }

        private int ID(AVLNode t) {
            return t == null ? -1 : t.ID;
        }

        private int max(int lhs, int rhs) {
            return lhs > rhs ? lhs : rhs;
        }

        private AVLNode insert(AVLNode t, char ID) {
            if (t == null)
                t = new AVLNode(ID);
            if (ID < t.ID)
                t.left = insert(t.left, ID);
            else {
                if (ID > t.ID)
                    t.right = insert(t.right, ID);

                else

                    return t;
            }


            return t;

        }

        public void inorder() {
            inorder(root);
        }

        public void inorder(AVLNode r) {
            if (r != null) {
                inorder(r.left);
                System.out.print(r.ID + " ");
                inorder(r.right);

            }
        }

        public void preorder() {
            preorder(root);
        }

        private void preorder(AVLNode r) {
            if (r != null) {
                System.out.print(r.ID + " ");
                preorder(r.left);
                preorder(r.right);

            }
        }

        public void postorder() {
            postorder(root);
        }

        private void postorder(AVLNode r) {
            if (r != null) {
                postorder(r.left);
                postorder(r.right);
                System.out.print(r.ID + " ");
            }
        }

        public static int Read() {
            int count = 0;
            try {
                Vector<Students> vector = new Vector<Students>();
                FileInputStream saveFile = new FileInputStream("D:/evans/oklist.txt");
                ObjectInputStream save;
                try {
                    for (; ; ) {
                        save = new ObjectInputStream(saveFile);
                        Students std = (Students) save.readObject();
                        vector.add(std);
                        count++;
                    }
                } catch (EOFException e) {

                }
                saveFile.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return count;
        }

        public void Write(Students stds) {
            try {
                FileOutputStream fileOut = new FileOutputStream("D:/evans/oklist,txt", true);
                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fileOut));

                out.writeObject(stds);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in info.ser");
            } catch (IOException i) {

            }
        }

    }
}*/

    class AccessAVLApp {

        class AVLNode {
            AccessAVLApp.AVLNode left, right;
            char ID;
            String fName;
            String lName;

            public AVLNode() {
                left = null;
                right = null;
                ID = 0;
                fName = null;
            }

            public AVLNode(char n) {
                left = null;
                right = null;
                ID = n;
                fName = null;
            }
        }

        class AVLTree {
            private AccessAVLApp.AVLNode root;

            public AVLTree() {
                root = null;
            }

            public boolean isEmpty() {
                return root == null;
            }

            public void makeEmpty() {
                root = null;
            }

            private int ID(AccessAVLApp.AVLNode t) {
                return t == null ? -1 : t.ID;
            }

            private int max(int lhs, int rhs) {
                return lhs > rhs ? lhs : rhs;
            }

            private AccessAVLApp.AVLNode insert(AccessAVLApp.AVLNode t, char ID) {
                if (t == null)
                    t = new AccessAVLApp.AVLNode(ID);
                if (ID < t.ID)
                    t.left = insert(t.left, ID);
                else {
                    if (ID > t.ID)
                        t.right = insert(t.right, ID);

                    else

                        return t;
                }


                return t;

            }

            public void inorder() {
                inorder(root);
            }

            public void inorder(AccessAVLApp.AVLNode r) {
                if (r != null) {
                    inorder(r.left);
                    System.out.print(r.ID + " ");
                    inorder(r.right);

                }
            }

            public void preorder() {
                preorder(root);
            }

            private void preorder(AccessAVLApp.AVLNode r) {
                if (r != null) {
                    System.out.print(r.ID + " ");
                    preorder(r.left);
                    preorder(r.right);

                }
            }

            public void postorder() {
                postorder(root);
            }

            private void postorder(AccessAVLApp.AVLNode r) {
                if (r != null) {
                    postorder(r.left);
                    postorder(r.right);
                    System.out.print(r.ID + " ");
                }
            }
        }

        public static boolean stringCompare(String word, String word2) {
            if (word.equals(word2)) {
                return true;
            }
            return false;
        }

        public static void printStudent(String studentID) throws Exception {
            // reading the file and keeping an buffer to tell me what my position is
            FileReader read = new FileReader("../data/oklist.txt");
            BufferedReader buff = new BufferedReader(read);

            // To store each line as one string
            String line;

            // initializing the array
            String[][] listArray = new String[5000][2];

            // will store the name if it is found in the list
            String isThere = "";

            int i = 0;

            // read in the names in the text file and store in the string line, row by row
            while ((line = buff.readLine()) != null) {
                for (int j = 0; j < listArray[i].length; j++) {
                    listArray[i][j] = line;
                }

                // firstWord will store the first word in the line
                String firstWord;
                String[] wordChecker = line.split(" ", 0);
                firstWord = wordChecker[0];

                // as per invoke example compare the studentID arg[0] to the first word in eachline
                if (stringCompare(studentID, firstWord)) {

                    // print full name if found
                    isThere = wordChecker[1] + " " + wordChecker[2];
                    break;
                } else {
                    isThere = "Access Denied";
                }

                i++;
            }
            System.out.println(isThere);
            System.exit(0);
            read.close();
        }

        public static void printAllStudents() throws Exception {
            // reading the file and keeping a buffer
            FileReader read = new FileReader("../data/oklist.txt");
            BufferedReader buff = new BufferedReader(read);

            // line to store each array item as one string
            String line;
            String[][] listArray = new String[5000][2];
            int i = 0;

            // read in the names in the text file and store in the string line, row by row
            while ((line = buff.readLine()) != null) {
                for (int j = 0; j < listArray[i].length; j++) {
                    listArray[i][j] = line;
                }
                // printing out everything
                System.out.println(line);
                i++;
            }
            System.exit(0);
            read.close();
        }


        public static void main(String[] args) throws Exception {

            if (args.length == 0) {
                // printing out all students
                printAllStudents();
            } else {
                //printing out the name of one student or access denied
                String word = args[0];
                printStudent(word);
            }

        }
    }