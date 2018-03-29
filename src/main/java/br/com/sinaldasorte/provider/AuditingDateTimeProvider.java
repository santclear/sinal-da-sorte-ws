package br.com.sinaldasorte.provider;

import org.springframework.data.auditing.DateTimeProvider;

import br.com.sinaldasorte.service.interfaces.DateTimeService;

import java.util.Calendar;
import java.util.GregorianCalendar;
 
public class AuditingDateTimeProvider implements DateTimeProvider {
 
    private final DateTimeService dateTimeService;
 
    public AuditingDateTimeProvider(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
 
    @Override
    public Calendar getNow() {
        return GregorianCalendar.from(dateTimeService.getCurrentDateAndTime());
    }
}
