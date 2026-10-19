package com.figs.automation.tests;

import com.figs.automation.base.BaseTest;
import com.figs.automation.pages.FingerPrintPage;
import com.figs.automation.pages.LoginPage;
import com.figs.automation.runners.FunctionalitySelector;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test 
    public void testLoginAndAgentFP() throws InterruptedException {
        new LoginPage(driver).login();
        new FingerPrintPage(driver).loginFingerprintIfRequired();
        new FunctionalitySelector(driver).selectAndRun();
    	}
    }
