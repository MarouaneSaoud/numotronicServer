package org.numo.functions;

import org.numo.model.StatusDevice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalculateDeviceStatus {
    public StatusDevice calculateDeviceStatus(String lastSeenDateTimeStr, LocalDateTime currentDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        LocalDateTime lastSeenDateTime = LocalDateTime.parse(lastSeenDateTimeStr, formatter);

        long hoursSinceLastSeen = ChronoUnit.HOURS.between(lastSeenDateTime, currentDateTime);
        if (hoursSinceLastSeen <= 6) {
            return StatusDevice.ONLINE;
        } else {
            return StatusDevice.OFFLINE;
        }
    }
}
