import java.math.BigInteger;
import java.util.Scanner;

public class rc2 {
	
	private static String CharToBinarString(char first, char second) {
		String charToString = Character.toString(first)+Character.toString(second);
		String binarString = new BigInteger(charToString.getBytes()).toString(2);
		return binarString;
	}
	
	private static String BinaryStringToSChar(String first) {
		String text = new String(new BigInteger(first, 2).toByteArray());
		return text;
	}
	
	private static int BinarStringToInt(String first) {
		return Integer.parseInt(first, 2);
	}
	
	private static String IntToBinarString(int first) {
		String binarString = Integer.toBinaryString(first);
		return binarString;
	}
	
	private static String[] Encrypt(String sWord, String sKey) {
		System.out.println("Кодировка: ");
		char[] cWord = sWord.toCharArray();
		char[] cKey = sKey.toCharArray();
		
		String a = CharToBinarString(cWord[0], cWord[1]);
		System.out.println(cWord[0] + " " + cWord[1]+" = " + a);
		String b = CharToBinarString(cWord[2], cWord[3]);
		System.out.println(cWord[2] + " " + cWord[3]+" = " + b);
		String c = CharToBinarString(cWord[4], cWord[5]);
		System.out.println(cWord[4] + " " + cWord[5]+" = " + c);
		String d = CharToBinarString(cWord[6], cWord[7]);
		System.out.println(cWord[6] + " " + cWord[7]+" = " + d);
		String key = CharToBinarString(cKey[0], cKey[1]);
		System.out.println(cKey[0] + " " + cKey[1]+" = " + key+"\n");
		
		String part1 = IntToBinarString(BinarStringToInt(a) + BinarStringToInt(key));
		System.out.println("1. " + cWord[0] + " " + cWord[1] +
						   " + " + cKey[0] + " " + cKey[1] +" => " +
						   CharToBinarString(cWord[0], cWord[1]) + " + " +
						   CharToBinarString(cKey[0], cKey[1]) + " => " +
						   BinarStringToInt(a) + " + " + BinarStringToInt(key) +
							" = " +  (BinarStringToInt(a) + BinarStringToInt(key)) +
							" => " + part1+"\n");
		
		String part2 = IntToBinarString(BinarStringToInt(c) & BinarStringToInt(d));
		System.out.println("2. " + cWord[4] + " " + cWord[5] +
				   " & " + cWord[6] + " " + cWord[7] +" => " +
				   CharToBinarString(cWord[4], cWord[5]) + " & " +
				   CharToBinarString(cWord[6], cWord[7]) + " => " +
				   BinarStringToInt(c) + " & " + BinarStringToInt(d) +
					" = " +  (BinarStringToInt(c) & BinarStringToInt(d)) +
					" => " + part2+"\n");
		
		String part3 = IntToBinarString(~BinarStringToInt(d) & BinarStringToInt(b));
		System.out.println("3. " +"(~ " + cWord[6] + " " + cWord[7] +
				   ") & " + cWord[2] + " " + cWord[3] +" => ~ (" +
				   CharToBinarString(cWord[6], cWord[7]) + ") & " +
				   CharToBinarString(cWord[2], cWord[3]) +" => ~ (" +
				   BinarStringToInt(d) + ") & " + BinarStringToInt(b) +
					" = " +  ((~BinarStringToInt(d)) & BinarStringToInt(b)) +
					" => " + part3+"\n");
		
		String part4 = IntToBinarString(BinarStringToInt(part2) + BinarStringToInt(part3));
		System.out.println("4. " +"( " + cWord[4] + " " + cWord[5] +
				   		   " & " + cWord[6] + " " + cWord[7] +" ) + " +
				   		   "((~ " + cWord[6] + " " + cWord[7] +
						   ") & " + cWord[2] + " " + cWord[3] + " ) => " +
				   		   part2 + " + " + part3 + " => " +
				   		   BinarStringToInt(part2) + " + " +
				   		   BinarStringToInt(part3) + " = " +
				   		   (BinarStringToInt(part2) + BinarStringToInt(part3)) +
				   		   " => " + part4+"\n");
		
		String part5 = IntToBinarString(BinarStringToInt(part4) + BinarStringToInt(part1));
		System.out.println("5. " +"( " + cWord[0] + " " + cWord[1] +
		   		   " + " + cKey[0] + " " + cKey[1] +" ) + " +
		   		   "(( " + cWord[4] + " " + cWord[5] +
				   " & " + cWord[6] + " " + cWord[7] +" ) + " +
				   "((~ " + cWord[6] + " " + cWord[7] +
				   ") & " + cWord[2] + " " + cWord[3] + " )) => " +
				   part1 + " + " + part4 + " => " +
				   BinarStringToInt(part1) + " + " +
		   		   BinarStringToInt(part4) + " = " +
		   		   (BinarStringToInt(part1) + BinarStringToInt(part4)) +
		   		   " => " + part5+"\n");
		
		String part6 = IntToBinarString(BinarStringToInt(part5)<<4);
		System.out.println("6. (" +"( " + cWord[0] + " " + cWord[1] +
		   		   " + " + cKey[0] + " " + cKey[1] +" ) + " +
		   		   "(( " + cWord[4] + " " + cWord[5] +
				   " & " + cWord[6] + " " + cWord[7] +" ) + " +
				   "((~ " + cWord[6] + " " + cWord[7] +
				   ") & " + cWord[2] + " " + cWord[3] + " ))) << 4 => (" +
				   part1 + " + " + part4 + ") << 4 => (" +
				   BinarStringToInt(part1) + " + " +
		   		   BinarStringToInt(part4) + ") <<4 = " +
		   		   (BinarStringToInt(part1) + BinarStringToInt(part4)) +
		   		   " => " + part6+"\n");
		
		a = part6;
		
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		System.out.println("d = " + d);
		System.out.println("a = " + a);
		System.out.println("key = " + key + "\n");
		
		System.out.println("Объединяем: "+ b + c + d + a);
		
		String[] values = new String[5];
		values[0] = b;
		values[1] = c;
		values[2] = d;
		values[3] = a;
		values[4] = key;
		
		
		return values;
	}
	
