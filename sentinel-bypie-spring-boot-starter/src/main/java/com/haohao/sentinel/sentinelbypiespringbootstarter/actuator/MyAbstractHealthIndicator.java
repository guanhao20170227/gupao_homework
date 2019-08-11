package com.haohao.sentinel.sentinelbypiespringbootstarter.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component("myHealth2")
public class MyAbstractHealthIndicator extends AbstractHealthIndicator {

    private static final String VERSION = "V1.0.0";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int code = 0;
        if (0 != code) {
            builder.down().withDetail("code", code)
                    .withDetail("version", VERSION).build();
        }
        builder.withDetail("code", code)
                .withDetail("version", VERSION).up().build();
    }
}
