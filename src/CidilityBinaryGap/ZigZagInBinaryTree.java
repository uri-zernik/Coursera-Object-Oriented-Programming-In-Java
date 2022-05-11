package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;

public class ZigZagInBinaryTree 
{
	
	public static void main(String[] args) {
		System.out.println("Žilina");	
		ZigZagInBinaryTree zigZagInBinaryTree = new ZigZagInBinaryTree();
		
		System.out.println("Ž 1");	

		//String ss = " 5 ,  ( 3 , ( 20 , ( 6 ,  None , None ) , None ) , None ) , ( 10 , ( 1 , None , None ) , ( 15 , ( 30 , None , ( 9 , None , None ) ) , ( 8 , None , None ) ) )  ";
	
		String ss = " 5 ,  ( 3 , ( 20 , ( 6 ,  None , None ) , None ) , None ) , ( 10 , ( 1 , None , None ) , ( 15 , ( 30 , None , ( 9 , ( 13 , None , None ) , None ) ) , ( 8 , ( 12 , None , None ) , None ) ) )  ";

		System.out.println("Ž 2");	

		Scanner sc = new Scanner(ss);
		System.out.println("Ž 3");	

		Tree tree1 = zigZagInBinaryTree.readTreeRec(sc,0,'m',-1);
		int total = zigZagInBinaryTree.solution(tree1);
		System.out.println("\nFound "+total+" zigs");
	}
	

	public Tree readTreeRec(Scanner sc, int nn, char pside, int px) { 
		ZigZagInBinaryTree zigZagInBinaryTree = new ZigZagInBinaryTree();
		Tree ret = zigZagInBinaryTree.new Tree();
		ret.status = 0;
		//System.out.println("NN starting tree="+nn);

    	while (ret.status <= 4 && sc.hasNext()) {

    		String token = sc.next();
    		//System.out.println("NN2="+token+": status="+ret.status+":");

    		if (ret.status == 0 && isNumeric(token)) {
        		//System.out.println("NN3="+token+": status="+ret.status+": pside="+pside+": px="+px+":");
    			ret.x = Integer.parseInt(token);
    			ret.status++;

    		} else if (ret.status == 3 && token.compareTo(")") == 0) {
    			int slx = -1;
    			if (ret.l != null) {
    				slx = (ret.l).x;
    			}
        		System.out.println("NN4="+ret.x+": status="+ret.status+": pside="+pside+": px="+px+": slx="+slx+":");
        		//System.out.println("NN7="+token);
    			return ret; // <-- we return here!!!
    			
    		} else if ((ret.status == 1 || ret.status == 2) && token.compareTo("(") == 0) {
        		//System.out.println("NN4="+token);

    			// start new tree
    			if (ret.status == 1) {
    				ret.l = readTreeRec(sc, nn+1,'l',ret.x); 
    			} else if (ret.status == 2) {
    				ret.r = readTreeRec(sc, nn+1,'r',ret.x); 
    			}
    			int slx= (ret.l != null) ? ret.l.x : -1;
        		//System.out.println("      NN41="+ret.x+": status="+ret.status+": slx="+slx+":");

    			ret.status++;

    		} else if ((ret.status == 1 || ret.status == 2) && token.compareTo("None") == 0) {
        		//System.out.println("NN6="+token);
        		if (ret.status == 1) {
        			ret.l = null;
        		} else if (ret.status == 2) {
        			ret.r = null;
            	} else {
        			System.out.println("INPUT ERROR! cant be none");
        		}
    			ret.status++;

        	} else if (token.compareTo(",") == 0) {
            	//System.out.println("NN0="+token);
         		
    		} else {
        		//System.out.println("NN8="+token);

    			ret = null;
    			System.out.println("INPUT ERROR!");	
    		}
    	}
    	return ret;
	} // readTreeRec()
	
	
	public boolean isNumeric(String str) {  // a hack found on StackOverflow
		  ParsePosition pos = new ParsePosition(0);
		  NumberFormat.getInstance().parse(str, pos);
		  return str.length() == pos.getIndex();
		}
	


	public int solution(Tree T)  {
		int total = 0;
		total = travNode(T, 'm',-1);
		return total;
	}
	
	private int travNode(Tree tt, char pside, int px) { // 0 = none; 1 = left; 2 = right
		if (tt == null) {
			return 0;
		}
		int zigr = travNode(tt.r,'r',tt.x);
		int zigl = travNode(tt.l,'l',tt.x);
		int hl = (tt.l ==null) ? 0 : 1;
		int hr = (tt.r ==null) ? 0 : 1;
		int myzigl = 0;
		int myzigr = 0;
        if (pside=='r' && hl == 1) myzigl=1;
        if (pside=='l' && hr == 1) myzigr = 1;
        int total = Math.max(zigl + myzigl, zigr + myzigr);
		System.out.println(""+tt.x+":TOT="+total+": MZL="+myzigl+": MZR="+myzigr+": ZL="+zigl+": ZR="+zigr+":");

		return total;
	}
	
	public class Tree {
		public int x;
		public Tree l;
		public Tree r;
		int status; // 0 -- x; 1 -- l; 2 -- r
			
		public Tree() { // const
				status = 0;
				l = r = null;
				x = 0;
			

		}
	}
}

	