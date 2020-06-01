package tests;

import helpers.FileReadHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Feature("Work with Projects")
public class FileReadTest {
        @Test
        @Story("Check reading from file")
        @DisplayName("This checks if string returned from file is correct")
        public void testString(){
                String txtToken;
                txtToken = FileReadHelper.getStringFromFile("D:\\code\\qa-guru\\allure-exercise\\src\\test\\java\\qa\\guru\\allure\\token.txt");
                System.out.println("This is what we got:" + txtToken);
                assertEquals("AAAAAAAAABBBBBBBBCCCCCCCCCCCCDDDDDDDDDDDD", txtToken);
        }


}