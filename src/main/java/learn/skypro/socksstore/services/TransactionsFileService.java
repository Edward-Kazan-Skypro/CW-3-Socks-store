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

    public boolean cleanTransactionsListJson(){
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

    public boolean cleanTransactionsListTxt(){
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

    public File getTxtFile() {
        if (Files.exists(Path.of(transactionsTxtFilePath, transactionsTxtFileName))) {
            return new File(transactionsTxtFilePath + "/" + transactionsTxtFileName);
        }
        return null;
    }

    public File getTransactionsListJson() {
        if (Files.exists(Path.of(transactionsListFilePath, transactionsListFileName))) {
            return new File(transactionsListFilePath + "/" + transactionsListFileName);
        }
        return null;
    }

    public boolean saveTransactionsListToJsonFile(String json) {
        try {
            cleanTransactionsListJson();
            Files.writeString(Path.of(transactionsListFilePath, transactionsListFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveTransactionsToTxtFile(String txt) {
        try {
            cleanTransactionsListTxt();
            Files.writeString(Path.of(transactionsTxtFilePath, transactionsTxtFileName), txt);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readTransactionsListFromJsonFile(){
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
}
