package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class CyclicRotation 
{
	public static int[] AA = {1,2,3,4,5};

	public static void main(String[] args) {
		System.out.println("Žilina");	
		int KK = 10;
		CyclicRotation c = new CyclicRotation();
		int sr[] = solution(AA, KK);
		for (int ii = 0; ii < AA.length; ii++) {
			System.out.print(":"+AA[ii]);
		}
		System.out.println(":");
		
	}
	
	public static int[] solution(int[] AA, int KK) {
		int LL = AA.length;
		int[] BB = new int[LL];
		for (int ii = 0; ii <= KK; ii++) {
			for (int jj = 0; jj < LL; jj++) {
				int MM = (ii + jj) % LL;
				BB[MM] = AA[jj];
			}
					
		}
	
	
		for (int jj = 0; jj < LL; jj++) {
			AA[jj] = BB[jj];
		}
		return AA;
	}

}
	