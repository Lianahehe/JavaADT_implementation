package ADT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Driver {
        
	
	public static void main (String[] args) throws IllegalArgumentException  {
		File file = new File("src/ADT/int20k.txt");
		//System.out.println(file);
		
		// creates new BST tree
		BST<Integer> tree = new BST<Integer>();
		Scanner input = null;
		try {
			input = new Scanner(file);
			while(input.hasNext()) {
				tree.add(input.nextInt());
				//System.out.println("this works");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			

		
		// creates new DLL
		DoublyLinkedList<Integer> DLL = new DoublyLinkedList<Integer>();
		Scanner input2 = null;
		try {
			input2 = new Scanner(file);
			while(input2.hasNext()) {
				//System.out.println(input2.next());
				DLL.add(input2.nextInt());
			}
			input2.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//DLL.printNodes();
		
		//System.out.println(DLL.setSize());

		//creates a list of 100 random numbers between 0 and 49,999
		int array[] = new int[100];
		Random rand = new Random();
		for(int i = 0; i < 100; i++) {
			int randInt = rand.nextInt(49999);
			
			array[i] = randInt;
			
		}
		

//		
		// DLL implementation for the random numbers
		long allTimes = 0;
		for (int x : array) {
			long startTime = System.nanoTime();
			DLL.isElement(x);
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			allTimes = allTimes + timeElapsed;
		}
		long avTime = allTimes / 100;
		System.out.println("Average time of DLL : " + avTime);
		
		//System.out.println("Execution time in milliseconds: " + timeElapsed);
		
		
		// BST 
		long allTimes2 = 0;
		for (int x: array) {
			long startTime2 = System.nanoTime();
			tree.isElement(x);
			long endTime2 = System.nanoTime();
			long timeElapsed2 = endTime2 - startTime2;
			allTimes2 = allTimes2 + timeElapsed2;
		}
		long avTime2 = allTimes2 / 100;
		System.out.println("Average time of BST : " + avTime2);
	

		
		
		
		
	}
	


}
