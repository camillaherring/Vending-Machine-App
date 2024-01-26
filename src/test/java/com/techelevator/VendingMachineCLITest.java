package com.techelevator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineCLITest {
private VendingMachineCLI cli = new VendingMachineCLI();
//    @Before
//    public void setUp() throws Exception {
//
//        cli.main(null);
//    }

//    @After
//    public void tearDown() throws Exception {
//        System.exit(1);
//    }



    @Test
    public void getBalance() {

    }

    @Test
    public void get_starting_balance() {
        double expected = 0.0;
        double actual = cli.getBalance();
        assertEquals(expected,actual);
    }

    @Test
    public void return_5_when_adding_to_balance() {
        cli.feedMoneyIn(5);
        assertEquals(5,cli.getBalance());
    }

    @Test
    public void return_6_when_adding_a_dollar_to_balance() {
        for(int i=0;i<6;i++){
            cli.feedMoneyIn(1);
        }
        double expected =0.0;
        double actual = cli.getBalance();
        assertEquals("yabet",expected,actual);


    }





    @Test
    public void feedMoneyIn() {
    }

    @Test
    public void approvePurchase() {
    }

    @Test
    public void subtractPrice() {
    }

    @Test
    public void makeChange() {
    }

    @Test//add multiple versions to test
    public void main() {
        //this handles generally running the application as normal
    }
}