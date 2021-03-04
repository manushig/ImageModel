package images;

public interface FilterInterface {
  public int[][][] doFilter(int[][][] rgbBuffer, float[][] kernel);
}
