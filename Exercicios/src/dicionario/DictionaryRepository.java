package dicionario;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class DictionaryRepository implements Closeable {
	private static final String FILE_PATH = "./src/dictionary.txt";

	private static final DictionaryRepository instance = new DictionaryRepository();

	private BufferedWriter writer;

	private DictionaryRepository() {
	}

	public static DictionaryRepository getInstance() {
		return instance;
	}

	public void save(String word, String definition) {
		if (writer == null) {
			load();
		}
		
		try {
			writer.write(String.format("%s:%s%n", word, definition));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar no dicionário!");
		}
	}

	public Map<String, String> load() {
		Map<String, String> dictionary = new HashMap<String, String>();

		try {
			if (Files.exists(Paths.get(FILE_PATH))) {
				dictionary = Files.lines(Paths.get(FILE_PATH), StandardCharsets.ISO_8859_1)
						.collect(Collectors.toMap(line -> line.split(":")[0], line -> line.split(":")[1]));
			}

			writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardCharsets.ISO_8859_1);
			
			dictionary.forEach(this::save);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha ao carregar o dicionário!");
			e.printStackTrace();
			System.exit(1);
		}

		return dictionary;
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}

}
