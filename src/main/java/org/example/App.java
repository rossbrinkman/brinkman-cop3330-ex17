package org.example;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
 *  UCF COP3330 Fall 2021 Assignment 1 Solution
 *  Copyright 2021 Ross Brinkman
 */

public class App 
{
    public static void main( String[] args )
    {
        DecimalFormat f = new DecimalFormat("#0.00####");
        Scanner scanner = new Scanner(System.in);
        int maleOrFemale;
        float ouncesAlcohol = 0, weight=0, hoursSinceLastDrink=0, alcoholDistributionRatio = 0, bloodAlcoholContent;
        String outputText;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    outputText = "Enter a 1 if you are male or a 2 if you are female: ";
                    maleOrFemale = Integer.parseInt(CheckIfValidInput(scanner, outputText));
                    if(maleOrFemale == 1)
                        alcoholDistributionRatio = 0.73f;
                    else if (maleOrFemale == 2)
                        alcoholDistributionRatio = 0.66f;
                    else
                    {
                        System.out.println( "Invalid input: Must be 1 or 2" );
                        return;
                    }
                    break;
                case 1:
                    outputText = "How many ounces of alcohol did you have? ";
                    ouncesAlcohol = Float.parseFloat(CheckIfValidInput(scanner, outputText));
                    break;
                case 2:
                    outputText = "What is your weight in pounds? ";
                    weight = Float.parseFloat(CheckIfValidInput(scanner, outputText));
                    break;
                case 3:
                    outputText = "How many hours has it been since your last drink? ";
                    hoursSinceLastDrink = Float.parseFloat(CheckIfValidInput(scanner, outputText));
                    break;
            }
        }

        bloodAlcoholContent = (ouncesAlcohol * 5.14f / weight * alcoholDistributionRatio) - .015f * hoursSinceLastDrink;

        System.out.println( "Your BAC is " + f.format(bloodAlcoholContent));

        String endString = bloodAlcoholContent >= .08f ? "It is not legal for you to drive" : "It is legal for you to drive";

        System.out.println(endString);

        scanner.close();
    }

    public static String CheckIfValidInput(Scanner s, String output)
    {
        while(true) {
            System.out.println(output);
            String currentInput = s.nextLine();
            try {
                Float.parseFloat(currentInput);
            } catch (NumberFormatException nfe) {
                System.out.println("Input must be numeric");
                continue;
            }
            return currentInput;
        }
    }
}
