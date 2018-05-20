package com.wallet.core.receipt

import com.wallet.core.currency.data.Currency
import com.wallet.core.market.OperationType
import io.reactivex.Single
import java.math.BigDecimal
import java.util.*

/**
 *   Copyright {2018} {Rômulo Eduardo G. Moraes}
 **/
class ReceiptManager(private val receiptRepository: ReceiptRepository) {

    fun getReceipts(): Single<List<Receipt>> {

        return receiptRepository.getReceipts()
    }



    fun createReceipt(debitCurrency: Currency,
                      debitCurrencyAmount: BigDecimal,
                      creditCurrency: Currency,
                      creditCurrencyAmount: BigDecimal,
                      operationType: OperationType): Single<Receipt> {

        val receipt = Receipt(debitCurrency, debitCurrencyAmount, creditCurrency,
                creditCurrencyAmount, operationType, Calendar.getInstance().timeInMillis)

        return receiptRepository.saveReceipt(receipt).toSingleDefault(receipt)
    }
}