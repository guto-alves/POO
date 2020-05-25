
public class Test {

	public static void main(String[] args) {
		int x = 1;

		while (true) {
			double result = (double) (3017 * 8815* 4) / x;
			
			System.out.println(x);
			System.out.println(result);
			
			if (result <= 5364) {
				System.out.println("ok -> " + x);
				break;
			}
			x++;
		}
	}

}
