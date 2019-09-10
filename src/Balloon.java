import org.lwjgl.opengl.GL11;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.Random;

public class Balloon {
	float x;
	float y;
	float size = 10;
	
	ArrayList<Particle> particleList = new ArrayList<Particle>();
	
	float maxSize;
	
	float angle;
	
	boolean flying = false;
	int animationTimer = 100;
	
	public Balloon(float x, float y){
		this.x = x;
		this.y = y;
		
		Random rand = new Random();
		
		maxSize = 100 + (rand.nextFloat() * 100);
		angle = rand.nextFloat() * 360;
		
	}
	
	void Draw(){
		 
		if (flying)
			FlyingNewPoint();
		
		GL11.glColor3f(0,1,0);

		GL11.glBegin(GL11.GL_LINE_LOOP);
		int num_segments = 100;
	    for(int i = 0; i < num_segments; i++)
	    {
	        float theta = 2.0f * 3.1415926f * (float) ((float)i / (float)num_segments);//get the current angle

	        float cx = size * (float) Math.cos(theta);//calculate the x component
	        float cy = size * (float) Math.sin(theta);//calculate the y component

	        GL11.glVertex2f(x + cx, y + cy);//output vertex

	    }
	    GL11.glEnd();
	    
	}
	
	void FlyingNewPoint(){
		Random rand = new Random();
		
		int choice = rand.nextInt(2); //generate either 0 or 1
		if (choice == 0){
			angle -= rand.nextFloat() * 30;
		}
		else{
			angle += rand.nextFloat() * 30;
		}
		
		float newX = x + ((float) Math.cos(Math.toRadians(angle)) * ((float) Math.pow(size, 1.2) / 3));
		float newY = y + ((float) Math.sin(Math.toRadians(angle)) * ((float) Math.pow(size, 1.2) / 3));
		
		if (newX < Game.width && newX > 0)
			x = newX;
		if (newY < Game.height && newY > 0)
			y = newY;
	}
	
	void PopAnimation(){
		int num_segments = (int) size * 5;
	    for(int i = 0; i < num_segments; i++)
	    {
	        float theta = 2.0f * 3.1415926f * (float) ((float)i / (float)num_segments);//get the current angle

	        float cx = size * (float) Math.cos(theta);//calculate the x component
	        float cy = size * (float) Math.sin(theta);//calculate the y component

	        Particle newParticle = new Particle(x + cx, y + cy);
	        particleList.add(newParticle);

	    }
	    
		for (int i = 0; i < particleList.size(); i++){
			Particle thisParticle = particleList.get(i);
			thisParticle.FlyingNewPoint();
		}
	}
	
	void Reset(){
		x = Game.width / 2;
		y = Game.height / 2;
		size = 10;
		flying = false;
	}
	
	
}
