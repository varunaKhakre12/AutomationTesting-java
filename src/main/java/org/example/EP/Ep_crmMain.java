package org.example.EP;


import org.example.assignment.Login2;

public class Ep_crmMain {
    public static void main(String[] args) throws InterruptedException {

        EP_CRM epCrm = new EP_CRM();
        epCrm.invokeBrowser();
        epCrm.login();
        epCrm.addBeneficiary();
//        greenKart.closeBrowser();
    }

}
