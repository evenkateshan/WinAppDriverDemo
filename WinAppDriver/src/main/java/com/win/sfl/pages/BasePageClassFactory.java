package com.win.sfl.pages;

import org.openqa.selenium.WebDriver;

public class BasePageClassFactory {
    protected WebDriver driver;
    private SFLLoginPage sflLoginPage;
  


    public BasePageClassFactory(WebDriver driver) {
        this.driver = driver;
    }

    public SFLLoginPage getLoginPage() {
        if (sflLoginPage == null) {
            sflLoginPage = new SFLLoginPage(driver);
        }
        return sflLoginPage;
    }


}
