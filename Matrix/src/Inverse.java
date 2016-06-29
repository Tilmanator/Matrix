public class Inverse {

	public static int determinate(int[][] initial)
			throws NonSquareMatrixException {
		if (initial.length != initial[0].length)
			throw new NonSquareMatrixException();

		if (initial.length == 2) {
			return initial[0][0] * initial[1][1] - initial[1][0]
					* initial[0][1];
		}

		int determinant = 0;
		for (int i = 0; i < initial.length; i++) {
			int curr = initial[0][i];
			int[][] temp = new int[initial.length - 1][initial.length - 1];
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

			 for (int p = 0; p < temp.length; p++) {
			 for (int h = 0; h < temp.length; h++) {
			 System.out.print(temp[p][h]+" ");
			 }
			 System.out.println();
			 }
			 System.out.println();

			determinant += curr * determinate(temp);
		}

		return determinant;
	}

	/*
	 * Has to be a square matrix
	 */
	public static int[][] inverse(int[][] initial) {
		if (initial.length != initial[0].length)
			return null;
		return null;
	}

	public static void main(String[] args) throws Exception {
		int[][] nums = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		int[][] test = { { 1, 2, 3 }, { 0, -4, 1 }, { 0, 3, -1 } };
		System.out.println(determinate(nums));
	}
}

class NonSquareMatrixException extends Exception {

}
