package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

class finalTest {

	@Test
	void test()
	{
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.setDistance(1000);
		scene.setBackground(new Color(255,0,0));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.addGeometries(
				new Triangle(new Point3D(1500, 1500, 1500),new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000),new Color(20, 250, 250), new Material(0, 0, 0, 0, 1)),
				new Sphere(new Point3D(0, 0, 50), 50,new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0)),
				new Sphere(new Point3D(0, 0, 50),25,new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));
		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), 1, 4E-5, 2E-7, new Vector(0, 0, 1)),new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

		ImageWriter imageWriter = new ImageWriter("final test", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

}
