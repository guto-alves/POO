package petshop;
import java.util.List;

public class Doenca {
	private String nome;
	private String descricao;
	private String causa;
	private List<String> sintonas;

	public Doenca(String nome, String descricao, String causa, List<String> sintonas) {
		this.nome = nome;
		this.descricao = descricao;
		this.causa = causa;
		this.sintonas = sintonas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public List<String> getSintonas() {
		return sintonas;
	}

	public void setSintonas(List<String> sintonas) {
		this.sintonas = sintonas;
	}

}
