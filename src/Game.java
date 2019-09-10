import  org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.List;
import java.util.Random;


public class Game {
	public static int width=640;
	public static int height=640;
	private static String title="Game";
	//static public List<GameObject> objList;
	
	long window;
	
	Balloon balloon;
	
	float sizeIncrement = 1.2f;
	float sizeDecrement = 0.1f;
	
	public Game()
	{
		//objList = new java.util.LinkedList<GameObject>();
		
		Random rand = new Random();
		
		//objList.add(new Bouncer(100, 100, width, heigth));
		
		/* do the wave
		for(int i = 0; i < height; i += 10){
			if ((i / 10) % 2 == 0)
				objList.add(new Bouncer(i, i, width, height));
			else
				objList.add(new VerticalBouncer(i, i+10, width, height));
		
		}*/
		
	}
	
	
	// returns window id
	public long init()
	{
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		

		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		//set up OpenGL
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwSwapInterval(1);
		
		// screen clear is white (this could go in drawFrame if you wanted it to change
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		
		// set projection to dimensions of window
        // set viewport to entire window
        GL11.glViewport(0,0,width,height);
         
        // set up orthographic projection to map world pixels to screen
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        balloon = new Balloon((float) (width / 2), (float) (height / 2));
		
		return window;
	}
	
	public boolean keyPressed(int x){
		return glfwGetKey(window, x) == GLFW_PRESS;
	}
	
	boolean click = false; //false for left click, true for right click
	boolean animation = false;
	
	public void drawFrame(float delta)
	{
		GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		balloon.Draw();
		
		if (keyPressed(GLFW_KEY_LEFT)){
			if (!click){
				balloon.size += sizeIncrement;
			}
		}
		if (keyPressed(GLFW_KEY_RIGHT)){
			if (click){
				balloon.size += sizeIncrement;
			}
		}
		if (keyPressed(GLFW_KEY_SPACE)){
			balloon.flying = true;
		}
		
		if (balloon.size > 10)
			if (balloon.flying)
				balloon.size -= sizeDecrement * (Math.pow(balloon.size, 1.2) / 15);
			else
				balloon.size -= sizeDecrement;
		
		if (balloon.size > balloon.maxSize){
			animation = true;
		}
		
		if (animation){
			if (balloon.animationTimer > 0){
				balloon.PopAnimation();
			}
		}
			
		if (balloon.flying && balloon.size <= 10)
			balloon.Reset();
		
		/*
		for (GameObject go : objList)
		{
			go.update(delta);
		}
		
		for (GameObject go : objList)
		{
			go.draw();

		}
		*/
		
	}



}
