package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CommonPrimeDivisorsSet 
{
	static ArrayList<Integer> myList = new ArrayList<Integer>(); 
	public static int[] Aarray = {15, 10, 3};
	public static int[] Barray = {75, 30, 5};
	public boolean[] prime;

	public static void main(String[] args) {
		System.out.println("Žilina");	
		int kk = 0;
		CommonPrimeDivisorsSet template = new CommonPrimeDivisorsSet();;
		int sr = template.solution(Aarray, Barray);
		System.out.println("SR="+sr+":");
		
	}
	
	
	
	public int solution(int[] AA, int[] BB) {
		boolean test = false;
		int LL = AA.length;
		int ABMax = -1;
		
		prime = new boolean[ABMax+1];
		
		// find the max elem of AA and BB
		for (int elem: AA) {
			ABMax = Math.max(elem,ABMax);
		}
		for (int elem: BB) {
			ABMax = Math.max(elem,ABMax);
		}
		if (test) System.out.println("The MAX is:"+ABMax);
		// set the countAB , not really necessary
		int[] countAB = new int[ABMax+1]; // how many times each number appears in AA
		for (int elem: AA) {
			countAB[elem] = 0;
		}
		for (int elem: BB) {
			countAB[elem] = 0;
		}
		for (int elem: AA) {
			countAB[elem]++;
		}
		for (int elem: BB) {
			countAB[elem]++;
		}
		// done setting count
		
		// created empty sets
		Map<Integer,Set<Integer>> ABdivisors = new HashMap<Integer,Set<Integer>>(); // the DIVISORS of each item in AA (a Map)
		//if (test) System.out.print("\nCOUNT:");
		for (int ii = 0; ii < ABMax+1; ii++) {
			int elem = countAB[ii];
			//if (test) System.out.print(" "+ (ii)+":"+elem);
			if (true || elem > 0) {
				Set<Integer> mySet = new HashSet<Integer>();
				//mySet.add(1);
				//mySet.add(ii);
				if (test) System.out.print((ii) + ":" +elem+":"+ mySet+":  ");

				ABdivisors.put(ii, mySet); // add this set at location elem
			}
		}
		if (test) System.out.println("\n");


		
		int divisor = 2;
		while (divisor  < ABMax+1) { // populate DIVISORS, the original Sieve algorithm
			Set<Integer>  myDivisorSet = ABdivisors.get(divisor);
			if (myDivisorSet.isEmpty()) {
				int elementCandidate = divisor;

				while (elementCandidate < ABMax + 1) {
					//if (countAB[elementCandidate] > 0) {
					Set<Integer> mySet = ABdivisors.get(elementCandidate);
					if (true) {
						mySet.add(divisor);
						// mySet.add(elementCandidate/divisor);

					}
					//}
					elementCandidate += divisor;
				}
			}
			divisor += 1;
		}	
		
	    /**	just printout **/
		Set entrySet = ABdivisors.entrySet();
	    Iterator it = entrySet.iterator();
	    while(it.hasNext()){
	    	Map.Entry me = (Map.Entry)it.next();
	        if (test) System.out.println("Key is: "+me.getKey() + 
	        " & " + 
	        " value is: "+me.getValue());
	    }
	    /**	just printout **/
	    
	    // check if divisor sets are equal and count
	    int total = 0;
	    for (int qq = 0; qq < LL; qq++) {
	    	int aa = AA[qq];
	    	int bb = BB[qq];
	    	Set<Integer>  divisorSetAA = ABdivisors.get(aa);
	    	Set<Integer>  divisorSetBB = ABdivisors.get(bb);

	    	boolean tt = divisorSetAA.equals(divisorSetBB);
	    	if (tt) total++;
	    	if (test) System.out.println("COMP: "+tt+":"+aa+":"+bb+":");
	    	
	    }
    
		return total;
	} // solution()

}
	