package com.mycompany.progpoep1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Progpoep3Test {

    // Reset system before each test (important for static lists)
    @BeforeEach
    void resetData() {
        Progpoep3.sentMessages.clear();
        Progpoep3.storedMessages.clear();
        Progpoep3.disregardedMessages.clear();
    }

    // ----------------------------
    // 1. MESSAGE CREATION TEST
    // ----------------------------
    @Test
    void testMessageCreation() {
        Progpoep3.Message msg =
                new Progpoep3.Message("+27831234567", "Hello World", "Sent");

        assertEquals("+27831234567", msg.getRecipient());
        assertEquals("Hello World", msg.getMessage());
        assertEquals("Sent", msg.getFlag());

        assertNotNull(msg.getMessageId());
        assertNotNull(msg.getMessageHash());
    }

    // ----------------------------
    // 2. MESSAGE ID FORMAT TEST
    // ----------------------------
    @Test
    void testMessageIdFormat() {
        Progpoep3.Message msg =
                new Progpoep3.Message("+27831234567", "Test", "Stored");

        assertTrue(msg.getMessageId().matches("\\d+"),
                "Message ID should be numeric");
    }

    // ----------------------------
    // 3. HASH GENERATION TEST
    // ----------------------------
    @Test
    void testMessageHashConsistency() {
        Progpoep3.Message msg1 =
                new Progpoep3.Message("+2783", "Hi", "Sent");

        Progpoep3.Message msg2 =
                new Progpoep3.Message("+2783", "Hi", "Sent");

        assertEquals(msg1.getMessageHash(), msg2.getMessageHash(),
                "Same input should produce same hash");
    }

    // ----------------------------
    // 4. RECIPIENT VALIDATION TEST (SA FORMAT)
    // ----------------------------
    @Test
    void testValidRecipient() {
        String recipient = "+27831234567";
        assertTrue(recipient.matches("\\+\\d{1,12}"));
    }

    @Test
    void testInvalidRecipient() {
        String recipient = "ABC123";
        assertFalse(recipient.matches("\\+\\d{1,12}"));
    }

    // ----------------------------
    // 5. MESSAGE FLAG LOGIC TEST
    // ----------------------------
    @Test
    void testMessageFlagAssignment() {
        Progpoep3.Message msg =
                new Progpoep3.Message("+27000111111", "POE Test", "Disregard");

        assertEquals("Disregard", msg.getFlag());
    }

    // ----------------------------
    // 6. ADD MESSAGE TO LISTS (SIMULATION)
    // ----------------------------
    @Test
    void testAddingToSentMessages() {
        Progpoep3.sentMessages.add(
                new Progpoep3.Message("+2783", "Hi", "Sent")
        );

        assertEquals(1, Progpoep3.sentMessages.size());
    }

    @Test
    void testAddingToStoredMessages() {
        Progpoep3.storedMessages.add(
                new Progpoep3.Message("+2783", "Stored msg", "Stored")
        );

        assertEquals(1, Progpoep3.storedMessages.size());
    }

    @Test
    void testAddingToDisregardedMessages() {
        Progpoep3.disregardedMessages.add(
                new Progpoep3.Message("+2783", "Ignore", "Disregard")
        );

        assertEquals(1, Progpoep3.disregardedMessages.size());
    }

    // ----------------------------
    // 7. JSON SAVE TEST
    // ----------------------------
    @Test
    void testSaveToJSONCreatesFile() {
        Progpoep3.storedMessages.add(
                new Progpoep3.Message("+27831234567", "Save test", "Stored")
        );

        Progpoep3.saveToJSON();

        File file = new File("messages.json");
        assertTrue(file.exists(), "JSON file should be created");
    }

    // ----------------------------
    // 8. JSON LOAD TEST
    // ----------------------------
    @Test
    void testLoadFromJSON() {
        // First add and save
        Progpoep3.storedMessages.add(
                new Progpoep3.Message("+27831234567", "Load test", "Stored")
        );

        Progpoep3.saveToJSON();

        // Clear and reload
        Progpoep3.storedMessages.clear();
        Progpoep3.loadFromJSON();

        assertTrue(Progpoep3.storedMessages.size() >= 1,
                "Messages should load from JSON");
    }

    // ----------------------------
    // 9. DELETE BY HASH TEST
    // ----------------------------
    @Test
    void testDeleteByHashLogic() {
        Progpoep3.Message msg =
                new Progpoep3.Message("+2783", "Delete me", "Stored");

        Progpoep3.storedMessages.add(msg);

        String hash = msg.getMessageHash();

        Progpoep3.storedMessages.removeIf(m ->
                m.getMessageHash().equals(hash));

        assertEquals(0, Progpoep3.storedMessages.size());
    }

    // ----------------------------
    // 10. SYSTEM INTEGRATION TEST
    // ----------------------------
    @Test
    void testSystemMessageRouting() {
        Progpoep3.Message msg =
                new Progpoep3.Message("+2783", "Route test", "Sent");

        Progpoep3.sentMessages.add(msg);

        assertTrue(Progpoep3.sentMessages.contains(msg));
    }
}