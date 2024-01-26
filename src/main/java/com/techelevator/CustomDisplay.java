package com.techelevator;//package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CustomDisplay {


    public void customDisplayBuilder(Map<String,Item>invMap){
        /*
        Requirements:
        Distance must be 17(largest name is 15+||makes 17)
        take for length of string subtract from default to get either how many spaces,-, we need

        start and finish of all string section must start and end with wall
         with the exception of top and bottom of row which needs corner pieces
         */
        //initialize types of characters for building string
        String rightTopCorner = "┓";//still need
        String leftTopCorner = "┍"; //need
        String bottomRightCorner = "┙"; //need
        String bottomLeftCorner = "└"; //need
        String wall = "|";
        String floor = "-";
        String quantity = "Qty: ";
        String space = " ";
        String priceTag = "$";
        String soldOut = "Sold Out";

        //Setup row A
        String rowALine1="";
        String rowALine2="";
        String rowALine3="";
        String rowALine4="";


        //setup rowB
        String rowBLine1="";
        String rowBLine2="";
        String rowBLine3="";
        String rowBLine4="";

        //setup rowC
        String rowCLine1="";
        String rowCLine2="";
        String rowCLine3="";
        String rowCLine4="";

        //setup rowD
        String rowDLine1="";
        String rowDLine2="";
        String rowDLine3="";
        String rowDLine4="";



        //basic idea: for eachloop asign to slot and concat


        for(Map.Entry<String,Item> disInv : invMap.entrySet()){
            String key = disInv.getKey();
            Item values = invMap.get(key);
            String slotId = values.getSlotId();
            String name = values.getName();
            Double price = values.getPrice();
            Character row = slotId.charAt(0);
            int qty = values.getQty();
            String tempLine1="";
            String tempLine2="";
            String tempLine3="";
            String tempLine4="";

            int wsLength = 15 - values.getName().length();




            //build tempLines
            //topleft+floor*15?+topright
            tempLine1 = String.format("%s%s%s",leftTopCorner,floor.repeat(15),rightTopCorner);

            tempLine2 = String.format("%s%s%s%s",wall,name,space.repeat(wsLength),wall);

            tempLine3 = String.format("%s%s%s",floor.repeat(4),space.repeat(12),wall);

            if(qty <=0){
                tempLine4 = String.format("|%s|  %s  |",slotId,soldOut);
            }else {
            tempLine4 = String.format("|%s|$%s %s%s|",slotId,price,quantity,qty);}

            if(row.equals('A')){
                rowALine1 += tempLine1;
                rowALine2 += tempLine2;
                rowALine3 += tempLine3;
                rowALine4 += tempLine4;
            }else if(row.equals('B')){
                rowBLine1 += tempLine1;
                rowBLine2 += tempLine2;
                rowBLine3 += tempLine3;
                rowBLine4 += tempLine4;
            }else if(row.equals('C')){
                rowCLine1 += tempLine1;
                rowCLine2 += tempLine2;
                rowCLine3 += tempLine3;
                rowCLine4 += tempLine4;
            }else if(row.equals('D')){
                rowDLine1 += tempLine1;
                rowDLine2 += tempLine2;
                rowDLine3 += tempLine3;
                rowDLine4 += tempLine4;
            }

        }
        String [] generateDisplay = {rowALine1,rowALine2,rowALine3,rowALine4,rowBLine1,rowBLine2,rowBLine3,rowBLine4,rowCLine1,rowCLine2,rowCLine3,rowCLine4,rowDLine1,rowDLine2,rowDLine3,rowDLine4};

        for(int i=0;i<generateDisplay.length;i++){
            System.out.println(generateDisplay[i]);
        }
    }

}
