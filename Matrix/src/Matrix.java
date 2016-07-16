import java.awt.Dimension;

public class Matrix {
	private double[][] data;

	// Constructors
	Matrix(double[][] m) {
		data = m;
	}

	Matrix(int[][] m) {
		data = new double[m.length][m[0].length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				data[i][j] = m[i][j];
	}

	// Simple operations
	public Dimension size() {
		return new Dimension(data.length, data[0].length);
	}

	public boolean isSquare() {
		return data.length == data[0].length;
	}
	
	public double[] getColumn(int col) {
		double[] column = new double[data.length];
		for (int i = 0; i < data.length; i++)
			column[i] = data[i][col];
		return column;
	}
	
	public double [] getRow(int row){
		return data[row];
	}
	
	public Matrix getInverse() {
		double [][] temp=null;
		try {
			temp= Inverse.inverse(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Matrix(temp);
	}

	public Matrix multiply(Matrix m) throws IncorrectDimensionException {
		if (data[0].length != m.data.length)
			throw new IncorrectDimensionException();
		double[][] result = new double[data.length][m.data[0].length];
		for (int q = 0; q < data.length; q++) {
			double[] row = data[q];
			for (int i = 0; i < result[0].length; i++) {
				double[] column = m.getColumn(i);
				for (int j = 0; j < column.length; j++) {
					result[q][i] += row[j] * column[j];
				}
			}
		}
		return new Matrix(result);
	}
	
	public void divideByConstant(double n) {
		data = Inverse.divideByConstant(data,n);
	}
	
	public Matrix divide(Matrix m) throws IncorrectDimensionException{
		m = m.getInverse();
		return this.multiply(m);
	}

	public void transpose() {
		data=Inverse.transpose(data);
	}

	public void displayMatrix() {
		Inverse.displayMatrix(data);
	}
	
	public double determinant() throws NonSquareMatrixException {
		return Inverse.determinate(data);
	}
}

class IncorrectDimensionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4789763037054408519L;

}
