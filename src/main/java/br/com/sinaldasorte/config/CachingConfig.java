package br.com.sinaldasorte.config;

import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
//@EnableScheduling
public class CachingConfig {
	
//	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
 
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("procureConcursosComNumeroMaiorQue");
    }
    
//    @CacheEvict(allEntries = true, value = {"procureConcursosComNumeroMaiorQue"})
//    @Scheduled(fixedDelay = 20 * 60 * 1000 ,  initialDelay = 500)
//    public void reportCacheEvict() {
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
//        LOGGER.info("Limpado cache, "+ df.format(new Date()));
//    }
}

