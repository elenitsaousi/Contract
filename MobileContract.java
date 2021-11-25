/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it219104;

/**
 *
 * @author eleni
 */
public class MobileContract extends Contract {
    int freeGb; //free GB per month, 0 for no gigabytes
    int freeSms; //free SMS per month, 0 for no SMS

    public MobileContract(int aa, long phone, int type, int startYear, int startMonth, int startDay, int duration, int freeMinutes, int h, int payment, int userAa, int freeGb, int freeSms, double monthlyCost) {
        //class constructor
        this.aa = aa;
        this.phone = phone;
        this.type = type;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.duration = duration;
        this.freeMinutes = freeMinutes;
        if (h == 0) {
            this.isOnline = false;
        }
        else this.isOnline = true;
        this.payment = payment;
        this.userAa =userAa;
        this.freeGb = freeGb;
        this.freeSms =freeSms;
        this.monthlyCost = monthlyCost;
    }
}

