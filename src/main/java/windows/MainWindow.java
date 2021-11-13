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
    private ArrayList<String> listOfServiceTags;
    private CycleForStats stats;

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
        addAllElementsToPanel();

        submitTransfer.addActionListener(e -> {
            Company previousOwner = getCheckedCompany(fromList);
            Company newOwner = getCheckedCompany(toList);
            CycleForTransfer cycle = new CycleForTransfer();
            cycle.getCycle(getListOfServiceTags(), previousOwner, newOwner);
            confirmTransferredTags(newOwner);
            clearListAndTextArea();
        });

        generateStats.addActionListener(e -> {
            String company = getCheckedCompany(fromList).getName();
            stats = new CycleForStats(company);
            stats.getCycleForStatistics(getListOfServiceTags());
            clearListAndTextArea();
        });

        generateFile.addActionListener(e -> {
            String companyName = getCheckedCompany(fromList).getName();
            OutputToExcel outputToExcel = new OutputToExcel(companyName);
            outputToExcel.generateExcelWithTags(getListOfServiceTags());
        });

        radioButtonAddFromCompany.addActionListener(e -> {
            NewCompanyWindow newFromCompanyWindow = new NewCompanyWindow();
            newFromCompanyWindow.createNewCompanyWindow(radioButtonAddFromCompany);
        });

        radioButtonAddToCompany.addActionListener(e -> {
            NewCompanyWindow newToCompanyWindow = new NewCompanyWindow();
            newToCompanyWindow.createNewCompanyWindow(radioButtonAddToCompany);
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

    public void addAllElementsToPanel() {
        getPanel().add(scrollBar);
        getPanel().add(serviceTagLabel);
        getPanel().add(previousOwnerLabel);
        getPanel().add(newOwnerLabel);
        getPanel().add(radioButtonLaptok1);
        getPanel().add(radioButtonBufo1);
        getPanel().add(radioButtonEco1);
        getPanel().add(radioButtonMax1);
        getPanel().add(radioButtonDeane1);
        getPanel().add(radioButtonCommonwealth1);
        getPanel().add(radioButtonLaptok2);
        getPanel().add(radioButtonBufo2);
        getPanel().add(radioButtonEco2);
        getPanel().add(radioButtonMax2);
        getPanel().add(radioButtonDeane2);
        getPanel().add(radioButtonCommonwealth2);
        getPanel().add(radioButtonAddFromCompany);
        getPanel().add(radioButtonAddToCompany);
        getPanel().add(submitTransfer);
        getPanel().add(success);
        getPanel().add(generateFile);
        getPanel().add(generateStats);
        getFrame().setVisible(true);
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
                .orElseThrow()
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

    private void confirmTransferredTags(Company newOwner) {
        success.setText(listOfServiceTags + " have been successfully transferred to " + newOwner.getName());
    }

    private ArrayList<String> getListOfServiceTags() {
        return listOfServiceTags = new ServiceTagParsing().trimStringToServiceTags(serviceTagText);
    }

    private void clearListAndTextArea() {
        listOfServiceTags.clear();
        serviceTagText.setText("");
    }
}
