/*
 *  Name: Roman Joska
 *  Class: CS4720
 *  Description: Make a count inv sort algorithm following the psuedocode in the book 
 */

//imports
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
public class CountInversion {

	public static void main(String[] args) throws IOException{
		//main for the sake of main
		int[] array = {54044,14108,79294,29649,25260,60660,2995,53777,49689,9083};
		ArrayInv result = SortCountInv(array, 10);
		System.out.printf("Inversions: %d \n", result.getTotalInv());
		
		System.out.printf("In SortCount Array - (");
		for(int k = 0; k < 10; k++) {
			System.out.printf("%d, ", result.getArray()[k]);
		}
		
		System.out.printf(")\n");
		
		//test for the large one
		/*
		Scanner scanner = new Scanner(new File("problem3.5.txt"));
		int [] tall = new int [100000];
		int i = 0;
		while(scanner.hasNextInt()){
		   tall[i++] = scanner.nextInt();
		}
		
		ArrayInv resultTall = SortCountInv(tall, 100000);
		
		System.out.printf("In SortCount Array2 - (");
		for(int k = 0; k < 100000; k++) {
			System.out.printf("%d, ", resultTall.getArray()[k]);
		}
		
		System.out.printf(")\n");
		*/
		/*
		File inputFileName = new File("problem3.5.txt");
		Scanner inputFile = new Scanner (inputFileName);
        int[] integers = new int [100000];
        int i=0;
        try {
            while(inputFile.hasNext())
            {
                integers[i] = inputFile.nextInt();
                i++;
            }
            inputFile.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        ArrayInv result2 = SortCountInv(integers, 100000);
        System.out.printf("Inversions: %d\n", result2.getTotalInv());
		
		System.out.printf("In SortCount Array - (");
		for(int k = 0; k < 100000; k++) {
			System.out.printf("%d ", result2.getArray()[k]);
		}
		System.out.printf(")\n");
		*/
		
	}// end of main

//make the count inv class
public static ArrayInv SortCountInv(int[] array, int size) {
	ArrayInv defau =  new ArrayInv(array, 0);
	if(size == 0 | size == 1) {
		return defau;
	}
	else {
		int leftSize = size/2;
		int rightSize = size - leftSize;
		int[] leftA = new int[leftSize];
		int[] rightA = new int[rightSize];	
		
		//fill the arrays
		for(int i = 0; i < leftSize; i++) {
			leftA[i] = array[i];
		}
		
		for(int i = 0; i < rightSize; i++) {
			rightA[i] = array[leftSize+i];
		}
		
		ArrayInv leftInv = SortCountInv(leftA, leftSize);
		ArrayInv rightInv = SortCountInv(rightA, rightSize);
		int[] leftB = leftInv.getArray();
		int[] rightB = 	rightInv.getArray();
		ArrayInv splitInv = MergeCountSplitInv(leftB, rightB, leftSize, rightSize);
		
		
		int totalInv = leftInv.getTotalInv() + rightInv.getTotalInv() + splitInv.getTotalInv();
		int[] returnArray = splitInv.getArray();
		
		/* Test prints
		System.out.printf("In SortCount Array - (");
		for(int k = 0; k < size; k++) {
			System.out.printf("%d ", returnArray[k]);
		}
		System.out.printf(")\n");
		*/
		
		ArrayInv result = new ArrayInv(returnArray, totalInv);
		
		return result;
	}
}

//count split inv function
public static ArrayInv MergeCountSplitInv(int[] arrayLeft, int[] arrayRight, int sizeL, int sizeR) {
	int i = 0;
	int j = 0;
	int inversions = 0;
	int size = sizeL + sizeR;
	int[] returnArray = new int[size];
	
	
	for(int k = 0; k < size; k++) {
		if(i < sizeL && j < sizeR) {
			if(arrayLeft[i] < arrayRight[j]) {
				returnArray[k] = arrayLeft[i];
				i++;
			}
			else {
				returnArray[k] = arrayRight[j];
				j++;
				inversions += (size/2) - i;
			}
		}
		else if(i == sizeL && j < sizeR) {
			returnArray[k] = arrayRight[j];
			j++;
			inversions += (size/2) - i;
		}
		else if(j == sizeR && i < sizeL) {
			returnArray[k] = arrayLeft[i];
			i++;
		}
	}
	/* test prints
	System.out.printf("Array - (");
	for(int k = 0; k < size; k++) {
		System.out.printf("%d ", returnArray[k]);
	}
	*/
	//System.out.printf(")   Inversions on array: %d \n", inversions);
	ArrayInv result = new ArrayInv(returnArray, inversions);
	return result;
}

}

class ArrayInv{
	private int[] array;
	private int totalInv;
	
	public ArrayInv (int[] array, int invs) {
		this.array = array;
		this.totalInv = invs;
	}
	
	//getters
	public int[] getArray() {
		return array;
	}
	public int getTotalInv() {
		return totalInv;
	}
	//setters
	public void setArray(int[] array) {
		this.array = array;
	}
	public void setInv(int number) {
		totalInv = number;
	}
	
}
