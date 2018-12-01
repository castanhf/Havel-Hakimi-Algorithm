import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
/**
 * Havel-Hakimi implementation
 * 
 * @author Filipe Castanheira 
 * @version 10/15/2018
 */
public class Havel
{
    String seq = "";
    ArrayList<Integer> num = new ArrayList<Integer>();
    Scanner userInput = new Scanner(System.in);

    public Havel() {
        System.out.println("A degree sequence can be written as the following:\n");
        System.out.println("(3, 2, 2, 2); or 3, 2, 2, 2; or 3 2 2 2; or 3222\n");
        System.out.println("The program is designed to only read the integers\n\n");
        System.out.println("Enter degree sequence:\n");

        seq = userInput.nextLine();
        userInput.close();

        System.out.println("We will check if the given sequence is valid according to the Havel Hakimi Algorithm\n");
    }

    public void inputData(String s) {
        for(int i = 0, n = seq.length(); i < n; i++) {
            if(Character.isDigit(seq.charAt(i))) {
                num.add(Integer.parseInt(String.valueOf(seq.charAt(i))));
            }
        }
    }

    public ArrayList<Integer> calc(ArrayList<Integer> arrList) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int count = 0;
        if(arrList.get(0) <= arrList.size() - 1) {
            for(int i = 1; i < arrList.get(0); i++) {
                temp.add(i - 1, arrList.get(i) - 1);
            }
            if(arrList.get(0) != arrList.size() - 1) {
                for(int j = arrList.get(0) - 1; j < arrList.size() - 1; j++) {
                    temp.add(j, arrList.get(j));
                }
            }
        }
        Collections.sort(temp);
        for(int k = 0; k < temp.size(); k++) {
            if(temp.get(k) == 0) {
                count++;
            }
        }
        if(count != temp.size()) {
            calc(temp);
        }
        return temp;
    }

    public boolean checkInitial(ArrayList<Integer> arrList) {
        for(int i = 0; i < arrList.size() - 1; i++) {
            if(arrList.get(i) < arrList.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Havel h = new Havel();
        h.inputData(h.seq);
        if(!h.checkInitial(h.num)) {
            System.out.println("Invalid degree sequence\n");
            System.out.println("Not organized in non-increasing order\n");
            return;
        }
        if(h.calc(h.num).size() == 0) {
            System.out.println("Invalid degree sequence\n");
            System.out.println("A possible explanation:");
            System.out.println("There are needed at least " + (h.num.get(0)) + " elements after ");
            System.out.println("the first element of the degree sequencefor the Havel Hakimi to work\n");
            System.out.println("OR\n");
            System.out.println("The algorithm's calculation may have yielded an invalid degree ");
            System.out.println("sequence according to the Havel Hakimi standards");
            return;
        }        
        System.out.println("Valid degree sequence");
    }
}