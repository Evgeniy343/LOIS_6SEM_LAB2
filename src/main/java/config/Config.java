/////////////////////////////////////////////////////////////////////////////////////////
// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 9: Построить СДНФ для заданной формулы
// Выполнил студент группы 921701 БГУИР Чурай Евгений Сергеевич
// Класс предназначен для указания файла с формулой для проверки

package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static String FILE_NAME;
    public static final String FILE_FORMAT = "txt";
    public static String IN_FILE_PATH;
    public static String OUT_FILE_PATH;
    public static final List<String> SYMBOLS = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","0","1"));
    public static final List<String> SIGNS = new ArrayList<>(Arrays.asList("!", "/\\", "\\/", "(", ")", "->", "~"));
    public static final List<String> OPERATIONS = new ArrayList<>(Arrays.asList("/\\", "\\/", "->", "~"));
    public static final String CONJUNCTION = "/\\";
    public static final String DISJUNCTION = "\\/";
    public static final String NEGATION = "!";
    public static final String IMPLICATION = "->";
    public static final String EQUIVALENCE = "~";

    public static void setFileName(String fileName) {
        FILE_NAME = fileName;
        IN_FILE_PATH = System.getProperty("user.dir") + "/files/in/" + FILE_NAME + "." + FILE_FORMAT;
        OUT_FILE_PATH = System.getProperty("user.dir") + "/files/out/" + FILE_NAME + "." + FILE_FORMAT;
    }
}
