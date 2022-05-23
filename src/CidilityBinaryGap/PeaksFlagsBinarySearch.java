package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class PeaksFlagsBinarySearch 
{
	static ArrayList<Integer> myList = new ArrayList<Integer>(); 
	public static int[] AA = {1, 5,3,4,3,  4,1,2,3,4,6,2};
	private static int ll = AA.length;

	public static void main(String[] args) {
		System.out.println("Žilina");	

		PeaksFlagsBinarySearch template = new PeaksFlagsBinarySearch();;
		int sr = template.solution(AA);
		System.out.println("\nMAX FLAG "+sr);
		
	}
	
	public int solution(int[] AA) {
		int ll = AA.length;
		int[] peaks = new int [ll/2+1];
		int peakNo = createPeaks(AA, peaks, ll);
		
		int maxFlags = (int)(Math.sqrt(peakNo))+1;
		System.out.println("MF="+maxFlags+":  PN="+peakNo);
		boolean found = false;
		int round = 0;
		int upperBound = 4;//maxFlags;
		int lowerBound = 0;
		int midPoint = -1;
		int prevMidPoint = -1;
		while (upperBound >= lowerBound) { // binary search, going up greedy until hit a wall

			midPoint = lowerBound + ( upperBound - lowerBound ) / 2;
			System.out.println("round="+ (round++) +": midPoint="+midPoint+":  upperBound="+upperBound+":  lowerBound="+lowerBound);

			found = tryFlags(peaks, midPoint);
			System.out.println("     found="+found+": midPoint="+midPoint+":");
			if (found) prevMidPoint = midPoint;

			if (found == true) {  // A[midPoint] < x
				lowerBound = midPoint + 1;
			} else if (found == false) { // A[midPoint] > x
					upperBound = midPoint - 1;
			} else { // A[midPoint] = x
				return midPoint; // at midpoint
			}

		}
		return prevMidPoint;
	}
	
	boolean tryFlags(int[] PP, int nf) {

			int prev = PP[0];
			System.out.println("     TRY:" + nf);

			int ss = 1; // no of peaks found so far
			for (int ii = 1; ii < PP.length; ii++) {
				if (PP[ii] - prev >= nf) {
					ss++;
					// System.out.println("FOUND nf="+nf+": ss="+ss+": prev="+prev+":
					// curr="+PP[ii]);
					prev = PP[ii];
				}
			}
		
		return (ss >= nf) ? true : false;
	
	}
	
	int createPeaks(int[] AA, int[] PP, int ll) {
		int ret = 0;
		for (int ii = 0; ii < ll; ii++) {
			if ((ii == 0 && AA[ii] > AA[ii+1]) 
					|| (ii == ll-1 && AA[ii] > AA[ii-1])
					|| (ii > 0 && ii < ll-1 && AA[ii] > AA[ii+1] && AA[ii] > AA[ii-1])) {
				PP[ret++] = ii;
				System.out.println(ii+":"+ret+":  :"+AA[ii-1]+":"+AA[ii]+":"+AA[ii+1]);

			}
			
		}
		System.out.println("PP="+ret);
		return ret;
	}

}
	