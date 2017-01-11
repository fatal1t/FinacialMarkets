/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.session;

import java.io.Serializable;

/**
 *
 * @author Filip
 */
public class UserData implements Serializable   {

    private final int companyUnit;
    private final String currency;
    private final String group;
    private final boolean ibAccount;
    private final double leverageMultiplier;
    private final String spreadType;

    public int getCompanyUnit() {
        return companyUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public String getGroup() {
        return group;
    }

    public boolean isIbAccount() {
        return ibAccount;
    }

    public double getLeverageMultiplier() {
        return leverageMultiplier;
    }

    public String getSpreadType() {
        return spreadType;
    }
    
    

    public UserData(int companyUnit, String currency, String group, boolean ibAccount, double leverageMultiplier, String spreadType) {
        this.companyUnit = companyUnit;
        this.currency = currency;
        this.group = group;
        this.ibAccount = ibAccount;
        this.leverageMultiplier = leverageMultiplier;
        this.spreadType = spreadType;
    }
    
    @Override
    public String toString()
    {
        return "CompanyUnit: " +Integer.toString(this.companyUnit)+
                " \nCurrency: " + this.currency+ " \nGroup: " + this.group +
                " \nIsIBAccount: " + Boolean.toString(this.ibAccount) + " \nLeverageMultiplier: " +
                Double.toString(this.leverageMultiplier)+ " \nSpreadType: " + this.spreadType;
    }
    
    
}
