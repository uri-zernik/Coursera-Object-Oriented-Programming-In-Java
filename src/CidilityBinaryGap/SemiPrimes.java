package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SemiPrimes 
{
	
    boolean prime[];
    boolean sPrime[];

	public static void main(String[] args) {
		System.out.println("Žilina");	
		int NN = 26;
		int PP[] = {1, 4, 16};
		int QQ[] = {26, 10, 20};
		SemiPrimes template = new SemiPrimes();;
		int[] sr = template.solution(NN, PP, QQ);
		
		System.out.print("\nRET ");
		for (int ii = 0; ii < PP.length; ii++) {
			System.out.print(sr[ii]+": ");
		}
		
	}
	
	
    void sieveOfEratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        for(int i=0;i<=n;i++)
            prime[i] = true;
         
        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }
         
        // Print all sprime numbers
        /**
        for(int i = 2; i <= n; i++)   {
            if(prime[i] == true) System.out.print(i + " ");
        }
        **/
    } // sieveOfEratosthenes()
	
	void removeIN(ArrayList<Integer> IN, int rm) {
		if (true) {
			int ind = IN.indexOf(rm);
			IN.remove(ind);
			return;
		} else {
			Iterator<Integer> iter = IN.iterator();
			while (iter.hasNext()) {
				int item = iter.next();
				if (item == rm) {
					iter.remove();
					break;
				}
			}
		}
	}
	
	public int[] solution(int NN, int[] PP, int[] QQ) {
		prime = new boolean[NN+1];
		sPrime = new boolean[NN+1];
		sieveOfEratosthenes(NN);
		int[] ret = new int[PP.length]; // the number of primes per query
		for (int ii = 2; ii < NN; ii++) {
			sPrime[ii] = false;
		}
		for (int ii = 2; ii < NN; ii++) {
			for (int jj = ii; jj < NN; jj++) {
				if (prime[ii] && prime[jj] && ii * jj <= NN) {
					sPrime[ii * jj] = true;
				}
			}
		}
		
		/**
		System.out.print("\nsPrimes: ");
		for (int ii = 2; ii <= NN; ii++) {
			if (sPrime[ii] == true) {
				System.out.print(ii+" ");
			}
		}
		System.out.println();
		**/
		
		/**  prepare queries **/
		
		int LL = PP.length; // no of queries

		int sPP[][] = new int[LL][2];
		int sQQ[][] = new int[LL][2];
		
		for (int ii = 0; ii < LL; ii++) {
			sPP[ii][0] = PP[ii]; sPP[ii][1] = ii;
			sQQ[ii][0] = QQ[ii]; sQQ[ii][1] = ii;
		}

		java.util.Arrays.sort(sPP, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Integer.compare(a[0], b[0]);
		    }
		});
		java.util.Arrays.sort(sQQ, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Integer.compare(a[0], b[0]);
		    }
		});

		/**
		System.out.print("\nPP: ");
		for (int ii = 0; ii < LL; ii++) {
			System.out.print(sPP[ii][0]+":"+sPP[ii][1]+" ");
		}
		System.out.print("\nQQ: ");
		for (int ii = 0; ii < LL; ii++) {
			System.out.print(sQQ[ii][0]+":"+sQQ[ii][1]+" ");
		}
		**/
		/**  done prepare queries **/
		
		
		/** run queries **/
		
		ArrayList<Integer> IN = new ArrayList<Integer>();
		int currPP = 0; int currQQ = 0;
		//System.out.print("\nII("+LL+"): ");

		for (int ii = 0; ii < NN+1; ii++) {
			//System.out.print("FOR:"+ii+" ");

			while (currPP < LL && sPP[currPP][0] <= ii) {
				//System.out.print(ii+":"+currPP+"  ");

				if (sPP[currPP][0] == ii) {
					IN.add(sPP[currPP][1]);
					//printIN(0, IN, sPP[currPP][1]);
				}
				currPP++;
			}


			Iterator<Integer> iter = IN.iterator();
			while(iter.hasNext()) {
				int item = iter.next();
				if (sPrime[ii]) ret[item]++;	
			}

			while (currQQ < LL && sQQ[currQQ][0] <= ii) {
				if (sQQ[currQQ][0] == ii) {
					//System.out.println("     VV: currQQ="+currQQ+": Scq="+sQQ[currQQ][1]+":  ");
					removeIN(IN, sQQ[currQQ][1]);
					//printIN(1, IN, sQQ[currQQ][1]);
				}
				currQQ++;
			}
			//System.out.println();
		}
		
		
		/** done run queries **/


		return ret;

	}  // solution()
	
	void printIN(int nn, ArrayList<Integer> IN, int recent) {
		System.out.print("\nIN"+nn+" ("+recent+"): ");
		Iterator<Integer> iter = IN.iterator();
		while(iter.hasNext()) {
			int item = iter.next();
			System.out.print(item+":  ");
		}
		System.out.println();
		
	}



}
	