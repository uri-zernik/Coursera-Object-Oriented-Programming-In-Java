package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;


public class BinaryGap {
	
	
	
	public static void main(String[] args) {
		System.out.println("Žilina");	
		int input = 47;
		int sr = solution(input);
		System.out.println("SOLUTION="+sr+":");

	}

	public static int solution(int input) {
		ArrayList<Integer> bitLArray = new ArrayList<Integer>();
		int[] bitArray = new int[1000];
		
		int ii = 0;
		int Res = input;
		while (Res > 0) {
			int Mod = Res % 2;
			Res = Res / 2;
			bitArray[ii] = Mod;
			System.out.println("HI: ii="+ii+": Mod="+Mod+":"+bitArray[ii]+": Res="+Res+":");
			ii++;
		}
		
		int jj = 0;
		int last = -1;
		int first = -1;
		int len = -1;
		//System.out.println("ARR0 ii=%d: jj=%d: aj=%d:\n\n", ii, jj, my_array[jj]);
		int maxLEN = 0;
		for (jj = 0; jj <= ii; jj++) {
			if (bitArray[jj] == 1) {

				last = first;
				first = jj;
				if (first > -1 && last > -1)
					len = first - last - 1;
				if (len > maxLEN) {
					maxLEN = len;
					System.out.println("   MAX="+maxLEN+": len="+len+": jj="+jj+": first="+first+": last ="+last+":");
				}
			}
		}

		return maxLEN;
	}
	
}