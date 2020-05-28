package dinossauro;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		Dinossauro skeep = new Dinossauro();
		Scanner scan = new Scanner(System.in);
		char letra = ' ';

		while (letra != 'E') {
			System.out.println("<--- Escolha a letra correspondente a opção ---> \n(P)ular \n(C)orrer \nCo(M)er"
					+ " \n(A)tirar \nTomar (S)ol \nFicar na So(M)bra \n(E)xit");
			String textoMaiusculo = scan.nextLine().toUpperCase();
			letra = textoMaiusculo.charAt(0);

			switch (letra) {
			case 'P':
				skeep.pular();
				break;
			case 'C':
				skeep.correr();
				break;
			case 'M':
				skeep.comer();
				break;
			case 'A':
				skeep.atirar();
				break;
			case 'S':
				skeep.tomarSol();
				break;
			case 'O':
				skeep.ficarNaSombra();
				break;
			case 'E':
				System.out.println("Encerrado");
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}

		scan.close();
	}

}
