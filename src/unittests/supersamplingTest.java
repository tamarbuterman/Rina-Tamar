package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
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
		public void testGlossySurface() 
		{
			Scene scene = new Scene("test");
			scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
			scene.setDistance(1000);
			scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
			scene.setBackground(Color.BLACK);
			//kt is 0.05
			Plane plane1 = new Plane(new Point3D(0, 5, 100), new Vector(0, 1, 0), new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0, 0.3)); 
			//Plane plane2 = new Plane(new Point3D(0, 5, 100), new Vector(-0.4, 1.5, 0), new Color(0,0,0), new Material(0.5, 0.5, 300, 0, 0.4)); 
			Plane plane2 = new Plane(new Point3D(0, 0, 300), new Vector(0, 0, 1), new Color(0, 40, 60), new Material(0.5, 0.5, 1200)); 

			Sphere sun = new Sphere(new Point3D(0, 0, 50), 30, new Color(java.awt.Color.YELLOW), new Material(0.5, 0.5, 1200, 0, 0.4));
			Triangle t1 = new Triangle(new Point3D(-30, 10, 50), new Point3D(-10, 35, 50), new Point3D(-60, 25,50), new Color(java.awt.Color.YELLOW), new Material(0.5, 0.5, 1200, 0, 0.4));
			Triangle t2 = new Triangle(new Point3D(80, -120, 50), new Point3D(70, -100, 50), new Point3D(50, -110,50), new Color(java.awt.Color.YELLOW), new Material(0.5, 0.5, 1200, 0, 0.4));
			Triangle t3 = new Triangle(new Point3D(90, -100, 50), new Point3D(110, -100, 50), new Point3D(97, -75,50), new Color(java.awt.Color.YELLOW), new Material(0.5, 0.5, 1200, 0, 0.4));
			
			//Sphere sphere = new Sphere(new Point3D(75, -80, 100), 20, new Color(255, 0, 0), new Material(0.5, 0.5, 1200, 0, 0.4)); // sphere 1
			//Sphere sphere4 = new Sphere(new Point3D(-25, -48, 300), 20, new Color(0, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.4)); // sphere 4
			//Sphere sphere2 = new Sphere(new Point3D(25, -18, 200), 20, new Color(0, 255, 0), new Material(0.5, 0.5, 1200,  0, 0.4)); // sphere 2
			//Sphere sphere3 = new Sphere(new Point3D(-75, -33, 140), 20, new Color(0, 0, 255), new Material(0.5, 0.5, 1200,  0, 0.4)); // blue
		

			

			scene.addGeometries(plane1, plane2,sun, t1);

			Point3D pos = new Point3D(0, -30, 30);

			//Point3D pos = new Point3D(0, 0, 20);
			Color color = new Color(400, 300, 300);

			PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
			//ImageWriter imageWriter = new ImageWriter("Glossy test (" + samples +")",200, 150, 600, 450);
			
			ImageWriter imageWriter = new ImageWriter("miniPfoject2 test", 200, 250, 600, 500);
			
			scene.addLights(point_light);

			Render render = new Render(imageWriter, scene);
			render.renderImage();
			render.writeToImage();
	}

}
