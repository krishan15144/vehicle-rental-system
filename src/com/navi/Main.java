package com.navi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static VehicleRentalCompany rentalCompany = VehicleRentalCompany.getInstance();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the file name:");
        String fileName = sc.nextLine();
        try (FileInputStream fis = new FileInputStream(fileName);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                run(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error while parsing commands from file");
        }
    }

    static void run(final String input) {
        try {
            String[] args = input.split("\\s+");

            if(args[0].equals("ADD_BRANCH")){
                System.out.println(rentalCompany.addBranch(args[1], Arrays.asList(args[2].split(","))));
            }
            else if(args[0].equals("ADD_VEHICLE")){
                System.out.println(rentalCompany.addVehicle(args[1],args[2],args[3],Integer.parseInt(args[4])));
            }
            else if(args[0].equals("BOOK")){
                System.out.println(rentalCompany.bookVehicle(args[1],args[2],Integer.parseInt(args[3]),Integer.parseInt(args[4])));
            }
            else if(args[0].equals("DISPLAY_VEHICLES")){
                final List<String> vehicles = rentalCompany.listAvailableVehicles(args[1],Integer.parseInt(args[2])
                    ,Integer.parseInt(args[3]),AvailabilityType.AVAILABILITY_BY_PRICE);
                for (final String v : vehicles) {
                    System.out.print(v + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Input format is incorrect");
        }
    }
}
