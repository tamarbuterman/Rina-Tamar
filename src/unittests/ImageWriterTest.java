/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.awt.Color;

import renderer.*;
import org.junit.Test;

/**
 * @author bruri
 *
 */
public class ImageWriterTest {

	@Test
	public void test()
	{
		ImageWriter grid = new ImageWriter("grid", 1600,1000,800,500);
		Color black = new Color(0,0,0);
		Color red = new Color(255,0,0);

		for(int i = 0; i<800; i++)
		{	
			for(int j= 0;j < 500; j++)
			{
				if(j % 50==0 || i % 50 == 0)
					grid.writePixel(i, j, black);
				else
					grid.writePixel(i, j, red);
			}
		}
		grid.writeToImage();	

	}

}
