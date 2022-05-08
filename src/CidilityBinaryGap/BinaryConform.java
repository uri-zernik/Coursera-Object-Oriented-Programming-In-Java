package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

import module6.EarthquakeMarker;


public class BinaryConform 
{
	


	static ArrayList<Integer> resLArray = new ArrayList<Integer>();  // keep as a list so you can sort, uniq, add, remove
	
	static int[] in = new int[5];  // input numbers
	static int[] ll = new int[5]; // length in bits of input numbers, must be the same for all
	
	static int NN = 3; // no of input numbers
	static final int maxBit = 6;  // the number of bit of input numbers
	
	static int[][] bitArray = new int[5][100]; // bit representation of input numbers, represent 1000^4	x 5 numbers
	static int[][] copyArray = new int[5][100]; // only the non-1 cols of bitArra, generated from bitArra by conversionArray
	
	static int[] conversionArray = new int[100]; // {4,6,9} , list the shared zero columns at play
	
	static int[][] bitCountArray = new int[100][100];  // count from 0 to 7 in bits
	static int[] zeroCol = new int[100]; // represent 10^4 , list the shared zero columns at play
	static int noZeroCols; // how many cols do we have in zeroCol

	static int totalCount; // how many candidates in this count? 2^4?
	
	public static void main(String[] args) {
		System.out.println("Žilina");	
		in[0] = 59;// all input numbers have 6 bits
		in[1] = 53;
		in[2] = 55;

		int sr = solution(NN);
		System.out.println("SOLUTION="+sr+":");
	}
	
	public static int solution(int NN) {		
		ll[0] = num2Bits(0,bitArray, in[0]);
		ll[1] = num2Bits(1,bitArray, in[1]);
		ll[2] = num2Bits(2,bitArray, in[2]);
		noZeroCols = FindConversion(NN);
		copyZeroBitsOnly(NN, noZeroCols);
		countAndCopy(NN, noZeroCols, bitCountArray);
		conformAll(copyArray, bitCountArray, NN, totalCount);
		int ret = SortUniq();
		return ret;
	}
	
	
	// convert int to its bit representation
	private static int num2Bits(int sn, int someArray[][], int my_in) {
		int ii = 0;
		int Res = my_in;
		System.out.println("N2B="+Res+": sn="+sn+": BITS=");
		while (Res > 0) {
			int Mod = Res % 2;
			Res = Res / 2;
			someArray[sn][ii] = Mod;
			//System.out.println("    HI: ii="+ii+": Mod="+Mod+": Res="+Res+": sn="+sn+":");
			//System.out.print(":"+Mod);
			ii++;
		}
		//System.out.println("");

		for (int jj = ii; jj < maxBit; jj++) {
			someArray[sn][jj] = 0;
		}
		return ii;
	}
	
	// create the conversionArray of non-1 cols
	private static int FindConversion(int NN) {
		System.out.println("FC NN="+NN+":");

		int zz = 0;
		for (int ii = 0; ii < maxBit; ii++) { // init
			zeroCol[ii] = 0;
		}
		System.out.println("FC init");

		
		for (int nn = 0; nn < NN; nn++) {
			for (int ii = 0; ii < ll[nn]; ii++) {
				System.out.println("  FC NN="+NN+": nn="+nn+": ii="+ii+": ll="+ll[nn]+":");
				if (bitArray[nn][ii] == 0) {
					zeroCol[ii]++; 
				}
		
			}
			System.out.println("");
		}
		System.out.println("FC collect");

		
		int ret = 0;
		for (int ii = 0; ii < 40; ii++) {
			if (zeroCol[ii] > 0) {
				conversionArray[ret++] = ii;
				System.out.println("CA["+ (ret-1) +"]="+ii+":");
			}
		}
		System.out.println("FC done");

		return ret;
	} // FindConversion()
	
	// copy only the non-1 cols of bitArray into another array
	private static void copyZeroBitsOnly(int NN, int noZeroCols) { 
		for (int nn = 0; nn < NN; nn++) {
			System.out.print("CAC :"+nn+"=");

			for (int ii = 0; ii < noZeroCols; ii++) {
				copyArray[nn][ii] = bitArray[nn][conversionArray[ii]];
				System.out.print(":"+copyArray[nn][ii]);
			}
			System.out.println("=");
			printBitArray(nn,copyArray);
		}
		System.out.println("DONE CAC :"+NN+":\n");

	}
	
	// print bitArray
	private static void printBitArray(int nn, int bitCountArray[][]) {
		System.out.println("printBitArray nn="+nn+":");
		System.out.print("NUM:"+nn+": ");
		for (int ii = 0; ii < noZeroCols; ii++) {
			System.out.print(":"+bitCountArray[nn][ii]);
		}
		System.out.println("");

	}

	// create bitCountArray for countArray
	private static void countAndCopy(int NN, int noZeroCols, int bitCountArray[][]) {
		System.out.println("COUNT COPY\n");

		int ii = 0;
		totalCount = (int)(Math.pow(2, noZeroCols));
		for (ii = 0; ii < totalCount; ii++) {
			System.out.println("   DOING NUMBER :"+ii+":");

			num2Bits(ii, bitCountArray, ii);
			printBitArray(ii, bitCountArray);
		}
		System.out.println("DONE COUNT COPY\n");

	}
	
	// sort the results and uniquify
	private static int SortUniq() {
		resLArray.sort(null);
		int ii = 0;
		System.out.println("SORT0="+resLArray.size()+":");
		for (Integer xx: resLArray) {
			System.out.println("   SORTED="+(ii++)+":"+xx+":");
		}
		for (int jj = 1; jj < resLArray.size(); jj++) {
			if (resLArray.get(jj) == resLArray.get(jj-1)) {
				//System.out.println("   UNIQ0="+(jj)+":"+resLArray.get(jj)+":");
				resLArray.remove(jj);jj--;
				//System.out.println("   UNIQ1="+(jj)+":"+resLArray.get(jj)+":");

			}
		}

		ii = 0;	
		System.out.println("SORT1="+resLArray.size()+":");
		for (Integer xx: resLArray) {
			System.out.println("   SORTED="+(ii++)+":"+xx+":");
		}
		return resLArray.size();
	} // SortUniq
	
	// conver bit array to an integer number
	private static int bit2Num(int aa[]) {
		int num = 0;
		for (int ii = 0; ii < noZeroCols; ii++) {
			num = 2 * num + aa[ii];
		}
		return num;
	}
	
	// check which numbers in the count conform with the zeroArray and create a result ListArray
	private static void conformAll(int a1[][],int a2[][], int n1, int n2) {
		for (int ii = 0; ii < n1; ii++) {
			for (int jj = 0; jj < n2; jj++) {
				boolean ok = conform(a1[ii], a2[jj], noZeroCols);
				System.out.println("CONFORM="+ok+" for:"+n1+":"+n2+":");
				if (ok == true) {
					int num = bit2Num(a2[jj]);
					resLArray.add(num);
				}
			}
		}
	
	}
	
	// does the second number conform with the first number
	private static boolean conform(int a1[], int a2[], int ll) {  // i2 conforms with i1?
		boolean ret = true;
		for (int ii = 0; ii < ll; ii++) {
			if (a1[ii] == 1 && a2[ii] == 0) {
				ret = false;
			}
		}
		return ret;
	}
	
}