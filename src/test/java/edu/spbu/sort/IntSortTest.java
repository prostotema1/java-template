package edu.spbu.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by artemaliev on 07/09/15.
 */
public class IntSortTest {
  public static final int SEED = 1;
  public static final int ARRAY_SIZE = 100000000;

  /**
   * @param size array size
   * @param seed for the pseudo random generator
   * @return random generated int array. It will be the same for the same seed and size.
   */
  int[] generateRandomIntArray(int size, long seed) {
    int array[] = new int[size];
    Random rnd = new Random(seed);
    for (int i = 0; i < array.length; i++) {
      array[i] = rnd.nextInt();
    }
    return array;
  }

  @Test
  public void testSortArray() throws Exception {
    int array[] = generateRandomIntArray(ARRAY_SIZE, SEED);

    IntSort.qSort(array, 0, array.length - 1);

    // проверяем правильность сортировки
    int previousValue = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      assertTrue("Element " + array[i] + " at " + i + " position is not in the order", array[i] >= previousValue);
      previousValue = array[i];
    }
  }
}
