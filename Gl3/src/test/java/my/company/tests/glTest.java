package my.company.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;
import my.company.steps.result;

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
	static List<result> results = new ArrayList<result>();
    /*
    public static List<Object[]> data() {
    	List<Object[]> params =
                new ArrayList<Object[]>();  
        for (int i = 1; i <= 50; i++) {  
             params.add(new Object[] {i,200+(i-1)*200});  
        }  
        return params;
    */
	@Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
		
        {0,100},{1,200},{3,400},{4,500},
        {5,600},{6,700},{7,800},{8,900}, 

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
        results.add(steps.script(fprice));
        steps.Screen();
        steps.quit();
    }
    
    @AfterClass
	public static void savelog() 
    {
    PrintWriter tlog = null;
    try
    {
    	tlog = new PrintWriter(new FileOutputStream("log.txt"));
    }
    catch(FileNotFoundException e)
    {
        System.out.println("Can't create log-file");
        System.exit(0);
    }
    
    for (result res : results )  
	{
    	tlog.println(res.tolog());
	}
    tlog.close();
    }
}
