package CS648A.assignment1.sort;

import CS648A.assignment1.randoms.RandomGenerator;

public class RandomizedQuickSort<T extends Comparable<T>> extends QuickSort<T> {
	@Override
	protected Integer getPivot(Integer l, Integer r) {
		return RandomGenerator.getIntBetween(l, r);
	}
}
