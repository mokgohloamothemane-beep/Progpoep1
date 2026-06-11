/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoep1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author mokgo
 */
public class Progpoep3 {
    
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String JSON_FILE = "messages.json";
        
    public static void main(String[]args){
        loadFromJSON();
        
        int choice;
        do {
            System.out.println("\n=== QUICKCHAT MENU ===");
            System.out.println("1. Send Message");
            System.out.println("2. Display Sent Messages");
            System.out.println("3. Display Disregarded Messages");
            System.out.println("4. Stored Messages Menu");
            System.out.println("5. Save & Exit");
            System.out.print("Choose: ");
            
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Choose: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1: sendMessage(); break;
                case 2: displayArray(sentMessages, "Sent Messages"); break;
                case 3: displayArray(disregardedMessages, "Disregarded Messages"); break;
                case 4: storedMessagesMenu(); break;
                case 5: saveToJSON(); System.out.println("Saved. Goodbye!"); break;
                default: System.out.println("Invalid choice");
            }
        } while(choice != 5);
    }

    static void sendMessage() {
        System.out.print("Enter recipient number: ");
        String recipient = sc.nextLine();
        System.out.print("Enter message: ");
        String msg = sc.nextLine();
        
        System.out.println("Choose flag: 1. Send 2. Store 3. Disregard");
        int flagChoice = sc.nextInt();
        sc.nextLine();
        
        if(flagChoice < 1 || flagChoice > 3){
    System.out.println("Invalid option");
    return;
}
        String flag = null;
        Message m = new Message(recipient, msg, flag);
        
        if(flag.equals("Sent")) sentMessages.add(m);
        else if(flag.equals("Stored")) storedMessages.add(m);
        else disregardedMessages.add(m);
        
        System.out.println("Message added to " + flag + " array");
    }

    static void storedMessagesMenu() {
        String choice;
        do {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display longest stored message");
            System.out.println("c. Search by message ID");
            System.out.println("d. Search by recipient");
            System.out.println("e. Delete by message hash");
            System.out.println("f. Display full report");
            System.out.println("g. Back to main menu");
            System.out.print("Choose: ");
            choice = sc.nextLine().toLowerCase();

            switch(choice) {
                case "a": displaySenderRecipient(); break;
                case "b": displayLongestMessage(); break;
                case "c": searchByID(); break;
                case "d": searchByRecipient(); break;
                case "e": deleteByHash(); break;
                case "f": displayReport(); break;
                case "g": return;
                default: System.out.println("Invalid choice");
            }
        } while(true);
    }

    static void displaySenderRecipient() {
        if(storedMessages.isEmpty()) {
            System.out.println("No stored messages");
            return;
        }
        for(Message m : storedMessages) {
            System.out.println("Recipient: " + m.getRecipient() + 
                               " | Message: " + m.getMessage());
        }
    }

    static void displayLongestMessage() {
        if(storedMessages.isEmpty()) {
            System.out.println("No stored messages");
            return;
        }
        Message longest = Collections.max(storedMessages, 
            (m1, m2) -> Integer.compare(m1.getMessage().length(), m2.getMessage().length()));
        System.out.println("Longest message: " + longest.getMessage());
    }

    static void searchByID() {
        System.out.print("Enter Message ID: ");
        String id = sc.nextLine();
        for(Message m : storedMessages) {
            if(m.getMessageId().equals(id)) {
                System.out.println("Recipient: " + m.getRecipient());
                System.out.println("Message: " + m.getMessage());
                return;
            }
        }
        System.out.println("Message ID not found");
    }

    static void searchByRecipient() {
        System.out.print("Enter recipient number: ");
        String recipient = sc.nextLine();
        boolean found = false;
        for(Message m : storedMessages) {
            if(m.getRecipient().equals(recipient)) {
                System.out.println(m.getMessage());
                found = true;
            }
        }
        if(!found) System.out.println("No messages for this recipient");
    }

    static void deleteByHash() {
        System.out.print("Enter Message Hash: ");
        String hash = sc.nextLine();
        Iterator<Message> it = storedMessages.iterator();
        while(it.hasNext()) {
            Message m = it.next();
            if(m.getMessageHash().equals(hash)) {
                System.out.println("Message: \"" + m.getMessage() + "\" successfully deleted");
                it.remove();
                return;
            }
        }
        System.out.println("Hash not found");
    }

    static void displayReport() {
        if(storedMessages.isEmpty()) {
            System.out.println("No stored messages");
            return;
        }
        System.out.println("\n=== FULL REPORT ===");
        for(Message m : storedMessages) {
            System.out.println("Hash: " + m.getMessageHash());
            System.out.println("Recipient: " + m.getRecipient());
            System.out.println("Message: " + m.getMessage());
            System.out.println("-------------------");
        }
    }

    static void displayArray(ArrayList<Message> list, String title) {
        if(list.isEmpty()) {
            System.out.println("No messages in " + title);
            return;
        }
        System.out.println("\n=== " + title + " ===");
        for(Message m : list) {
            System.out.println(m);
            System.out.println("-------------------");
        }
    }

    static void loadTestData() {
        sentMessages.add(new Message("+27834557896", "Did you get the cake?", "Sent"));
        sentMessages.add(new Message("0838884567", "It is dinner time !", "Sent"));
        storedMessages.add(new Message("+27838884567", 
            "Where are you? You are late! I have asked you to be on time.", "Stored"));
        storedMessages.add(new Message("+27838884567", 
            "Ok, I am leaving without you.", "Stored"));
        disregardedMessages.add(new Message("+27834484567", 
            "Yohoooo, I am at your gate.", "Disregard"));
    }

    static void saveToJSON() {
        JSONArray arr = new JSONArray();
        for(Message m : storedMessages) {
            arr.put(m.toJSON());
        }
        try(FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(arr.toString(4));
        } catch(IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }

    static void loadFromJSON() {
        File file = new File(JSON_FILE);
        if(!file.exists()) return;
        
        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            JSONArray arr = new JSONArray(content);
            for(int i = 0; i < arr.length(); i++) {
                storedMessages.add(Message.fromJSON(arr.getJSONObject(i)));
            }
        } catch(Exception e) {
            System.out.println("Error loading JSON: " + e.getMessage());
        }
    }

    static String generateReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    // Nested Message class
    static class Message {
        private String messageId;
        private String messageHash;
        private String recipient;
        private String message;
        private String flag;
        
        public Message(String recipient, String message, String flag) {
            this.recipient = recipient;
            this.message = message;
            this.flag = flag;
            this.messageId = generateMessageId();
            this.messageHash = generateHash();
        }
        
        private String generateMessageId() {
            return String.valueOf((int)(Math.random() * 900000) + 100000);
        }
        
        private String generateHash() {
            return Integer.toHexString((recipient + message).hashCode());
        }
        
        public JSONObject toJSON() {
            JSONObject obj = new JSONObject();
            obj.put("messageId", messageId);
            obj.put("messageHash", messageHash);
            obj.put("recipient", recipient);
            obj.put("message", message);
            obj.put("flag", flag);
            return obj;
        }
        
        public static Message fromJSON(JSONObject obj) {
            Message m;
            m = new Message(obj.getString("recipient"), 
                    obj.getString("message"),
                    obj.getString("flag"));
            m.messageId = obj.getString("messageId");
            m.messageHash = obj.getString("messageHash");
            return m;
        }

        public String getMessageId() { return messageId; }
        public String getMessageHash() { return messageHash; }
        public String getRecipient() { return recipient; }
        public String getMessage() { return message; }
        public String getFlag() { return flag; }
        
        @Override
        public String toString() {
            return "ID: " + messageId + "\nRecipient: " + recipient + 
                   "\nMessage: " + message + "\nFlag: " + flag;
        
        }
    }
    }
