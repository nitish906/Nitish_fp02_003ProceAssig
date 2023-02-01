package com.masai;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CsvFileReader {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		csvOutput(input);
	}
	
	
	public static void csvOutput(String input) {
	
		String[] array = input.split(", ");
		
		if(array.length==0) {
			System.out.println("Invalid Entites Input");
			return;
		}
		
		Map<String,String> cells = new LinkedHashMap<>();
		
		try {
			for(String str:array) {
			
				String[] cellVal = str.split(": ");

				if(cellVal.length!=2) {
					System.out.println("Invalid Entite Input");
					return;
				}
				
				String cell = cellVal[0];
				String value = cellVal[1];
				
				
				if(isValidCell(cell)) {
					if(isNum(value)) {
						cells.put(cell, value);
					}else {
						if(isValidExpression(value)) {
							
							String exp = value.substring(1);
							
							String[] expArray = exp.split("[+*/-]");
							
							boolean add = false;
							
							if(exp.contains("+")) add = true;

							Integer val1 = null;
							Integer val2 = null;
							
							if(isValidCell(expArray[0])) {
								if(cells.containsKey(expArray[0])) {
									val1 = Integer.parseInt(cells.get(expArray[0]));
								}else {
									System.out.println("Invalid Input");
									return;
								}
							}
							if(isValidCell(expArray[1])) {
								if(cells.containsKey(expArray[1])) {
									val2 = Integer.parseInt(cells.get(expArray[1]));
								}else {
									System.out.println("Invalid Entite Input");
									return;
								}
							}
							
				
							if(isNum(expArray[0]) && isNum(expArray[1])) {
								Integer result = null; 
								Integer n1 =  Integer.parseInt(expArray[0]);
								Integer n2 =  Integer.parseInt(expArray[1]);
								if(add) {
									result = add(n1, n2);
								}
								cells.put(cell, "" +result);
							}else if(val1!=null && val2 !=null){
					            Integer result = null;
					            if(add) {
									result = add(val1, val2);
								}
								cells.put(cell, "" +result);
							}else if(val1!=null && val2 == null) {
								Integer result = null; 
								Integer n1 =  val1;
								Integer n2 =  Integer.parseInt(expArray[1]);
								if(add) {
									result = add(n1, n2);
								}
								cells.put(cell, "" +result);
							}else if(val1==null && val2 != null) {
								Integer result = null; 
								Integer n1 =  Integer.parseInt(expArray[0]);
								Integer n2 = val2;
								if(add) {
									result = add(n1, n2);
								}
								cells.put(cell, "" +result);
							
							}else {
								System.out.println("invalid Entite input");
								return;
							}
						}else {
							System.out.println("Invalid Entite Input");
							return;
						}
					}
				}else {
					System.out.println("Invalid Entite cell=" + cell);
					return;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid Entite Input");
			return;
		}
		System.out.println(cells);
	}
	
	
	

	public static boolean isValidCell(String str) {
		return Pattern.matches("^[A-Z]{1,2}[1-9]{1}[0-9]{0,4}$", str);
	}
	
	
	public static boolean isNum(String str) {
		return Pattern.matches("^[0-9]+$", str);
	}
	
	
	public static boolean isValidExpression(String str) {
		return Pattern.matches("^=[A-Z0-9]+[+*/-][A-Z0-9]+$", str);
	}
	
	public static int add(int n1,int n2) {
		return n1 + n2;
	}


}
