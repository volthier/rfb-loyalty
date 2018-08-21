package com.rfb.bootstrap;

import com.rfb.domain.*;
import com.rfb.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class RfbBootstrap implements CommandLineRunner {

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;
    private final RfbEvAttRepository rfbEvAttRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public RfbBootstrap(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository,
                        RfbEvAttRepository rfbEvAttRepository, UserRepository userRepository,
                        PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEvAttRepository = rfbEvAttRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    @Override
    public void run(String... strings) throws Exception {

        if(rfbLocationRepository.count() == 0){
            initData();
        }

    }

    private void initData() {
        User user = new User();
        user.setFirstName("Johnny");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setLogin("johnny");
        user.setEmail("johnny@runningforbrews.com");
        user.setActivated(true);
        // rfbUser.addAuthority(authorityRepository.findOne("ROLE_RUNNER"));
        //  rfbUser.addAuthority(authorityRepository.findOne("ROLE_ORGANIZER"));
        userRepository.save(user);
        //load data
        RfbLocation asaNorte = getRfbLocation("asa norte", DayOfWeek.MONDAY.getValue());

        //rfbUser.setHomeLocation(aleAndWitch);
        userRepository.save(user);

        RfbEvent aleEvent = getRfbEvent(asaNorte);

        //getRfbEventAttendance(user, aleEvent);

        RfbLocation ratc = getRfbLocation("St Pete - Right Around The Corner", DayOfWeek.TUESDAY.getValue());

        RfbEvent ratcEvent = getRfbEvent(ratc);

        // getRfbEventAttendance(user, ratcEvent);

        RfbLocation stPeteBrew = getRfbLocation("St Pete - St Pete Brewing", DayOfWeek.WEDNESDAY.getValue());

        RfbEvent stPeteBrewEvent = getRfbEvent(stPeteBrew);

        //   getRfbEventAttendance(user, stPeteBrewEvent);

        RfbLocation yardOfAle = getRfbLocation("St Pete - Yard of Ale", DayOfWeek.THURSDAY.getValue());

        RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);

        //  getRfbEventAttendance(user, yardOfAleEvent);

        RfbLocation pourHouse = getRfbLocation("Tampa - Pour House", DayOfWeek.MONDAY.getValue());
        RfbLocation macDintons = getRfbLocation("Tampa - Mac Dintons", DayOfWeek.TUESDAY.getValue());

        RfbLocation satRun = getRfbLocation("Saturday Run for testing", DayOfWeek.SATURDAY.getValue());
    }


    private void getRfbEventAttendance(RfbUser rfbUser, RfbEvent rfbEvent) {
        RfbEvAtt rfbAttendance = new RfbEvAtt();
        rfbAttendance.setRfbEvent(rfbEvent);
        rfbAttendance.setRfbUser(rfbUser);
        rfbAttendance.setAttendanceDay(LocalDate.now());

        System.out.println(rfbAttendance.toString());

        rfbEvAttRepository.save(rfbAttendance);
        rfbEventRepository.save(rfbEvent);
    }

    private RfbEvent getRfbEvent(RfbLocation rfbLocation) {
        RfbEvent rfbEvent = new RfbEvent();
        rfbEvent.setEventCode(UUID.randomUUID().toString());
        rfbEvent.setEventDate(LocalDate.now());
        rfbLocation.addRfbEvent(rfbEvent);
        rfbLocationRepository.save(rfbLocation);
        rfbEventRepository.save(rfbEvent);
        return rfbEvent;
    }

    private RfbLocation getRfbLocation(String locationName, int value) {
        RfbLocation rfbLocation = new RfbLocation();
        rfbLocation.setLocationName(locationName);
        rfbLocation.setRunDayOfWeek(value);
        rfbLocationRepository.save(rfbLocation);
        return rfbLocation;
    }
}
