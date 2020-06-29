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

class DepthOfFieldTest
{
	@Test
	public void mini1part1() 
	{
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 1, 1));

		scene.setDistance(1000);
		scene.setFocalPlane(1010);
		scene.setBackground(new Color(java.awt.Color.PINK));
		scene.setAmbientLight(new AmbientLight(new

		Color(java.awt.Color.WHITE), 0));
		
		scene.addGeometries(

						new Sphere(new Point3D(0, 0,20),20,new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0)),

						new Sphere(new Point3D(30, 35, 30),20,new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0) ),
						
						new Sphere(new Point3D(60, 70, 50),20,new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0)),
								
						new Sphere(new Point3D(-30, -35,30),20,new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0)),
						
						new Sphere(new Point3D(-60, -70, 50),20,new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0)),
						
						new Sphere(new Point3D(90,105, 70),20,new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0)),
						
						new Sphere(new Point3D(-90,-105, 70),20,new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0)),
						
						new Plane(new Point3D(0, 0, 200), new Vector(0, 0, 1), new Color(0, 40, 60), new Material(0.5, 0.5, 1200, 0, 0)) 
								
						);

		scene.addLights(new SpotLight(new Color(700, 400, 400),
		new Point3D(40, -40, -115), 1, 4E-4, 2E-5, new Vector(0,0, 1)));

		ImageWriter imageWriter = new ImageWriter("mini1part1",

		200, 200, 600, 600);

		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
		}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Test
	public void mini() {
		  Scene scene = new Scene("test");*/
	       // scene.getCamera().setP0(new Point3D(/*-50*/100,/*-500*/0,/*50*/400));//z:300
//	        scene.getCamera().set_vTo(new Vector(0,1,-1));
		  
/*		  scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 0.1, 0.1));

			scene.setDistance(1000);
			scene.setFocalPlane(1010);
			scene.setBackground(new Color(java.awt.Color.PINK));
			scene.setAmbientLight(new AmbientLight(new

			Color(java.awt.Color.WHITE), 0));
			

	        Plane back = new Plane(new Point3D(0,0,-200),new Vector(0,0,1),new Color(106, 86, 77));
	        back.getMaterial().setShininess(1000);
	        Triangle tLeft = new Triangle(new Point3D(/*450-300*///150,-330,-100),new Point3D(/*250-300*/0,-150,50),
	 //               new Point3D(-900-300,0,-100),new Color(139, 101, 57));
	   //     Triangle tCentralLeft = new Triangle(new Point3D(/*250-300*/0,-150,50),new Point3D(/*170-300*/-40,0,120),
	     //           new Point3D(-900-300,0,-100),new Color(139, 101, 57));
//	        Triangle tCentralRight = new Triangle(new Point3D(/*170-300*/-40,0,120),new Point3D(/*250-300*/0,150,50),
	//                new Point3D(-900-300,0,-100),new Color(139, 101, 57));
	  //      Triangle tRight = new Triangle(new Point3D(/*250-300*/0,150,50),new Point3D(/*450-300*/150,330,-100),
	    //            new Point3D(-900-300,0,-100),new Color(139, 101, 57));
	      //  Triangle tBackLeft = new Triangle(new Point3D(/*450-300*/150,-330,-100),new Point3D(/*400-300*/200,-150,0),
	        //        new Point3D(-900-300,0,-100),new Color(139, 101, 57));
//	        Triangle tBackRight = new Triangle(new Point3D(/*400-300*/200,150,0),new Point3D(/*450-300*/150,330,-100),
	//                new Point3D(-900-300,0,-100),new Color(139, 101, 57));
//
//
	//        Sphere s1 = new Sphere(new Point3D(400-300,-30,-30),230,new Color(197, 109, 99));
	  //      scene.addGeometries(s1);
	    //    Sphere s2 = new Sphere(50,new Point3D(500-300,0,140));
	      //  s2.setEmmission(new Color(83, 6, 0));	        scene.addGeometry(s2);
	        //Sphere s3 = new Sphere(new Point3D(530-300,110/*60*/,-120),270,new Color(65, 30, 40));
	      //  scene.addGeometries(s3);
	     //   Sphere s4 = new Sphere(new Point3D(510-300,-120/*-60*/,-140),270,new Color(141, 23, 0));
	    //    scene.addGeometries(s4);

	      //  back.getMaterial().setShininess(200);

//	        scene.addGeometries(back);
	//        scene.addGeometries(tLeft);
	  //      scene.addGeometries(tCentralLeft);
	    //    scene.addGeometries(tCentralRight);
	      //  scene.addGeometries(tRight);
	        //scene.addGeometries(tBackLeft);
	   //     scene.addGeometries(tBackRight);

	     //   scene.addLight(new PointLight(new Color(70, 248, 255),new Point3D(0,200,500),
           //     0, 0.00005, 0.0000008));
	 //       scene.addLights(new SpotLight(new Color(106, 103, 104), new Point3D(-800,1200,1150),
	   //     		0.0000008, 0.00000008, 0.0000008, new Vector(1,-1,-1)));
	     //   scene.addLights(new SpotLight(new Color(83, 106, 83), new Point3D(-800,-1700,1150),
	       // 		0.0000008, 0.0000000008, 0.0000008, new Vector(1,1,-1)));*/
//	        scene.addLight(new DirectionalLight(new Color(28, 54, 195),new Vector(0,200,100)));


	   /*     ImageWriter imageWriter = new ImageWriter("MyTest4", 500, 500, 500, 500);
	        Render render = new Render(imageWriter, scene);
	        render.renderImage();
	        render.writeToImage();
		
		
	}*/
//}
		
		
		/*Scene scene = new Scene("mini");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150),new Color(255,255,255), new Material(0.9, 0.25, 100, 0.9, 1)), //
				new Triangle(
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150),new Color(java.awt.Color.WHITE), new Material(0, 0.8, 60) ),
				new Sphere(new Point3D(0, 0,20),20,new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0))
				);

		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),1, 0.00001, 0.000005, 
				   new Vector(-1, 1, 4)));

		
		//scene.addLights(new SpotLight(new Color(700, 400, 400),
			//	new Point3D(40, -40, -115), 1, 4E-4, 2E-5, new Vector(-1, 1, 4)));

		ImageWriter imageWriter = new ImageWriter("mini", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}


	//@Test
/*
	public void mini1part1() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 0.1, 0.1));

	scene.setDistance(1000);
	scene.setFocalPlane(1010);
	scene.setBackground(new Color(java.awt.Color.PINK));
	scene.setAmbientLight(new AmbientLight(new

	Color(java.awt.Color.WHITE), 0));
	
	scene.addGeometries(

					new Sphere(new Point3D(0, 0,20),20,new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0)),

					new Sphere(new Point3D(30, 35, 30),20,new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0) ),
					
					new Sphere(new Point3D(60, 70, 50),20,new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0)),
							
					new Sphere(new Point3D(-30, -35,30),20,new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0)),
					
					new Sphere(new Point3D(-60, -70, 50),20,new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0)),
					
					new Sphere(new Point3D(90,105, 70),20,new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0)),
					
					new Sphere(new Point3D(-90,-105, 70),20,new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0))
					
					new Plane(new Point3D(0, 20, 600), new Vector(0, 0, 1), new Color(0, 30, 30), new Material(0.5, 0.5, 1200)); 
							
					);

	/*scene.addGeometries(
			
	new Sphere( new Point3D(-80, -80, 200),50,new Color(java.awt.Color.RED),

	new Material(0.3, 0.7, 100, 0.5, 0)),

	new Sphere(new Point3D(-10, -10,100),50,new Color(java.awt.Color.BLUE),

	new Material(0.3, 0.7, 100, 0.5, 0)),

	new Sphere(new Point3D(50, 50, 20),50,new Color(java.awt.Color.GREEN),

	new Material(0.3, 0.7, 100, 0.5 , 0) ));*/

	/*scene.addLights(new SpotLight(new Color(700, 400, 400),
	new Point3D(40, -40, -115), 1, 4E-4, 2E-5, new Vector(0,0, 1)));

	ImageWriter imageWriter = new ImageWriter("mini1part1",

	200, 200, 600, 600);

	Render render = new Render(imageWriter, scene);

	render.renderImage();
	render.writeToImage();
	}
	
	
	
	/*@Test
	void test()
	{
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 10, 10));
		scene.setDistance(1000);
		scene.setFocalPlane(2050);
		scene.setBackground(new Color(0,0,0));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.addGeometries(
				new Triangle(new Point3D(1500, 1500, 1500),new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000),new Color(20, 20, 20), new Material(0, 0, 0, 0, 1)),
				new Sphere(new Point3D(0, 0, 50), 50,new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0)),
				new Sphere(new Point3D(0, 0, 50),25,new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));
		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), 1, 4E-5, 2E-7, new Vector(0, 0, 1)),new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

		ImageWriter imageWriter = new ImageWriter("depth of field test", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}*/
	
	/*@Test
	void test2()
	{
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 2, 2));
		scene.setDistance(1000);
		scene.setFocalPlane(4000);
		scene.setBackground(new Color(java.awt.Color.lightGray));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.addGeometries(
				 new Sphere(new Point3D(-400, -400, 100), 30, new Color(java.awt.Color.BLUE), new Material(0.3, 0.7, 100, 0.5, 0)),
				 new Sphere(new Point3D(-50, -50, 500), 30, new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5, 0)),
				 new Sphere(new Point3D(30,33, 6000), 30, new Color(java.awt.Color.BLACK), new Material(0.3, 0.5, 50, 0.7 , 0)),
				 new Sphere(new Point3D(100, 100, 10000), 30, new Color(java.awt.Color.YELLOW), new Material(0.3, 0.7, 100, 0.5, 0)),
				 new Sphere(new Point3D(200, 200, 3000), 30, new Color(java.awt.Color.ORANGE), new Material(0.3, 0.7, 100, 0.5, 0)),
				// new Plane(new Point3D(0, 1.5, 0), new Point3D(4, 0.7, 0), new Point3D(0, 0, 50000), new Color(java.awt.Color.BLACK)),
				 new Sphere(new Point3D(35, 35, -20), 30, new Color(java.awt.Color.BLUE), new Material(0.3, 0.5, 50, 0.7 , 0)));
			/*scene.addGeometries(
				new Sphere(  new Point3D(0, -25, 100),25, new Color(java.awt.Color.BLUE),new Material(0.5, 0.5, 100)),
				new Sphere(new Point3D(50, 50, -5),30,new Color(java.awt.Color.RED), new Material(0.4, 0.3, 100, 0.3, 0)));
			 */
		/*scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

		ImageWriter imageWriter = new ImageWriter("depth of field test2", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	// @Test
	/* public void mini1part1() {
	 Scene scene = new Scene("Test scene");
	 scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 20, 20));
	 scene.setDistance(1000);
	 scene.setFocalPlane(1500);
	 scene.setBackground(Color.BLACK);
	 scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));

	 scene.addGeometries(
			 new Sphere(new Point3D(-80, -80, 6600), 50, new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5, 0)),
			 new Sphere(new Point3D(-10, -10, 3600), 50, new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5, 0)),
			 new Sphere(new Point3D(50, 50, 600), 50, new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5 , 0)));


	 scene.addLights(new SpotLight(new Color(700, 400, 400), new Point3D(40, -40, -115), 1, 4E-4, 2E-5,  new Vector(0, 0, 1)));
	 
	 ImageWriter imageWriter = new ImageWriter("mini1part1", 1000, 1000, 1000, 1000);
	 Render render = new Render(imageWriter, scene);

	 render.renderImage();
	 render.writeToImage();
	 }*/
	 
//}
