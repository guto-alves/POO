package animalbcefx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnimalControl {
	private ObservableList<Animal> animals;
 
	public AnimalControl() {
		animals = FXCollections.observableArrayList();
	}

	public void adicionar(Animal animal) {
		animals.add(animal);
	}

	public Animal pesquisarPorNome(String nome) {
		for (Animal animal : animals) {
			if (animal.getNome().contains(nome)) {
				return animal;
			}
		}
		return null;
	}

	public ObservableList<Animal> getAlunos() {
		return animals;
	}
}
