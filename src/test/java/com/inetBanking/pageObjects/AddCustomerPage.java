package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage
{
    WebDriver ldriver;
    public AddCustomerPage(WebDriver rdriver)
    {
       ldriver=rdriver;
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(how= How.XPATH, using ="//a[@href='addcustomerpage.php']")
    @CacheLookup
    WebElement lnkAddNewCustomer;

    @FindBy(how= How.NAME, using ="name")
    @CacheLookup
    WebElement txtCustomerName;

    @FindBy(how= How.NAME, using ="rad1")
    @CacheLookup
    WebElement rdGender;

    @FindBy(how= How.ID_OR_NAME, using ="dob")
    @CacheLookup
    WebElement txtdob;

    @FindBy(how= How.NAME, using ="addr")
    @CacheLookup
    WebElement txtaddress;

    @FindBy(how= How.NAME, using ="city")
    @CacheLookup
    WebElement txtcity;

    @FindBy(how= How.NAME, using ="state")
    @CacheLookup
    WebElement txtstate;

    @FindBy(how= How.NAME, using ="pinno")
    @CacheLookup
    WebElement txtpinno;

    @FindBy(how= How.NAME, using ="telephoneno")
    @CacheLookup
    WebElement txttelephoneno;

    @FindBy(how= How.NAME, using ="emailid")
    @CacheLookup
    WebElement txtemailid;

    @FindBy(how= How.NAME, using ="password")
    @CacheLookup
    WebElement txtpassword;

    @FindBy(how= How.NAME, using ="sub")
    @CacheLookup
    WebElement btnSubmit;

    public void clickAddNewCustomer()
    {
        lnkAddNewCustomer.click();
    }

    public void custName(String cname)
    {
        txtCustomerName.sendKeys(cname);
    }

    public void custGender(String cgender)
    {
        rdGender.click();
    }

    public void custdob(String mm, String dd, String yy)
    {
        txtdob.sendKeys(mm);
        txtdob.sendKeys(dd);
        txtdob.sendKeys(yy);
    }

    public void custAddress(String caddress)
    {
        txtaddress.sendKeys(caddress);
    }

    public void custcity(String city)
    {
        txtcity.sendKeys(city);
    }

    public void custState(String state)
    {
        txtstate.sendKeys(state);
    }

    public void custPinno(String pinno)
    {
        txtpinno.sendKeys(String.valueOf(pinno));
    }

    public void custtelephoneno(String telephoneno)
    {
        txttelephoneno.sendKeys(telephoneno);
    }

    public void custEmailid(String emailid)
    {
        txtemailid.sendKeys(emailid);
    }

    public void custPassword(String password)
    {
        txtpassword.sendKeys(password);
    }

    public void custSubmit()
    {
        btnSubmit.click();
    }
}
