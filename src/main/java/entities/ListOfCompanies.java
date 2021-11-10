package entities;

import entities.Company;

import java.util.ArrayList;

public class ListOfCompanies {
    public static ArrayList<Company> companyList = new ArrayList<>();

    static final Company laptokcom = new Company("Laptokcom", "dima@laptok.com", "Krakowska 56", "PL", "Wieliczka", "Lesser Poland", "32-020", "601913957");
    static final Company bufotech = new Company("Bufotech", "bufotech@gmail.com", "", "UK", "", "", "FY83AY", "601913957");
    static final Company ecomputers = new Company("Ecomputers", "dima@laptok.com", "", "UK", "", "", "Ne166DZ", "601913957");
    static final Company maxmart = new Company("MaxMart", "dima@laptok.com", "118 Edwardia Dr.", "US", "Greensboro", "", "27409", "601913957");
    static final Company deane = new Company("Deane Computer Solutions Limited", "dima@laptok.com", "Unit 8F Middle Farm Way", "UK", "Dorchester", "", "DT13AR", "601913957");
    static final Company commonwealth = new Company("Commonwealth Charter Academy", "", "", "", "", "", "17102", "");

    public static void addingCompaniesToList() {
        companyList.add(laptokcom);
        companyList.add(bufotech);
        companyList.add(ecomputers);
        companyList.add(maxmart);
        companyList.add(deane);
        companyList.add(commonwealth);
    }

    public ListOfCompanies() {
    }

}
