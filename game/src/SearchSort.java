public class SearchSort
{
  private static int count = 0;

  public static int recursiveBinarySearch(Item[] arr, int low, int high, int target)
  {
    int mid;
    if(low > high)
    {
      System.out.println("recursive binary search count: " + count);
      return -1;
    }
    else
    {
      mid = (low + high)/2;
      //if the value in the middle is less than the target...
      if(arr[mid].getCost() < target)
      {
        count++;
        //recursively call the right half of the array
        return recursiveBinarySearch(arr, mid + 1, high, target);
      }
      //if it's greater...
      if(arr[mid].getCost() > target)
      {
        count++;
        //recursively call the left half of the array
        return recursiveBinarySearch(arr, low, mid - 1, target);
      }
      count++;
      System.out.println("recursive binary search count: " + count);
      return mid;
    }
  }
  
  public static void mergeSort(Item[] arr, int low, int high)
  {
    //if the left index is less than right index
    if(low < high)
    {
      //split it roughly in half
      int mid = (low + high)/2;
      
      //recursively splitting the left in half
      mergeSort(arr, low, mid);
      
      //then recursively split the right in half
      mergeSort(arr, mid + 1, high);
      
      //then merge the subarrays in order
      merge(arr, low, mid, high);
    }
  }
  public static void merge(Item[] arr, int low, int mid, int high)
  {
    //copy the left "half" to a new array
    Item[] left = new Item[mid + 1 - low];
    
    //populate the copy
    for(int i = 0; i < left.length; i++)
      left[i] = arr[i + low];
    
    //keep track of the leftmost index
    int leftIndex = 0;
    
    //as long as low < middle < high
    while(low < mid + 1 && mid + 1 <= high)
    {
      
      //if the right index is less than the left index
      if(arr[mid+1].getCost() < left[leftIndex].getCost())
      {
        //store the right index and increment
        arr[low] = arr[mid+1];
        low++;
        mid++;
      }
      //otherwise...
      else
      {
        //take the left index and increment
        arr[low] = left[leftIndex];
        low++;
        leftIndex++;
      }
    }
    //there may be leftovers in the left "half"
    while(low < mid + 1)
    {
      //so take them from the left "half"  and increment
      arr[low] = left[leftIndex];
      low++;
      leftIndex++;
    }
  }
}