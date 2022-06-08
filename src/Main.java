import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static String pathToSaves = "C:/Games/saveGames/";
    private static String zipSaves = "C:/Games/saveGames/saveGames.zip";

    public static void main(String[] args) {

        GameProgress save1 = new GameProgress("save1", 100, 15, 1, 0);
        GameProgress save2 = new GameProgress("save2", 80, 12, 5, 925);
        GameProgress save3 = new GameProgress("save3", 43, 2, 13, 8953);

        List<String> savesPaths = new ArrayList<>();
        savesPaths.add(pathToSaves + save1.getName());
        savesPaths.add(pathToSaves + save2.getName());
        savesPaths.add(pathToSaves + save3.getName());

        File gameSave1 = saveGame(pathToSaves, save1);
        File gameSave2 = saveGame(pathToSaves, save2);
        File gameSave3 = saveGame(pathToSaves, save3);

        zipFiles(zipSaves, savesPaths);

        deleteGameSave(gameSave1);
        deleteGameSave(gameSave2);
        deleteGameSave(gameSave3);

    }

    public static File saveGame(String path, GameProgress save) {
        File saveFile = new File(path + save.getName());
//        Создание файла сохранения игры:
        try {
            if (saveFile.createNewFile()) {
                System.out.println("\nФайл сохранения" + " (" + save.getName() + ") успешно создан.");
            } else {
                System.out.println("\nПроизошла ошибка при попытке создания файла сохранения :'(");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
//        Запись экземпляра класса GameProgress в созданный файл сохранения:
        try (FileOutputStream fos = new FileOutputStream(saveFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
            System.out.println("Игровой прогресс успешно сохранен в файл" + " (" + save.getName() + ")");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Произошла ошибка при записи игрового прогресса :'(");
        }
        return saveFile;
    }

    public static void zipFiles(String path, List<String> saveGames) {
        try (FileOutputStream fos = new FileOutputStream(zipSaves);
             ZipOutputStream zout = new ZipOutputStream(fos)) {
            for (String saveGame : saveGames) {
                try (FileInputStream fis = new FileInputStream(saveGame)) {
                    ZipEntry entry = new ZipEntry(saveGame.substring(pathToSaves.length()));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteGameSave(File gameSave) {
        if (gameSave.delete()) {
            System.out.println("Сохранение игры" + " (" + gameSave.getName() + ")" + " успешно удалено.");
        } else {
            System.out.println("При попытке удаления сохранения" + " (" + gameSave.getName() + ")" + " произошла ошибка :(");
        }
    }
}
