package br.com.agentedasorte.negocio.dto;

public class EstatisticaDTO {
	private Integer dezena;
	private Long frequencia;
	private Double frequenciaPorCento;

	public EstatisticaDTO() {
	}

	public EstatisticaDTO(Integer dezena, Long frequencia, Double frequenciaPorCento) {
		this.dezena = dezena;
		this.frequencia = frequencia;
		this.frequenciaPorCento = frequenciaPorCento;
	}

	public Integer getDezena() {
		return dezena;
	}

	public Long getFrequencia() {
		return frequencia;
	}

	public Double getFrequenciaPorCento() {
		return frequenciaPorCento;
	}
}
