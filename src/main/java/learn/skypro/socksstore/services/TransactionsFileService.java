package learn.skypro.socksstore.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TransactionsFileService {
    @Value("${path.to.transactionsJson.file}")
    private String transactionsListFilePath;

    @Value("${name.of.transactionsJson.file}")
    private String transactionsListFileName;

    @Value("${path.to.transactionsTXT.file}")
    private String transactionsTxtFilePath;

    @Value("${name.of.transactionsTXT.file}")
    private String transactionsTxtFileName;

    public boolean cleanTransactionsListJson(String transactionsListFilePath, String transactionsListFileName) {
        try {
            Path path = Path.of(transactionsListFilePath, transactionsListFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cleanTransactionsTxt(String transactionsTxtFilePath, String transactionsTxtFileName) {
        try {
            Path path = Path.of(transactionsTxtFilePath, transactionsTxtFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public File getTxtFile(String transactionsTxtFilePath, String transactionsTxtFileName) {
        return new File(transactionsTxtFilePath + "/" + transactionsTxtFileName);
    }

    public File getTransactionsListJson(String transactionsListFilePath, String transactionsListFileName) {
        return new File(transactionsListFilePath + "/" + transactionsListFileName);
    }

    public boolean saveTransactionsListToJsonFile(String json, String transactionsListFilePath, String transactionsListFileName) {
        try {
            cleanTransactionsListJson(transactionsListFilePath, transactionsListFileName);
            Files.writeString(Path.of(transactionsListFilePath, transactionsListFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveTransactionsToTxtFile(String txt, String transactionsTxtFilePath, String transactionsTxtFileName) {
        try {
            cleanTransactionsTxt(transactionsTxtFilePath, transactionsTxtFileName);
            Files.writeString(Path.of(transactionsTxtFilePath, transactionsTxtFileName), txt);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readTransactionsListFromJsonFile(String transactionsListFilePath, String transactionsListFileName) {
        if (Files.exists(Path.of(transactionsListFilePath, transactionsListFileName))) {
            try {
                String json = Files.readString(Path.of(transactionsListFilePath, transactionsListFileName));
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getTransactionsListFilePath() {
        return transactionsListFilePath;
    }

    public String getTransactionsListFileName() {
        return transactionsListFileName;
    }

    public String getTransactionsTxtFilePath() {
        return transactionsTxtFilePath;
    }

    public String getTransactionsTxtFileName() {
        return transactionsTxtFileName;
    }
}
