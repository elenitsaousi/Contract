package it219104;

public class Contract {
    int aa; //contract incremental number
    long phone; //phone number the contract is assigned to
    int type; //0 = mobile, 1 = home
    int startYear; //year of the contract start
    int startMonth; //month of the contract start
    int startDay; //day of the contract start
    int duration; //12 or 24 months
    int freeMinutes; //free minutes
    double monthlyCost; //in euros
    boolean isOnline; //if the account is online, no paper bill received
    int payment; //0 = credit card, 1 = cash, 2 = bank payment
    int userAa; //the incremental number of the user the contract belongs
}