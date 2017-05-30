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
	
    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
		
        //_____________________________\\
        {1,1000},{2,1500},{3,2000},
        {4,2500},{5,3000},{6,3500},
        {7,4000},{8,4500},{9,5000},
     
        {10,5500},{11,6000},{12,6500},
        {13,6500},{14,7000},{15,7500},
        {14,8000},{15,8500},{16,9000},
        /*
        {17,9500},{18,10000},{19,10500},
        {20,11000},{21,11500},{22,12000},
        {23,12500},{24,13000},{25,13500},
        
        {26,14000},{27,14500},{28,15000},
        {29,15500},{30,16000},{31,16500},
        {32,17000},{33,17500},{34,18000},
        
        {35,18500},{36,19000},{37,20000},
        {38,20500},{39,21000},{40,21500},
        {41,22000},{42,22500},{43,23000},
        
        {44,23500},{45,24000},{46,24500},
        {47,25000},{48,25500},{49,26000},
        {50,26500},{51,27000},{52,27500},
        
        {53,28000},{54,28500},{55,29000},
        {56,29500},{57,30000},{58,30500},
        {59,31000},{60,31500},{61,32000},
        */
        //_____________________________\\
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