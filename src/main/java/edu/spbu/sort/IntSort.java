package edu.spbu.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IntSort {
  public static void qSort(int[] arr, int low, int high) {
    int LowerBound = low, HigherBound = high;
    int pivot = arr[(LowerBound + HigherBound) / 2];
    int temp;

    while(LowerBound <= HigherBound) {
      while (arr[LowerBound] < pivot) { ++LowerBound; }
      while (arr[HigherBound] > pivot) { --HigherBound; }

      if (LowerBound <= HigherBound) {
        temp = arr[LowerBound];
        arr[LowerBound] = arr[HigherBound];
        arr[HigherBound] = temp;
        ++LowerBound;
        --HigherBound;
      }
    }

    if (HigherBound > low) { qSort(arr, low, HigherBound); }
    if (LowerBound < high) { qSort(arr, LowerBound, high); }
  }
}

