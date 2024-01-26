package com.techelevator;

import javax.imageio.IIOException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoggerSalesREport {

    private File log = new File("logger.txt");
    /*
    example
    {Potato Crisps,[0,0]}

    user feeds money
    logger writes money fedd
    user selects item
    validates ifCanPurchase
    transaction goes to logger and updates map
    writes to loggger:
    date time chips B4 int    int
    updates inv
    updates salesMap
    total += price;


    write creates sales report
    string setup key|arr[0]|arr[1]
    total
    orr

    writter finds and replace

    lineInText.conatins(name)
    write updating info
    also take total, add current transaction value

    Note: use variable for #ofitemsbought --> trans%2=define disccount
    cont: if discount set to true.
    NOTE: if .conatains Total Sales, split on " ", take lineInText[2] for updating total money
     */


//    public void loggerSetup(){
//
//        try {
//
//            if(log.createNewFile()){
//                System.out.println("Logger Created");
//            }else {
//                System.out.println("Logger alredy exsist");
//            }
//        }catch (IOException e){
//            System.out.println("An Error has occured. Please Consult UmbrellaCorp IT.");
//        }
//
//    }

    public void loggerWrite(String message, double transaction, double balance){
        //handle doubles not showing penny section
       double[] money = {Math.round(transaction*100.00) / 100.00,Math.round(balance*100.00) / 100.00};
        String transactionStr= Double.toString(transaction);
        String balanceStr = Double.toString(balance);
       for(int i=0;i<money.length;i++){
           String centsAdjust= Double.toString(money[i]);
           String[] decimalSplit = centsAdjust.split("\\.");
           if(decimalSplit[1].length()==1){
               if (i==0){
                   transactionStr = String.join(".",decimalSplit)+"0";
               } else {
                   balanceStr = String.join(".",decimalSplit)+"0";
               }
           }

       }

       PrintWriter logWritter = null;
       if (log.exists()) {
           try {
               logWritter = new PrintWriter(new FileOutputStream(log, true));
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
       }else {
           try {
               logWritter = new PrintWriter(log);
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
       }

            DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            LocalDateTime currentDateTime = LocalDateTime.now();
            String current = currentDateTime.format(targetFormat);
            String logOutput = String.format("%s %s $%s $%s\n",current,message,transactionStr,balanceStr);


            logWritter.append(logOutput);
            logWritter.flush();
            logWritter.close();

    }
}
