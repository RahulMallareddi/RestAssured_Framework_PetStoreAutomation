package api.payload;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // Create a Random object
        Random random = new Random();

        // Define the range for years
        int startYear = 2000; // Start year
        int endYear = 2024; // End year

        // Generate random values for year, month, day, hour, minute, second, and nanosecond
        int randomYear = startYear + random.nextInt(endYear - startYear + 1);
        int randomMonth = random.nextInt(12) + 1;
        int randomDay = random.nextInt(28) + 1; // Keeping it simple by limiting to 28 days
        int randomHour = random.nextInt(24);
        int randomMinute = random.nextInt(60);
        int randomSecond = random.nextInt(60);
        int randomNano = random.nextInt(1_000_000_000);

        // Create a random date-time based on the random values
        LocalDateTime randomDateTime = LocalDateTime.of(randomYear, randomMonth, randomDay, randomHour, randomMinute, randomSecond, randomNano);

        // Format to ISO 8601
        String isoDateTime = randomDateTime.toInstant(ZoneOffset.UTC).toString();

        // Print the random date-time in ISO 8601 format
        System.out.println("Random ISO 8601 Date-Time: " + isoDateTime);
    }
}

