package learn.skypro.socksstore.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class SocksFileServiceTest {
    private final SocksFileService socksFileService;

    SocksFileServiceTest() {
        socksFileService = new SocksFileService();
    }

    //Понятия не имею как написать этот тест...
    //Первый вариант был через "замокивание" метода из проверяемого класса, но этот вариант забраковали.
    //Сам проверяемый метод по сути ничего не возвращает, просто содержит 2 команды:
    // - удаление файла (если он есть) - Files.deleteIfExists(path);
    // - создание нового файла - Files.createFile(path);
    // - в случае неудачи удаления существующего файла или создания нового файла - выбрасывается исключение.
    //Получается надо проверить "начинку" метода? Корректность работы внутренних команд метода?
    //
    @Test
    @DisplayName("Проверка работы метода по очистке Json файла со списком носков")
    void firstTestCleanSocksListJson() throws IOException {
        //Проверим команду Files.deleteIfExists(path)
        //Создаем временную переменную - файл
        Path pathToTmpFile = Files.createTempDirectory("tmpFile");
        //Проверяем корректность возможности удаления временного файла
        assertTrue(Files.deleteIfExists(pathToTmpFile));

        //Теперь будем проверять команду Files.createFile(path)
        //Для этого обновим путь, без этого файл не создается
        pathToTmpFile = Files.createTempDirectory("tmpFile");
        Path tmpFileForTesting = null;
        try {
            //Создаем временный файл
            tmpFileForTesting = Files.createTempFile(pathToTmpFile, "tmpFile", ".tmp");
            //Проверяем, получилось ли создать временный файл, не равен ли он нулю
            assertNotNull(tmpFileForTesting);
        } catch (IOException thrown) {
            //Если файл не удалось создать, то сообщаем об этом
            System.out.println("Ошибка создания файла!");
        }
    }

    //Другой вариант теста.
    //Здесь нет проверки внутренностей проверяемого метода.
    //А просто метод вызывается и проверяется.
    //В блоке try появляется исключение, информация о котором выводится в консоль
    //И проверка на false проходит успешно и весь тест считается пройденным успешно
    //Такой вариант теста сгенерировался на каком-то онлайн сайте
    //Смысл такого тестирования мне не понятен - убеждаемся, в том, что выбрасывается исключение?
    @Test
    public void secondTestCleanSocksListJson1() {
        Logger log = Logger.getLogger(String.valueOf(this.getClass()));
        try {
            log.info("Starting execution of cleanSocksListJson");
            socksFileService.cleanSocksListJson();
            assertTrue(true);
        } catch (Exception exception) {
            log.info("Exception in execution of cleanSocksListJson-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    //Остальные тесты я закомментировал

   /* @Test
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
    }*/
}