import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestPen {

    String word = "UnitTest";
    String halfWord = word.substring(0, word.length()/2);
    String emptyWord = "";
    String modifiedColor = "BLACK";
    double sizeLetter = 2.0;

    @Test
    public void testWriteSimplePen() {
        Pen simplePen = new Pen(word.length());

        assertTrue("В ручке нет чернил!", simplePen.isWork());
        assertEquals("Написан неправильный текст!", word, simplePen.write(word));
        assertEquals("Закончились чернила, нельзя ничего писать!", emptyWord, simplePen.write(word));
        // проверка цвета чернил для стандартной ручки:
        assertEquals("Неправильный цвет чернил!", "BLUE", simplePen.getColor());
    }

    @Test
    public void testWritePenWithNotEnoughInk() {
        Pen penWithNotEnoughInk = new Pen(halfWord.length(), sizeLetter);

        // Чернил достаточно только на часть текста:
        assertEquals("Написан неправильный текст!", halfWord, penWithNotEnoughInk.write(word));
        assertFalse("В ручке есть чернила!", penWithNotEnoughInk.isWork());
    }

    @Test
    public void testGetColorForRedefinedColor() {
        Pen penWithRedefinedColor = new Pen(word.length(), sizeLetter, modifiedColor);

        // Проверка переопределенного цвета чернил:
        assertEquals("Неправильный цвет чернил!", modifiedColor, penWithRedefinedColor.getColor());
    }

    @Test
    public void testWritePenWithNoInk() {
        Pen penWithNoInk = new Pen(0);

        assertFalse("В ручке есть чернила!", penWithNoInk.isWork());
        assertEquals("Пустой ручкой нельзя ничего написать!", emptyWord, penWithNoInk.write(word));
    }

    @Test
    public void testDoSomethingElse() {
        Pen testDoSomethingElsePen = new Pen(word.length(), sizeLetter, modifiedColor);

        System.out.println("EXPECTED ink Color: " + testDoSomethingElsePen.getColor());
        System.out.print("ACTUAL ink Color: ");
        testDoSomethingElsePen.doSomethingElse();
    }

    @Test
    public void testWriteImmortalPen() {
        Pen immortalPen = new Pen(word.length(), 0);

        /*
         *  Если размер букв сделать равным или меньше нуля, то чернила никогда не закончатся.
         *  При попытке написать слово, будет логичным возвращать пустую строку: "".
         *  В приложении этого не происходит и возвращается текст (можно написать бесконечный цикл).
         *  Если это поправят, в этом тесте ошибки не будет.
         */
        assertEquals("Невозможно отобразить текст с размером букв <= 0 !", emptyWord, immortalPen.write(word));
    }
}
