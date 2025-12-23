/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.w2044381_hamzahassan_planeseatmanagement;

/**
 *
 * Hamza_Hassan_Plane_Management_System
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class W2044381_HamzaHassan_PlaneSeatManagement {
    private static final int ROWS = 4;
    private static final int[] COLUMNS = {14, 12, 12, 14};
    private static final char[][] seats = new char[ROWS][]; // Here is where i define the 2D array to store seating positions

    public static void main(String[] args) {
        // The Welcome message
        System.out.println("*******************************************************");
        System.out.println("** Welcome to the Plane Seats Management Application **");
        System.out.println("*******************************************************");
        
        initializeSeats(); // Initialising seat status array
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            // Displaying menu bar and options for user
            System.out.println("=================================");
            System.out.println("============MENU=================");
            System.out.println("=================================");
            System.out.println("1) Buy a seat *");
            System.out.println("2) Cancel a seat *");
            System.out.println("3) Find the first available seat *");
            System.out.println("4) Show seating plan *");
            System.out.println("5) Print tickets information and total sales *");
            System.out.println("6) Search ticket *");
            System.out.println("0) Quit *");
            System.out.println("**********************************************");
            choice = getIntInput(scanner, "Please enter the choice of option you require: ");
            
            //Here its checking and making sure the user enters only options 0 to 6 anythhing else is wronly
            if (choice < 0 || choice > 6) {
                System.out.println("This is not a choice available. Please enter a number between 0 and 6.");
                continue; // Continue
            }

            // Performing a task based on user's choice
            switch (choice) {
                case 1:
                    buySeat(scanner); // Buy a seat
                    break;
                case 2:
                    cancelSeat(scanner); // Cancel a seat
                    break;
                case 3:
                    find_first_available(); // Find the first available seat
                    break;
                case 4:
                    showSeatingPlan(); // Show the seating plan
                    break;
                case 0:
                    System.out.println("Exiting program..."); // Quit Exiting the program
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // Initialise the seat status array
    private static void initializeSeats() {
        for (int i = 0; i < ROWS; i++) {
            seats[i] = new char[COLUMNS[i]];
            for (int j = 0; j < COLUMNS[i]; j++) {
                seats[i][j] = 'O'; // Set all seats to available initially
            }
        }
    }

    // Method to buy a seat
    private static void buySeat(Scanner scanner) {
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = Character.toUpperCase(scanner.next().charAt(0));
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter. Please enter a letter between A and D.");
            return;
        
}
        int seatNumber = getIntInput(scanner, "Enter seat number (1-" + (rowLetter == 'B' || rowLetter == 'C' ? 12 : 14) + "): ");
        if (seatNumber < 1 || (rowLetter == 'B' || rowLetter == 'C' ? seatNumber > 12 : seatNumber > 14)) {
            System.out.println("Invalid seat number. Please enter a number within the valid range.");
            return;
    }

        int row = rowLetter - 'A';
        int column = seatNumber - 1;

        if (isValidSeat(row, column) && seats[row][column] == 'O') {
            char seatPrice;
            int price;
            if (column >= 0 && column <= 4) {
                seatPrice = 'A'; 
                price = 200;
            } else if (column >= 5 && column <= 8) {
                seatPrice = 'B'; 
                price = 150;
            } else {
                seatPrice = 'C'; 
                price = 100; 
            }

            seats[row][column] = 'X'; // Mark the seat as sold (X)
            System.out.println("You have bought Seat " + rowLetter + seatNumber  + " for £" + price);
        } else {
            System.out.println("Invalid seat or seat already sold.");
        }
    }

    // Method for cancelling a seat
    private static void cancelSeat(Scanner scanner) {
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = Character.toUpperCase(scanner.next().charAt(0));
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter. Please enter a letter between A and D.");  //To avoid a user putting in an invalid row letter.
            return;
    }
        int seatNumber = getIntInput(scanner, "Enter seat number (1-" + (rowLetter == 'B' || rowLetter == 'C' ? 12 : 14) + "): ");
        if (seatNumber < 1 || (rowLetter == 'B' || rowLetter == 'C' ? seatNumber > 12 : seatNumber > 14)) {
            System.out.println("Invalid seat number. Please enter a number within the valid range."); //To avoid the user entering an invalid seat number.
            return;
    }
        int row = rowLetter - 'A';
        int column = seatNumber - 1;

        if (isValidSeat(row, column) && seats[row][column] == 'X') {
            seats[row][column] = 'O'; // Mark the seat as available (O)
            System.out.println("Seat " + rowLetter + seatNumber + " has been canceled and made available.");
        } else {
            System.out.println("This seat hasn't been sold so it cannot be cancelled ");
        }
    }

    // Method to find the first available seat
    private static void find_first_available() {
        boolean found = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS[i]; j++) {
                if (seats[i][j] == 'O') {
                    System.out.println("First available seat: " + (char)('A' + i) + (j + 1));
                    found = true;
                    break;
                }
            }
            if (found) break; // If the seat is found in current row, break out of loop
        }

        if (!found) {
            System.out.println("No available seats.");
        }
    }

    // Method to show seating arrangements
    private static void showSeatingPlan() {
        System.out.println("Seating Arrangements:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS[i]; j++) {
                System.out.print(seats[i][j]); // Displaying seat status
            }
            System.out.println();
        }
    }

    // Method to check if seating input is correct and valid
    private static boolean isValidSeat(int row, int column) {
        if (row < 0 || row >= ROWS)
            return false;
        return column >= 0 && column < COLUMNS[row];
    }

    // Method to check the input is correct when the user inputs a value
    private static int getIntInput(Scanner scanner, String message) {
        int input = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print(message);
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consuming the invalid input
            }
        } while (!validInput);
        return input;
    }
}
