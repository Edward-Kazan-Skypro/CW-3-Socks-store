package learn.skypro.socksstore.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class SocksFileServiceTest {
    private final SocksFileService socksFileService;

    @TempDir
    File tempDirForTesting;

    SocksFileServiceTest() {
        socksFileService = new SocksFileService();
    }

    @Test
    @DisplayName("Проверка работы метода по очистке Json файла со списком носков")
    void testCleanSocksListJson() throws IOException {
        //Для удобства дадим имя временному файлу как в рабочей программе
        String nameFileForTesting = "socksList.json";
        //Получим путь к временному файлу
        String pathFileForTesting = tempDirForTesting.getPath();
        //Проверим работу тестируемого метода
        assertTrue(socksFileService.cleanSocksListJson(pathFileForTesting, nameFileForTesting));
        //Должен появиться файл, проверим это
        assertTrue(Files.exists(Path.of(pathFileForTesting, nameFileForTesting)));
        //Удалим файл
        assertTrue(Files.deleteIfExists(Path.of(pathFileForTesting, nameFileForTesting)));
        //Проверим удаление файла
        assertFalse(Files.exists(Path.of(pathFileForTesting, nameFileForTesting)));
    }


    @Test
    @DisplayName("Проверка работы метода по получению (ранее сохраненного) Json файла со списком носков")
    void getSocksListJson() {
        String nameFileForTesting = "socksList.json";
        String pathFileForTesting = tempDirForTesting.getPath();
        //Создадим файл, который будем считывать и проверять
        File testFile = new File(pathFileForTesting + "/" + nameFileForTesting);
        assertEquals(testFile, socksFileService.getSocksListJson(pathFileForTesting, nameFileForTesting));
    }

    @Test
    @DisplayName("Проверка работы метода по сохранению в файл списка носков в формате Json")
    void saveSocksListToJsonFile() {
        String stringForSave = "string for save";
        String nameFileForTesting = "socksList.json";
        String pathFileForTesting = tempDirForTesting.getPath();
        assertTrue(socksFileService.saveSocksListToJsonFile(stringForSave, pathFileForTesting, nameFileForTesting));
    }

    @Test
    @DisplayName("Проверка работы метода по получению списка носков из файла Json")
    void readSocksListFromJsonFile() throws IOException {
        String stringForSave = "string for save";
        String nameFileForTesting = "socksList.json";
        String pathFileForTesting = tempDirForTesting.getPath();
        //Для проверки надо что-нибудь записать в файл, чтобы потом считать
        socksFileService.saveSocksListToJsonFile(stringForSave, pathFileForTesting, nameFileForTesting);
        String savedString = Files.readString(Path.of(pathFileForTesting, nameFileForTesting));
        assertEquals(stringForSave, savedString);
    }
}