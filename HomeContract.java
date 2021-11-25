/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it219104;

public class HomeContract extends Contract {
    int speed; //speed of the line in mbps, 0 for no connection
    int lineType; //0 for no connection, 1 for ADSL, 2 for VDSL

    public HomeContract(int aa, long phone, int type, int startYear, int startMonth, int startDay, int duration, int freeMinutes, int h, int payment, int userAa, int speed, int lineType, double monthlyCost) {
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
        this.speed = speed;
        this.lineType =lineType;
        this.monthlyCost = monthlyCost;
    }
}

