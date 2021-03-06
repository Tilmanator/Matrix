public class Inverse {

	Inverse(){
		
	}
	
	/**
	 * 
	 * @param initial the original matrix
	 * @return the determinate of the matrix
	 * @throws NonSquareMatrixException
	 */
	public static double determinate(double[][] initial)
			throws NonSquareMatrixException {
		if (initial.length != initial[0].length)
			throw new NonSquareMatrixException();

		// base cases
		//shortcut of 2 by 2 matrix
		if (initial.length == 2) {
			return initial[0][0] * initial[1][1] - initial[1][0]
					* initial[0][1];
		}
		if (initial.length==1)
			return initial[0][0];

		int determinant = 0;
		for (int i = 0; i < initial.length; i++) {
			double curr = initial[0][i];
			double[][] temp = new double[initial.length - 1][initial.length - 1];
			boolean crossover = false;
			for (int j = 1; j < initial.length; j++) {
				for (int h = 0; h < initial.length; h++) {
					if (h == i)
						crossover = true;
					else {
						temp[j - 1][h - (crossover ? 1 : 0)] = initial[j][h];
					}

				}
				crossover = false;

			}
			// Break it down into smaller pieces if necesary
			determinant += curr * determinate(temp) * (i % 2 == 1 ? -1 : 1);
		}
		return determinant;
	}

	/**
	 * Similar to determinant
	 * @param m the original matrix
	 * @return the cofactor matrix
	 * @throws Exception
	 */
	public static double[][] cofactorMatrix(double[][] m) throws Exception {
		double[][] co = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				double[][] temp = new double[m.length - 1][m.length - 1];
				boolean rowSkip = false;
				boolean colSkip = false;
				for (int row = 0; row < m.length; row++) {
					for (int col = 0; col < m.length; col++) {
						if (row == i)
							rowSkip = true;
						if (col == j)
							colSkip = true;
						else if (row != i && col != j) {
							temp[row - (rowSkip ? 1 : 0)][col
									- (colSkip ? 1 : 0)] = m[row][col];
						}
					}
					colSkip = false;
				}
				rowSkip = false;
				co[i][j] = determinate(temp);
			}
		}

		// Ensure addition subtraction rule is abided by
		for (int p = 0; p < co.length; p++) {
			for (int h = 0; h < co.length; h++) {
				// Changed, took out p * co.length
				if ((p+ h) % 2 == 1)
					co[p][h] *= -1;
			}
		}

		return co;
	}
/**
 * 
 * @param initial the initial matrix
 * @return the inverse of the given matrix
 * @throws Exception
 */
	public static double[][] inverse(double[][] initial) throws Exception {
		if (initial.length != initial[0].length)
			return null;
		double[][] mod = transpose(cofactorMatrix(initial));
		return divideByConstant(mod, determinate(initial));
	}

	public static double[][] divideByConstant(double[][] m, double n) {
		double[][] y = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++)
				y[i][j] = (m[i][j] * 1.0 / n);
		return y;
	}

	public static void displayMatrix(double[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static void displayMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 
	 * @param m the original matrix
	 * @return the transpose of the matrix
	 */
	public static double[][] transpose(double[][] m) {
		double[][] temp = new double[m[0].length][m.length];

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				temp[i][j] = m[j][i];
			}
		}
		return temp;

	}

	public static void main(String[] args) throws Exception {
//		int[][] nums = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
//				{ 13, 14, 15, 16 } };
//		int[][] test = { { 1, 2, 3 }, { 0, -4, 1 }, { 0, 3, -1 } };
//		int[][] check = { { 1, 2, 3 }, { 0, 4, 5 }, { 1, 0, 6 } };
//		int[][] next = { { 6, 1, 1 }, { 4, -2, 5 }, { 2, 8, 7 } };
//		int [][] twos = {{1,4},{5,7}};
		double [][] m = {{1,2,3},{0,1,4},{5,6,0}};
		// cofactorMatrix(check);
		// System.out.println(determinate(check));
		// //System.out.println(determinate(nums));
//		int[][] m = cofactorMatrix(twos);
//		displayMatrix(m);
//		System.out.println();
		double[][] matrix = inverse(m);
		displayMatrix(matrix);
		// System.out.println(determinate(next));
	}

}

class NonSquareMatrixException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7262729471669571424L;
}
