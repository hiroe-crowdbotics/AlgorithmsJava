// Java program to implement Jump Search.
import java.io.Scanner;
public class JumpSearch
{
    public static int jumpSearch(int[] arr, int x)
    {
        int n = arr.length;
 
        // Finding block size to be jumped
        int step = (int)Math.floor(Math.sqrt(n));
 
        // Finding the block where element is
        // present (if it is present)
        int prev = 0;
        while (arr[Math.min(step, n)-1] < x)
        {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }
 
        // Doing a linear search for x in block
        // beginning with prev.
        while (arr[prev] < x)
        {
            prev++;
 
            // If we reached next block or end of
            // array, element is not present.
            if (prev == Math.min(step, n))
                return -1;
        }
 
        // If element is found
        if (arr[prev] == x)
            return prev;
 
        return -1;
    }
 
    // Driver program to test function
    public static void main(String [ ] args)
    {
        //int arr[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        //int x = 55;
        int array[] = new int[10];
        int position = 0;
        Scanner scan = new Scanner(System.in);
        for(int i=0; i < 10; i++) {
            array[i] = scan.nextInt();
        }
        System.out.println("Enter the position at where you need to find the number");
        for(int j=0; j < array.length(); j++) {
            position = scan.nextInt();
        }
        System.out.println("Element at position:" + position + "is, " + jumpSearch(array, position));
        
        // Find the index of 'x' using Jump Search
        int index = jumpSearch(arr, x);
 
        // Print the index where 'x' is located
        System.out.println("\nNumber " + x + " is at index " + index);
    }
}

