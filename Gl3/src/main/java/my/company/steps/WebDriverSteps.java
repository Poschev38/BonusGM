package my.company.steps;

import com.google.common.base.Predicate;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
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
    	driver.get("http://cp41135.tmweb.ru/catalog");
    }

    @Step
    public result script(int fprice) throws InterruptedException 
    {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
 		result res = new result();
 		int sumprice =  0;
 		
 		WebElement catalog = driver.findElement(By.id("content"));
  		List<WebElement> list = catalog.findElements(By.cssSelector("div.node-catalog"));
  		
	  		while(sumprice < fprice)
			{
	  		  chosemeals: for (WebElement foods : list)  
	  		  {
	  			 WebElement name  = foods.findElement(By.cssSelector("div.left-col h2"));
	  			 WebElement price = foods.findElement(By.cssSelector("div.field-name-commerce-price"));
	  			 WebElement offer = foods.findElement(By.cssSelector("input.form-submit"));
		  		   if(Math.random() > 0.8)
		  			 {
		  				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",foods);
		  				Thread.sleep(1000);
		  				
		  				(new WebDriverWait(driver, 10)).withMessage("не найдена кнопка заказа")	
		  				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.form-submit")));
		  				 offer.click();
		  				 
		  				 System.out.println(String.format("Блюдо: %s\n по цене: %s", name.getText(), price.getText()));	
		  				 Thread.sleep(3000);
		  				 
		  				 WebElement finalprice = driver.findElement(By.cssSelector("div.final-price"));
		  				 
		  				 System.out.println(String.format("Итоговая цена: %s\n", finalprice.getText()));
		  				 
		  				 sumprice =  Integer.parseInt((finalprice.getText()).replaceAll(" ", ""));
		  		
		  				 	if( sumprice > fprice) break chosemeals;
  			         }
  			   }
		   }
	  		
  		 if( sumprice > fprice)
  		   {
  			 System.out.println("Итоговая цена больше: " + fprice + " ...Делаем заказ\n");
  			 
  			 WebElement checkout1 = driver.findElement(By.cssSelector("a.checkout-button"));
  			 checkout1.click();
  			 
  			 WebDriverWait waitfor = new WebDriverWait(driver, 5, 1000);
  			 waitfor.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-customer-profile-billing-field-phone-und-0-value")));
  			 waitfor.withMessage("Элемент не был найден!");
  			
  			 //ввод номера телефона
  			 driver.findElement(By.id("edit-customer-profile-billing-field-phone-und-0-value")).clear();
  			 driver.findElement(By.id("edit-customer-profile-billing-field-phone-und-0-value")).sendKeys("Autotest/88005553535");
  			 driver.findElement(By.id("edit-continue")).click();
  			 
  			 //форма с бонусом
  			 waitfor.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ctools-modal-content")));
  			 waitfor.until(ExpectedConditions.
  				  textToBePresentInElementLocated(By.id("modal-title"), 
  						  "Наше спец. предложение!"));
  			 
  			 WebElement bonus_meal = driver.findElement(By.cssSelector("div.title"));
  			 WebElement bonus_price = driver.findElement(By.xpath(".//*[@id='node-251']/div[1]/div[3]/div/div/div/div"));
  			 WebElement discount_percentage = driver.findElement(By.cssSelector(".discount "));
  			 
  			System.out.println(String.format("Бонусное блюдо: %s\nЦена бонусного блюда: %s\nПроцент скидки: %s",
  					bonus_meal.getText(), bonus_price.getText() ,discount_percentage.getText()));
  			
  			 //заносим параметры в класс, в котором содержатся результаты 
  			 res.sumprice = sumprice;
  			 res.discount_percentage = Integer.parseInt((discount_percentage.getText()).replaceAll("\\D*\\s*", ""));
  			 res.bonus_price = Integer.parseInt((bonus_price.getText()).replaceAll(" ", ""));
  			 res.bonus_meal = bonus_meal.getText().toString();  			 
  		  }
  		 	 return res;
      }
  		 	
        @Attachment
        @Step("Make screen shot of results page")
        public byte[] Screen() throws InterruptedException 
        {
        	Thread.sleep(2000);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        
        public void quit() 
        {
            driver.quit();
        }
}

