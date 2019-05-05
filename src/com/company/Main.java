package com.company;

import java.util.Scanner;

public class Main {

    static Integer Protons;
    static Integer ProtonsLeft;
    static Integer ProtonsToPrint;
    static Integer NeutronsToprint;

    static Integer power = 0;

    static Integer PossibleRows;
    static Integer PossibleLastRowElements;
    static Integer AvailLeftSpacePro;
    static Integer AvailLeftSpaceNeu;

    static Integer LeftSpaceCountNeu;
    static Integer LeftSpaceCountPro;

    static String LastPrinted;

    static boolean FinalStage = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter the number of protons : ");
        Protons = sc.nextInt();

       // SpaceCount = 0;

        LeftSpaceCountPro = 0;
        LeftSpaceCountNeu = 0;


        InitSpace(Protons);

        AvailLeftSpacePro = (int)Math.pow(2,PossibleLastRowElements)*2;
        AvailLeftSpaceNeu = (int)Math.pow(2,PossibleLastRowElements)*2;

        //System.out.println("Possible last row elements : " +(int)Math.pow(2,PossibleLastRowElements));

        LastPrinted = "P";
        ProtonsToPrint = (int)Math.pow(2,power);
        NeutronsToprint = (int)Math.pow(2,power);
        ProtonsLeft = Protons;

        if(Protons.equals(0)){
            System.out.println("Not enough protons for the bomb");
        }
        else{

            while(ProtonsLeft != 0){

                if(LastPrinted.equals("N")){

                    //Checks here if ProtonsLeft is less than or equal to ProtonsToPrint
                    if(ProtonsToPrint>=ProtonsLeft){

                        PrintSpace(AvailLeftSpacePro);
                        //LeftSpaceCountPro++;
                        ReduceLeftSpacePro();


                        for(int y=0; y<ProtonsLeft; y++){

                            try{
                                Thread.sleep(300,20);
                            }
                            catch (InterruptedException e){
                                e.getMessage();
                            }

                            System.out.print("P ");
                        }

                        IncreasePower(power);
                        FinalStage = true;
                        LastPrinted = "P";
                        System.out.println();
                    }
                    else{

                        PrintSpace(AvailLeftSpacePro);
                        //LeftSpaceCountPro++;
                        ReduceLeftSpacePro();

                        for(int y=0; y<ProtonsToPrint; y++){

                            try{
                                Thread.sleep(300,20);
                            }
                            catch (InterruptedException e){
                                e.getMessage();
                            }

                            System.out.print("P ");
                        }

                        ReduceProtons();
                        power++;
                        IncreasePower(power);
                        LastPrinted = "P";
                        System.out.println();
                    }
                }
                else if (LastPrinted.equals("P")){

                    if (FinalStage){

                        PrintSpace(AvailLeftSpaceNeu);
                        //LeftSpaceCountNeu++;
                        ReduceLeftSpaceNeu();

                        for(int y=0; y<(ProtonsLeft*2); y++){

                            try{
                                Thread.sleep(300,20);
                            }
                            catch (InterruptedException e){
                                e.getMessage();
                            }

                            System.out.print("N ");
                        }
                        ProtonsLeft = 0;
                        LastPrinted = "N";
                        System.out.println();
                    }
                    else{

                        PrintSpace(AvailLeftSpaceNeu);
                        //LeftSpaceCountNeu++;
                        ReduceLeftSpaceNeu();

                        for(int y=0; y<NeutronsToprint; y++){

                            try{
                                Thread.sleep(300,20);
                            }
                            catch (InterruptedException e){
                                e.getMessage();
                            }

                            System.out.print("N ");
                        }
                        LastPrinted = "N";
                        System.out.println();
                    }
                }
                else {
                    System.out.println("Incorrect number of protons");
                }
            }

        }


    }

    public static void IncreasePower(int pow){
        ProtonsToPrint = (int)Math.pow(2,pow);
        NeutronsToprint = (int)Math.pow(2,pow);
        System.out.println();
    }

    public static void ReduceProtons(){
        ProtonsLeft = ProtonsLeft - ProtonsToPrint;
    }

    public static void PrintSpace(int Spaces){
        for (int i=0; i<Spaces; i++){
            System.out.print(" ");
        }
    }

    public static void InitSpace(int pros){
        int count = 0;
        Integer NewPro = pros;

        while(!IsPowerOfTwo(NewPro)){
            NewPro++;
        }

        while (NewPro != (int)Math.pow(2,count)){
            count++;
        }

        PossibleRows = count;
        PossibleLastRowElements = count-1;
    }

    static boolean IsPowerOfTwo(int n) {
        if(n==0){
            return false;
        }
        else{
            return (int)(Math.ceil((Math.log(n) / Math.log(2)))) == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
        }

    }

    static void ReduceLeftSpacePro(){
        int SpaceToReduce = (int)Math.pow(2,LeftSpaceCountPro);
        AvailLeftSpacePro = AvailLeftSpacePro - SpaceToReduce;
        LeftSpaceCountPro++;

    }

    static void ReduceLeftSpaceNeu(){
        int SpaceToReduce = (int)Math.pow(2,LeftSpaceCountNeu);
        AvailLeftSpaceNeu = AvailLeftSpaceNeu - SpaceToReduce;
        LeftSpaceCountNeu++;
    }
}
