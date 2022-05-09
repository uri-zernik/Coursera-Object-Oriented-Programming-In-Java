package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class Genomic 
{
	
	public static String SS = "CAGCCTA";
	public static int[] PP = {2,5,0};
	public static int[] QQ = {4,5,6};

	public static void main(String[] args) {
		System.out.println("Žilina");	
		int kk = 0;
		int[] sr = solution(SS, PP, QQ);
		printResultArray(sr, 3, 5);
		
	}
	
	public static int[] solution(String SS, int[] PP, int QQ[]) {
		ArrayList<Integer>[] pList = new ArrayList[100000]; // the list of start points per S item
		ArrayList<Integer>[] qList = new ArrayList[100000]; // the list of end points per S item

		int NN = SS.length();
		int MM = PP.length;
		
		//new ArrayList<Integer>[100000];
			
		int[] priceArray = new int[300]; // for each letter in ascii its corresponding price
		priceArray['A'] = 1;
		priceArray['C'] = 2;
		priceArray['G'] = 3;
		priceArray['T'] = 4;
		int maxVal = 5; // constant larger than 4
		
		int[] resultArray = new int[50000]; // the results per query, direct access
		ArrayList<Integer> openList = new ArrayList<Integer>(); // the list of queries that are open (assumed to be a short list)		
		// setup
		setup(pList, qList, MM);
		// run
		for(int ii = 0; ii < NN; ii++) {
			startSeq(openList,pList,ii );
			calcSeq(openList, resultArray, priceArray, ii, maxVal, MM);
			endSeq(openList,qList,ii);
		}

		return resultArray;
	} // solution
	
	private static void startSeq(ArrayList<Integer> openList, ArrayList<Integer>[] pList, int ii ) {
			
		printOpenList(openList,0);

		ArrayList<Integer> myP = pList[ii];
		if (myP != null) {
			for (Integer pp : myP) {
				openList.add(pp); // add the sequence number
				System.out.println("+++++++++	ADD: loc="+ii+": sn="+pp+":");
			}
		}
	}
	
	private static void calcSeq(ArrayList<Integer> openList, int[] resultArray, int[] priceArray, int ii, int maxVal, int MM) {
		for (Integer oo: openList) {
			int val = resultArray[oo];
			if (val == 0) resultArray[oo] = maxVal;
			resultArray[oo] = Math.min(resultArray[oo], priceArray[SS.charAt(ii)]);
			printResultArray(resultArray,0, MM);
			System.out.println("================ 	RA  loc=" + ii + ": oo=" +oo+ ": val=" + val + ":");
		}
	}
	
	private static void endSeq(ArrayList<Integer> openList, ArrayList<Integer>[] qList, int ii) {
		ArrayList<Integer> myQ = qList[ii];
		if (myQ != null) {
			for (Integer qq : myQ) {
				for (Integer oo: openList) {
					if (qq == oo) {
						openList.remove(qq);
						System.out.println("-------  	REM  loc=" + ii + ": sn=" + qq + ":");
						break;
					}
				}
			}
		}
		printOpenList(openList,5);	
	
	} // endSeq()
	
	private static void setup(ArrayList<Integer>[] pList,ArrayList<Integer>[] qList, int MM) {
		for (int ii = 0; ii < MM; ii++) {  // prepare pq lists
			int myPP = PP[ii];
			ArrayList<Integer> pListItem = null;
			if (pList[myPP] == null) {
				pListItem = new ArrayList<Integer>();
				pList[myPP] = pListItem;
			}
			pList[myPP].add(ii);
			
			int myQQ = QQ[ii];
			ArrayList<Integer> qListItem = null;
			if (qList[myQQ] == null) {
				qListItem = new ArrayList<Integer>();
				qList[myQQ] = qListItem;
			}
			qList[myQQ].add(ii);
		}
	}
	
	private static void printOpenList(ArrayList<Integer> openList, int nn) {
		int ll = openList.size();
		System.out.print("            openList"+nn+" l="+ll+":");
		for (Integer oo: openList) {
			System.out.print(":"+oo);
		}
		System.out.println(":");	

	}
	
	private static void printResultArray(int[] resultArray, int nn, int MM) {
		int ll = resultArray.length;
		System.out.print("            resultArray"+nn+" l="+MM+":  ");
		//for (Integer rr: resultArray) {
		for (int rr = 0; rr < MM; rr++) {
			if (resultArray[rr] == 0) break;
			System.out.print(":"+resultArray[rr]);
		}
		System.out.println(":");	

	}
}
	