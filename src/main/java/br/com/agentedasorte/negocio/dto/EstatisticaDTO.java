package br.com.agentedasorte.negocio.dto;

public class EstatisticaDTO {
	private String dezena;
	private Long frequencia;
	private Double frequenciaPorCento;

	public EstatisticaDTO() {
	}

	public EstatisticaDTO(String dezena, Long frequencia, Double frequenciaPorCento) {
		this.dezena = dezena;
		this.frequencia = frequencia;
		this.frequenciaPorCento = frequenciaPorCento;
	}

	public String getDezena() {
		return dezena;
	}

	public Long getFrequencia() {
		return frequencia;
	}

	public Double getFrequenciaPorCento() {
		return frequenciaPorCento;
	}
}
