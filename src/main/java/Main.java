/////////////////////////////////////////////////////////////////////////////////////////
// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 9: Построить СДНФ для заданной формулы
// Выполнил студент группы 921701 БГУИР Чурай Евгений Сергеевич
// Класс предназначен для проверки формулы и для проверки знаний пользователя

import config.Config;
import parser.Formula;
import parser.SDNFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String expression = "";

        while (true) {
            Integer choice = menu();
            switch (choice) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    expression = scanner.nextLine();
                    break;
                case 2:
                    StringBuilder builder = new StringBuilder(expression);
                    Config.setFileName(chooseFileName());
                    try (FileInputStream fin = new FileInputStream(Config.IN_FILE_PATH)) {
                        while (fin.available() > 0) {
                            int oneByte = fin.read();
                            builder.append(((char) oneByte));
                        }
                        expression = builder.toString();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not find!!!");
                    }
                    break;
                case 3:
                    System.exit(0);
            }
            try {
                Formula formula = new Formula(expression);
                printResult(expression, formula);
            } catch (SDNFException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printResult(String expression, Formula formula) throws SDNFException {
        System.out.println(expression);
        System.out.println();
        if (formula.isResult()) {
            formula.output();
            System.out.println(formula.getResultParser());
            System.out.println("\n");
        }
    }

    public static Integer menu(){
        System.out.println("1.Input mode");
        System.out.println("2.Read from file");
        System.out.println("3.Exit");
        System.out.print("Enter your choice:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        return Integer.parseInt(choice);
    }

    public static String chooseFileName() {
        System.out.println("Enter file name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
