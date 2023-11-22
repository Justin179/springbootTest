package com.justin.springboottest.javabestpractices.methods.general.programinterfaces.before;

import java.math.BigDecimal;

class AccountServiceImpl implements AccountService {

    @Override
    public BigDecimal getAccountBalance(String accountId) {
        // actual implementation skipped for brevity
        return BigDecimal.ONE;
    }

}
