package ba.unsa.etf.rs.tutorijal8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class Zadatak2Test {

    @BeforeEach
    void setUp() {
        TransportDAO dao = TransportDAO.getInstance();
        dao.resetDatabase();
    }


    @Test
    void dodijeliVozacuAutobus() {
        InputStream defaultSystemIn = setupCustomInputStreamAndReturnStandardInputStream(
                "dodaj autobus\r\nMan\r\nX534\r\n59\r\n" +
                        "dodaj autobus\r\nIsuzu\r\nA77\r\n42\r\n" +
                        "dodaj autobus\r\nIcarbus\r\nB856\r\n47\r\n" +
                        "ukloni autobus\r\n2\r\n" +
                        "dodaj vozaca\r\n" +
                        "Zadatak\r\nZadatkovic\r\n4444444444444\r\n" +
                        "1.1.1980\r\n1.6.2000\r\n" +
                        "dodaj vozaca\r\n" +
                        "Aplikacija\r\nAplikacijevic\r\n" +
                        "5555555555555\r\n" +
                        "12.12.1975\r\n" +
                        "1.1.2019\r\n"+
                        "Dodijeli vozaca autobusu\r\n" +
                        "2\r\n" +
                        "1\r\n");

        String[] args= {"a","b"};


        Main.main(args);


        revertToStandardInputStream(defaultSystemIn);

        TransportDAO dao = TransportDAO.getInstance();
        Driver driver = dao.getDrivers().get(1);
        Bus bus = dao.getBusses().get(0);
        assertAll(()->{
            assertEquals(bus.getDriverOne(),driver);
        });
    }

    @Test
    void dodijeliIIspisiAutobuse() {
        InputStream defaultSystemIn = setupCustomInputStreamAndReturnStandardInputStream(
                "dodaj autobus\r\nMan\r\nX534\r\n59\r\n" +
                        "dodaj autobus\r\nIsuzu\r\nA77\r\n42\r\n" +
                        "dodaj autobus\r\nIcarbus\r\nB856\r\n47\r\n" +
                        "ukloni autobus\r\n2\r\n" +
                        "dodaj vozaca\r\n" +
                        "Zadatak\r\nZadatkovic\r\n4444444444444\r\n" +
                        "1.1.1980\r\n1.6.2000\r\n" +
                        "dodaj vozaca\r\n" +
                        "Aplikacija\r\nAplikacijevic\r\n" +
                        "5555555555555\r\n" +
                        "12.12.1975\r\n" +
                        "1.1.2019\r\n"+
                        "Dodijeli vozaca autobusu\r\n" +
                        "2\r\n" +
                        "1\r\n" +
                        "Ispisi autobuse\r\n");

        String[] args= {"a","b"};

        PrintStream defaultSystemOut = System.out;
        ByteArrayOutputStream outputHolder = setupCustomOutputStreamAndReturnIt();

        Main.main(args);

        revertToStandardOutputStream(defaultSystemOut);

        revertToStandardInputStream(defaultSystemIn);

        String expected = "Man"+" "+ "X534"+" ( seats: "+59+" )"+" - ("+"Aplikacija Aplikacijevic ( 5555555555555 )"+")";
        TransportDAO dao = TransportDAO.getInstance();
        Driver driver = dao.getDrivers().get(1);
        Bus bus = dao.getBusses().get(0);
        System.out.println("."+outputHolder.toString()+"."+expected+".");
        assertAll(()->{
            assertTrue(outputHolder.toString().contains(expected));
            assertEquals(bus.getDriverOne(),driver);
        });
    }

    private void revertToStandardOutputStream(PrintStream defaultSystemOut) {
        System.out.flush();
        System.setOut(defaultSystemOut);
    }

    private ByteArrayOutputStream setupCustomOutputStreamAndReturnIt() {
        ByteArrayOutputStream outputHolder = new ByteArrayOutputStream();
        PrintStream myStream = new PrintStream(outputHolder);
        System.setOut(myStream);
        return outputHolder;
    }

    private void revertToStandardInputStream(InputStream defaultSystemIn) {
        System.setIn(defaultSystemIn);
    }

    private InputStream setupCustomInputStreamAndReturnStandardInputStream(String s) {
        String driverInput = s;
        byte[] input = new byte[driverInput.length()];
        for (int i = 0; i < driverInput.length(); i++) {
            input[i] = (byte) driverInput.charAt(i);
        }

        ByteArrayInputStream inputHolder = new ByteArrayInputStream(input);
        InputStream defaultSystemIn = System.in;
        System.setIn(inputHolder);
        return defaultSystemIn;
    }
}