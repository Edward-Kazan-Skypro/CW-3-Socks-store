package learn.skypro.socksstore.product.socks.repository;

import learn.skypro.socksstore.product.socks.transactions.TransactionSocks;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class TransactionRepositorySocks<Socks> {

    HashMap <LocalDateTime, TransactionSocks<Socks>>  transactionStorage = new HashMap<>();

    public boolean addTransaction(TransactionSocks<Socks> transactionSocks){
        TransactionSocks<Socks> bufferTransactionSocks = transactionSocks;
        if (transactionSocks != null){
            transactionStorage.put(LocalDateTime.now(), transactionSocks);
            return true;
        }
        return false;
    }



}
