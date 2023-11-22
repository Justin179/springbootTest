package com.justin.springboottest.javabestpractices.methods.general.programinterfaces.before;

import java.math.BigDecimal;

// is this interface necessary if there is only one implementation?
interface AccountService {

    BigDecimal getAccountBalance(String accountId);

}
