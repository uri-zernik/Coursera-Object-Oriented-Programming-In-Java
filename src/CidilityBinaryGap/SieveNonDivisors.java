package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SieveNonDivisors 
{
	static ArrayList<Integer> myList = new ArrayList<Integer>(); 
	public static int[] myArray = {3,1,2,3,6};

	public static void main(String[] args) {
		System.out.println("Žilina");	

		SieveNonDivisors template = new SieveNonDivisors();;
		//int[] sr= template.solution(myArray);
		
		int ll = myArray.length;

		for (int ii = 0; ii < ll; ii++) {
			System.out.print(myArray[ii]+" ");
		}
		System.out.println();
		
		int[] sr1= template.solution1(myArray);

		
	}
	
	/******* STRAT *********/
	
	public int[] solution1(int[] AA) {
		boolean test = true;
		int LL = AA.length;
		int AMax = -1;

		for (int elem: AA) {
			AMax = Math.max(elem,AMax);
		}

		int[] countAA = new int[AMax+1]; // how many times each number appears in AA
		for (int elem: AA) {
			countAA[elem] = 0;
		}
		for (int elem: AA) {
			countAA[elem]++;
		}
		
		Map<Integer,Set<Integer>> divisors = new HashMap<Integer,Set<Integer>>(); // the DIVISORS of each item in AA (a Map)
		if (test) System.out.print("\nCOUNT:");
		for (int ii = 0; ii < AMax+1; ii++) {
			int elem = countAA[ii];
			//if (test) System.out.print(" "+ (ii)+":"+elem);
			if (elem > 0) {
				Set<Integer> mySet = new HashSet<Integer>();
				mySet.add(1);
				mySet.add(ii);
				if (test) System.out.print((ii) + ":" +elem+":"+ mySet+":  ");

				divisors.put(ii, mySet); // add this set at location elem
			}
		}
		if (test) System.out.println("\n");


		
		int divisor = 2;
		while (divisor * divisor < AMax+1) { // populate DIVISORS, the original Sieve algorithm
			int elementCandidate = divisor;
			
			while (elementCandidate  < AMax+1) {			
				if (divisors.get(elementCandidate) != null) { 
					Set<Integer> mySet2 = divisors.get(elementCandidate);
					if (!mySet2.contains(divisor)) {
		                divisors.get(elementCandidate).add(divisor);
		                divisors.get(elementCandidate).add(elementCandidate/divisor);
					}
				}
				elementCandidate += divisor;
			}
			divisor += 1;
		}	
		
	    /**	just printout **/
		Set entrySet = divisors.entrySet();
	    Iterator it = entrySet.iterator();
	    while(it.hasNext()){
	    	Map.Entry me = (Map.Entry)it.next();
	        if (test) System.out.println("Key is: "+me.getKey() + 
	        " & " + 
	        " value is: "+me.getValue());
	    }
	    /**	just printout **/

	    Set<Integer> setAA = new HashSet<Integer>(); // a set version of AA
		for (int ii = 0; ii < LL; ii++) {
			setAA.add(AA[ii]);
		}

		
	    int[] ret = new int[LL];  // the inefficient result
		for (int ii = 0; ii < LL; ii++) {  // go over AA to print results
			ret[ii] = 0;
			Set<Integer> mySet = divisors.get(AA[ii]); // the set of divisors of this element of AA
			
		    //Iterator it1 = setAA.iterator();
			for (Integer jj: setAA){ // go over the set of AA items
				
				if (!mySet.contains(jj)) {
					ret[ii] += countAA[jj];
				}
			}
		}

	   
		if (test) System.out.print("\nRET: ");
		for (int ii = 0; ii < LL; ii++) {
			if (test) System.out.print(ret[ii]+" ");

		}
		if (test) System.out.println();


	    
		return ret;
	}	
		

	public int[] solution(int[] AA) {
		int ll = AA.length;
		int[][] BB = new int[ll][3];  // sorted version of AA with index to original placement
		 
		int[] Ret = new int[ll]; // the return array

		for (int ii = 0; ii < ll; ii++) {
			BB[ii][0] = AA[ii]; BB[ii][1] = ii; BB[ii][2] = 0;
		}
		
		Arrays.sort(BB, (a, b) -> Integer.compare(a[0], b[0]));
		
		java.util.Arrays.sort(BB, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Integer.compare(a[0], b[0]);
		    }
		});
	

		for (int ii = 0; ii < ll; ii++) {
			for (int jj = 0; jj < ll; jj++) {
				if (BB[ii][0] % BB[jj][0] != 0) {
					BB[ii][2]++;
				}
			}
		}
		
		/**
		for (int ii = 0; ii < ll; ii++) {
			if (test) System.out.print(BB[ii][0]+":"+BB[ii][1]+":"+BB[ii][2]+" ");
		}
		if (test) System.out.println();
		**/
		
		for (int ii = 0; ii < ll; ii++) {
			Ret[BB[ii][1]] = BB[ii][2];
		}

		
		/**
		for (int ii = 0; ii < ll; ii++) {
			if (test) System.out.print(Ret[ii]+" ");
		}
		if (test) System.out.println();
	    **/
		
		return Ret;
	}

}
	