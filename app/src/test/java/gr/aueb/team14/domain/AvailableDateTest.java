package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AvailableDateTest {
    private AvailableDate date;

    @BeforeEach
    void setUp() {
        date = new AvailableDate(new Date(2021, 4, 1), new Date(2021, 4, 2));
    }

    @Test
    void confirmBookedInitAsFalse() {
        assertFalse(date.isBooked());
    }
}