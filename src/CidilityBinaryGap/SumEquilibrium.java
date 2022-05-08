package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class SumEquilibrium 
{
	public static int[] AA = {1,2,3,4,5,1,4,3};
	private static int LL = AA.length;

	public static void main(String[] args) {
		System.out.println("Žilina");
		int KK = 0;
		int sr = solution(AA);
		System.out.println("Ž"+sr);
		
	}
	
	public static int solution(int[] AA) {
		int total = 0;
		for (int ii = 0; ii < LL; ii++) {
			total += AA[ii];
		}
		int tot0 = 0;
		int tot1 = total;
		int prevDiff = total+1;
		int diff = 0;
		int ret = total+1;

		int ind = -1;
		for (int ii = 0; ii < LL; ii++) {

			tot0 = tot0 + AA[ii];
			tot1 = tot1 - AA[ii];
			diff = Math.abs(tot1 - tot0);
			if (prevDiff < diff && ind == -1) { 
				ret = prevDiff; 
				ind = ii; 
			}
			System.out.println("Q ii="+ii+": prevDiff="+prevDiff+": diff="+diff+": tot1="+tot0+": tot2="+tot1+": ind="+ind);
			prevDiff = diff; 
		}
		return ret;
	}
}
	