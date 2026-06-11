/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.progpoep1.Quickchat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mokgo
 */
import static org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.Test;
    

public class quickchattest {
   

    Quickchat qc = new Quickchat();

    @Test
    void testCheckUserName_Valid() {
        assertTrue(qc.checkUserName("k_1"));
        assertTrue(qc.checkUserName("a_b"));
    }

    @Test
    void testCheckUserName_Invalid_NoUnderscore() {
        assertFalse(qc.checkUserName("kyle"));
    }

    @Test
    void testCheckUserName_Invalid_TooLong() {
        assertFalse(qc.checkUserName("abcde_f"));
    }

    @Test
    void testCheckPasswordComplexity_Valid() {
        assertTrue(qc.checkPasswordComplexity("Pass123!"));
    }

    @Test
    void testCheckPasswordComplexity_Invalid_TooShort() {
        assertFalse(qc.checkPasswordComplexity("Pass1!"));
    }

    @Test
    void testCheckPasswordComplexity_Invalid_NoUppercase() {
        assertFalse(qc.checkPasswordComplexity("pass123!"));
    }

    @Test
    void testCheckPasswordComplexity_Invalid_NoNumber() {
        assertFalse(qc.checkPasswordComplexity("Password!"));
    }

    @Test
    void testCheckPasswordComplexity_Invalid_NoSpecialChar() {
        assertFalse(qc.checkPasswordComplexity("Pass1234"));
    }

    @Test
    void testCheckCellPhoneNumber_Valid() {
        assertTrue(qc.checkCellPhoneNumber("+2764687511"));
    }

    @Test
    void testCheckCellPhoneNumber_Invalid_TooLong() {
        assertFalse(qc.checkCellPhoneNumber("+27646875112"));
    }

    @Test
    void testCheckCellPhoneNumber_Invalid_NoPlus27() {
        assertFalse(qc.checkCellPhoneNumber("0646875112"));
    }

    @Test
    void testRegisterUser_Success() {
        boolean result = qc.registerUser("k_1", "Pass123!", "+2764687511");
        assertTrue(result);
    }

    @Test
    void testLoginUser_Success() {
        qc.registerUser("k_1", "Pass123!", "+2764687511");
        boolean result = qc.loginUser("k_1", "Pass123!");
        assertTrue(result);
    }

    @Test
    void testLoginUser_WrongPassword() {
        qc.registerUser("k_1", "Pass123!", "+2764687511");
        boolean result = qc.loginUser("k_1", "Wrong123!");
        assertFalse(result);
    }
}
    }
    
   

