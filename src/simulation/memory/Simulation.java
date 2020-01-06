package simulation.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>Memory simulation</p>
 * Example:
<<<<<<< HEAD
 * java -jar ./SimulationMemory.jar 64 1 32 1 16 1000 20 true false First Biggest Suitable
=======
 * java -jar ./SimulationMemory.jar 64 1 32 1 16 1000 20 false false First Biggest Suitable
>>>>>>> 3ddbd347e35f76e2fa469abee1ef02ff89c175cf
 * need to refine the transfer of parameters to the .jar
 * @author Pavel Muhortov
 */
public class Simulation {

	public static void main(String[] args) {
		//String[] input = args;
		String[] input = {"64","1","32","1","16","16","1","true","false","First","Biggest","Suitable"};
		
		Config cfg = new Config(input);
		int memSize = cfg.getMemSize();
		int minProcSize = cfg.getMinProcSize();
		int maxProcSize = cfg.getMaxProcSize();
		int minProcTime = cfg.getMinProcTime();
		int maxProcTime = cfg.getMaxProcTime();
		int simTime = cfg.getSimTime();
		int iteration = cfg.getIteration();
		boolean print = cfg.getPrint();
		boolean stat = cfg.getStat();
		String[] strategy = cfg.getStrategy();		
		
		for (String strat: strategy) {
			double[] simResultDouble = new double[iteration];
			for (int i=0; i<iteration; i++) {
				MemoryFind memoryBlock = new MemoryFind(memSize);
				List<Process> procList = new ArrayList<>();
				int blockMemory = 0, addMemory = 0;
				for (int t=0; t<simTime; t++) {
					if (print)
						System.out.print(memoryBlock.getSizeFree() +"/"+ memoryBlock.getSizeFull() +"\t"+ memoryBlock.getContent());
					for(Process proc: procList){
						if (proc.lifeTime ==0) {
							memoryBlock.del(proc.memStart, proc.memSize);
							procList.remove(proc);
							break;
						}
						proc.lifeTime--;
					}
					Process newProc = new Process(minProcSize, maxProcSize, minProcTime, maxProcTime);
					if (strat == "First")
						newProc.memStart = memoryBlock.first(newProc.memSize);
					if (strat == "Biggest")
						newProc.memStart = memoryBlock.biggest(newProc.memSize);									
					if (strat == "Suitable")
						newProc.memStart = memoryBlock.suitable(newProc.memSize);
					if (newProc.memStart >= 0) {
						memoryBlock.add(newProc.memStart, newProc.memSize);
						procList.add(newProc);
						addMemory++;
						if (print)
							System.out.print("\tadd");
					}
					else {
						blockMemory++;
						if (print)
							System.out.print("\tblock");
					}
					if (print) {
						System.out.println("\tproc: time "+newProc.lifeTime +"\tstart mem "+ newProc.memStart +"\tsize "+ newProc.memSize);
					}
				}
				System.out.println("Memory add/block: "+ addMemory +"/"+ blockMemory);
				if (stat) {
					simResultDouble[i] = addMemory;
				}
				else {
					simResultDouble[i] = blockMemory;
				}
			}
			System.out.println("Average \""+ strat +"\": "+ Statistic.average(simResultDouble));
			System.out.println("Variance \""+ strat +"\": "+ Statistic.variance(simResultDouble));
			System.out.println("Standart deviation \""+ strat +"\": "+ Statistic.stdDeviation(simResultDouble));
			System.out.println();
		}
	}
}
