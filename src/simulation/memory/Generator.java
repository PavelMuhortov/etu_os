package simulation.memory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p/>Generator set</p>
 * @author Pavel Muhortov
 */
final class Generator {
	private Generator() {}
	
	/**
	 * <p/>Random number generator</p>
	 * @param min
	 * @param max
	 * @return random integer in "min".."max" range
	 */
	public static int randInt(int min, int max){
	    return  ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}