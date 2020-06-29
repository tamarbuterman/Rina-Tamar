package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

class supersamplingTest
{
		@Test
		public void test() 
		{
			Scene scene = new Scene("test");
			scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 1, 1));
			scene.setDistance(1000);
			scene.setFocalPlane(1010);
			scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
			scene.setBackground(Color.BLACK);
			
			//Plane plane1 = new Plane(new Point3D(0, 5, 100), new Vector(0, 1, 0), new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0)); 
			Plane plane2 = new Plane(new Point3D(0, 0, 300), new Vector(0, 0, 1), new Color(0, 40, 60), new Material(0.5, 0.5, 1200, 0, 0)); 

			for(int i=108, count=0; i>-100; i -=18, count++)
			{
				if(count%3 == 0)
					scene.addGeometries(new Triangle(new Point3D(i-18, -140, 50), new Point3D(i, -140, 50), new Point3D(i-9, -110,50), new Color(java.awt.Color.GREEN), new Material(0.5, 0.5, 1200, 0.4, 0.4)));
				if(count%3 == 1)
					scene.addGeometries(new Triangle(new Point3D(i-18, -140, 50), new Point3D(i, -140, 50), new Point3D(i-9, -110,50), new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 1200, 0, 0.4)));
				if(count%3 == 2)
					scene.addGeometries(new Triangle(new Point3D(i-18, -140, 50), new Point3D(i, -140, 50), new Point3D(i-9, -110,50), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 1200, 0.4, 0.4)));
			}
			
			double i=15, j=3;
			for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
			{
				scene.addGeometries(new Sphere(new Point3D(x, y, 200), 10, new Color(40, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.4))); 
			}
			i=15;
			j=-3;
			for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
			{
				scene.addGeometries(new Sphere(new Point3D(x, y, 20), 10, new Color(40, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.4))); 
			}
			scene.addGeometries(

					new Sphere(new Point3D(5, 10 ,20),15,new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0)),

					new Sphere(new Point3D(35, 45, 70),15,new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0) ),
					
					new Sphere(new Point3D(10, 70, 150),15,new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0)),
							
					new Sphere(new Point3D(-20, -25,70),15,new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0)),
					
					new Sphere(new Point3D(-10, -70, 150),15,new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0))
					
					);

			scene.addGeometries(plane2);
			
			DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
		    SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point3D(-100, 0, 0), 1, 4E-4, 2E-5, new Vector(0,0, 1));
		    PointLight point_light = new PointLight(new Color(400, 300, 300), new Point3D(0, 145, 50), 1, 0.0005, 0.000005);
			
			ImageWriter imageWriter = new ImageWriter("miniPfoject2 test", 200, 250, 600, 500);
			
			scene.addLights(point_light, direction_light, spot_light);

			Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

			render.renderImage();
			render.writeToImage();
	}

}
