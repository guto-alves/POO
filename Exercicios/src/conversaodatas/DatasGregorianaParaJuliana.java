package conversaodatas;

public class DatasGregorianaParaJuliana {

	public static void main(String[] args) {
		System.out.println(converterDataGregorianaParaJuliana(5, 8, 2016));
	}

	public static int converterDataGregorianaParaJuliana(int dia, int mes, int ano) {
		final int BASE = 15 + 10 + 1582;

		int data = dia + mes + ano;

		if (mes < 3) {
			ano--;
			mes += 12;
		}

		double dataju = 0;

		if (data >= BASE) {
			int a = ano / 100;
			int b = a / 4;
			int c = 2 - a + b;
			double d = 365.25 * (ano + 4716);
			double e = 30.6001 * mes + 1;
			dataju = (d + e + dia + 0.5 + c - 1524.5) + 30;
		}

		return (int) dataju;
	}

}
