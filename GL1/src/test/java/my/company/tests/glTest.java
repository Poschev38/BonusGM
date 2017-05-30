package my.company.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;

import java.util.Arrays;
import java.util.List;

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
		
        {1,1000},//{2,1500},
        /*
		{4,2500},{5,3000},{6,3500},
		{7,7000},{8,1500},{9,4500},
		{10,5000},{11,5500},{12,6000},
		*/
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
        steps.script(fprice);
        steps.Screen();
        steps.quit();
    }
    
    
}