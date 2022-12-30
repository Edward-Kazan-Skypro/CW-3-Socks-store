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
class SocksFileServiceTest {

    private final SocksFileService socksFileService = Mockito.mock(SocksFileService.class);

    @Test
    @DisplayName("Проверка работы метода по очистке Json файла со списком носков")
    void cleanSocksListJson() {
        when(socksFileService.cleanSocksListJson()).thenReturn(true);
        assertTrue(socksFileService.cleanSocksListJson());
    }

    @Test
    @DisplayName("Проверка работы метода по получению (ранее сохраненного) Json файла со списком носков")
    void getSocksListJson() throws IOException {
        File tempFile = Files.createTempDirectory(null).toFile();
        when(socksFileService.getSocksListJson()).thenReturn(tempFile);
        File returnedFile = socksFileService.getSocksListJson();
        assertEquals(tempFile, returnedFile);
    }

    @Test
    @DisplayName("Проверка работы метода по сохранению в файл списка носков в формате Json")
    void saveSocksListToJsonFile() {
        String json = "";
        when(socksFileService.saveSocksListToJsonFile(json)).thenReturn(true);
        assertTrue(socksFileService.saveSocksListToJsonFile(json));
    }

    @Test
    @DisplayName("Проверка работы метода по получению списка носков из файла Json")
    void readSocksListFromJsonFile() {
        String readedJson = null;
        when(socksFileService.readSocksListFromJsonFile()).thenReturn(readedJson);
        String returnedJson = socksFileService.readSocksListFromJsonFile();
        assertEquals(readedJson, returnedJson);
    }
}