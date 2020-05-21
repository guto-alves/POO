package petshop;
import java.util.List;
import java.util.Set;

public class Teste {

	public static void main(String[] args) {
		Dono dono1 = new Dono("Gustavo", "490260884", "Rua X", "08040340", "gustavo@gmail.com",
				List.of(85509491, 52467006));
		Dono dono2 = new Dono("Maria", "490260884", "Rua Y", "08040340", "maria@gmail.com",
				List.of(85509491, 52467006));

		Doenca doenca1 = new Doenca("d1", "", "", List.of("sintona 1", "sintona 2"));
		Doenca doenca2 = new Doenca("d2", "", "", List.of("sintona 2", "sintona 3"));

		Animal animal1 = new Animal("cachorro", 20, "M", "raça", null, Set.of(doenca1), dono1);
		Animal animal2 = new Animal("gato", 20, "F", "raça", null, Set.of(doenca1, doenca2), dono2);

		Veterinario veterinario1 = new Veterinario("Alice", 15, "156464", "alice@gmail.com",
				List.of("espec 1", "espec 2", "espec 3"));
		Veterinario veterinario2 = new Veterinario("Larissa", 15, "156464", "larissa@gmail.com",
				List.of("espec 1"));
		
		veterinario1.atender(animal1);
		veterinario2.atender(animal2);
	}

}
