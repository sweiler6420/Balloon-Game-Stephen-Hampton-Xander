import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class GameLoop {
	public static void main(String[] args)
	{


		Game g = new Game();
		long window=g.init();

		float time = (float)glfwGetTime();
		// Run the rendering loop until the user has attempted to close
		// the window
		while ( !glfwWindowShouldClose(window) ) {

			glfwPollEvents();
			float time2=(float)glfwGetTime();
			g.drawFrame(time2-time);
			glfwSwapBuffers(window);
			time=time2;
			
		}

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
	}
}
