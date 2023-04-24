package ru.rikmasters.test.driverservice.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rikmasters.test.driverservice.service.DriverService;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CongratulationScheduler {

    /*
    cron format: sec min hour day_of_month month day_of_week
     */
    private static final String CRON_CONGRATULATION = "30 30 12 * * *";
    private final DriverService driverService;

    @Scheduled(cron = CRON_CONGRATULATION)
    public void congratsDrivers() {
        Date today = Date.valueOf(LocalDate.now());
        driverService.findAllByBirthDate(today)
                .forEach(driver -> log.info("Сегодня день рождения у " + driver.getFio()));
    }
}
