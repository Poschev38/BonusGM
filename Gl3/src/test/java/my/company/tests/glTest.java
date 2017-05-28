package my.company.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class glTest 
{

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
		
        {1,500},{2,1000},{3,1500},
        {4,2000},{5,2500},{6,3000},
		});
    }
    
    int fprice;
    int fno;
	
    public glTest(int no, int price) {
        fprice= price;
        fno= no;     	
    }
        
    private WebDriverSteps steps;
    @Before
    public void setUp() throws Exception
    {
        ChromeDriverManager.getInstance().setup();
        steps = new WebDriverSteps(new ChromeDriver());
    }
	
    @Test
    public void test() throws Exception
    {	
    	System.out.println(String.format("Номер теста: %d\n", fno));
    	System.out.println(String.format("Cтоимость заказа: %d\n", fprice));
		
        steps.openMainPage();
        steps.script(fprice, fno);
        steps.Screen();
        steps.quit();
    }
    
    
    
}