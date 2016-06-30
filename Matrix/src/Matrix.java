import java.awt.Dimension;

public class Matrix {
	private double[][] data;

	Matrix(double[][] m) {
		data = m;
	}

	Matrix(int[][] m) {
		data = new double[m.length][m[0].length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				data[i][j] = m[i][j];
	}

	public Dimension size() {
		return new Dimension(data.length, data[0].length);
	}

	public boolean isSquare() {
		return data.length == data[0].length;
	}

	public Matrix getInverse() {
		return null;
	}

	public void transpose() {
		double[][] temp = new double[data[0].length][data.length];

		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[i].length; j++)
				temp[j][i] = data[i][j];
		data = temp;
	}

}
