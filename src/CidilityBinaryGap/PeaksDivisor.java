package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class PeaksDivisor 
{
	public static int[] AA = {1,2,3,4,3,4,1,2,3,4, 6,2};
	private static int ll = AA.length;

	public static void main(String[] args) {
		System.out.println("Žilina");	

		PeaksDivisor template = new PeaksDivisor();;
		int sr = template.solution(AA);
		System.out.println("\nMAX BLOCKS "+sr);
		
	}
	
	public int solution(int[] AA) {
		int ll = AA.length;
		int[] peaks = new int [ll]; // a peak if marked as 0...n;  a non-peak is -1
		int peakNo = createPeaks(AA, peaks, ll);
		
		System.out.println("PN="+peakNo+": ll="+ll);
		boolean found = false;
		int round = 0;
		int ii;
		for (ii = 2; found == false && ii < ll; ii++ ) { 


			found = (ll % ii == 0) && tryBlocks(peaks, ll, ii);
			System.out.println("     found="+found+": bn="+ll/ii+": size="+ii+":");
			if (found) return ll / ii;
		}
		return (found) ? ii : -1;
	}
	
	
	
	boolean tryBlocks(int[] PP, int ll, int blockSize) {

		int prev = PP[0];
		int noOfBlocks = ll / blockSize;
		System.out.println("     TRY: block size K" + blockSize+": noOfBlocks="+noOfBlocks+":");
		boolean foundBlockWithNoPeak = false;
		for (int ii = 0; ii < noOfBlocks; ii++) {
			System.out.println("           ii="+ii);
			int startCurrBlock = ii * blockSize;
			boolean foundPeak = false;
			for (int jj = 0; jj < blockSize; jj++) {
				System.out.println("               jj="+jj+":  peak?="+PP[startCurrBlock+jj]);
				if (PP[startCurrBlock+jj] > -1) { // -1 means it's not a peak
					foundPeak = true;
					System.out.println("                  FOUND PEAK jj="+ (startCurrBlock+jj));
					break;
				}
			}
			if (foundPeak == false) {
				foundBlockWithNoPeak = true;
				break;
			}
		}
		System.out.println("     RETURN: block size K" + blockSize+": found="+!foundBlockWithNoPeak+":");

		return !foundBlockWithNoPeak ;
	
	}
	
	int createPeaks(int[] AA, int[] PP, int ll) {
		int ret = 0;
		for (int ii = 0; ii < ll; ii++) {
			if ((ii == 0 && ii < ll-1 && AA[ii] > AA[ii+1]) // peak on item 0
					|| (ii == 0 && ii == ll-1) // one item
					|| (ii == ll-1 && AA[ii] > AA[ii-1]) // peak on last item
					|| (ii > 0 && ii < ll-1 && AA[ii] > AA[ii+1] && AA[ii] > AA[ii-1])) { // any other item
				PP[ii] = ret++;
				System.out.println(ii+":"+ret+":  :"+AA[ii-1]+":"+AA[ii]+":"+AA[ii+1]);

			} else {
				PP[ii] = -1;
			}
			
		}
		System.out.println("PP="+ret);
		return ret;
	}

}
	