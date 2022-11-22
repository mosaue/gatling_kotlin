package models

import java.math.BigDecimal

data class Account(
    val accountName: String = "Savings",
    val accountTypeCode: String = "8",
    val openingDeposit: BigDecimal = BigDecimal.valueOf(100).setScale(0),
    val ownerTypeCode: String = "18"
)
