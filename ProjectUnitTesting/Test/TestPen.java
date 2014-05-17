import org.junit.Ignore;
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
    public void testSimplePen() {
        Pen simplePen = new Pen(word.length());

        assertTrue("В ручке нет чернил!", simplePen.isWork());
        assertEquals("Написан неправильный текст!", word, simplePen.write(word));
        // Чернила кончились и ничего написать уже нельзя:
        assertEquals("Написан неправильный текст!", emptyWord, simplePen.write(word));
        // проверка цвета чернил для стандартной ручки:
        assertEquals("Неправильный цвет чернил!", "BLUE", simplePen.getColor());
    }

    @Test
    public void testPenWithNotEnoughInk() {
        Pen penWithNotEnoughInk = new Pen(halfWord.length(), sizeLetter);

        // Чернил достаточно только на часть текста:
        assertEquals("Написан неправильный текст!", halfWord, penWithNotEnoughInk.write(word));
    }

    @Test
    public void testPenWithRedefinedColor() {
        Pen penWithRedefinedColor = new Pen(word.length(), sizeLetter, modifiedColor);

        // Проверка переопределенного цвета чернил:
        assertEquals("Неправильный цвет чернил!", modifiedColor, penWithRedefinedColor.getColor());
    }

    @Test
    public void testPenWithNoInk() {
        Pen penWithNoInk = new Pen(0);

        assertFalse("В ручке есть чернила!", penWithNoInk.isWork());
        assertEquals("Написан неправильный текст!", emptyWord, penWithNoInk.write(emptyWord));
    }

    @Test
    public void testDoSomethingElsePen() {
        Pen testDoSomethingElsePen = new Pen(word.length());

        System.out.println("Expected ink Color: " + testDoSomethingElsePen.getColor());
        System.out.print("Actual ink Color: ");
        testDoSomethingElsePen.doSomethingElse();
    }

    @Ignore
    @Test (expected = Exception.class)
    public void testImmortalPen() {
        Pen immortalPen = new Pen(word.length(), 0);

        do {
            immortalPen.write(word);
        } while (immortalPen.isWork());
    }
}
