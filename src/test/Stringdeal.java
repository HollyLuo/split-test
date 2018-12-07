package test;


import java.util.Scanner;

public class Stringdeal {
 
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		String s = scann.nextLine();
		
		System.out.println(search(s));
 
		scann.close();
	}
	
	/**
	 * 功能：求一个字符串中连续出现次数最多的子串
	 * @param str
	 * @return
	 */
	/*
	 * 把字符串用后缀树的形式表现出来如下：
	 * a b c a b c a b c d  .substring[0]
	 * b c a b c a b c d  ...substring[1]
	 * c a b c a b c d  .....substring[2]
	 * a b c a b c d  .......substring[3]
	 * b c a b c d  .........substring[4]
	 * c a b c d  ...........substring[5]
	 * a b c d  .............substring[6]
	 * b c d  ...............substring[7]
	 * c d  .................substring[8]
	 * d  ...................substring[9]
	 */
	public static String search(String str){
		int len = str.length();
	    int maxCount = 0;
	    String longest = "";
	    for(int pos1 = 0; pos1 < len; pos1++)
	        for(int pos2 = pos1 + 1; pos2 < len && 2*pos2-pos1 <=len; pos2++){
	        	// 保证 2*pos2-pos1 <=len , 不能substring(pos2,2*pos2-pos1)会出现越界
	        	int count = 1;
	            if(str.substring(pos1,pos2).equals(str.substring(pos2,2*pos2-pos1))){
	            	// str.substring(pos2,2*pos2-pos1)相当于子串substring[pos2]
	                int offset = pos2-pos1;
	                count++;
	                for(int s = pos2 + offset; s < len && s+offset <= len; s += offset){
	                    if(str.substring(pos1,pos1+offset).equals(str.substring(s,s+offset))){
	                        count++;
	                    }else{
	                        break;
	                    }
	                }
	                if(count > maxCount){
	                    maxCount = count;
	                    longest = str.substring(pos1,pos1+offset);
	                }
	            }
	        }
	    return (longest+","+String.valueOf(maxCount));
	    }
	}
