package edu.spbu.matrix;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

// Класс плотной матрицы
public class DenseMatrix implements Matrix {
  private int[][] matrix;
  private int rows;
  private int cols;

  public DenseMatrix(int rows, int cols) {
    this.matrix = new int[rows][cols];
    this.rows = rows;
    this.cols = cols;

    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        this.matrix[i][j] = 0;
      }
    }
  }

  // Получение матрицы из файла
  public DenseMatrix(String fileName) {
    this.rows = 0;
    this.cols = 0;
    ArrayList<int[]> rows = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      int[] matrixRow = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
      this.cols = matrixRow.length;
      this.rows = 1;
      rows.add(matrixRow);

      while ((line = reader.readLine()) != null) {
        matrixRow = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        rows.add(matrixRow);
        ++this.rows;
      }

      this.matrix = new int[this.rows][this.cols];
      for (int i = 0; i < this.rows; ++i)
      {
        this.matrix[i] = rows.get(i);
      }
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
      System.out.println("File not found.");
      System.exit(1);
    } catch (IOException ex) {
      ex.printStackTrace();
      System.out.println("IOException");
      System.exit(2);
    }
  }



  public int hash() {
    // хеш матрицы
    int hash = 0;

    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.cols; ++j)
      { if(i == j){
          hash += this.matrix[i][j];}
      }
    }
    return hash;
  }
  // Преобразование матрицы в string
  public String toString() {
    String result = "";

    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.cols; ++j) {
        result.concat(Integer.toString(this.matrix[i][j]));
        result.concat(" ");
      }
      result.concat("\n");
    }

    return result;
  }

  // Однопоточное умножение матриц
  @Override
  public Matrix mul(Matrix A) {
    if (A instanceof DenseMatrix)
    {
      DenseMatrix Result_matrix = new DenseMatrix(this.rows, ((DenseMatrix) A).cols);
      for (int i = 0; i < this.rows; ++i)
      {
        for (int j = 0; j < ((DenseMatrix) A).cols; ++j)
        {
          for (int k = 0; k < this.cols; ++k)
          {
            Result_matrix.matrix[i][j] += this.matrix[i][k] * ((DenseMatrix) A).matrix[k][j];
          }
        }
      }
      return Result_matrix;
    } else {
      return null;
    }
  }

  // Многопоточное умножение матриц
  @Override
  public Matrix mulMT(Matrix o) {
    return null;
  }

  // Проверка на равенство матриц
  @Override
  public boolean equals(Object other_matrix) {
    if (other_matrix instanceof DenseMatrix) {
      DenseMatrix X = (DenseMatrix) other_matrix;

      if (this.hash() != X.hash() || this.rows != X.rows || this.cols != X.cols) {
        return false;
      }

      for (int i = 0; i < this.rows; ++i) {
        for (int j = 0; j < this.cols; ++j) {
          if (this.matrix[i][j] != X.matrix[i][j]) {
            return false;
          }
        }
      }

      return true;
    } else {
      return false;
    }
  }
}