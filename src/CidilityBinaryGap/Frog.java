package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class Frog 
{

	public static void main(String[] args) {
		System.out.println("Žilina");	
		int DD = 30, XX = 10, YY = 100;
		int sr= solution(XX,YY,DD);
		System.out.println(sr);
		
	}
	
	public static int solution(int XX, int YY,int DD) {
		int res = (YY - XX)/DD;
		int mod = (YY - XX)%DD;
		return (mod == 0)? res : res+1;
	}

}
	