package windows;

import com.opencsv.CSVReader;
import entities.Company;
import entities.ListOfCompanies;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class NewCompanyWindow extends BaseWindow {

    private JTextField companyName;
    private JTextField companyEmail;
    private JTextField companyAddress;
    private JTextField companyCity;
    private JTextField companyZipCode;
    private JTextField companyPrefixNumber;
    private JTextField companyTelNumber;
    private JTextField companyState;
    private JTextField companyCountry;
    private JButton addNewCompanyButton;
    private JLabel newNameLabel;
    private JLabel newEmailLabel;
    private JLabel newAddressLabel;
    private JLabel newCountryLabel;
    private JLabel newCityLabel;
    private JLabel newStateLabel;
    private JLabel newZipCodeLabel;
    private JLabel newPrefixNumberLabel;

    public void createNewCompanyWindow(JRadioButton buttonNewCompany) {
        getPanelForWindow();
        getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setAllButtons();
        addAllElementsToPanel();

        addNewCompanyButton.addActionListener(e -> {
            Company extraCompany = setNewCompany();
            ListOfCompanies.companyList.add(extraCompany);
            buttonNewCompany.setText(extraCompany.getName());
            getFrame().dispose();
        });
        getFrame().setVisible(true);
    }

    @Override
    public void getPanelForWindow() {
        super.getPanelForWindow();
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setAllButtons() {
        newNameLabel = addNewLabel("Company name:", Color.BLACK);
        newNameLabel.setBounds(10, 20, 100, 25);

        companyName = new JTextField();
        companyName.setBounds(120, 20, 300, 25);

        newEmailLabel = addNewLabel("Email:", Color.BLACK);
        newEmailLabel.setBounds(10, 50, 100, 25);

        companyEmail = new JTextField();
        companyEmail.setBounds(120, 50, 300, 25);

        newAddressLabel = addNewLabel("Address:", Color.BLACK);
        newAddressLabel.setBounds(10, 80, 100, 25);

        companyAddress = new JTextField();
        companyAddress.setBounds(120, 80, 300, 25);

        newCountryLabel = addNewLabel("Country", Color.BLACK);
        newCountryLabel.setBounds(10, 110, 100, 25);

//        companyCountry = new JComboBox(CSVReaderFile.toStringArray(new FileReader("C:\\Users\\Dimmer\\Desktop\\TrustMe\\TransferApp\\src\\main\\resources\\country.csv")));
        companyCountry = new JTextField();
        companyCountry.setBounds(120, 110, 300, 25);

        newCityLabel = addNewLabel("City:", Color.BLACK);
        newCityLabel.setBounds(10, 140, 100, 25);

        companyCity = new JTextField();
        companyCity.setBounds(120, 140, 300, 25);

        newStateLabel = addNewLabel("State", Color.BLACK);
        newStateLabel.setBounds(10, 170, 100, 25);

        companyState = new JTextField();
        companyState.setBounds(120, 170, 300, 25);

        newZipCodeLabel = addNewLabel("Zip Code:", Color.BLACK);
        newZipCodeLabel.setBounds(10, 200, 100, 25);

        companyZipCode = new JTextField();
        companyZipCode.setBounds(120, 200, 300, 25);

        newPrefixNumberLabel = addNewLabel("Prefix & Number", Color.BLACK);
        newPrefixNumberLabel.setBounds(10, 230, 100, 25);

        companyPrefixNumber = new JTextField("48");
        companyPrefixNumber.setBounds(120, 230, 30, 25);

        companyTelNumber = new JTextField();
        companyTelNumber.setBounds(150, 230, 270, 25);

        addNewCompanyButton = new JButton("Submit");
        addNewCompanyButton.setBounds(10, 260, 100, 25);
    }

    public void addAllElementsToPanel() {
        getPanel().add(newNameLabel);
        getPanel().add(companyName);
        getPanel().add(newEmailLabel);
        getPanel().add(companyEmail);
        getPanel().add(newAddressLabel);
        getPanel().add(companyAddress);
        getPanel().add(newCityLabel);
        getPanel().add(companyCity);
        getPanel().add(newZipCodeLabel);
        getPanel().add(companyZipCode);
        getPanel().add(newPrefixNumberLabel);
        getPanel().add(companyPrefixNumber);
        getPanel().add(companyTelNumber);
        getPanel().add(addNewCompanyButton);
        getPanel().add(companyCountry);
        getPanel().add(newCountryLabel);
        getPanel().add(companyState);
        getPanel().add(newStateLabel);
        getFrame().setVisible(true);
    }

    public Company setNewCompany() {
        Company company = new Company();
        company.setName(companyName.getText());
        company.setEmail(companyEmail.getText());
        company.setAddress(companyAddress.getText());
//        company.setCountry(Objects.requireNonNull(companyCountry.getSelectedItem()).toString());
        company.setCountry(companyCountry.getText());
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
