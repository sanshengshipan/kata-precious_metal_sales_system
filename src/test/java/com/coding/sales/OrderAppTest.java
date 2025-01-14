package com.coding.sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderAppTest {
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][]{
                {"sample_command.json", "sample_result.txt"},
                {"test01_command.json", "test01_result.txt"},
                {"test02_command.json", "test02_result.txt"},
                {"test_command.json", "test_result.txt"}
        };

        return Arrays.asList(data);
    }

    private String commandFileName;
    private String expectedResultFileName;

    public OrderAppTest(String commandFileName, String expectedResultFileName) {
        this.commandFileName = commandFileName;
        this.expectedResultFileName = expectedResultFileName;
    }

    @Test
    public void should_checkout_order() {
        String orderCommand = FileUtils.readFromFile(getResourceFilePath(commandFileName));
        OrderApp app = new OrderApp();
        String actualResult = app.checkout(orderCommand);
        System.out.println("actualResult:="+actualResult);
        String expectedResult = FileUtils.readFromFile(getResourceFilePath(expectedResultFileName));
        System.out.println("expectedResult:="+expectedResult);
        assertEquals(expectedResult, actualResult);
    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }

}
