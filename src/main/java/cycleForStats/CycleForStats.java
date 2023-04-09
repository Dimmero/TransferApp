package cycleForStats;

import BaseElements.BaseAbstractPage;
import core.OutputToExcel;
import core.SeleniumDriver;

import java.util.ArrayList;

public class CycleForStats extends BaseAbstractPage  {

    private final OutputToExcel outputToExcel;
    private final DellLoginPage dellLoginPage;
    private final WarrantyInfoPage warrantyInfoPage;

    public CycleForStats(String company) {
        driver = new SeleniumDriver();
        this.dellLoginPage = new DellLoginPage();
        this.outputToExcel = new OutputToExcel(company);
        this.warrantyInfoPage = new WarrantyInfoPage();
    }

    public void getCycleForStatistics(ArrayList<String> list) {
        list.forEach(tag -> {
            dellLoginPage.passServiceTagAndSubmit(tag);
            outputToExcel.getStatistics(list.indexOf(tag), tag, warrantyInfoPage);
            driver.closeTabForStats();
        });
        outputToExcel.writeToFile();
        driver.quitDriver();
    }
}




