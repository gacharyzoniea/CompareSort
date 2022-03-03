package compareSort;

import java.util.Random;

public class Main {
	
	public static int[] copy(int a[]) {
		int[] copy = new int[a.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = a[i];
		}
		return copy;
	}
	
	public static void merge(int Arr[], int start, int mid, int end) {

		int temp[] = new int[end - start + 1];
//		i, j, and k are used to navigate the array
		int i = start, j = mid+1, k = 0;


		while(i <= mid && j <= end) {
			if(Arr[i] <= Arr[j]) {
				temp[k] = Arr[i];
				k ++; 
				i ++;
			}
			else {
				temp[k] = Arr[j];
				k ++; 
				j ++;
			}
		}

		while(i <= mid) {
			temp[k] = Arr[i];
			k ++; 
			i ++;
		}

		while(j <= end) {
			temp[k] = Arr[j];
			k ++; 
			j ++;
		}

		for(i = start; i <= end; i += 1) {
			Arr[i] = temp[i - start];
		}
	}

	public static int[] mergeSort(int[] a, int start, int end) {

		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(a, start, mid);
			mergeSort(a, mid+1, end);
			merge(a, start, mid, end);
		}
		
		return a;
	}

//	used to return array to proper max heap order
	public static void heapify(int[] arr, int n, int i) {
		int max = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		
		if (l < n && arr[l] > arr[max]) {
			max = l;
		}
		if (r < n && arr[r] > arr[max]) {
			max = r;
		}
		if (max != i) {
			int temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			
			heapify(arr, n, max);
		}
	}

	public static int[] heapSort(int[] a) {
//		create copy array
		int[] copy = copy(a);
//		sort
		int n = copy.length;
//		make max heap
		for (int i = n / 2 - 1; i >= 0; i--) {
	        heapify(copy, n, i);
	      }
		for (int i = n - 1; i >= 0; i--) {
			 int temp = copy[0];
		        copy[0] = copy[i];
		        copy[i] = temp;
		        
//				heapify the root
				heapify(copy, i, 0);
		}	
		return copy;
	}

	public static int[] insertionSort(int[] a) {
//		create copy array
		int[] copy = copy(a);
//		sort
		for (int i = 1; i < copy.length; i++) {
			int key = copy[i];
			int j = i - 1;
			while (j >= 0 && copy[j] > key) {
				copy[j + 1] = copy[j];
				j -= 1;
			}
			copy[j + 1] = key;
		}
		return copy;
	}

	public static int[] selectionSort(int[] a) {
//		create copy array
		int[] sA = copy(a);
		
//		selection sort
		int temp;
		int min;
		for (int i = 0; i < sA.length - 1; i++) {
			min = i;

			for (int j = i + 1; j < sA.length; j++) {
				if (sA[j] < sA[min]) {
					min = j;
				}
			}
			if (min != i) {
				temp = sA[min];
				sA[min] = sA[i];
				sA[i] = temp;
			}
		}
		return sA;
	}

	public static int[] bubbleSort(int[] a) {
//		create copy array
		int[] copy = copy(a);
//		bubble sort
		int temp;
		for (int i = 0; i < copy.length; i++) {
			for (int j = 1; j < copy.length - i; j++) {
				if (copy[j - 1] > copy[j]) {
					temp = copy[j - 1];
					copy[j - 1] = copy[j];
					copy[j] = temp;
				}
			}
		}
		return copy;
	}
	
//********************MAIN**********************************************************
	
	public static int max = Integer.MAX_VALUE;
	public static int min = 1;
	
	public static void main(String[] args) {
		// create array of random ints to be sorted
		int[] arr = new int[80000];
		Random rand = new Random();

		for (int i = 0; i < arr.length; i++) {
			int randint = rand.nextInt((max - min) + 1) + min;
			arr[i] = randint;
		}
		int copy [] = copy(arr);
		
		System.out.println("Running...");
		// testing
		long average = 0;
		//sort
		for(int i = 0; i < 10; i++) {
			long start = System.nanoTime();
//			bubbleSort(arr);   
			/*At 10,000 the runtime is around 100ms going up by a factor of 10 is far too much, even 
			*30,000 takes 10 seconds*/
//			selectionSort(arr);
			/*you can see selection sort's better efficiency here as it does about double the cell count
			 * in the same amount of time*/
//			insertionSort(arr);
			/*very similar speed to selection sort, in real-world situations, it would be marginally better*/
//			mergeSort(arr, 0, arr.length-1);
			/*Here the MASSIVE increase in efficiency can easily be seen getting 10-100x greater results
			 * at the same runtimesz. It does, however begin using too much space at 1000000000*/
			heapSort(arr);
			/*heapsort has similar timings to mergesort, but is able to handle bigger arrays*/
			long finish = System.nanoTime();
			long timeInMSecs = (finish-start)/1000000;
			average += timeInMSecs;
		}
		average = average / 10;
		System.out.println(average);
	}

}
