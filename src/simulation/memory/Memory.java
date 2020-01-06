package simulation.memory;

/**
 * <p/>Memory model for simulation</p>
 * @author Pavel Muhortov
 */
public class Memory {
	protected int memSize;
	protected int[] memory;
	
	/**
	 * <p/>Constructor for object memory with size "size"</p>
	 * @param size
	 */
	public Memory(int size) {
		memSize = size;
		memory = new int[size];
		for (int i=0; i<memory.length; i++) {
			memory[i] = 0;
		}
	}
	/**
	 * @return full size of memory
	 */
	public int getSizeFull() {
		return memSize;
	}
	/**
	 * @return free size of memory
	 */
	public int getSizeFree() {
		int sizeFree = 0;
		for (int i=0; i<memSize; i++) {
			if (memory[i] == 0) {
				sizeFree ++;
			}
		}
		return sizeFree;
	}
	/**
	 * <p/>Checking memory for free blocks</p>
	 * @return all free blocks
	 */
	protected int[] getFreeBlocks() {
		int memIndex = 0, startCheckIndex = 0;
		int[] freeBlocks = new int[memSize];
		while (memIndex<memSize) {
			startCheckIndex = memIndex;
			int memFreeSize = 0;
			boolean memHave = false;
			while (memIndex<memSize && memory[memIndex] == 0) {
				memFreeSize++;
				memIndex++;
				memHave = true;
			}
			if (memHave) {
				freeBlocks[startCheckIndex] = memFreeSize;
			}
			memIndex++;			
		}
		return freeBlocks;
	}
	/**
	 * @return memory content
	 */
	public String getContent() {
		String result="";
		for (int i:memory) {
			result +=Integer.toString(i);
		}
		return result;
	}
	/**
	 * <p/>Adding content to memory from "start" position of "size" size</p>
	 * @param start
	 * @param size
	 */
	public void add(int start, int size) {
		for (int i=start; i<start+size; i++) {
			memory[i]=1;
		}
	}
	/**
	 * <p/>Deleting content to memory from "start" position of "size" size</p>
	 * @param start
	 * @param size
	 */
	public void del(int start, int size) {
		for (int i=start; i<start+size; i++) {
			memory[i]=0;
		}
	}
}