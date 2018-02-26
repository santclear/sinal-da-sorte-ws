package br.com.sinaldasorte.dto;

public class EstatisticaDto {
	private String dezena;
	private Long frequencia;
	private Double frequenciaPorCento;

	public EstatisticaDto() {
	}

	public EstatisticaDto(String dezena, Long frequencia, Double frequenciaPorCento) {
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
