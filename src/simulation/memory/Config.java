package simulation.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>Configuration for simulation</p>
 * @author Pavel Muhortov
 */
public class Config {
	private int memSize;
	private int minProcSize, maxProcSize;
	private int minProcTime, maxProcTime;
	private List<String> strategy = new ArrayList<>();
	private int iteration, simTime;
	private boolean print, stat;
	
	public Config(String[] args) {
		boolean configReady = false;
		if (args.length > 0) {
			if (args[0].contains("config")) {
				configReady = setConfigFromFile(args[1]);
			}
			else {
				configReady = setConfigFromArgs(args);
			}
		}
		if (!configReady) {
			setConfigDefault();
		}
	}
	private boolean setConfigFromFile(String filepath) {
		//TODO
		return true;
	}
	private boolean setConfigFromArgs(String[] args) {
		try{
			memSize = Integer.parseInt(args[0]);
			minProcSize = Integer.parseInt(args[1]);
			maxProcSize = Integer.parseInt(args[2]);
			minProcTime = Integer.parseInt(args[3]);
			maxProcTime = Integer.parseInt(args[4]);
			simTime = Integer.parseInt(args[5]);
			iteration = Integer.parseInt(args[6]);
			print = Boolean.parseBoolean(args[7]);
			stat = Boolean.parseBoolean(args[8]);
			for (int a=9; a< args.length; a++) {
				strategy.add(args[a]);
			}
		}
		catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	private boolean setConfigDefault() {
		memSize = 64;
		minProcSize = 1;
		maxProcSize = 32;
		minProcTime = 1;
		maxProcTime = 16;
		strategy.add("First");
		strategy.add("Biggest");
		strategy.add("Suitable");
		iteration = 20;
		simTime = 1000;
		print = false;
		stat = false;
		return true;
	}
	public int getMemSize() {
		return memSize;
	}
	public int getMinProcSize() {
		return minProcSize;
	}
	public int getMaxProcSize() {
		return maxProcSize;
	}
	public int getMinProcTime() {
		return minProcTime;
	}
	public int getMaxProcTime() {
		return maxProcTime;
	}
	public String[] getStrategy() {
		return strategy.toArray(new String[strategy.size()]);
	}
	public int getIteration() {
		return iteration;
	}
	public int getSimTime() {
		return simTime;
	}
	public boolean getPrint() {
		return print;
	}
	public boolean getStat() {
		return stat;
	}
}
