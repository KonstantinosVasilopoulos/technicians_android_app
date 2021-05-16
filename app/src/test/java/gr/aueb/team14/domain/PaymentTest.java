package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment(60.1);
    }

    @Test
    void confirmCompletedInitAsFalse() {
        assertFalse(payment.isCompleted());
    }
}