	private static String[] Descript(String[] Encripted) {
		System.out.println("\nРаскодировка: ");
		String a, b, c, d, key;
		
		b = Encripted[0];
		c = Encripted[1];
		d = Encripted[2];
		a = Encripted[3];
		key = Encripted[4];
		
		String part1 = IntToBinarString(BinarStringToInt(a)>>4);
		System.out.println("1. a >> 4 = " + a + " >> 4 = " + 
							(BinarStringToInt(part1)>>4) + " = " +
							part1+"\n");
		
		String part2 = IntToBinarString(BinarStringToInt(c) & BinarStringToInt(d));
		System.out.println("2. c & d = " + 
				  			c + " & " +
				  			d + " => " +
				  			BinarStringToInt(c) + " & " + BinarStringToInt(d) +
				  			" = " +  (BinarStringToInt(c) & BinarStringToInt(d)) +
				  			" => " + part2+"\n");
		
		String part3 = IntToBinarString(~BinarStringToInt(d) & BinarStringToInt(b));
		System.out.println("3. (~ d) & b  => ~ (" +
				   d + ") & " +
				   b +" => ~ (" +
				   BinarStringToInt(d) + ") & " + BinarStringToInt(b) +
					" = " +  ((~BinarStringToInt(d)) & BinarStringToInt(b)) +
					" => " + part3+"\n");
		
		String part4 = IntToBinarString(BinarStringToInt(part2) + BinarStringToInt(part3));
		System.out.println("4. (c & d) + (not d ^b) => " +
				   		   part2 + " + " + part3 + " => " +
				   		   BinarStringToInt(part2) + " + " +
				   		   BinarStringToInt(part3) + " = " +
				   		   (BinarStringToInt(part2) + BinarStringToInt(part3)) +
				   		   " => " + part4+"\n");
		
		String part5 = IntToBinarString(BinarStringToInt(part1) - BinarStringToInt(part4));
		System.out.println("5. ( PUNCT_1 - (b ^ (not d) + (c ^ d) )) => " +
							part1 + " - " + part4 + " => " +
				   		   BinarStringToInt(part1) + " - " +
				   		   BinarStringToInt(part4) + " = " +
				   		   (BinarStringToInt(part1) - BinarStringToInt(part4)) +
				   		   " => " + part5+"\n");
		
		String part6 = IntToBinarString(BinarStringToInt(part5) - BinarStringToInt(key));
		System.out.println("6. PUNCT_5 - key => " +
							part5 + " - " + key + " => " +
				   		   BinarStringToInt(part5) + " - " +
				   		   BinarStringToInt(key) + " = " +
				   		   (BinarStringToInt(part5) - BinarStringToInt(key)) +
				   		   " => " + part6+"\n");
		
		String[] values = new String[5];
		values[0] = part6;
		values[1] = b;
		values[2] = c;
		values[3] = d;
		values[4] = key;
		System.out.println("Объединяем: "+ part6 + b + c + d+"\n");
		
		return values;

	}
	
	
	public static void main(String args[])
	{
	       Scanner sc = new Scanner(System.in);
	       String word, key;
	       do {
	    	   System.out.print("Введите слово из 8 букв(на латинице): ");
		       word = sc.nextLine();
		       
	       }while(word.length()!=8);
	       
	       do {
	    	   System.out.print("Введите кодовое слово из 2 букв (на латинице): ");
		       key = sc.nextLine();
	       }while(key.length()!=2);
	       
	       System.out.println();
	       sc.close();
	       
	       String[] encryptedWord = Encrypt(word, key);
	       String[] descriptedWord = Descript(encryptedWord);
	       
	       for(int i=0;i<4;i++)
	       {
	    	   if (descriptedWord[i].length()<16) 
	    	   {
	    		   descriptedWord[i] = "0" + descriptedWord[i];
	    	   }
	       }
	       
	       String binaryWord = descriptedWord[0] + descriptedWord[1] +
	    		   descriptedWord[2] + descriptedWord[3];
	       String binaryKey = descriptedWord[4];
	       
	    	
   		   System.out.println("Ваше защифрованное слово: " + BinaryStringToSChar(binaryWord));
   		   System.out.println("Ваш защифрованный ключ: " + BinaryStringToSChar(binaryKey));
		
	}
}
