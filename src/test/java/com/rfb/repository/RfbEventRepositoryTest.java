package com.rfb.repository;

import com.rfb.RfbloyaltyApp;
import com.rfb.bootstrap.RfbBootstrap;
import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbLocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RfbloyaltyApp.class})
public class RfbEventRepositoryTest extends AbstractRepositoryTest {

    private RfbUserRepository rfbUserRepository;

    @Before
    public void setUp() throws Exception {

        RfbBootstrap rfbBootstrap = new RfbBootstrap( rfbLocationRepository,
            rfbEventRepository, rfbEvAttRepository, userRepository, passwordEncoder, authorityRepository, rfbUserRepository);
    }

    @Test
    public void findAllByRfbLocationAndEventDate() throws Exception {
        RfbLocation asaNorte = rfbLocationRepository.findAllByLocationName("asa norte");

        assertNotNull(asaNorte);

        RfbEvent event = rfbEventRepository.findByRfbLocationAndEventDate(asaNorte, LocalDate.now());

        assertNotNull(event);

    }
}
