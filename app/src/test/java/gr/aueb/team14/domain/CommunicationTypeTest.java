package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommunicationTypeTest {

    @Test
    void valueOf() {
        assertEquals(CommunicationType.SMS, CommunicationType.valueOf("SMS"));
        assertEquals(CommunicationType.Phone, CommunicationType.valueOf("Phone"));
    }
}