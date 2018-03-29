package br.com.sinaldasorte.service;

import java.time.ZonedDateTime;

import br.com.sinaldasorte.service.interfaces.DateTimeService;

public class CurrentTimeDateTimeService implements DateTimeService {
 
    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}
