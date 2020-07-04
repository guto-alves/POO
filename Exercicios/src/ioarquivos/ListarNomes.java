package ioarquivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class ListarNomes {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		Formatter output = new Formatter(
				new File("C:\\Users\\Gustavo\\Downloads\\texto.txt"));
	
		String nome = scanner.nextLine();
		
		while (!nome.equals("sair")) {
			output.format("%s%n", nome);
			nome = scanner.nextLine();
		}

		scanner.close();
		output.close();
	}

}
