package JavaTest;

import org.junit.Test;
import main.Pen;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestPen {

    String word = "UnitTest";
    String halfWord = word.substring(0, word.length()/2);
    String emptyWord = "";
    String modifiedColor = "BLACK";
    double sizeLetter = 2.0;

    @Test
    public void testWriteWithSimplePen() {
        Pen simplePen = new Pen(word.length());

        assertEquals("Написан неправильный текст!", word, simplePen.write(word));
        assertEquals("Закончились чернила, нельзя ничего писать!", emptyWord, simplePen.write(word));
        // проверка цвета чернил для стандартной ручки:
        assertEquals("Неправильный цвет чернил!", "BLUE", simplePen.getColor());
    }

    @Test
    public void testWriteWithNotEnoughInk() {
        Pen penWithNotEnoughInk = new Pen(halfWord.length(), sizeLetter);

        // Чернил достаточно только на часть текста:
        assertEquals("Написан неправильный текст!", halfWord, penWithNotEnoughInk.write(word));
    }

    @Test
    public void testWriteWithNoInk() {
        Pen penWithNoInk = new Pen(0);

        assertFalse("В ручке есть чернила!", penWithNoInk.isWork());
        assertEquals("Пустой ручкой нельзя ничего написать!", emptyWord, penWithNoInk.write(word));
    }

    @Test
    public void testWriteZeroSizeLetter() {
        Pen immortalPen = new Pen(word.length(), 0);

        /*
         *  Если размер букв сделать равным нулю или меньше, то чернила никогда не закончатся (можно написать бесконечный цикл).
         *  В этом случае при попытке написать текст, будет логичным возвращать пустую строку: "".
         *  В приложении этого не происходит и возвращается весь текст.
         *  Если это поправить, то в этом тесте ошибки не будет.
         */
        assertEquals("Нельзя отобразить текст с размером шрифта <= 0 !", emptyWord, immortalPen.write(word));
    }

    @Test
    public void testGetColorForRedefinedColor() {
        Pen penWithRedefinedColor = new Pen(word.length(), sizeLetter, modifiedColor);

        // Проверка переопределенного цвета чернил:
        assertEquals("Неправильный цвет чернил!", modifiedColor, penWithRedefinedColor.getColor());
    }

    @Test
    public void testDoSomethingElse() {
        Pen testDoSomethingElsePen = new Pen(word.length(), sizeLetter, modifiedColor);

        System.out.println("EXPECTED ink Color: " + modifiedColor);
        System.out.print("ACTUAL ink Color  : ");
        testDoSomethingElsePen.doSomethingElse();
    }
}
