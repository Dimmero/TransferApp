package windows;

import com.opencsv.CSVReader;
import entities.Company;
import entities.ListOfCompanies;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class NewCompanyWindow extends BaseWindow {

    private JTextField companyName;
    private JTextField companyEmail;
    private JTextField companyAddress;
    private JTextField companyCity;
    private JTextField companyZipCode;
    private JTextField companyPrefixNumber;
    private JTextField companyTelNumber;
    private JTextField companyState;
    private JComboBox companyCountry;

    public void createNewCompanyWindow(NewCompanyWindow newCompanyWindow, JRadioButton buttonNewCompany) throws IOException {
        newCompanyWindow.getPanelForWindow();

        JLabel newNameLabel = addNewLabel("entities.Company name:", Color.BLACK);
        newNameLabel.setBounds(10, 20, 100, 25);

        companyName = new JTextField();
        companyName.setBounds(120, 20, 300, 25);

        JLabel newEmailLabel = addNewLabel("Email:", Color.BLACK);
        newEmailLabel.setBounds(10, 50, 100, 25);

        companyEmail = new JTextField();
        companyEmail.setBounds(120, 50, 300, 25);

        JLabel newAddressLabel = addNewLabel("Address:", Color.BLACK);
        newAddressLabel.setBounds(10, 80, 100, 25);

        companyAddress = new JTextField();
        companyAddress.setBounds(120, 80, 300, 25);

        JLabel newCountryLabel = addNewLabel("Country", Color.BLACK);
        newCountryLabel.setBounds(10, 110, 100, 25);

        companyCountry = new JComboBox(CSVReaderFile.toStringArray(new FileReader("C:\\Users\\Dimmer\\Desktop\\TrustMe\\TransferApp\\src\\main\\resources\\country.csv")));
        companyCountry.setBounds(120, 110, 300, 25);

        JLabel newCityLabel = addNewLabel("City:", Color.BLACK);
        newCityLabel.setBounds(10, 140, 100, 25);

        companyCity = new JTextField();
        companyCity.setBounds(120, 140, 300, 25);

        JLabel newStateLabel = addNewLabel("State", Color.BLACK);
        newStateLabel.setBounds(10, 170, 100, 25);

        companyState = new JTextField();
        companyState.setBounds(120, 170, 300, 25);

        JLabel newZipCodeLabel = addNewLabel("Zip Code:", Color.BLACK);
        newZipCodeLabel.setBounds(10, 200, 100, 25);

        companyZipCode = new JTextField();
        companyZipCode.setBounds(120, 200, 300, 25);

        JLabel newPrefixNumberLabel = addNewLabel("Prefix & Number", Color.BLACK);
        newPrefixNumberLabel.setBounds(10, 230, 100, 25);

        companyPrefixNumber = new JTextField("48");
        companyPrefixNumber.setBounds(120, 230, 30, 25);

        companyTelNumber = new JTextField();
        companyTelNumber.setBounds(150, 230, 270, 25);

        JButton addNewCompanyButton = new JButton("Submit");
        addNewCompanyButton.setBounds(10, 260, 100, 25);

        newCompanyWindow.getPanel().add(newNameLabel);
        newCompanyWindow.getPanel().add(companyName);
        newCompanyWindow.getPanel().add(newEmailLabel);
        newCompanyWindow.getPanel().add(companyEmail);
        newCompanyWindow.getPanel().add(newAddressLabel);
        newCompanyWindow.getPanel().add(companyAddress);
        newCompanyWindow.getPanel().add(newCityLabel);
        newCompanyWindow.getPanel().add(companyCity);
        newCompanyWindow.getPanel().add(newZipCodeLabel);
        newCompanyWindow.getPanel().add(companyZipCode);
        newCompanyWindow.getPanel().add(newPrefixNumberLabel);
        newCompanyWindow.getPanel().add(companyPrefixNumber);
        newCompanyWindow.getPanel().add(companyTelNumber);
        newCompanyWindow.getPanel().add(addNewCompanyButton);
        newCompanyWindow.getPanel().add(companyCountry);
        newCompanyWindow.getPanel().add(newCountryLabel);
        newCompanyWindow.getPanel().add(companyState);
        newCompanyWindow.getPanel().add(newStateLabel);

        addNewCompanyButton.addActionListener(e -> {
            Company extraCompany = setNewCompany();
            ListOfCompanies.companyList.add(extraCompany);
            buttonNewCompany.setText(extraCompany.getName());
            newCompanyWindow.getFrame().dispose();
        });
        newCompanyWindow.getFrame().setVisible(true);
    }

    public Company setNewCompany() {
        Company company = new Company();
        company.setName(companyName.getText());
        company.setEmail(companyEmail.getText());
        company.setAddress(companyAddress.getText());
        company.setCountry(Objects.requireNonNull(companyCountry.getSelectedItem()).toString());
        company.setCity(companyCity.getText());
        company.setState(companyState.getText());
        company.setZipCode(companyZipCode.getText());
        company.setPrefixNumber(companyPrefixNumber.getText());
        company.setTelNumber(companyTelNumber.getText());
        return company;
    }

    public static class CSVReaderFile {
        public static String[] toStringArray(FileReader file) throws IOException {
            CSVReader reader = new CSVReader(file);
            String[] arrayOfCsv = reader.readNext();
            String arrayToString = Arrays.toString(arrayOfCsv);
            arrayOfCsv = arrayToString.substring(1, arrayToString.length() - 1).replaceAll(",$", "").split(",");
            return arrayOfCsv;
        }
    }
}
