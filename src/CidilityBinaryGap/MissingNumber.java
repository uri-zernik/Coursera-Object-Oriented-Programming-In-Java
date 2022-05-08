package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class MissingNumber 
{
	public static int[] AA = {5,6,3,4,2};
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
		int ret = -1;
		int ii =0;
		int curr = 0;
		for (ii = 0; ii < LL; ii++) {
			if (AA[ii] != curr+1) {
				ret = curr+1;
			} else {
				curr = AA[ii];
			}
		}

		return ret;
	}	
}


