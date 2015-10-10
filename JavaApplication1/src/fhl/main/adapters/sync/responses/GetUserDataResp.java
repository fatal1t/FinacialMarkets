/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.adapters.sync.responses;

/**
 *
 * @author Filip
 */
public class GetUserDataResp {
    private final int companyUnit;
    private final String currency;
    private final String group;
    private final boolean ibAccount;
    private final double leverageMultiplier;
    private final String spreadType;

    /**
     *
     * @param companyUnit
     * @param currency
     * @param group
     * @param ibAccount
     * @param leverageMultiplier
     * @param spreadType
     */
    public GetUserDataResp(int companyUnit, String currency, String group, boolean ibAccount, double leverageMultiplier, String spreadType) {
        this.companyUnit = companyUnit;
        this.currency = currency;
        this.group = group;
        this.ibAccount = ibAccount;
        this.leverageMultiplier = leverageMultiplier;
        this.spreadType = spreadType;
    }

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
}
