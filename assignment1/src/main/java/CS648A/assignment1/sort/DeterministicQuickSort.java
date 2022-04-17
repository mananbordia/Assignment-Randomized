package CS648A.assignment1.sort;

public class DeterministicQuickSort<T extends Comparable<T>> extends QuickSort<T> {
	@Override
	protected Integer getPivot(Integer l, Integer r) {
		return r;
	}
}
