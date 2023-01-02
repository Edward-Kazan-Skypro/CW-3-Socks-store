package learn.skypro.socksstore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class TransactionsFileServiceTest {

    @TempDir
    File tempDirForTesting;

    private final TransactionsFileService transactionsFileService;

    TransactionsFileServiceTest() {
        transactionsFileService = new TransactionsFileService();
    }

    @Test
    @DisplayName("Проверка работы метода по очистке Json файла со списком транзакций")
    void cleanTransactionsListJson() throws IOException {
        //Для удобства дадим имя временному файлу как в рабочей программе
        String nameFileForTesting = "transactions.json";
        //Получим путь к временному файлу
        String pathFileForTesting = tempDirForTesting.getPath();
        //Проверим работу тестируемого метода
        assertTrue(transactionsFileService.cleanTransactionsListJson(pathFileForTesting, nameFileForTesting));
        //Должен появиться файл, проверим это
        assertTrue(Files.exists(Path.of(pathFileForTesting, nameFileForTesting)));
        //Удалим файл
        assertTrue(Files.deleteIfExists(Path.of(pathFileForTesting, nameFileForTesting)));
        //Проверим удаление файла
        assertFalse(Files.exists(Path.of(pathFileForTesting, nameFileForTesting)));
    }

    @Test
    @DisplayName("Проверка работы метода по очистке Txt файла со списком транзакций")
    void cleanTransactionsListTxt() throws IOException {
        //Для удобства дадим имя временному файлу как в рабочей программе
        String nameFileForTesting = "transactions.txt";
        //Получим путь к временному файлу
        String pathFileForTesting = tempDirForTesting.getPath();
        //Проверим работу тестируемого метода
        assertTrue(transactionsFileService.cleanTransactionsTxt(pathFileForTesting, nameFileForTesting));
        //Должен появиться файл, проверим это
        assertTrue(Files.exists(Path.of(pathFileForTesting, nameFileForTesting)));
        //Удалим файл
        assertTrue(Files.deleteIfExists(Path.of(pathFileForTesting, nameFileForTesting)));
        //Проверим удаление файла
        assertFalse(Files.exists(Path.of(pathFileForTesting, nameFileForTesting)));
    }

    @Test
    @DisplayName("Проверка работы метода по получению Txt файла со списком транзакций")
    void getTxtFile() {
        String nameFileForTesting = "transactions.txt";
        String pathFileForTesting = tempDirForTesting.getPath();
        //Создадим файл, который будем считывать и проверять
        File testFile = new File(pathFileForTesting + "/" + nameFileForTesting);
        assertEquals(testFile, transactionsFileService.getTxtFile(pathFileForTesting, nameFileForTesting));
    }

    @Test
    @DisplayName("Проверка работы метода по получению Json файла со списком транзакций")
    void getTransactionsListJson() {
        String nameFileForTesting = "transactions.json";
        String pathFileForTesting = tempDirForTesting.getPath();
        //Создадим файл, который будем считывать и проверять
        File testFile = new File(pathFileForTesting + "/" + nameFileForTesting);
        assertEquals(testFile, transactionsFileService.getTransactionsListJson(pathFileForTesting, nameFileForTesting));
    }

    @Test
    @DisplayName("Проверка работы метода по сохранению (записи) Json файла со списком транзакций")
    void saveTransactionsListToJsonFile() {
        String stringForSave = "string for save";
        String nameFileForTesting = "transactions.json";
        String pathFileForTesting = tempDirForTesting.getPath();
        assertTrue(transactionsFileService.saveTransactionsListToJsonFile(stringForSave, pathFileForTesting, nameFileForTesting));
    }

    @Test
    @DisplayName("Проверка работы метода по сохранению (записи) Txt файла со списком транзакций")
    void saveTransactionsToTxtFile() {
        String stringForSave = "string for save";
        String nameFileForTesting = "transactions.txt";
        String pathFileForTesting = tempDirForTesting.getPath();
        assertTrue(transactionsFileService.saveTransactionsToTxtFile(stringForSave, pathFileForTesting, nameFileForTesting));
    }

    @Test
    @DisplayName("Проверка работы метода по чтению сохраненного Json файла со списком транзакций")
    void readTransactionsListFromJsonFile() throws IOException {
        String stringForSave = "string for save";
        String nameFileForTesting = "transactions.json";
        String pathFileForTesting = tempDirForTesting.getPath();
        //Для проверки надо что-нибудь записать в файл, чтобы потом считать
        transactionsFileService.saveTransactionsListToJsonFile(stringForSave, pathFileForTesting, nameFileForTesting);
        String savedString = Files.readString(Path.of(pathFileForTesting, nameFileForTesting));
        assertEquals(stringForSave, savedString);
    }
}