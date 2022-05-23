package CidilityBinaryGap;

import java.util.ArrayList;
import java.util.Arrays;

public class BoundedSlices 
{
	public static void main(String[] args) {
		System.out.println("Žilina");
		int[] AA = {3,5,7,6,3};

		BoundedSlices template = new BoundedSlices();;
		int kk = 2;
		template.solution(AA,kk);
		
	}
	
	public int solution(int[] AA, int kkk) {
		int ll = AA.length;
		System.out.println("LL="+ll+"\n");
		int total = 0;
		for (int pp = 0; pp < ll; pp++) {
			System.out.println("PP="+pp);

			int maxpp = 0;
			int minpp = 1000000;
		
			for (int qq = pp; qq < ll; qq++) {
				maxpp = Math.max(maxpp,  AA[qq]);
				minpp = Math.min(minpp,  AA[qq]);
				boolean ok = (maxpp - minpp) <= kkk;
				System.out.println("   QQ="+ok+"   ("+pp+" "+qq+") mx="+maxpp+": mn="+minpp+": diff="+ (maxpp-minpp) +":"+kkk+":");

				if (ok) {
					total++;
					
					//System.out.print("   OK="+pp+":"+qq+": tot="+total+":");
					/**
					for (int kk = pp; pp <= qq; pp++) {
						System.out.print("  "+kk+":"+ AA[kk]+": ");
					}
					
					System.out.println("");
					**/
				}
			}
		}

		return total;
	}

}
	