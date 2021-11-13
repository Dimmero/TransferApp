package core;

import entities.ListOfCompanies;
import windows.MainWindow;

public class Runner {
    public static void main(String[] args)  {
        ListOfCompanies.addingCompaniesToList();
        MainWindow window = new MainWindow();
        window.createMainWindow();
    }

}
