package CS648A.assignment1.randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
	private final static Random random = new Random();

	/*
	 * Returns a random between [a, b]
	 */
	public static Integer getIntBetween(int origin, int limit) {
		return random.nextInt(limit - origin + 1) + origin;
	}

	public static List<Integer> getRandomIntegerListOfLength(int length) {
		List<Integer> list = new ArrayList<>();
		while (length-- > 0) {
			int number = random.nextInt();
			list.add(number);
		}
		return list;
	}

	public static List<Double> getRandomDoubleListOfLength(int length) {
		List<Double> list = new ArrayList<>();
		while (length-- > 0) {
			Double number = random.nextDouble();
			list.add(number);
		}
		return list;
	}
}
