package edu.spbu.matrix;

/**
 *
 */
public interface Matrix {

  /* one-threading matrix multiplication */
  Matrix mul(Matrix o);

  /* multi-threading matrix multiplication */
  Matrix mulMT(Matrix o);
}
