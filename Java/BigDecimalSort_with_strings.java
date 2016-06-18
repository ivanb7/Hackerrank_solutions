package Java;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class BigDecimalSort_with_strings{

    public static void main(String []args) throws FileNotFoundException
    {
        //Input
        Scanner sc = new Scanner(new FileReader("src/Java/input.txt"));
        int n=sc.nextInt();
        String []s=new String[n];
        BigDecimal[] a = new BigDecimal[n];
        System.out.println("Input:");
        for(int i=0;i<n;i++)
        {
            s[i]=sc.next();
            //a[i] = new BigDecimal(s[i]);
            System.out.print(s[i] + " ");
        }
        System.out.println("\nOutput:");
        //In-place quicksort
        //https://en.wikipedia.org/wiki/Quicksort
        quicksortBig(s,0,s.length);
        //Output
        for(int i=0;i<n;i++)
        {
            System.out.print(s[i] + " ");
        }

    }
    
    public static void quicksortBig(String[] arr,int lo, int hi){
        if(lo < hi){
            //Choose pivot
            int p = partition(arr, lo, hi);
            //partitioning
            quicksortBig(arr, lo, p);
            quicksortBig(arr,p+1,hi);
        }
    }
    public static int partition(String[] arr, int lo, int hi){
        String pivot = arr[hi-1];
        int i = lo; //index for swap
        for(int j=lo;j<(hi-1);j++){
        	//COMPARING
            //arr[j] >= pivot
            if(compareBigDecimals(arr[j],pivot) >= 0){
                swap(arr,i,j);
                i = i+1;
            } 
        }
        swap(arr,i,hi-1);
        return i;
    } 
    //compareBigDecimals(arr[j],pivot) <op> 0
    //where op is any of the six operators (<, ==, >, >=, !=, <=)
    public static int compareBigDecimals(String str1,String str2){
    	BigDecimal x = new BigDecimal(str1);
    	BigDecimal y = new BigDecimal(str2);
    	
    	if(x.compareTo(y) < 0)
    		return -1;
    	if(x.compareTo(y) == 0)
    		return 0;
    	else
    		return 1;
    }
    
    public static void swap(String[] arr,int idx1,int idx2){
        String temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
        //printArr(arr);
    }
    public static void printArr(String[] a){
    	for(int i=0;i<a.length;i++)
        {
            System.out.print(a[i] + " ");
        }
    	System.out.print("\n");
    }

}
