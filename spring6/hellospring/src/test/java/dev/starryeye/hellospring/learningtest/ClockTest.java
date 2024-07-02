package dev.starryeye.hellospring.learningtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {

    @DisplayName("Clock 은 계속 흐르는 시계이다.")
    @Test
    void clock() {

        Clock clock = Clock.systemDefaultZone();

        LocalDateTime now1 = LocalDateTime.now(clock);
        LocalDateTime now2 = LocalDateTime.now(clock);

        assertThat(now2).isAfter(now1);
    }

    @DisplayName("고정된 시간을 가진 시계도 만들 수 있다.")
    @Test
    void fixed_clock() {

        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime now1 = LocalDateTime.now(clock);
        LocalDateTime now2 = LocalDateTime.now(clock);

        LocalDateTime plus1Hour = LocalDateTime.now(clock).plusHours(1);

        assertThat(now2).isEqualTo(now1);
        assertThat(plus1Hour).isEqualTo(now1.plusHours(1));
    }
}
