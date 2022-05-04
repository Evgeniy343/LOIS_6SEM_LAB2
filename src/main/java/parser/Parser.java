/////////////////////////////////////////////////////////////////////////////////////////
// Лабораторная работа №2 по дисциплине ЛОИС
// Вариант 9: Построить СДНФ для заданной формулы
// Выполнил студент группы 921701 БГУИР Чурай Евгений Сергеевич
// Класс предназначен для парсинга формул

package parser;

import config.Config;

import java.util.*;

public class Parser {
    private final String EXPRESSION;

    private ExpressionTree tree;
    private boolean result;
    private String message;

    private final Set<String> ELEMENTS;
    private final List<String> ATOMS;

    public Parser(String expression) throws SDNFException {
        this.EXPRESSION = expression;
        ELEMENTS = new HashSet<>();
        ATOMS = new ArrayList<>();
        result = false;
        message = "";
        try {
            checkSymbols();//проверка символов
            checkBrackets();
            tree = new ExpressionTree(expression, this);

        } catch (SDNFException sknfException) {
            throw new SDNFException(sknfException.getNumber());
        }
    }

    private void checkSymbols() throws SDNFException {
        if (EXPRESSION.length() == 1) {//проверка на неизвестный символ
            if (!Config.SYMBOLS.contains(EXPRESSION)) {
                throw new SDNFException(6);
            }
        }
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (!(Config.SYMBOLS.contains("" + EXPRESSION.charAt(i)) || Config.SIGNS.contains("" + EXPRESSION.charAt(i)))) {
                String sign = searchSign(EXPRESSION, i);
                if (!Config.SIGNS.contains(sign)) {
                    throw new SDNFException(6);
                } else {
                    if (sign.length() == 2) {
                        i++;
                    }
                }
            }
        }
    }

    private String searchSign(String expression, int pointer) {//поиск бинарных и унарных операторов
        if (expression.charAt(pointer) == '!' || expression.charAt(pointer) == '~')
            return expression.charAt(pointer) + "";
        return "" + expression.charAt(pointer) + expression.charAt(pointer + 1);
    }

    private void checkBrackets() throws SDNFException {
        if (EXPRESSION.contains(")(")) {//скобки в разные стороны
            throw new SDNFException(3);
        }
        if (EXPRESSION.charAt(0) == ')') {//закрывающая скобка перед открывающей
            throw new SDNFException(3);
        }
        if (EXPRESSION.charAt(0) != '(' && EXPRESSION.length() != 1) {//проверка на атом
            throw new SDNFException(3);
        }
        if (EXPRESSION.charAt(0) == '(' && EXPRESSION.charAt(EXPRESSION.length() - 1) != ')') {//проверка на выражение
            throw new SDNFException(3);
        }
        int checkOpen = 0;
        int checkClose = 0;
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (EXPRESSION.charAt(i) == '(') {
                checkOpen++;
            } else if (EXPRESSION.charAt(i) == ')') {
                checkClose++;
            }
        }
        //проверка на количество открывающих и закрывающих скобок
        if (checkOpen > checkClose) {
            throw new SDNFException(1);
        }
        if (checkClose > checkOpen) {
            throw new SDNFException(2);
        }
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void addElements(String element) {
        ELEMENTS.add(element);
    }

    public void addAtoms(String atom) {
        ATOMS.add(atom);
    }

    public Set<String> getELEMENTS() {
        return ELEMENTS;
    }

    public List<String> getATOMS() {
        return ATOMS;
    }

    public ExpressionTree getTree() {
        return tree;
    }
}
