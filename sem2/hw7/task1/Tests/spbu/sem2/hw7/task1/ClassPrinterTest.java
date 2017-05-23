package spbu.sem2.hw7.task1;

import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ClassPrinterTest {

    @Test
    public void classPrinterTest() throws IOException {
        File fileTest = new File("outputTest.txt");
        File fileCorrect = new File("outputCorrect.txt");
        ClassPrinter cp = new ClassPrinter(TestClass.class, fileTest);
        cp.print();
        Assert.assertTrue(compareFiles(fileTest, fileCorrect));
    }

    public boolean compareFiles(File file1, File file2) throws FileNotFoundException {
        Scanner sc1 = new Scanner(new FileReader(file1));
        Scanner sc2 = new Scanner(new FileReader(file2));
        while (sc1.hasNext() && sc2.hasNext()) {
            String str1 = sc1.nextLine();
            String str2 = sc2.nextLine();
            if (!str1.equals(str2)) {
                System.out.println(str1);
                System.out.println(str2);
                return false;
            }
        }
        return true;
    }
}