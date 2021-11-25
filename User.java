/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it219104;


import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class User {
    int aa; //user incremental number
    int afm; //the afm of the user
    String address; //the address and address number of the user
    String idNumber; //the personal id number of the user
    int role; //0 for civilian, 1 for student, 2 for professional
    String email; //the email of the user
    int discount; //the total discount for the user
    
    HashMap<Long, LinkedList<Integer>> phoneContracts = new HashMap<>(); //list of contracts belonging to a specific phone number

    HashMap<Integer, HomeContract> userHomeContracts = new HashMap<>(); //home contracts belonging to the user, keyed on their incremental number
    HashMap<Integer, MobileContract> userMobileContracts = new HashMap<>(); //mobile contracts belonging to the user, keyed on their incremental number

    public User(int aa, int afm, String address, String idNumber, int role, String email) {
        //class constructor. all fields correspond to the user fields.
        this.aa = aa;
        this.afm = afm;
        this.address = address;
        this.idNumber = idNumber;
        this.role = role;
        this.email = email;
        this.discount = 0;
    }

    public boolean insertContract(int aa) {
        System.out.println("Please insert your new contract details.");
        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Please insert the phone number the contract is about: ");
        long phone;
        boolean mustBeHome; //boolean variable that is true if the phone corresponds to a home number
        while (true) {
            try {
                phone = Long.parseLong(scanner1.nextLine());
                if (phone >= 2000000000L && phone <= 2999999999L) {
                    //if the phone starts with 2, it is a home contract
                    mustBeHome = true;
                }
                else if (phone >= 6900000000L && phone <= 6999999999L) {
                    //if the phone starts with 69, it is a mobile contract
                    mustBeHome = false;
                }
                else throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("You have inserted an invalid home or mobile phone number. It must be 10 digits and start with 2 or 69. Try again.");
                continue;
            }
            break;
        }

        int type;
        if (mustBeHome) type = 0;
        else type = 1;
        
        int duration;
        System.out.print("Please enter your desired contact duration in months, 12 or 24: ");
        while (true) {
            try {
                duration = Integer.parseInt(scanner1.nextLine()); //convert a string to integer
                if (duration != 12 && duration != 24) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a valid duration.");
                continue;
            }
            break;
        }
        int startYear;
        System.out.print("Please insert your starting year. Values allowed between 2000 and 2099: ");
        while (true) {
            try {
                startYear = Integer.parseInt(scanner1.nextLine());
                if (startYear < 2000 || startYear > 2099) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a year between 2000 and 2099, inclusive.");
                continue;
            }
            break;
        }
        int startMonth;
        System.out.print("Please insert your starting month: ");
        while (true) {
            try {
                startMonth = Integer.parseInt(scanner1.nextLine());
                if (startMonth < 1 || startMonth > 12) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a valid month.");
                continue;
            }
            break;
        }
        int startDay;
        System.out.print("Please insert your starting day: ");
        while (true) {
            try {
                startDay = Integer.parseInt(scanner1.nextLine());
                if (startDay < 1 || startDay > 31) throw new ArithmeticException();
                if (startMonth == 2 && startYear%4 == 0 && startDay > 29) throw new ArithmeticException();
                else if (startMonth == 2 && startYear%4 != 0 && startDay > 28) throw new ArithmeticException();
                else if ((startMonth == 4 || startMonth == 6 || startMonth == 9 || startMonth == 11) && startDay == 31) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a valid day, based on the year and the month you entered.");
                continue;
            }
            break;
        }


        //date compatibility check

        int smallest; //find out which contract of the compared ones is before the other
        boolean problemFound = false; //if a problem with the dates is found, then the contract cannot be created. this flag will take this information to the program
        LinkedList<Integer> l = phoneContracts.get(phone);
        if (l != null) {
            for (Iterator it = l.iterator(); it.hasNext();) {
                //iterate over all contracts of the phone, to find overlapping contracts.
                Integer contractAa = (Integer) it.next();
                Contract contract;
                if (type == 0) {
                    contract = userHomeContracts.get(contractAa);
                }
                else contract = userMobileContracts.get(contractAa);
                //we do NOT allow two contracts to be active even on the same day.
                if (startYear < contract.startYear || (startYear == contract.startYear && startMonth < contract.startMonth) ||
                (startYear == contract.startYear && startMonth == contract.startMonth && startDay < contract.startDay)) {
                    //we find which contract is before the other.
                    smallest = 1;
                }
                else smallest = 2;
                if (smallest == 1) {
                    //for the contract that is before the other, we make sure that when its duration ends, the other contract will not have started.
                    int plusYears = 1;
                    if (duration == 24) plusYears = 2;
                    if (startYear+plusYears > contract.startYear || (startYear+plusYears == contract.startYear && startMonth > contract.startMonth) ||
                    (startYear+plusYears == contract.startYear && startMonth == contract.startMonth && startDay >= contract.startDay)) {
                        problemFound = true;
                        break;
                    }
                }
                else {
                    int plusYears = 1;
                    if (contract.duration == 24) plusYears = 2;
                    if (contract.startYear+plusYears > startYear || (contract.startYear+plusYears == startYear && contract.startMonth > startMonth) ||
                    (contract.startYear+plusYears == startYear && contract.startMonth == startMonth && contract.startDay >= startDay)) {
                        problemFound = true;
                        break;
                    }
                }
            }
        }
        if (problemFound) {
            //if there is an overlap, then return false and force the user to start again the procedure.
            return false;
        }

        int freeMinutes;
        System.out.print("Please insert the number of your desired free minutes. The number must be between 200 and 8000: ");
        while (true) {
            try {
                freeMinutes = Integer.parseInt(scanner1.nextLine());
                if (freeMinutes < 200 || freeMinutes > 8000) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            break;
        }

        int isOnline;
        System.out.print("Please enter 1 if you desire the contract to be online. Enter 0 otherwise: ");
        while(true) {
            try {
                isOnline = Integer.parseInt(scanner1.nextLine());
                if (isOnline != 0 && isOnline != 1) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            break;
        }

        int payment;
        System.out.print("Please enter your desired way of payment. 0 for credit card, 1 for cash, 2 for bank payment: ");
        while(true) {
            try {
                payment = Integer.parseInt(scanner1.nextLine());
                if (payment != 0 && payment != 1 && payment != 2) throw new ArithmeticException(); //check for correct input
            }
            catch(Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            break;
        }

        if (type == 0) {
            //HomeContract

            int speed;
            System.out.println("Please insert your desired internet speed, in Mbps. Allowed values are 24 (ADSL), 50 (VDSL) and 100 (VDSL).");
            System.out.print("If you do not want internet in your contract, please enter 0: ");
            while (true) {
                try {
                    speed = Integer.parseInt(scanner1.nextLine());
                    if (speed != 0 && speed != 24 && speed != 50 && speed != 100) throw new ArithmeticException(); //check for correct input
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }
                break;
            }

            int linetype;
            if (speed == 0) linetype = 0;
            else if (speed == 24) linetype = 1;
            else linetype = 2;

            //we will now calculate the monthly cost.
            double monthlyCost = 0;
            monthlyCost += 0.02*((double)freeMinutes); //0.02 euros per minute
            if (speed == 24) monthlyCost += 15; //15 euros for ADSL, 25 for VDSL(50) and 35 for VDSL(100)
            else if (speed == 50) monthlyCost += 25;
            else if (speed == 100) monthlyCost += 35;

            //update the hashmaps with the newly created contract
            HomeContract ccc = new HomeContract(aa,phone,type,startYear,startMonth,startDay,duration,freeMinutes,isOnline,payment,this.aa,speed,linetype,monthlyCost);
            if (phoneContracts.get(phone) == null) {
                LinkedList<Integer> li2 = new LinkedList<>();
                phoneContracts.put(phone,li2); //if the phone did not exist, create a new list with contracts for that number
            }
            phoneContracts.get(phone).push(aa); //create a contract for the number on the list
            userHomeContracts.put(aa,ccc); //put the contract in the list of mobile contracts for the user

        }
        else {
            //MobileContract

            int freeGb;
            System.out.println("Please insert your desired free GB per month, with a minimum of 2 and a maximum of 100. The number must be an integer.");
            System.out.print("If you do not want free GB in your contract, please enter 0: ");
            while (true) {
                try {
                    freeGb = Integer.parseInt(scanner1.nextLine());
                    if (freeGb != 0 && (freeGb < 2 || freeGb > 100)) throw new ArithmeticException(); //check for correct input
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }
                break;
            }

            int freeSms;
            System.out.println("Please insert your desired free SMS per month, with a minimum of 100 and a maximum of 5000.");
            System.out.print("If you do not want free SMS in your contract, please enter 0: ");
            while (true) {
                try {
                    freeSms = Integer.parseInt(scanner1.nextLine());
                    if (freeSms != 0 && (freeSms < 100 || freeSms > 5000)) {
                        throw new ArithmeticException(); //check for correct input
                    }
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }
                break;
            }

            //we will now calculate the monthly cost.
            double monthlyCost = 0;
            monthlyCost += 0.02*((double)freeMinutes); //0.02 euros per minute
            if (freeGb != 0) {
                monthlyCost += 3*freeGb; //3 euros per GB
            }
            if (freeSms != 0) {
                monthlyCost += 0.01*freeSms; //0.01 euros per SMS
            }

            //update the hashmaps with the newly created contract
            MobileContract ccc = new MobileContract(aa,phone,type,startYear,startMonth,startDay,duration,freeMinutes,isOnline,payment,this.aa,freeGb,freeSms,monthlyCost);
            if (phoneContracts.get(phone) == null) {
                LinkedList<Integer> li2 = new LinkedList<>();
                phoneContracts.put(phone,li2); //if the phone did not exist, create a new list with contracts for that number
            }
            phoneContracts.get(phone).push(aa); //create a contract for the number on the list
            userMobileContracts.put(aa,ccc); //put the contract in the list of mobile contracts for the user
        }

        return true;
    }
}
