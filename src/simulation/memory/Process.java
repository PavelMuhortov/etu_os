package simulation.memory;

/**
 * <p/>Process model for simulation</p>
 * @author Pavel Muhortov
 */
public class Process {
	public int memStart;
	public int memSize;
	public int lifeTime;
	
	/**
	 * <p/>Constructor for object process with memory "minSize".."maxSize" and lifetime "minTime".."maxTime"</p>
	 * @param minSize
	 * @param maxSize
	 * @param minTime
	 * @param maxTime
	 */
	public Process(int minSize, int maxSize, int minTime, int maxTime) {
		memSize=Generator.randInt(minSize, maxSize);
		lifeTime=Generator.randInt(minTime, maxTime);
	}
}