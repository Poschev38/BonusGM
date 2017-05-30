package my.company.steps;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;

public class result 
{	
	public int sumprice = 0;
	public int discount_percentage = 0;
	public int bonus_price = 0;
	public String bonus_meal = new String("none");	
	
	public String tolog() 
	{
		return String.format("%d\t%d\t%d\t%s\n", 
		  	sumprice, discount_percentage, bonus_price, bonus_meal);
	}	
}