package aeroporto;

import java.util.List;

public class Aeronave {
	private String modelo;
	private String anoFabric;
	private String dtUltManut;
	private List<Passageiro> passageiros;
	private Piloto piloto;

	public void decolar() {
		System.out.println("*** Aeronave decolando ***");
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnoFabric() {
		return anoFabric;
	}

	public void setAnoFabric(String anoFabric) {
		this.anoFabric = anoFabric;
	}

	public String getDtUltManut() {
		return dtUltManut;
	}

	public void setDtUltManut(String dtUltManut) {
		this.dtUltManut = dtUltManut;
	}

	public List<Passageiro> getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(List<Passageiro> passageiros) {
		this.passageiros = passageiros;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

}
