package Java;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class BigDecimalSort{

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
            a[i] = new BigDecimal(s[i]);
            System.out.print(a[i].toPlainString() + " ");
        }
        System.out.println("\nOutput:");
        //In-place quicksort
        //https://en.wikipedia.org/wiki/Quicksort
        quicksortBig(a,0,a.length);
        //Output
        for(int i=0;i<n;i++)
        {
            System.out.print(a[i].toPlainString() + " ");
        }

    }
    
    public static void quicksortBig(BigDecimal[] arr,int lo, int hi){
        if(lo < hi){
            //Choose pivot
            int p = partition(arr, lo, hi);
            //partitioning
            quicksortBig(arr, lo, p);
            quicksortBig(arr,p+1,hi);
        }
    }
    public static int partition(BigDecimal[] arr, int lo, int hi){
        BigDecimal pivot = arr[hi-1];
        int i = lo; //index for swap
        for(int j=lo;j<(hi-1);j++){
        	//COMPARING
            //arr[j] >= pivot
            if(arr[j].compareTo(pivot) >= 0){
                swap(arr,i,j);
                i = i+1;
            } 
        }
        swap(arr,i,hi-1);
        return i;
    } 
    public static void swap(BigDecimal[] arr,int idx1,int idx2){
        BigDecimal temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
        //printArr(arr);
    }
    public static void printArr(BigDecimal[] a){
    	for(int i=0;i<a.length;i++)
        {
            System.out.print(a[i].toPlainString() + " ");
        }
    	System.out.print("\n");
    }

}
