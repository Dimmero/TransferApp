package cycles;

import core.SeleniumDriver;
import entities.Company;
import forms.NewOwnerForm;
import forms.PreviousOwnerForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.TransferWarrantyPage;

public class CycleForTransfer {
    private TransferWarrantyPage transferWarrantyPage;
    private PreviousOwnerForm previousOwnerForm;
    private NewOwnerForm newOwnerForm;

    public CycleForTransfer() {
        this.transferWarrantyPage = new TransferWarrantyPage();
        this.previousOwnerForm = new PreviousOwnerForm();
        this.newOwnerForm = new NewOwnerForm();
    }

    public void getCycle(String serviceTag, Company fromCompany, Company toCompany) {
        try {
            transferWarrantyPage.passServiceTagAndGoToTheNextPage(serviceTag);
            if (previousOwnerForm.fillForm(fromCompany)) {
                return;
            }
            newOwnerForm.fillForm(toCompany);
        } catch (Exception l) {
            System.out.println(serviceTag + "has already been done");
        }
    }


}

