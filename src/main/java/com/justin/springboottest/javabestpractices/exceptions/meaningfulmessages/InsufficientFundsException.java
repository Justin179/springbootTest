package com.justin.springboottest.javabestpractices.exceptions.meaningfulmessages;

import java.math.BigDecimal;

class InsufficientFundsException extends RuntimeException {

    //    good idea not to expose this standard constructor
    //    public InsufficientFundsException(String message) {
    //        super(message);
    //    }

    public InsufficientFundsException(BigDecimal balance, BigDecimal amountToWithdraw) {
        super(String.format("balance=%s, amountToWithdraw=%s", balance.toString(), amountToWithdraw.toString()));
    }
}
