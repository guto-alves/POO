package dicionario;

import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;

public class Dictionary {

	public static void main(String[] args) {
		try (DictionaryRepository dictionaryManager = DictionaryRepository.getInstance()) {
			Map<String, String> dictionary = dictionaryManager.load();
			
			while (true) {
				String word = JOptionPane.showInputDialog("Digite uma palavra: ");

				if (word.isBlank()) {
					continue;
				}

				if (dictionary.containsKey(word)) {
					JOptionPane.showMessageDialog(null, "Definição:\n" + dictionary.get(word));
				} else {
					String definition = JOptionPane
							.showInputDialog(String.format("A palavra \"%s\" ainda não existe no dicionário!\n"
									+ "Por favor, caso saiba, nos ajude informando-a "
									+ "(deixe o campo de texto em branco para voltar):", word));
					if (!definition.isBlank()) {
						dictionary.put(word, definition);
						dictionaryManager.save(word, definition);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
