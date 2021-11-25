package it219104;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class It219104 {

    public static void main(String[] args) {
        HashMap<Integer, Boolean> existsAfm = new HashMap<>(); //hashmap to check if an AFM already exists, so that no duplicates are made
        HashMap<String, Boolean> existsId = new HashMap<>(); //hashmap to check if an ID number already exists, so that no duplicates are made

        HashMap<Integer, User> users = new HashMap<>(); //users of the service, keyed on their afm
        Scanner scanner = new Scanner(System.in);

        int user_aa = 30000003; //starting aa for users to be enrolled
        int contract_aa = 50000003; //starting aa for contracts to be enrolled
        //the incremental numbers of users and contracts will never be the same, but we do not care about this. they are different so that the aa is easily recognizable when
        //it belongs to a user or a contract.

        User u1 = new User(30000001,555555555,"Athinon 8","AC392124",2,"george22@gmail.com");
        User u2 = new User(30000002,777777777,"Thessalonikis 16","AD858222",1,"marios_pap_2934@gmail.com");
        users.put(555555555,u1);
        users.put(777777777,u2);
        existsAfm.put(555555555,true);
        existsAfm.put(777777777,true);
        existsId.put("AC392124",true);
        existsId.put("AD858222",true);
        LinkedList<Integer> lii1 = new LinkedList<>();
        LinkedList<Integer> lii2 = new LinkedList<>();
        u1.phoneContracts.put(6955555555L,lii1);
        u2.phoneContracts.put(2197777777L,lii2);
        MobileContract c1 = new MobileContract(50000001,6955555555L,1,2019,7,8,24,500,1,0,30000001,3,0,19);
        u1.phoneContracts.get(6955555555L).push(50000001);
        u1.userMobileContracts.put(50000001,c1);
        HomeContract c2 = new HomeContract(50000002,2197777777L,1,2020,7,8,12,500,0,1,30000002,0,0,10);
        u2.phoneContracts.get(2197777777L).push(50000002);
        u2.userHomeContracts.put(50000002,c2);

        while (true) {
            System.out.println("Καλως ηρθες!");
            System.out.println("Πατα 1 για να δημιουργησεις προφιλ");
            System.out.println("Πατα 2 για να δημιουργησεις συμβολαιο");
            System.out.println("Πατα 2 για να διαγραψεις ενα υπαρχον συμβολαιο");
            System.out.println("Πατα 3 για να δεις πληροφοριες για το ενεργο συμβολαιο σου και στατιστικα");
            System.out.println("Πατα 4 για να τερματισεις το συστημα");
            System.out.print("Επελεξε: ");
            int choice0;
             while (true) {
                try {
                    choice0 = Integer.parseInt(scanner.nextLine());
                    if (choice0 != 0 && choice0 != 1 && choice0 != 2 && choice0!=3 && choice0!=4 ) throw new ArithmeticException(); //check for correct input
                }
                catch(Exception e) {
                    System.out.println("Βαλε εναν εγκυρο αριθμο");
                    continue;
                }
                break;
            }

           
            if (choice0 == 4) {
                break;
            }

            User currentUser = new User(0,0,"aa","aa",0,"aa");

            if (choice0 >= 1 && choice0 <= 4) {
                System.out.print("Βαλε τα ΑΦΜ σου: ");
                int afm;
                while (true) {
                    try {
                        afm = Integer.parseInt(scanner.nextLine());
                        if (afm < 100000000 || afm > 999999999) {
                            System.out.println("Το ΑΦΜ πρεπει να εχει 9 ψηφια και να ξεκιναει απο 1-9. Προσπαθησε ξανα.");
                            continue;
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Το ΑΦΜ πρεπει να εχει 9 ψηφια και να ξεκιναει απο 1-9. Προσπαθησε ξανα.");
                        continue;
                    }
                    break;
                }

                if (existsAfm.get(afm) != null) {
                    System.out.println("Καλως ηρθες!");
                    currentUser = users.get(afm);
                }
                else {
                    System.out.println("Δεν υπαρχει χρηστης με αυτο το ΑΦΜ. Δημιουργησε ενα προφιλ.");
                    continue;
                }
            }
            else {
                System.out.println("Βαλε το ΑΦΜ σου: ");
                int afm;
                while (true) {
                    try {
                        afm = Integer.parseInt(scanner.nextLine());
                        if (afm < 100000000 || afm > 999999999) {
                            System.out.println("AFM must be 9 digits and start with 1-9. Try again.");
                            continue;
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Το ΑΦΜ πρεπει να εχει 9 ψηφια και να ξεκιναει απο 1-9. Προσπαθησε ξανα.");
                        continue;
                    }
                    break;
                }
                if (existsAfm.get(afm) != null) {
                    System.out.println("Υπαρχει ηδη χρηστης με αυτο το ΑΦΜ.");
                    continue;
                }
                System.out.print("Βαλε την διευθυνση σου: ");
                String address = scanner.nextLine();
                System.out.print("Βαλε το ID σου: ");
                String id = scanner.nextLine();
                while (existsId.get(id) != null) {
                    System.out.print("Αυτος ο αριθμος υπαρχει ηδη με διαφορετικο ΑΦΜ. Βαλε το ID σου: ");
                    id = scanner.nextLine();
                }
                /*String firstTwoChars= id.substring(0,2);  //gets the first two characters
                String lastSixChars= id.substring(id.length() - 6); //gets the six last characters */
               // String thirdChar=id.substring(3); //gets the third character 
                Pattern p1=Pattern.compile("[A-Z]{2}"); 
                Pattern p2=Pattern.compile("[0-9]{6}");
                Pattern p3=Pattern.compile(" "); 
                Matcher m1=p1.matcher(id); //compares the first two chars with p1
                Matcher m2=p2.matcher(id);  //compares the last six chars with p2
                Matcher m3=p3.matcher(id); //compares the third char with p3
                while (!m1.find() || !m2.find() || !m3.find() || id.length() !=9) {
                    System.out.println("Το ID πρεπει να περιεχει πρωτα 2 γραμματα και μετα 6 ψηφια, π.χ.: AB 123456");
                    id = scanner.nextLine();
                    m1= p1.matcher(id);
                    m2= p2.matcher(id);
                    m3= p3.matcher(id);
                }
                System.out.println("Επελεξε: 1 αν εισαι φοιτητς, 2 iαν εισαι επαγγελματιας, ή 0 αν εισαι ιδιωτης: ");
                int role;
                while (true) {
                    try {
                        role = Integer.parseInt(scanner.nextLine());
                        if (role != 0 && role != 1 && role != 2) throw new ArithmeticException(); //check for correct input
                    }
                    catch(Exception e) {
                        System.out.println("Please enter a valid number.");
                        continue;
                    }
                    break;
                }
                String email;
                System.out.print("Βαλε την ηλεκτρονικη σου διευθυνση: ");
                Pattern p=Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]");
                email = scanner.nextLine();
                Matcher m= p.matcher(email);
                while (email.isEmpty()) {
                      System.out.print("Βαλε την ηλεκτρονικη σου διευθυνση: ");
                      email = scanner.nextLine();
                }
                while ( !m.find()) {
                    System.out.println("Βαλε ξανα την ηλεκτρονικη σου διευθυνση, π.χ.: el123@sdg.as1");
                    email = scanner.nextLine();
                    m= p.matcher(email);
                }
                currentUser = new User(user_aa, afm, address, id, role, email);
                user_aa++;
                existsAfm.put(afm,true);
                existsId.put(id,true);
                users.put(afm,currentUser);
                System.out.println("Το προφιλ σου δημιουργηθηκε!");
                continue;
            }
            
            int choice=-1;
            if (choice0 == 1) choice = 0;
            else if (choice0 == 2) choice = 1;
            else if (choice0 == 3 || choice0 == 4) choice = 2;

            if (choice == 0) {
                System.out.println();
                if (currentUser.insertContract(contract_aa)) {
                    System.out.println("Το συμβολαιο σου δημιουργηθηκε!");
                    System.out.println();
                    System.out.println("Αυτες ειναι οι πληροφοριες του συμβολαιου σου:");
                    HomeContract h = currentUser.userHomeContracts.get(contract_aa);
                    MobileContract m = currentUser.userMobileContracts.get(contract_aa);
                    if (m == null) {
                        System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                        System.out.println(h.aa);
                        System.out.print("Το τηλεφωνο σου: ");
                        System.out.println(h.phone);
                        System.out.println("Τυπος συμβολαιου: σταθερο συμβολαιο");
                        int plusYears;
                        if (h.duration == 12) plusYears = 1;
                        else plusYears = 2;
                        System.out.print("Το συμβολαιο ειναι ενεργο απο: ");
                        System.out.print(h.startDay);
                        System.out.print("-");
                        System.out.print(h.startMonth);
                        System.out.print("-");
                        System.out.print(h.startYear);
                        System.out.print(" to ");
                        System.out.print(h.startDay);
                        System.out.print("-");
                        System.out.print(h.startMonth);
                        System.out.print("-");
                        System.out.println(h.startYear+plusYears);
                        if (h.isOnline) {
                            System.out.println("Ο λογαριασμος σου ειναι ηλεκτρονικος.");
                        }
                        else System.out.println("Ο λογαριασμος σου ειναι ηλεκτρονικος.");
                        System.out.print("Δωρεαν λεπτα ομιλιας τον μηνα: ");
                        System.out.println(h.freeMinutes);
                        if (h.speed == 0) {
                            System.out.println("Δεν περιεχει συνδεση στο ιντερνετ.");
                        }
                        else if (h.speed == 24) {
                            System.out.println("ADSL 24Mbps συνδεση.");
                        }
                        else if (h.speed == 50) {
                            System.out.println("VDSL 50Mbps συνδεση.");
                        }
                        else System.out.println("VDSL 100Mbps συνδεση.");
                        System.out.print("Συνολικο κοστος (σε ευρω): ");
                        System.out.println(h.monthlyCost);
                    }
                    else {
                        System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                        System.out.println(m.aa);
                        System.out.print("Το τηλεφωνο σου: ");
                        System.out.println(m.phone);
                        System.out.println("Τυπος συμβολαιου: Κινητο");
                        int plusYears;
                        if (m.duration == 12) plusYears = 1;
                        else plusYears = 2;
                        System.out.print("Το συμβολαιο ειναι ενεργο απο: ");
                        System.out.print(m.startDay);
                        System.out.print("-");
                        System.out.print(m.startMonth);
                        System.out.print("-");
                        System.out.print(m.startYear);
                        System.out.print(" to ");
                        System.out.print(m.startDay);
                        System.out.print("-");
                        System.out.print(m.startMonth);
                        System.out.print("-");
                        System.out.println(m.startYear+plusYears);
                        if (m.isOnline) {
                            System.out.println("Ο λογαριασμος σου ειναι ηλεκτρονικος.");
                        }
                        else System.out.println("Ο λογαριασμος σου δεν ειναι ηλεκτρονικος.");
                        System.out.print("Δωρεαν λεπτα ομιλιας τον μηνα: ");
                        System.out.println(m.freeMinutes);
                        if (m.freeGb == 0) {
                            System.out.println("Δεν περιλαμβανονται Gb");
                        }
                        else {
                            System.out.print("Δωρεαν GB τον μηνα: ");
                            System.out.println(m.freeGb);
                        }
                        if (m.freeSms == 0) {
                            System.out.println("Δεν περιλαμβανονται sms.");
                        }
                        else {
                            System.out.print("Δωρεαν sms τον μηνα: ");
                            System.out.println(m.freeSms);
                        }
                        System.out.print("Συνολικο κοστος (σε ευρω): ");
                        System.out.println(m.monthlyCost);
                    }
                    
                    contract_aa++;
                }
                else {
                    System.out.println("Το συμβολαιο δεν μπορει να δημιουργηθει. Οι ημερομηνια ειναι ιδια με αλλο ενεργο συμβολαιο με τον ιδιο αριθμο.");
                }

                //calculate the total discount for the user. 7% basic discount per active contract, potentially having another discount with other rules.
                //the discount is cummulative. maximum discount can be 45%.
                int discount = 0;

                if (!currentUser.userHomeContracts.isEmpty() || !currentUser.userMobileContracts.isEmpty()) {
                    if (currentUser.role == 1) discount+=15;
                    else if (currentUser.role == 2) discount+=10;

                    LocalDate currentDate = LocalDate.now();

                    for (HomeContract hc : currentUser.userHomeContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((hc.startYear < currentYear || (hc.startYear == currentYear && hc.startMonth < currentMonth) ||
                        hc.startYear == currentYear && hc.startMonth == currentMonth && hc.startDay <= currentDay) &&
                        (hc.startYear+(hc.duration/12) > currentYear || (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth > currentMonth) ||
                        (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth == currentMonth && hc.startDay >= currentDay))) {
                            discount+=7;
                            if (hc.freeMinutes > 1000) discount+=8;
                            if (hc.payment == 0 || hc.payment == 2) discount+=5;
                            if (hc.isOnline) discount+=2;
                            if (discount > 45) break;
                        }
                        
                    }

                    for (MobileContract mc : currentUser.userMobileContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((mc.startYear < currentYear || (mc.startYear == currentYear && mc.startMonth < currentMonth) ||
                        mc.startYear == currentYear && mc.startMonth == currentMonth && mc.startDay <= currentDay) &&
                        (mc.startYear+(mc.duration/12) > currentYear || (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth > currentMonth) ||
                        (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth == currentMonth && mc.startDay >= currentDay))) {
                            discount+=7;
                            if (mc.freeMinutes > 1000) discount+=11;
                            if (mc.payment == 0 || mc.payment == 2) discount+=5;
                            if (mc.isOnline) discount+=2;
                            if (discount > 45) break;
                        }
                    }
                    if (discount > 45) discount = 45; //if cummulative discount is over 45%, it becomes 45%.
                }
                currentUser.discount = discount;

                if (discount == 0) {
                    System.out.println("Δεν εχεις εκτπωσει,γιατι δεν εχεις ενεργο συμβολαιο αυτη την περιοδο.");
                }
                else {
                    System.out.print("Η εκπτωση σου ειναι: ");
                    System.out.print(currentUser.discount);
                    System.out.println("%");
                }
                
                
            }
            else if (choice == 1) {
                //delete a contract
                if (currentUser.userHomeContracts.size() + currentUser.userMobileContracts.size() == 0) {
                    System.out.println("Δεν εχεις συμβολαια για να διαγραψεις!");
                    continue;
                }
                System.out.println();
                System.out.println("Τα διαθεσιμα συμβολαια σου ειναι: ");
                long deletePhone = 1; //since we need to delete the contract, we need to know the phone so that we delete it from phonecontracts as well
                for (HomeContract hc : currentUser.userHomeContracts.values()) {
                    System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                    System.out.print(hc.aa);
                    System.out.print(", Νουμερο: ");
                    System.out.print(hc.phone);
                    deletePhone = hc.phone;
                    System.out.print(", Τυπος: Σταθερο συμβολαιο, Ημερομηνια εναρξης: ");
                    System.out.print(hc.startDay);
                    System.out.print("-");
                    System.out.print(hc.startMonth);
                    System.out.print("-");
                    System.out.print(hc.startYear);
                    System.out.print(", Διαρκεια: ");
                    System.out.print(hc.duration);
                    System.out.println(" μηνες.");
                }
                for (MobileContract mc : currentUser.userMobileContracts.values()) {
                    System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                    System.out.print(mc.aa);
                    System.out.print(", Νουμερο: ");
                    System.out.print(mc.phone);
                    deletePhone = mc.phone;
                    System.out.print(", Τυπος: Κινητο συμβολαιο, Ημερομηνια εναρξης: ");
                    System.out.print(mc.startDay);
                    System.out.print("-");
                    System.out.print(mc.startMonth);
                    System.out.print("-");
                    System.out.print(mc.startYear);
                    System.out.print(", Διαρκεια: ");
                    System.out.print(mc.duration);
                    System.out.println(" μηνες.");
                }
                System.out.println();
                System.out.print("Βαλε τον μοναδικο αριθμο του συμβολαιου που επιθυμεις να διαγραψεις: ");
                int deleteAa;
                while (true) {
                    try {
                        deleteAa = Integer.parseInt(scanner.nextLine());
                        if (currentUser.userHomeContracts.get(deleteAa) == null && currentUser.userMobileContracts.get(deleteAa) == null) throw new ArithmeticException(); //check for correct input
                    }
                    catch(Exception e) {
                        System.out.println("Βαλε εναν εγκυρο μοναδικο κωδικο, απο την παραπανω λιστα.");
                        continue;
                    }
                    break;
                }
                if (currentUser.userHomeContracts.get(deleteAa) != null) {
                    currentUser.userHomeContracts.remove(Integer.valueOf(deleteAa));
                }
                else currentUser.userMobileContracts.remove(Integer.valueOf(deleteAa));
                currentUser.phoneContracts.get(deletePhone).remove(Integer.valueOf(deleteAa));

                //calculate the discount for the user again
                //calculate the total discount for the user. 7% basic discount per active contract, potentially having another discount with other rules.
                //the discount is cummulative. maximum discount can be 45%.
                int discount = 0;

                if (!currentUser.userHomeContracts.isEmpty() || !currentUser.userMobileContracts.isEmpty()) {
                    if (currentUser.role == 1) discount+=15;
                    else if (currentUser.role == 2) discount+=10;

                    LocalDate currentDate = LocalDate.now();

                    for (HomeContract hc : currentUser.userHomeContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((hc.startYear < currentYear || (hc.startYear == currentYear && hc.startMonth < currentMonth) ||
                        hc.startYear == currentYear && hc.startMonth == currentMonth && hc.startDay <= currentDay) &&
                        (hc.startYear+(hc.duration/12) > currentYear || (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth > currentMonth) ||
                        (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth == currentMonth && hc.startDay >= currentDay))) {
                            discount+=7;
                            if (hc.freeMinutes > 1000) discount+=8;
                            if (hc.payment == 0 || hc.payment == 2) discount+=5;
                            if (hc.isOnline) discount+=2;
                            if (discount > 45) break;
                        }
                        
                    }

                    for (MobileContract mc : currentUser.userMobileContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((mc.startYear < currentYear || (mc.startYear == currentYear && mc.startMonth < currentMonth) ||
                        mc.startYear == currentYear && mc.startMonth == currentMonth && mc.startDay <= currentDay) &&
                        (mc.startYear+(mc.duration/12) > currentYear || (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth > currentMonth) ||
                        (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth == currentMonth && mc.startDay >= currentDay))) {
                            discount+=7;
                            if (mc.freeMinutes > 1000) discount+=11;
                            if (mc.payment == 0 || mc.payment == 2) discount+=5;
                            if (mc.isOnline) discount+=2;
                            if (discount > 45) break;
                        }
                    }
                    if (discount > 45) discount = 45;
                }
                currentUser.discount = discount;
                System.out.println("Το συμβολαιο διαφραφτηκε!");
            }
            else {
                System.out.print("Πατα 0 για να δεις πληροφοριες για το ενεργο συμβολαιο σου, ή πατα 1 για να δεις στατιστικα: ");
                int opp;
                while (true) {
                    try {
                        opp = Integer.parseInt(scanner.nextLine());
                        if (opp != 0 && opp != 1) throw new ArithmeticException(); //check for correct input
                    }
                    catch(Exception e) {
                        System.out.println("Βαλε εναν εγκυρο αριθμο.");
                        continue;
                    }
                    break;
                }
                if (opp == 0) {
                    if (currentUser.userHomeContracts.size() + currentUser.userMobileContracts.size() == 0) {
                        System.out.println("Δεν εχεις ενεργα συμβολαια!");
                        continue;
                    }
                    LocalDate currentDate = LocalDate.now();
                    int activeContracts = 0;
                    for (HomeContract hc : currentUser.userHomeContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((hc.startYear < currentYear || (hc.startYear == currentYear && hc.startMonth < currentMonth) ||
                        hc.startYear == currentYear && hc.startMonth == currentMonth && hc.startDay <= currentDay) &&
                        (hc.startYear+(hc.duration/12) > currentYear || (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth > currentMonth) ||
                        (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth == currentMonth && hc.startDay >= currentDay))) {
                            activeContracts++;
                            break;
                        }
                    }
                    for (MobileContract mc : currentUser.userMobileContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((mc.startYear < currentYear || (mc.startYear == currentYear && mc.startMonth < currentMonth) ||
                        mc.startYear == currentYear && mc.startMonth == currentMonth && mc.startDay <= currentDay) &&
                        (mc.startYear+(mc.duration/12) > currentYear || (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth > currentMonth) ||
                        (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth == currentMonth && mc.startDay >= currentDay))) {
                            activeContracts++;
                            break;
                        }
                    }
                    if (activeContracts == 0) {
                        System.out.println("Δεν εχεις ενεργα συμβολαια!");
                        continue;
                    }
    
                    HashMap<Integer, Boolean> isActive = new HashMap<>();
                
                    System.out.println("YoΤα ενεργα συμβολαια σου ειναι: ");
                    for (HomeContract hc : currentUser.userHomeContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((hc.startYear < currentYear || (hc.startYear == currentYear && hc.startMonth < currentMonth) ||
                        hc.startYear == currentYear && hc.startMonth == currentMonth && hc.startDay <= currentDay) &&
                        (hc.startYear+(hc.duration/12) > currentYear || (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth > currentMonth) ||
                        (hc.startYear+(hc.duration/12) == currentYear && hc.startMonth == currentMonth && hc.startDay >= currentDay))) {
                            isActive.put(hc.aa,true);
                            System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                            System.out.print(hc.aa);
                            System.out.print(", Νουμερο: ");
                            System.out.print(hc.phone);
                            System.out.print(", Τυπος: Σταθερο συμβολαιο, Ημερομηνια εναρξης: ");
                            System.out.print(hc.startDay);
                            System.out.print("-");
                            System.out.print(hc.startMonth);
                            System.out.print("-");
                            System.out.print(hc.startYear);
                            System.out.print(", Διαρκεια: ");
                            System.out.print(hc.duration);
                            System.out.println(" μηνες.");
                        }
                    }
                    for (MobileContract mc : currentUser.userMobileContracts.values()) {
                        int currentDay = currentDate.getDayOfMonth();
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        //double check needs to be made. one that the starting date is before the current date, two that the ending date is after the current date.
                        if ((mc.startYear < currentYear || (mc.startYear == currentYear && mc.startMonth < currentMonth) ||
                        mc.startYear == currentYear && mc.startMonth == currentMonth && mc.startDay <= currentDay) &&
                        (mc.startYear+(mc.duration/12) > currentYear || (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth > currentMonth) ||
                        (mc.startYear+(mc.duration/12) == currentYear && mc.startMonth == currentMonth && mc.startDay >= currentDay))) {
                            isActive.put(mc.aa,true);
                            System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                            System.out.print(mc.aa);
                            System.out.print(", Νουμερο: ");
                            System.out.print(mc.phone);
                            System.out.print(", Τυπος: Κινητο συμβολαιο, Ημερομηνια εναρξης: ");
                            System.out.print(mc.startDay);
                            System.out.print("-");
                            System.out.print(mc.startMonth);
                            System.out.print("-");
                            System.out.print(mc.startYear);
                            System.out.print(", Διαρκεια: ");
                            System.out.print(mc.duration);
                            System.out.println(" μηνες.");
                        }
                    }
                    System.out.println();
                    System.out.print("Βαλε τον μοναδικο αριθμο του συμβολαιου του οποιου επιθυμεις να δεις τα στοιχεια του: ");
                    int viewAa;
                    while (true) {
                        try {
                            viewAa = Integer.parseInt(scanner.nextLine());
                            if (isActive.get(viewAa) == null) throw new ArithmeticException(); //check for correct input
                        }
                        catch(Exception e) {
                            System.out.println("Βαλε εναν εγκυρο μοναδικο αριθμο, απο την παραπανω λιστα.");
                            continue;
                        }
                        break;
                    }
                    System.out.println("Εδω ειναι οι πληροφοριες του ενεργου συμβολαιου σου:");
                    HomeContract h = currentUser.userHomeContracts.get(viewAa);
                    MobileContract m = currentUser.userMobileContracts.get(viewAa);
                    if (m == null) {
                        System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                        System.out.println(h.aa);
                        System.out.print("Νουμερο: ");
                        System.out.println(h.phone);
                        System.out.println("Τυπος: Σταθερο συμβολαιο");
                        int plusYears;
                        if (h.duration == 12) plusYears = 1;
                        else plusYears = 2;
                        System.out.print("Το συμβολαιο ειναι ενεργο απο: ");
                        System.out.print(h.startDay);
                        System.out.print("-");
                        System.out.print(h.startMonth);
                        System.out.print("-");
                        System.out.print(h.startYear);
                        System.out.print(" to ");
                        System.out.print(h.startDay);
                        System.out.print("-");
                        System.out.print(h.startMonth);
                        System.out.print("-");
                        System.out.println(h.startYear+plusYears);
                        if (h.isOnline) {
                            System.out.println("Ο λογαριασμος του συμβολαιου ειναι ηλεκτρονικος.");
                        }
                        else System.out.println("Ο λογαριασμος του συμβολαιου δεν ειναι ηλεκτρονικος.");
                        System.out.print("Δωρεαν λεπτα ομιλιας τον μηνα: ");
                        System.out.println(h.freeMinutes);
                        if (h.speed == 0) {
                            System.out.println("Δεν περιλαμβανεται συνδεση στο ιντερνετ.");
                        }
                        else if (h.speed == 24) {
                            System.out.println("ADSL 24Mbps συνδεση.");
                        }
                        else if (h.speed == 50) {
                            System.out.println("VDSL 50Mbps συνδεση.");
                        }
                        else System.out.println("VDSL 100Mbps συνδεση.");
                        System.out.print("Συνολικο κοστος (σε ευρω): ");
                        System.out.println(h.monthlyCost);
                    }
                    else {
                        System.out.print("Ο μοναδικος αριθμος του συμβολαιου σου: ");
                        System.out.println(m.aa);
                        System.out.print("Νουμερο: ");
                        System.out.println(m.phone);
                        System.out.println("Τυπος: Κινητο συμβολαιο");
                        int plusYears;
                        if (m.duration == 12) plusYears = 1;
                        else plusYears = 2;
                        System.out.print("Το συμβολαιο ειναι ενεργο απο ");
                        System.out.print(m.startDay);
                        System.out.print("-");
                        System.out.print(m.startMonth);
                        System.out.print("-");
                        System.out.print(m.startYear);
                        System.out.print(" to ");
                        System.out.print(m.startDay);
                        System.out.print("-");
                        System.out.print(m.startMonth);
                        System.out.print("-");
                        System.out.println(m.startYear+plusYears);
                        if (m.isOnline) {
                            System.out.println("Ο λογαριασμος ειναι ηλεκτρονικος.");
                        }
                        else System.out.println("Ο λογαριασμος ειναι ηλεκτρονικος.");
                        System.out.print("Δωρεαν λεπτα ομιλιας τον μηνα: ");
                        System.out.println(m.freeMinutes);
                        if (m.freeGb == 0) {
                            System.out.println("Δεν περιλαμβανονται δωρεαν Gb.");
                        }
                        else {
                            System.out.print("Δωρεαν GB τον μηνα: ");
                            System.out.println(m.freeGb);
                        }
                        if (m.freeSms == 0) {
                            System.out.println("Δεν περιλαμβαονται δωρεαν SMS.");
                        }
                        else {
                            System.out.print("Δωρεαν sms τον μηνα: ");
                            System.out.println(m.freeSms);
                        }
                        System.out.print("Συνολικο κοστος τον μηνα (σε ευρω): ");
                        System.out.println(m.monthlyCost);
                    }
                }
                else {
                    //view statistics
                    System.out.print("Αν επιθυμεις να δεις στατιστικα για σταθερο συμβολαιο, πατα 0. Για κινητο συμβολαιο, πατα 1: ");
                    int optn;
                    while (true) {
                        try {
                            optn = Integer.parseInt(scanner.nextLine());
                            if (optn != 0 && optn != 1 && optn != 2) throw new ArithmeticException(); //check for correct input
                        }
                        catch(Exception e) {
                            System.out.println("Βαλε εναν εγκυρο αριθμο.");
                            continue;
                        }
                        break;
                    }
                    if (optn == 0) {
                        System.out.println("Πατα 0 για στατιστικα σε δωρεαν λεπτα ομιλιας");
                        System.out.println("Πατα 1 για στατιστικα σχετικα με το internet");
                        System.out.println("Πατα 2 για στατιστικα στο μηνιαιο κοστος");
                        System.out.print("Pick your choice: ");
                        int optn2;
                        while (true) {
                            try {
                                optn2 = Integer.parseInt(scanner.nextLine());
                                if (optn2 != 0 && optn2 != 1 && optn2 != 2) throw new ArithmeticException(); //check for correct input
                            }
                            catch(Exception e) {
                                System.out.println("Βαλε εναν εγκυρο αριθμο.");
                                continue;
                            }
                            break;
                        }
                        if (optn2 == 0) {
                            int maxmins = 0;
                            int minmins = 9000;
                            int totalmins = 0;
                            int totalcontrs = 0;
                            for (User u: users.values()) {
                                for (HomeContract hc: u.userHomeContracts.values()) {
                                    totalcontrs++;
                                    totalmins+=hc.freeMinutes;
                                    if (hc.freeMinutes > maxmins) maxmins = hc.freeMinutes;
                                    if (hc.freeMinutes < minmins) minmins = hc.freeMinutes;
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print("Ελαχιστος αριθμος δωρεαν λεπτων ομιλιας: ");
                                System.out.println(minmins);
                                System.out.print("Μεγιστος αριθμος δωρεαν λεπτων ομιλιας: ");
                                System.out.println(maxmins);
                                System.out.print("Μεσος ορος των δωρεαν λεπτων ομιλιας: ");
                                System.out.println((double)totalmins / (double)totalcontrs);
                            }
                        }
                        else if (optn2 == 1) {
                            int d0 = 0;
                            int d24 = 0;
                            int d50 = 0;
                            int d100 = 0;
                            int totalcontrs = 0;
                            for (User u: users.values()) {
                                for (HomeContract hc: u.userHomeContracts.values()) {
                                    if (hc.speed == 0) d0++;
                                    else if (hc.speed == 24) d24++;
                                    else if (hc.speed == 50) d50++;
                                    else if (hc.speed == 100) d100++;
                                    totalcontrs++;
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print(d0);
                                System.out.print(" απο τα ");
                                System.out.print(totalcontrs);
                                System.out.println(" συμβολαια δεν περιλαμβανουν ιντερνετ.");
                                System.out.print(d24);
                                System.out.print(" συμβολαια χρησιμοποιουν ADSL 24Mbps ιντερνετ, ");
                                System.out.print(d50);
                                System.out.print(" συμβολαια χρησιμοποιουν VDSL 50Mbps ιντερνετ, ");
                                System.out.print(d100);
                                System.out.println(" συμβολαια χρησιμοποιουν VDSL 100Mbps ιντερνετ.");
                            }
                            
                        }
                        else {
                            double mincost = 0;
                            double maxcost = 0;
                            double totalcost = 0;
                            int totalcontrs = 0;
                            for (User u: users.values()) {
                                for (HomeContract hc: u.userHomeContracts.values()) {
                                    totalcontrs++;
                                    totalcost+=hc.monthlyCost;
                                    if (hc.monthlyCost > maxcost) maxcost = hc.monthlyCost;
                                    if (hc.monthlyCost < mincost) mincost = hc.monthlyCost;
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print("Ελαχιστη τιμη για σταθερο συμβολαιο: ");
                                System.out.println(mincost);
                                System.out.print("Μεγιστη τιμη για σταθερο συμβολαιο: ");
                                System.out.println(maxcost);
                                System.out.print("Μεση τιμη για σταθερο συμβολαιο: ");
                                System.out.println(totalcost / (double)totalcontrs);
                            }
                        }
                    }
                    else if (optn == 1) {
                        System.out.println("Πατα 0 για στατιστικα σε δωρεαν λεπτα ομιλιας");
                        System.out.println("Πατα 1 για στατιστικα σε δωρεαν GB");
                        System.out.println("Πατα 2 για στατιστικα σε δωρεαν SMS");
                        System.out.println("Πατα 3 για στατιστικα σχετικα με το μηνιαιο κοστος");
                        System.out.print("Επελεξε: ");
                        int optn2;
                        while (true) {
                            try {
                                optn2 = Integer.parseInt(scanner.nextLine());
                                if (optn2 != 0 && optn2 != 1 && optn2 != 2 && optn2 != 3) throw new ArithmeticException(); //check for correct input
                            }
                            catch(Exception e) {
                                System.out.println("Βαλε εναν εγκυρο αριθμο.");
                                continue;
                            }
                            break;
                        }
                        if (optn2 == 0) {
                            int maxmins = 0;
                            int minmins = 9000;
                            int totalmins = 0;
                            int totalcontrs = 0;
                            for (User u: users.values()) {
                                for (MobileContract mc: u.userMobileContracts.values()) {
                                    totalcontrs++;
                                    totalmins+=mc.freeMinutes;
                                    if (mc.freeMinutes > maxmins) maxmins = mc.freeMinutes;
                                    if (mc.freeMinutes < minmins) minmins = mc.freeMinutes;
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print("Ελαχιστος αριθμος δωρεαν λεπτων ομιλιας: ");
                                System.out.println(minmins);
                                System.out.print("Μεγιστος αριθμος δωρεαν λεπτων ομιλιας: ");
                                System.out.println(maxmins);
                                System.out.print("Μεση τιμη δωρεαν λεπτων ομιλιας: ");
                                System.out.println((double)totalmins / (double)totalcontrs);
                            }
                        }
                        else if (optn2 == 1) {
                            int d0 = 0;
                            int mingb = 150;
                            int maxgb = 0;
                            int totalgb = 0;
                            int totalcontrs = 0;
                            int totalnonzerocontrs = 0;
                            for (User u: users.values()) {
                                for (MobileContract mc: u.userMobileContracts.values()) {
                                    totalcontrs++;
                                    if (mc.freeGb == 0) d0++;
                                    else {
                                        totalnonzerocontrs++;
                                        if (mc.freeGb > maxgb) maxgb = mc.freeGb;
                                        if (mc.freeGb < mingb) mingb = mc.freeGb;
                                        totalgb+=mc.freeGb;
                                    }
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print(d0);
                                System.out.print(" απο τα ");
                                System.out.print(totalcontrs);
                                System.out.println(" συμβολαια δεν περιεχουν δωρεαν GB.");
                                System.out.println("Απο τα σταθερα συμβολαια που περιεχουν GB: ");
                                System.out.print("Ελαχιστος αριθμος δωρεαν GB τον μηνα: ");
                                System.out.println(mingb);
                                System.out.print("Μεγιστος αριθμος δωρεαν GB τον μηνα: ");
                                System.out.println(maxgb);
                                System.out.print("Μεση τιμη δωρεαν GB τον μηνα: ");
                                System.out.println((double)totalgb / (double)totalnonzerocontrs);
                            }
                        }
                        else if (optn2 == 2) {
                            int d0 = 0;
                            int minsms = 10000;
                            int maxsms = 0;
                            int totalsms = 0;
                            int totalcontrs = 0;
                            int totalnonzerocontrs = 0;
                            for (User u: users.values()) {
                                for (MobileContract mc: u.userMobileContracts.values()) {
                                    totalcontrs++;
                                    if (mc.freeSms == 0) d0++;
                                    else {
                                        totalnonzerocontrs++;
                                        if (mc.freeSms > maxsms) maxsms = mc.freeSms;
                                        if (mc.freeSms < minsms) minsms = mc.freeSms;
                                        totalsms+=mc.freeSms;
                                    }
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print(d0);
                                System.out.print(" απο τα ");
                                System.out.print(totalcontrs);
                                System.out.println(" συμβολαια δεν περιεχουν δωρεαν SMS τον μηνα.");
                                System.out.println("Απο τα κινητα συμβολαια που περιεχουν δωρεαν SMS: ");
                                System.out.print("Ελαχιστος αριθμος δωρεαν SMS τον μηνα: ");
                                System.out.println(minsms);
                                System.out.print("Μεγιστος αριθμος δωρεαν SMS τον μηνα: ");
                                System.out.println(maxsms);
                                System.out.print("Μεσος αριθμος δωρεαν SMS τον μηνα: ");
                                System.out.println((double)totalsms / (double)totalnonzerocontrs);
                            } 
                        }
                        else {
                            double mincost = 0;
                            double maxcost = 0;
                            double totalcost = 0;
                            int totalcontrs = 0;
                            for (User u: users.values()) {
                                for (MobileContract mc: u.userMobileContracts.values()) {
                                    totalcontrs++;
                                    totalcost+=mc.monthlyCost;
                                    if (mc.monthlyCost > maxcost) maxcost = mc.monthlyCost;
                                    if (mc.monthlyCost < mincost) mincost = mc.monthlyCost;
                                }
                            }
                            if (totalcontrs == 0) {
                                System.out.println("Δεν υπαρχουν συμβολαια.\n");
                            }
                            else {
                                System.out.print("Ελαχιστο κοστος του κινητου συμβολαιου: ");
                                System.out.println(mincost);
                                System.out.print("Μεγιστο κοστος του κινητου συμβολαιου: ");
                                System.out.println(maxcost);
                                System.out.print("Μεσο κοστος του κινητου συμβολαιου: ");
                                System.out.println(totalcost / (double)totalcontrs);
                            }
                            
                        }
                    }
                    
                            
                        }
                    
                }
            }
        }

    }


