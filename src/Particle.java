import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Particle {
	float x;
	float y;
	float angle;
	
	Particle(float x, float y){
		this.x = x;
		this.y = y;
		
		Random rand = new Random();
		angle = rand.nextFloat() * 360;
	}
	
	void Draw(){
		 
		GL11.glColor3f(0,1,0);

		GL11.glBegin(GL11.GL_LINE_LOOP);
		int num_segments = 100;
	    for(int i = 0; i < num_segments; i++)
	    {
	        float theta = 2.0f * 3.1415926f * (float) ((float)i / (float)num_segments);//get the current angle

	        float cx = 5 * (float) Math.cos(theta);//calculate the x component
	        float cy = 5 * (float) Math.sin(theta);//calculate the y component

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
		
		float newX = x + ((float) Math.cos(Math.toRadians(angle)) * ((float) Math.pow(5, 1.2) / 3));
		float newY = y + ((float) Math.sin(Math.toRadians(angle)) * ((float) Math.pow(5, 1.2) / 3));
		
		if (newX < Game.width && newX > 0)
			x = newX;
		if (newY < Game.height && newY > 0)
			y = newY;
	}
}
