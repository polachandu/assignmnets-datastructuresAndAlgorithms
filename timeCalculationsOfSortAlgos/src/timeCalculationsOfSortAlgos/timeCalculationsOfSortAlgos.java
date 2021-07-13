package timeCalculationsOfSortAlgos;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class timeCalculationsOfSortAlgos {
static FileWriter fw=null;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		Scanner scan =new Scanner(System.in);
		System.out.println("Select the order of integer in an array\n 1:random\n 2:ascending order\n 3:descending order ");
		
		
		int order=scan.nextInt();
		System.out.println("Give the size of an array: ");
		int size=scan.nextInt();
		int[] intArray=new int[size];

		if(order == 1) {
		for(int i=0;i<size;i++) {
			intArray[i]=ThreadLocalRandom.current().nextInt(0,size);
			
		}
		
		}
		if(order ==2) {
			for(int i=0;i<size;i++) { 
				intArray[i]=ThreadLocalRandom.current().nextInt(0,size);
				}
			Arrays.sort(intArray);
			

			}
		if(order==3) {
			for(int i=size-1;i>0;i--) {
				intArray[i]=ThreadLocalRandom.current().nextInt(0,size);
			}
			Integer[] intArray1 = new Integer[size];
			for(int i=0;i<size;i++) {
				intArray1[i] = intArray[i];
			}
			Arrays.sort(intArray1, Collections.reverseOrder());

		}
		System.out.println("Select the sorting Algorithm that give text file of that sorted algorithm \n 1:Bubble sort \n 2:Insertion sort \n 3: Merge sort \n 4:Quick sort");
		
		
		
		int type=scan.nextInt();
		switch (type) {
		
		case 1:
		long start1 = System.currentTimeMillis();
		bubbleSort(intArray);
		long end1 = System.currentTimeMillis();
		System.out.println("Sorting time for quick sort is: " + (end1 - start1)/1000 + " seconds");
		
		type("Bubble");
		fw.write("Bubble sorted array is: ");
		fw.write("\n");
		for(int i=0;i<intArray.length;i++) {
			String str=" ";
			str+=intArray[i];
			fw.write(str);
			fw.write("\n");
		}
		fw.write("\n");
			break;
			
			
			
			
		case 2:
			long start2 = System.currentTimeMillis();
			insertionSort(intArray);
			long end2 = System.currentTimeMillis();
			System.out.println("Sorting time for quick sort is: " + (end2 - start2)/1000 + " seconds");
			
			type("Insertion");

			fw.write("Insertion sorted array is: ");
			fw.write("\n");
			for(int i=0;i<intArray.length;i++) {
				String str=" ";
				str+=intArray[i];
				fw.write(str);
				fw.write("\n");
			}
			fw.write("\n");
			break;
		
		case 3:
			
			
		long start3 = System.currentTimeMillis();
		mergeSort(intArray,0,intArray.length);
		long end3 = System.currentTimeMillis();
		System.out.println("Sorting time for quick sort is: " + (end3 - start3)/1000 + " seconds");
		
		type("Merge");

		fw.write("Merge sorted array is: ");
		fw.write("\n");
		for(int i=0;i<intArray.length;i++) {
			String str=" ";
			str+=intArray[i];
			fw.write(str);
			fw.write("\n");
		}
		fw.write("\n");
		break;
		
		
		case 4:
		long start4 = System.currentTimeMillis();
		quickSort(intArray,0,intArray.length);
		long end4 = System.currentTimeMillis();
		System.out.println("Sorting time for quick sort is: " + (end4 - start4)/1000 + " seconds");
		
		type("Quick");

		fw.write("Quick sorted array is: ");
		fw.write("\n");
		for(int i=0;i<intArray.length;i++) {
			String str=" ";
			str+=intArray[i];
			fw.write(str);
			fw.write("\n");
		}
		fw.write("\n");
		break;
		
		
		}
		fw.close();
	}
	private static void type(String string) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("/Users/apple/Desktop/practice/assignmnets-datastructuresAndAlgorithms/timeCalculationsOfSortAlgos/src/timeCalculationsOfSortAlgos/"+string+".txt");
		 fw =new FileWriter(file);
	}
	//------------merge sort----------//
	public static void mergeSort(int[] input,int start,int end) {
		
		if(end-start<2) {
			return ;
		}
		int mid = (start+end)/2;
		mergeSort(input,start,mid);
		mergeSort(input,mid,end);
		merge(input, start, mid, end);
		
		
		
		
	}
	
	public static void merge(int[] input,int start,int mid,int end) {
		// if last element in the left partition array is <= first element in the right partition array
		if(input[mid-1]<=input[mid]) {
			return ;
		}
		
		int i=start;
		int j=mid;
		int tempIndex = 0;  //this is going to keep the track where we are in temporary array when we're doing copy
		
		int[] temp =new int[end-start];
		
		// i<mid means traversing over left partition array is completed and j<end means traversing over right partition array is completed
		while(i<mid && j<end) {
			// if input[i]<= input[j] then the smaller element will be added to temporary array(called as temp)
			//if input[i] = input[j] then duplicate elements will be added
			//and tempIndex will be incremented and after adding an element to temp array, array position gets increment(from where element is copied to temporary array) 
			temp[tempIndex++] = input[i]<=input[j] ? input[i++] : input[j++];
		}
		//tempIndex tells us how many elements we handled
		//mid-i tells us the number of elements copy over into the temporary array
		System.arraycopy(input, i, input, start+tempIndex, mid-i);
		System.arraycopy(temp,0, input, start, tempIndex);
	}
	
	//-----------quick sort-------------------//
	
	
	public static void quickSort(int[] input, int start, int end) {
		
		// if end-start<2 means that it's dealing with one array element
		if((end-start)<2) {
			return;
		}
		//pivotInex is the position of the pivot in the sorted array
		int pivotIndex = partition(input,start,end);
		quickSort(input,start,pivotIndex);
		quickSort(input, pivotIndex+1, end);
		
		
	}
	
	public static int partition(int[] input,int start, int end) {
		int pivot = input[start];
		int i = start;
		int j = end;
		while(i<j) {
			
			// traversing from right to left looking for smaller elements
			while(i<j && input[--j]>=pivot);
			if(i<j) {
				input[i]=input[j];
			}
			// traversing from left to right looking for larger elements
			while(i<j && input[++i]<=pivot);
			if(i<j) {
				input[j]=input[i];
			}
		}
		input[j] = pivot;
		return j;
	}
	
	//--------------------insertion sort------------------//
	
	public static void insertionSort(int[] intArray) {
		for(int firstUnsortedIndex=1;firstUnsortedIndex<intArray.length;firstUnsortedIndex++) { //we assume that first index is sorted
			var newElement = intArray[firstUnsortedIndex];
			int j;
			for(j=firstUnsortedIndex;j>0&&intArray[j-1]>newElement;j--) { //Traversing from right to left
				intArray[j]=intArray[j-1];
			}
			intArray[j]=newElement;
		}
		
;
		
		}
	
	//------------------bubble sort---------------------//
	
	
	public static void bubbleSort(int[] intArray) {
		for(int lastUnsortedIndex = intArray.length-1;lastUnsortedIndex>0;lastUnsortedIndex--) {
			for(int b=0;b<lastUnsortedIndex;b++) {
				if(intArray[b]>intArray[b+1]) {
					swap(intArray,b,b+1);
				}
			}
		}
		
		
	}
	public static void swap(int[] array,int i,int j) {
			
			int temp = array[i];
			array[i] = array[j];
			array[j]  = temp;
		}
	

}





