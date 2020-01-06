package simulation.memory;

/**
 * <p/>Statistic tools set</p>
 * @author Pavel Muhortov
 */
final class Statistic {
	private Statistic() {}
	
	/**
	 * @param array
	 * @return Среднее значение
	 */
	public static double average(double[] array) {
		double average = 0;
		for (int i=0; i<array.length; i++) {
			average += array[i];
		}
		average = average/array.length;
		return average;
	}
	/**
	 * @param array
	 * @return Дисперсию
	 */
	public static double variance(double[] array) {
		double avg = Statistic.average(array);
		double variance = 0;
		for (int i=0; i<array.length; i++) {
			variance += (array[i]-avg)*(array[i]-avg);
		}
		variance = variance/array.length;
		return variance;
	}
	/**
	 * @param array
	 * @return Среднеквадратичное отклонение
	 */
	public static double stdDeviation(double[] array) {
		double stdDeviation = Math.sqrt(Statistic.variance(array));
		return stdDeviation;
	}
}