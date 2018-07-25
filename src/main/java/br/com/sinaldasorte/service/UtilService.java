package br.com.sinaldasorte.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@CacheEvict(allEntries = true, value = { "procureConcursosComNumeroMaiorQue" })
	public void reportCacheEvict() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		LOGGER.info("Limpado cache, " + df.format(new Date()));
	}
}
