package my.company.steps;

import com.google.common.base.Predicate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;


public class WebDriverSteps 
{

    private WebDriver driver;
    	public WebDriverSteps(WebDriver driver) 
    {
        this.driver = driver;
    }
	
    @Step
    public void openMainPage() 
    {
    	driver.get("https://grill-man.mirsaitov.pw/");
    }

    @Step
    public void script(int fprice) throws InterruptedException 
    {
    	/*
    	PrintWriter file = null;
        try
        {
            file = new PrintWriter(new FileOutputStream("order.txt", true));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Ошибка открытия файла order.txt");
            System.exit(0);
        }
        */
    	
    	WebElement catalog = driver.findElement(By.id("content"));
  		 List<WebElement> list = catalog.findElements(By.cssSelector("div.poduct-id")); 
  		 
  		
  		 
  		 int sumprice =  0;
  		 chosemeals: for (WebElement foods : list)  
  		 {
  			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",foods);
				 
				Thread.sleep(1000);
				
  			 WebElement name  = foods.findElement(By.cssSelector("p.tovar-name"));
  			 WebElement price = foods.findElement(By.cssSelector("p.tovar-price"));
  			 WebElement offer = foods.findElement(By.cssSelector("input.form-submit"));
  			
  			 
  			 if(Math.random() > 0.8)
  			 {
  				 offer.click();
  				 System.out.println(String.format("Блюдо: %s\n по цене: %s", name.getText(), price.getText()));
  				 
  				 Thread.sleep(3000);
  				 
  				 WebElement finalprice = driver.findElement(By.cssSelector("span.summa"));
  				 System.out.println(String.format("Итоговая цена: %s\n", finalprice.getText()));
  				 	
  				 
  				 sumprice =  Integer.parseInt((finalprice.getText()).replaceAll("\\D*\\s*", ""));
  				 
  				if( sumprice > fprice) break chosemeals;
  				
  				 
  			 }

  		 }		 
  		 
  		 if( sumprice > fprice)
  		{
  			//System.out.println("Итоговая цена больше:" + maxprice + "Руб.\n");
  			 
  			 
  			 WebElement checkout1 = driver.findElement(By.cssSelector(".cart-link-block.cart-link-block-2>a"));
  			 checkout1.click();
  			 
  			 System.out.println("Оформление заказа. Вводим данные...\n");
  			 
  			 
  			 WebDriverWait waitfor = new WebDriverWait(driver, 5, 1000);
  			 waitfor.withMessage("Элемент не был найден!");
  			 waitfor.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-customer-profile-billing-field-phone-und-0-value")));
			
  			 
  			 driver.findElement(By.id("edit-customer-profile-billing-field-phone-und-0-value")).sendKeys("Autotest/88005553535");
  			 /*
  			  * driver.findElement(By.id("edit-customer-profile-billing-field-polit-und")).click();
  			  
  			 driver.findElement(By.id("edit-continue")).click();
  			 
  			 WebElement complete = driver.findElement(By.cssSelector(".checkout-completion-message>p>strong"));
  			 System.out.println(String.format("Заказ оформлен!: %s\n", complete.getText()));
  			 
  			 WebElement numberOrder = driver.findElement(By.cssSelector(".checkout-completion-message>p>a"));
  			 numberOrder.click();
  			 
  			 WebElement finall = driver.findElement(By.cssSelector
  					 ("div.view-content"));
  			System.out.println(String.format("Информация о вашем последнем заказе: %s\n", finall.getText()));
  			 */
  		}
  		 else
  		 {
  			System.out.println("Сумма не набралась"); 
  		 }
  			
  			
  			 
  		 }
  		 
    
  			 /*
  			 waitfor.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ctools-modal-content")));
  			 WebElement bonus = driver.findElement(By.cssSelector("div.ctools-modal-content"));
  			 System.out.println(String.format("Бонус к заказу: %s\n", bonus.getText()));
  			 
  			file.println(String.format("Бонус к заказу: %s\n", bonus.getText()));
  			file.println(" ");
  			file.close();
  			 */
  			
  		 
        
        @Attachment
        @Step("Make screen shot of results page")
        public byte[] Screen() throws InterruptedException 
        {
        	Thread.sleep(1500);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        
        

        public void quit() 
        {
            driver.quit();
        }
         
}
