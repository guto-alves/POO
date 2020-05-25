
public class TowersOfHanoi {

	public static void main(String[] args) {
		solveTowers1(4, 1, 2, 3);
//		solveTowers(4, 1, 2, 3);
	}

	public static void solveTowers(int disk, int origem, int temp, int destination) {
		if (disk > 0) {
			solveTowers(disk - 1, origem, destination, temp);
			System.out.printf("Movendo do Pino %d para Pino %d%n", origem, destination);
			solveTowers(disk - 1, temp, origem, destination);
		}
	}

	public static void solveTowers1(int disk, int sourcePeg, int tempPeg, int destinationPeg) {
		if (disk == 1) {
			System.out.printf("%n%d --> %d", sourcePeg, destinationPeg);
			return;
		}
		
		solveTowers1(disk - 1, sourcePeg, destinationPeg, tempPeg);
		
		System.out.printf("%n%d --> %d", sourcePeg, destinationPeg);
		
		solveTowers1(disk - 1, tempPeg, sourcePeg, destinationPeg);
	}
}
