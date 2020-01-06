package simulation.memory;

public class MemoryFind extends Memory{
	
	public MemoryFind(int size) {
		super(size);
	}
	/**
	 * @param procSize
	 * @return first position for adding content to memory or -1 if not free space
	 */
	public int first(int procSize) {
		int memIndex = 0;
		int[] freeBlocks = getFreeBlocks();
		while (memIndex<memSize && procSize<=getSizeFree()) {
			if (freeBlocks[memIndex]>=procSize) {
				return memIndex;
			}
			memIndex++;
		}
		return -1;
	}
	/**
	 * @param procSize
	 * @return biggest position for adding content to memory or -1 if not free space
	 */
	public int biggest(int procSize) {
		int memIndex = 0, biggestBlock = 0;
		int[] freeBlocks = getFreeBlocks();
		while (memIndex<memSize && procSize<=getSizeFree()) {
			if (freeBlocks[memIndex]>freeBlocks[biggestBlock]) {
				biggestBlock = memIndex;
			}
			memIndex++;
		}
		if (freeBlocks[biggestBlock]>=procSize) {
			return biggestBlock;
		}
		return -1;
	}
	/**
	 * @param procSize
	 * @return suitable position for adding content to memory or -1 if not free space
	 */
	public int suitable(int procSize) {
		int suitableBlock = procSize;
		int[] freeBlocks = getFreeBlocks();
		while (suitableBlock<=getSizeFree()) {
			for (int memIndex=0; memIndex<memSize; memIndex++) {
				if (freeBlocks[memIndex] == suitableBlock) {
					return memIndex;
				}
			}
			suitableBlock++;
		}
		return -1;
	}
}