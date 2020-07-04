package daocursos;

public class Curso {
	private long id;
	private String nome;
	private String descricao;
	private boolean ativo;
	private String inicio;
	private String termino;
	
	public Curso() {
	}

	public Curso(String nome, String descricao, boolean ativo, 
			String inicio, String termino) {
		this(0, nome, descricao, ativo, inicio, termino);
	}
	
	public Curso(long id, String nome, String descricao, boolean ativo, 
			String inicio, String termino) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.inicio = inicio;
		this.termino = termino;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}
}
