package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class OddOccurrencesInArray 
{
	public static int[] AA = {1};//,2,6,1,2,6,1,3,5,2,6,1,2,6};
	public static int[][] BB = new int[1000000][2];
	private static int LL;

	public static void main(String[] args) {
		LL = AA.length;
		int sr = solution(AA);
		System.out.println("OUT:"+sr+":");
		return;
	}
	private static void printArray(int AA[]) {
		for (int ii = 0; ii < AA.length; ii++) {
			System.out.print(":"+AA[ii]);
		}
		System.out.println(":");
	}
	
	public static int solution(int[] AA) {
		Arrays.sort(AA);
		printArray(AA);
		int foundOdd = -1;
		int curr = -1;
		int ii =0;
		for (ii = 0; ii < LL; ii++) {
			System.out.println("II : ii="+ii+":  AA[ii]="+AA[ii]+": curr="+curr+":");

			if (curr == -1) {
				curr = AA[ii];
			} else if (curr > -1) {
				if (AA[ii] == curr) {
					curr = -1;
				} else {
					foundOdd = AA[ii-1];
					break;
				}
			}
			System.out.println("OO : ii="+ii+":  AA[ii]="+AA[ii]+": curr="+curr+":");
		}
		if (ii == LL) foundOdd = AA[ii-1];
		return foundOdd;
	}	
}


