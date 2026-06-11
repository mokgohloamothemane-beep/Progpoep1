/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoep1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mokgo
 */
public class QuickchatMenu {
     
    static ArrayList<Message>messages = new ArrayList<>();
    static int idCounter = 1;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice;
		
		do{
		    System.out.println("----Quickchat Menu----");
                    System.out.println("Enter the number of message you want to send");
                    System.out.println("Choose an option.");
		    System.out.println("1.Create a Message.");		   
		    System.out.println("2.Read all messages.");
		    System.out.println("3.delete all messages.");
		    System.out.println("4.Exit.");
		    
		     choice = input.nextInt();
		     input.nextLine();
		     
		     switch (choice){
		         case 1 : 
		             createMessage(input);
		             break;
		             
		             case 2 : 
		                 readMessages();
		                 break;
		                 
		                 case 3 :
		                     deleteMessage(input);
		                     break;
		                     
		                     case 4 :
		                         System.out.println("Goodbye!");
		                         break;
		                         
		                         default:
		                         System.out.println("Invalid option ");
		     }
		     
                    }while(choice != 4);
                input.close();
	}
	//Create
	public static void createMessage(Scanner input ){
            
	    System.out.println("Enter MessageID : ");
	    int id = input.nextInt();
	    input .nextLine();
	    
	    System.out.println("Enter Recipient(+1234..) : ");
	    String recipient = input.nextLine();
	    
	   System.out.println("Enter Message content : ");
	    String content = input.nextLine();
	    
	    if(recipient.startsWith("+") && recipient.length()<=13){
	        Message msg = new Message(id,recipient,content);
	        messages.add(msg);
	        
	        System.out.println("Message added successfully");
	    }else{
	        System.out.println("Invalid Recipient!must start with + and be 13 chars ");
	    }
	}
	
	// read all 
	public static void readMessages(){
	    if(messages.isEmpty()){
	        System.out.println("no message was found ");
	        return ;
	    }
	    System.out.println("----all messages----");
	    
	    for (Message msg : messages ){
	        System.out.println(msg);
	    }
	}
	
	// delete 
	public static void deleteMessage(Scanner input){
	    if(messages.isEmpty()){
	        System.out.println("no message to delete ");
	        return;
	    }
	    
	    System.out.println("Enter MessageID to delete");
	    int id = input.nextInt();
	    
	    boolean  found = false ;
	    
	    for(int i = 0 ;i < messages.size(); i++){
	        if (messages.get(i).id==id ){
	            messages.remove(i);
	            
	   System.out.println("Message deleted .");
                  found =true;
                  break;
	        }
	    }
	}
	
	//Message class
	static class Message{
	    
	    int id ;
	    String recipient;
	    String content;
	    
	    public Message(int id,String Recipient,String content){
	        this.id = id;
	        this.recipient = Recipient;
	        this.content = content;
	    }
	}

    }

