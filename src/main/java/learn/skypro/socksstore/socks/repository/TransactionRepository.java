package learn.skypro.socksstore.socks.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import learn.skypro.socksstore.services.TransactionsFileService;
import learn.skypro.socksstore.socks.model.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
@Repository
public class TransactionRepository {


    private final TransactionsFileService transactionsFileService;

    public TransactionRepository(TransactionsFileService transactionsFileService) {
        this.transactionsFileService = transactionsFileService;
    }

    static Long idCounter = 1L;
    TreeMap<Long, TransactionSocks> transactionList = new TreeMap<>();

    public void addSTransaction(int cottonPart,
                                SocksColor socksColor,
                                SocksSize socksSize,
                                int quantity,
                                TransactionsType transactionsType
                                ) {
        idCounter = 1L;
        while (transactionList.containsKey(idCounter)) {
            idCounter++;
        }
        String fullDateCreateTransaction = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"));
        String onlyDate = fullDateCreateTransaction.substring(6, 16);
        String onlyTime = fullDateCreateTransaction.substring(0, 5);

        transactionList.put(idCounter, new TransactionSocks(cottonPart,
                socksColor,
                socksSize,
                quantity,
                onlyDate,
                onlyTime,
                transactionsType));
        transactionsFileService.saveTransactionsListToJsonFile(jsonFromList());
        transactionsFileService.saveTransactionsToTxtFile(viewAllTransactions());
    }

    private String jsonFromList() {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(transactionList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private TreeMap<Long, TransactionSocks> listFromFile() {
        try {
            String json = transactionsFileService.readTransactionsListFromJsonFile();
            if (StringUtils.isNotEmpty(json) || StringUtils.isNotBlank(json)) {
                transactionList = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return transactionList;
    }

    public String viewAllTransactions() {
        String result = "";
        int counter = 0;
        result += "Список транзакций:\n";
        result += "Поступление на склад:\n";
        //Сформируем список транзакций - ПОСТУПЛЕНИЕ на склад
        //А для этого сформируем список уникальных дат
        TreeSet<String> sortedDates = new TreeSet<>();
        for (TransactionSocks externalStep : getTransactionList().values()) {
            if (externalStep.getTransactionsType().equals(TransactionsType.INCOMING)) {
                sortedDates.add(externalStep.getDateCreateTransaction());
            }
        }
        //Теперь у нас есть список уникальных дат
        //На его основе и будем формировать текст
        for (String date: sortedDates){
            result += "дата - " + date + "\n";
            for (TransactionSocks internalStep : getTransactionList().values()) {
                if (internalStep.getDateCreateTransaction().equals(date)){
                    result += internalStep.getTimeCreateTransaction() + " " +
                            "цвет " + internalStep.getSocksColor() + ", " +
                            "размер " + internalStep.getSocksSize() + ", " +
                            "содержание хлопка " + internalStep.getCottonPart() + "%, " +
                            "количество " + internalStep.getQuantity() + " пар.\n";
                    counter++;
                }
            }
        }
        if (counter == 0) {
            result += "- поступлений на склад не было.";
        }
        sortedDates.clear();
        counter = 0;
        result += "Выбытие со склада:\n";
        //Сформируем список транзакций - ВЫБЫТИЕ со склада
        //А для этого сформируем список уникальных дат
        for (TransactionSocks externalStep : getTransactionList().values()) {
            if (externalStep.getTransactionsType().equals(TransactionsType.OUTGOING)) {
                sortedDates.add(externalStep.getDateCreateTransaction());
            }
        }
        //Теперь у нас есть список уникальных дат
        //На его основе и будем формировать текст
        for (String date: sortedDates){
            result += "дата - " + date + "\n";
            for (TransactionSocks internalStep : getTransactionList().values()) {
                if (internalStep.getDateCreateTransaction().equals(date)){
                    result += internalStep.getTimeCreateTransaction() + " " +
                            "цвет " + internalStep.getSocksColor() + ", " +
                            "размер " + internalStep.getSocksSize() + ", " +
                            "содержание хлопка " + internalStep.getCottonPart() + "%, " +
                            "количество " + internalStep.getQuantity() + " пар.\n";
                    counter++;
                }
            }
        }
        if (counter == 0) {
            result += "- выбытие не происходило.\n";
        }
        sortedDates.clear();
        counter = 0;
        result += "Списание со склада:\n";
        //Сформируем список транзакций - СПИСАНИЕ со склада
        //А для этого сформируем список уникальных дат
        for (TransactionSocks externalStep : getTransactionList().values()) {
            if (externalStep.getTransactionsType().equals(TransactionsType.CANCELLATION)) {
                sortedDates.add(externalStep.getDateCreateTransaction());
            }
        }
        //Теперь у нас есть список уникальных дат
        //На его основе и будем формировать текст
        for (String date: sortedDates){
            result += "дата - " + date + "\n";
            for (TransactionSocks internalStep : getTransactionList().values()) {
                if (internalStep.getDateCreateTransaction().equals(date)){
                    result += internalStep.getTimeCreateTransaction() + " " +
                            "цвет " + internalStep.getSocksColor() + ", " +
                            "размер " + internalStep.getSocksSize() + ", " +
                            "содержание хлопка " + internalStep.getCottonPart() + "%, " +
                            "количество " + internalStep.getQuantity() + " пар.\n";
                    counter++;
                }
            }
        }
        if (counter == 0) {
            result += "- списания не было.";
        }
        return result;
    }

    @PostConstruct
    private void init() {
        transactionList = listFromFile();
    }
}
