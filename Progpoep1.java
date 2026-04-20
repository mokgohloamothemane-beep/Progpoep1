/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoep1;

import java.util.Scanner;

/**
 *
 * @author mokgo
 */
public class Progpoep1 {
    static Scanner input = new Scanner(System.in);
    		static String username="      ";
	    	static String password="";
			static String cellNum="";

    public static void main(String[] args) {
        System.out.println("=====registration====");

		//USERNAME

		final int USERNAME_MAX =5;
		while(username.length() > USERNAME_MAX || !username.contains("_")){
		System.out.println("Enter username: ");
		String username = input.nextLine();
		    if(username.length() == USERNAME_MAX){
		    System.out.println("the username that was entered is valid as it is properly formatted");
		}
		else if(username.length() <= USERNAME_MAX || !username.contains("_")){
		    System.out.println("the username that was entered is invalid making the capturing process unsuccessful");
		}

		final int PASSWORD_MIN = 8;
		String password = "";
		do {
			System.out.println("Enter your password: ");
			password = input.nextLine();

			if(password.length() < PASSWORD_MIN) {
				System.out.println("The password that was entered is invalid as it is not properly formatted");

			}
		}  while(password.length() <PASSWORD_MIN || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*") || !password.matches(".*[^a-zA-Z0-9 ].*"));
		System.out.println("The password that was entered is valid making the capturing process successful");

		final int CELL_LENGTH =10;
		String cellNum =  "  ";
		do {
			System.out.println("Enter cell number");
			cellNum=input.nextLine();

			if(cellNum.length()<CELL_LENGTH) {
				System.out.println("Cellphone number is invalid as it is not properly formmatted");
			}
		}  while(cellNum.length() !=CELL_LENGTH)  ;
		System.out.println("Cellphone number was correct");

		//response message//

		System.out.println("The provided information was relevant making the account creation proccess a success");
		System.out.println("welcome ");

		signingAuthenticator();
}
	}

	public static void signingAuthenticator() {
		String inputUsername;
		String inputPassword;
		String inputCellNum;
		
		System.out.println("====Login====");
		
		System.out.println("Enter username");
		inputUsername = input.nextLine();

		System.out.println("Enter password");
		inputPassword = input.nextLine();

		System.out.println("Enter cell number");
		inputCellNum = input.nextLine();

		//username methods//

		if(username.equals(inputUsername)) {
			System.out.println("Username is correct");

		} else {
			System.out.println("Username that was entered is invalid as it is not properly formatted");
		}
		//password methods//

		if(inputPassword.equals(password)) {
			System.out.println("The password that was entered is valid making the capturing process successful");

		} else {
			System.out.println("the entered password must be atleast 8 characters for capturing to proceed");
		}


		//cellphone methods//

		if (inputCellNum.equals(cellNum)) {
			System.out.println("Cellphone was correct ");
		} else {
			System.out.println("the entered cellNum is non existent in the SA format");
		}
		System.out.println("username:"+username);
		System.out.println("cellNum:"+cellNum);
		System.out.println("password:"+ password + "*");
	}
        
        public static boolean checkUsername(String username) {
    return username.length() <= 5 && username.contains("_");
}

public static boolean checkPassword(String password) {
    return password.length() >= 8 &&
           password.matches(".*[A-Z].*") &&
           password.matches(".*\\d.*") &&
           password.matches(".*[^a-zA-Z0-9].*");
}

public static boolean checkCellNumber(String cellNum) {
    return cellNum.matches("0\\d{9}");
}

}
