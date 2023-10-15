package entities;

import entities.Company;

import java.util.ArrayList;

public class ListOfCompanies {
    public static ArrayList<Company> companyList = new ArrayList<>();

    //Here you need to add variables with company data and then add to companyList in addingCompaniesToList()

    public static void addingCompaniesToList() {
        companyList.add(new Company());
    }

    public ListOfCompanies() {
    }

}
