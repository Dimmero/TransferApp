package windows;

import cycles.CycleForStats;
import cycles.CycleForTransfer;
import core.*;
import entities.Company;
import entities.ListOfCompanies;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class MainWindow extends BaseWindow {
    private final String URL_TRANSFER = "https://www.dell.com/support/assets-transfer/pl-pl";
    private final String URL_STATS = "https://www.dell.com/support/home/pl-pl?app=products";

    private JTextArea serviceTagText;
    private JScrollPane scrollBar;
    private JButton generateFile;
    private JButton generateStats;
    private JButton submitTransfer;
    private JLabel serviceTagLabel;
    private JLabel previousOwnerLabel;
    private JLabel newOwnerLabel;
    private JLabel success;
    private JRadioButton radioButtonLaptok1;
    private JRadioButton radioButtonLaptok2;
    private JRadioButton radioButtonBufo1;
    private JRadioButton radioButtonBufo2;
    private JRadioButton radioButtonEco1;
    private JRadioButton radioButtonEco2;
    private JRadioButton radioButtonMax1;
    private JRadioButton radioButtonMax2;
    private JRadioButton radioButtonDeane1;
    private JRadioButton radioButtonDeane2;
    private JRadioButton radioButtonCommonwealth1;
    private JRadioButton radioButtonCommonwealth2;
    static JRadioButton radioButtonAddFromCompany;
    static JRadioButton radioButtonAddToCompany;
    private final ArrayList<JRadioButton> fromList;
    private final ArrayList<JRadioButton> toList;
    public CycleForStats stats;
    public ServiceTagParsing tagParsing = new ServiceTagParsing();

    public MainWindow() {
        super();
        this.fromList = new ArrayList<>();
        this.toList = new ArrayList<>();
    }

    public void createMainWindow() {
        getPanelForWindow();
        setAllButtons();
        addToFromList();
        addToToList();

        generateFile.addActionListener(e -> {
            try {
                String companyName = getCheckedCompany(fromList).getName();
                OutputToExcel outputToExcel = new OutputToExcel(companyName);
                outputToExcel.generateExcelWithTags(tagParsing.trimStringToServiceTags(serviceTagText));
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        generateStats.addActionListener(e -> {
            try {
                tagParsing = new ServiceTagParsing();
                String company = getCheckedCompany(fromList).getName();
                stats = new CycleForStats(company);
                stats.getCycleForStatistics(tagParsing.trimStringToServiceTags(serviceTagText));
                SeleniumDriver.driver.quit();
                ServiceTagParsing.listOfServiceTags.clear();
                serviceTagText.setText("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        radioButtonAddFromCompany.addActionListener(e -> {
            NewCompanyWindow newCompanyWindow = new NewCompanyWindow();
            try {
                newCompanyWindow.createNewCompanyWindow(newCompanyWindow, radioButtonAddFromCompany);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        radioButtonAddToCompany.addActionListener(e -> {
            NewCompanyWindow newCompanyWindow = new NewCompanyWindow();
            try {
                newCompanyWindow.createNewCompanyWindow(newCompanyWindow, radioButtonAddToCompany);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        submitTransfer.addActionListener(e -> {
            tagParsing = new ServiceTagParsing();
            Company previousOwner = getCheckedCompany(fromList);
            Company newOwner = getCheckedCompany(toList);
            ArrayList<String> listOfServiceTags = tagParsing.trimStringToServiceTags(serviceTagText);
            CycleForTransfer cycle = new CycleForTransfer();
            SeleniumDriver.initDriver();
            listOfServiceTags
                    .forEach(tag -> {
                        SeleniumDriver.openNewTab(URL_TRANSFER);
                        cycle.getCycle(tag, previousOwner, newOwner);
                        SeleniumDriver.closeDriver();
                    });
            SeleniumDriver.driver.quit();
            success.setText(listOfServiceTags + " have been successfully transferred to " + newOwner.getName());
            listOfServiceTags.clear();
            serviceTagText.setText("");
        });
    }

    private void setAllButtons() {
        serviceTagLabel = new JLabel("Provide service tags for transferring:");
        serviceTagLabel.setForeground(Color.BLACK);
        serviceTagLabel.setBounds(10, 20, 220, 25);

        serviceTagText = new JTextArea();
        scrollBar = new JScrollPane(serviceTagText);
        scrollBar.setBounds(240, 20, 100, 315);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        previousOwnerLabel = new JLabel("Previous owner");
        previousOwnerLabel.setForeground(Color.BLACK);
        previousOwnerLabel.setBounds(15, 70, 100, 25);

        newOwnerLabel = new JLabel("New owner");
        newOwnerLabel.setForeground(Color.BLACK);
        newOwnerLabel.setBounds(140, 70, 100, 25);

        radioButtonLaptok1 = new JRadioButton("Laptokcom");
        radioButtonLaptok1.setBounds(10, 100, 100, 25);

        radioButtonBufo1 = new JRadioButton("Bufotech");
        radioButtonBufo1.setBounds(10, 130, 100, 25);

        radioButtonEco1 = new JRadioButton("Ecomputers");
        radioButtonEco1.setBounds(10, 160, 100, 25);

        radioButtonMax1 = new JRadioButton("MaxMart");
        radioButtonMax1.setBounds(10, 190, 100, 25);

        radioButtonDeane1 = new JRadioButton("Deane Computer Solutions Limited");
        radioButtonDeane1.setBounds(10, 220, 100, 25);

        radioButtonCommonwealth1 = new JRadioButton("Commonwealth Charter Academy");
        radioButtonCommonwealth1.setBounds(10, 250, 100, 25);

        radioButtonAddFromCompany = new JRadioButton("Add new");
        radioButtonAddFromCompany.setBounds(10, 280, 100, 25);

        radioButtonLaptok2 = new JRadioButton("Laptokcom");
        radioButtonLaptok2.setBounds(120, 100, 100, 25);

        radioButtonBufo2 = new JRadioButton("Bufotech");
        radioButtonBufo2.setBounds(120, 130, 100, 25);

        radioButtonEco2 = new JRadioButton("Ecomputers");
        radioButtonEco2.setBounds(120, 160, 100, 25);

        radioButtonMax2 = new JRadioButton("MaxMart");
        radioButtonMax2.setBounds(120, 190, 100, 25);

        radioButtonDeane2 = new JRadioButton("Deane Computer Solutions Limited");
        radioButtonDeane2.setBounds(120, 220, 100, 25);

        radioButtonCommonwealth2 = new JRadioButton("Commonwealth Charter Academy");
        radioButtonCommonwealth2.setBounds(120, 250, 100, 25);

        radioButtonAddToCompany = new JRadioButton("Add new");
        radioButtonAddToCompany.setBounds(120, 280, 100, 25);

        submitTransfer = new JButton("Submit");
        submitTransfer.setBounds(10, 310, 210, 25);

        success = new JLabel("Result:");
        success.setForeground(Color.BLACK);
        success.setBounds(10, 340, 450, 25);

        generateFile = new JButton("Generate");
        generateFile.setBounds(350, 20, 100, 25);

        generateStats = new JButton("Statistics");
        generateStats.setBounds(350, 50, 100, 25);
    }

    public void addAllElementsToPanel(MainWindow mainWindow) {
        mainWindow.getPanel().add(scrollBar);
        mainWindow.getPanel().add(serviceTagLabel);
        mainWindow.getPanel().add(previousOwnerLabel);
        mainWindow.getPanel().add(newOwnerLabel);

        mainWindow.getPanel().add(radioButtonLaptok1);
        mainWindow.getPanel().add(radioButtonBufo1);
        mainWindow.getPanel().add(radioButtonEco1);
        mainWindow.getPanel().add(radioButtonMax1);
        mainWindow.getPanel().add(radioButtonDeane1);
        mainWindow.getPanel().add(radioButtonCommonwealth1);
        mainWindow.getPanel().add(radioButtonLaptok2);
        mainWindow.getPanel().add(radioButtonBufo2);
        mainWindow.getPanel().add(radioButtonEco2);
        mainWindow.getPanel().add(radioButtonMax2);
        mainWindow.getPanel().add(radioButtonDeane2);
        mainWindow.getPanel().add(radioButtonCommonwealth2);
        mainWindow.getPanel().add(radioButtonAddFromCompany);
        mainWindow.getPanel().add(radioButtonAddToCompany);
        mainWindow.getPanel().add(submitTransfer);
        mainWindow.getPanel().add(success);
        mainWindow.getPanel().add(generateFile);
        mainWindow.getPanel().add(generateStats);
        mainWindow.getFrame().setVisible(true);
    }

    private Map<JRadioButton, Company> getMapOfButtonsAndCompanies(List<JRadioButton> list) {
        Map<JRadioButton, Company> map = new LinkedHashMap<>();
        IntStream.range(0, ListOfCompanies.companyList.size())
                .forEach(index -> {
                    map.put(list.get(index), ListOfCompanies.companyList.get(index));
                });
        return map;
    }

    public Company getCheckedCompany(List<JRadioButton> list) {
        Map<JRadioButton, Company> map = getMapOfButtonsAndCompanies(list);
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSelected())
                .findAny()
                .get()
                .getValue();
    }

    public void addToFromList() {
        fromList.add(radioButtonLaptok1);
        fromList.add(radioButtonBufo1);
        fromList.add(radioButtonEco1);
        fromList.add(radioButtonMax1);
        fromList.add(radioButtonDeane1);
        fromList.add(radioButtonCommonwealth1);
        fromList.add(radioButtonAddFromCompany);
    }

    public void addToToList() {
        toList.add(radioButtonLaptok2);
        toList.add(radioButtonBufo2);
        toList.add(radioButtonEco2);
        toList.add(radioButtonMax2);
        toList.add(radioButtonDeane2);
        toList.add(radioButtonCommonwealth2);
        toList.add(radioButtonAddToCompany);
    }

}
