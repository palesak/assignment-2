import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Palesa Khoali
 */

class AccessAVLApp{
public static class Students implements Comparable<Students>, Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;
    private String fName;
    String lName;
    public Students getFistName;

    Students(char std_ID, String std_fName, String std_lName){
        ID = std_ID;
        fName = std_fName;
        lName = std_lName;

    }
    public void print(){
        System.out.println(ID);
        System.out.println(fName);
        System.out.println(lName);

    }
    @Override
    public String toString(){
        return ID + "-" + fName + "-" + lName;
    }
    public int getID()
    {
        return ID;
    }
    public void setID (int std_ID)
    {
        ID = std_ID;
    }
    public String getfName()
    {
        return fName;
    }
    public void setfName (String std_fName)
    {
        fName = std_fName;
    }
    public String getlName()
    {
        return lName;
    }
    public void setlName (String std_lName)
    {
        lName = std_lName;
    }
    @Override
    public int compareTo(Students o) {
        return 0;
    }

    public void input(){
        System.out.print("Please input Students \n");
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please input a Students ID");
        ID = myScanner.nextInt();
        myScanner.nextLine();
        System.out.println("Please input Students fName");
        fName = myScanner.nextLine();
        System.out.println("Please input StudentslName");
        lName = myScanner.nextLine();

    }

}
static class AVLNode
{
    AVLNode left, right;
    char ID;
    /*@FindBy(css="")
    private WebElement webElement;
    @FindBy(css="")
    private WebElement webElement;
    @FindBy(css="")
    private WebElement webElement;*/
    String fName;
    String lName;

    public AVLNode()
    {
        left = null;
        right = null;
        ID = 0;
        fName = null;
    }
    public AVLNode(char n)
    {
        left = null;
        right = null;
        ID = n;
        fName = null;
    }
}
static class AVLTree
{
    private static AVLNode root;

    public AVLTree()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root==null;
    }
    public void makeEmpty()
    {
        root = null;
    }
    private int ID(AVLNode t)
    {
        return t == null ? -1 : t.ID;
    }
    private int max(int lhs, int rhs)
    {
        return lhs > rhs ? lhs : rhs;
    }
    private AVLNode insert(AVLNode t, char ID)
    {
        if(t == null)
            t = new AVLNode(ID);
        if (ID < t.ID)
            t.left = insert(t.left, ID);
        else {
            if(ID > t.ID)
                t.right = insert(t.right, ID);

            else

                return t;
        }


        return t;

    }
    public void inorder()
    {
        inorder(root);
    }
    public void inorder(AVLNode r)
    {
        if(r != null)
        {
            inorder(r.left);
            System.out.print(r.ID +" ");
            inorder(r.right);

        }
    }
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(AVLNode r)
    {
        if(r !=null)
        {
            System.out.print(r.ID +" ");
            preorder(r.left);
            preorder(r.right);

        }
    }
    public void postorder()
    {
        postorder(root);
    }

    private void postorder(AVLNode r)
    {
        if(r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.ID +" ");
        }
    }
    /*public static int Read() {
        int count = 0;
        try {
            Vector<Students> vector = new Vector<Students>();
            FileInputStream saveFile = new FileInputStream("../oklist.txt");
            ObjectInputStream save;
            try {
                for(;;){
                    save = new ObjectInputStream(saveFile);
                    Students std = (Students) save.readObject();
                    vector.add(std);
                    count++;
                }
            }catch(EOFException e){

            }
            saveFile.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return count;
    }*/
    public void Write(Students stds) {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("../oklist,txt", true);
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fileOut));

            out.writeObject(stds);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in info.ser");
        }catch(IOException i)
        {

        }
    }

}

    public static void main(String[] args)
    {
        Students std = null;

        char ID = 0;

        String fName = null;
        //char ID = 0;
        String lName = null;
        try (Scanner scan = new Scanner(System.in)) {

            AVLTree avl = new AVLTree();

            System.out.println("Binary Search Tree Operations \n");
            char ch;

            do {
                System.out.println("\n Binary Search Tree operations\n");
                System.out.println("1. insert ");
                Scanner input = new Scanner(System.in);
    //Int choice = scan.nextLine();
                if (input.nextLine() != "") {
                    //case "test 1":
                        System.out.println("Please input Students \n");

                        System.out.println("Please input a student ID");
                        ID = input.next().charAt(0);
                        scan.nextLine();
                        System.out.println("Please input Students fName");
                        fName = scan.nextLine();
                        System.out.println("Please input Students lName");
                        lName = scan.nextLine();
                        std = new Students(ID, fName, lName);
                        //bst.insert(std);
                        avl.Write(std);
                        break;
                    /*default:
                        System.out.println("Access Denied! \n");
                        break;*/
                }
                System.out.print("\nPost order : ");
                avl.postorder();
                System.out.print("\nPre order : ");
                avl.preorder();
                System.out.print("\nIn order : ");
                avl.inorder();

                System.out.println("\nDo you want to continue (Type y or n) \n");
                ch = scan.next().charAt(0);

            } while (ch == 'Y' || ch == 'y');
        }

    }
}



