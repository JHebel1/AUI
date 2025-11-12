package org.example.player.Infrastructure.initializers;

import lombok.RequiredArgsConstructor;
import org.example.club.Domain.entities.Club;
import org.example.player.Domain.entities.Player;
import org.example.player.Application.services.PlayerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@DependsOn("clubInitializer")
public class PlayerInitializer implements InitializingBean {
    private final PlayerService playerService;

    @Override
    public void afterPropertiesSet() throws Exception{
        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("lionel_messi".getBytes()))
                .name("Lionel")
                .surname("Messi")
                .age(37)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("fc_barcelona".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("gerard_pique".getBytes()))
                .name("Gerard")
                .surname("Piqué")
                .age(35)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("fc_barcelona".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("sergio_busquets".getBytes()))
                .name("Sergio")
                .surname("Busquets")
                .age(35)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("fc_barcelona".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("jordi_alba".getBytes()))
                .name("Jordi")
                .surname("Alba")
                .age(34)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("fc_barcelona".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("frenkie_de_jong".getBytes()))
                .name("Frenkie")
                .surname("de Jong")
                .age(26)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("fc_barcelona".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("mohamed_salah".getBytes()))
                .name("Mohamed")
                .surname("Salah")
                .age(33)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("liverpool_fc".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("virgil_van_dijk".getBytes()))
                .name("Virgil")
                .surname("van Dijk")
                .age(32)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("liverpool_fc".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("jordan_henderson".getBytes()))
                .name("Jordan")
                .surname("Henderson")
                .age(33)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("liverpool_fc".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("trent_alexander_arnold".getBytes()))
                .name("Trent")
                .surname("Alexander-Arnold")
                .age(25)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("liverpool_fc".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("alisson_becker".getBytes()))
                .name("Alisson")
                .surname("Becker")
                .age(31)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("liverpool_fc".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("karim_benzema".getBytes()))
                .name("Karim")
                .surname("Benzema")
                .age(35)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("real_madrid".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("luka_modric".getBytes()))
                .name("Luka")
                .surname("Modrić")
                .age(37)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("real_madrid".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("toni_kroos".getBytes()))
                .name("Toni")
                .surname("Kroos")
                .age(33)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("real_madrid".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("vinicius_junior".getBytes()))
                .name("Vinícius")
                .surname("Júnior")
                .age(22)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("real_madrid".getBytes()))
                        .build())
                .build());

        playerService.save(Player.builder()
                .id(UUID.nameUUIDFromBytes("thibaut_courtois".getBytes()))
                .name("Thibaut")
                .surname("Courtois")
                .age(31)
                .club(Club.builder()
                        .id(UUID.nameUUIDFromBytes("real_madrid".getBytes()))
                        .build())
                .build());
    }
}
