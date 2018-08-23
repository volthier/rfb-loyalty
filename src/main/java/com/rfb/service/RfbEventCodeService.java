package com.rfb.service;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbLocation;
import com.rfb.repository.RfbEventRepository;
import com.rfb.repository.RfbLocationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RfbEventCodeService {

    private final Logger log = LoggerFactory.getLogger(RfbEventCodeService.class);

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;

    public RfbEventCodeService( RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository) {
        this.rfbEventRepository = rfbEventRepository;
        this.rfbLocationRepository = rfbLocationRepository;
    }

    //@Scheduled(cron = "0 0 * * * ?")
    @Scheduled(cron = "0 * * * * ?")
//    @Scheduled(cron = "* * * * * ?")
    public void generateRunEventCodes() {
        log.debug("Gerando os Eventos.....!!!!");

        List<RfbLocation> rfbLocations = rfbLocationRepository.findAllByRunDayOfWeek(LocalDate.now().getDayOfWeek().getValue());

        log.debug("Localizações encontradas para o evento:  "+ rfbLocations.size());

        rfbLocations.forEach( location -> {
            log.debug("Checando eventos por localização: " + location.getId());

            RfbEvent existingEvent = rfbEventRepository.findByRfbLocationAndEventDate(location, LocalDate.now());

            if(existingEvent == null) {
                log.debug("Eventos não encontado, Criando Evento");

                RfbEvent newEvent = new RfbEvent();
                newEvent.setRfbLocation(location);
                newEvent.setEventDate(LocalDate.now());
                newEvent.setEventCode(RandomStringUtils.randomAlphanumeric(10).toUpperCase());

                rfbEventRepository.save(newEvent);

                log.debug("Evento criado ==> " + newEvent.toString() );
            }else {
                log.debug("Existe evento para o dia!");
            }
        });

    }
}
