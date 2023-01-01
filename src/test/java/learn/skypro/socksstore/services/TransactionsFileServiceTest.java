package learn.skypro.socksstore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransactionsFileServiceTest {
    private final TransactionsFileService transactionsFileService = Mockito.mock(TransactionsFileService.class);

    @Test
    @DisplayName("Проверка работы метода по очистке Json файла со списком транзакций")
    void cleanTransactionsListJson() {
        when(transactionsFileService.cleanTransactionsListJson()).thenReturn(true);
        assertTrue(transactionsFileService.cleanTransactionsListJson());
    }

    @Test
    @DisplayName("Проверка работы метода по очистке Txt файла со списком транзакций")
    void cleanTransactionsListTxt() {
        when(transactionsFileService.cleanTransactionsListTxt()).thenReturn(true);
        assertTrue(transactionsFileService.cleanTransactionsListTxt());
    }

    @Test
    @DisplayName("Проверка работы метода по получению Txt файла со списком транзакций")
    void getTxtFile() throws IOException {
        File tempFile = Files.createTempDirectory(null).toFile();
        when(transactionsFileService.getTxtFile()).thenReturn(tempFile);
        File returnedFile = transactionsFileService.getTxtFile();
        assertEquals(tempFile, returnedFile);
    }

    @Test
    @DisplayName("Проверка работы метода по получению Json файла со списком транзакций")
    void getTransactionsListJson() throws IOException {
        File tempFile = Files.createTempDirectory(null).toFile();
        when(transactionsFileService.getTransactionsListJson()).thenReturn(tempFile);
        File returnedFile = transactionsFileService.getTransactionsListJson();
        assertEquals(tempFile, returnedFile);
    }

    @Test
    @DisplayName("Проверка работы метода по сохранению (записи) Json файла со списком транзакций")
    void saveTransactionsListToJsonFile() {
        String json = "";
        when(transactionsFileService.saveTransactionsListToJsonFile(json)).thenReturn(true);
        assertTrue(transactionsFileService.saveTransactionsListToJsonFile(json));
    }

    @Test
    @DisplayName("Проверка работы метода по сохранению (записи) Txt файла со списком транзакций")
    void saveTransactionsToTxtFile() {
        String txt = "";
        when(transactionsFileService.saveTransactionsToTxtFile(txt)).thenReturn(true);
        assertTrue(transactionsFileService.saveTransactionsToTxtFile(txt));
    }

    @Test
    @DisplayName("Проверка работы метода по чтению сохраненного Json файла со списком транзакций")
    void readTransactionsListFromJsonFile() {
        String readedJson = null;
        when(transactionsFileService.readTransactionsListFromJsonFile()).thenReturn(readedJson);
        String returnedJson = transactionsFileService.readTransactionsListFromJsonFile();
        assertEquals(readedJson, returnedJson);
    }
}