package org.example.init;

import lombok.RequiredArgsConstructor;
import org.example.entity.Club;
import org.example.service.ClubService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClubInitializer implements InitializingBean {
    private final ClubService clubService;

    @Override
    public void afterPropertiesSet() throws Exception{
        clubService.save(Club.builder()
                .id(UUID.nameUUIDFromBytes("fc_barcelona".getBytes()))
                .name("FC Barcelona")
                .country("Spain").
                build());
        clubService.save(Club.builder()
                .id(UUID.nameUUIDFromBytes("liverpool_fc".getBytes()))
                .name("Liverpool FC")
                .country("England").
                build());
        clubService.save(Club.builder()
                .id(UUID.nameUUIDFromBytes("real_madrid".getBytes()))
                .name("Real Madrid")
                .country("Spain").
                build());
    }

}
