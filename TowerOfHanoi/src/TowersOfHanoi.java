public class TowersOfHanoi {

	public static void main(String[] args) {
//		solveTowers(3, 1, 2, 3);
		solveTowers1(3, 1, 2, 3);
	}

	public static void solveTowers(int disk, int sourcePeg, int tempPeg, int destinationPeg) {
		if (disk == 1) {
			System.out.printf("%n%d --> %d", sourcePeg, destinationPeg);
			return;
		}

		solveTowers(disk - 1, sourcePeg, destinationPeg, tempPeg);

		System.out.printf("%n%d --> %d", sourcePeg, destinationPeg);

		solveTowers(disk - 1, tempPeg, sourcePeg, destinationPeg);
	}

	public static void solveTowers1(int disk, int source, int temp, int destination) {
		if (disk > 0) {
			solveTowers1(disk - 1, source, destination, temp);
			System.out.printf("Movendo do Pino %d para Pino %d%n", source, destination);
			solveTowers1(disk - 1, temp, source, destination);
		}
	}

}
