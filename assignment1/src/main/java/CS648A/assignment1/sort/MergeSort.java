package CS648A.assignment1.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {


	
	private int numberOfCmps;
	private long timeTaken;

	private List<T> mergeSort(List<T> array, Integer l, Integer r) {
		List<T> baseCaseResult = baseCase(array, l, r);

		if (Objects.nonNull(baseCaseResult))
			return baseCaseResult;

		Integer mid = (l + r) >> 1;
		List<T> left = mergeSort(array, l, mid - 1);
		List<T> right = mergeSort(array, mid, r);
		return merge(left, right);
	}


	private List<T> merge(List<T> left, List<T> right) {
		List<T> result = new ArrayList<>();

		Integer L = 0;
		Integer R = 0;

		while (L < left.size() && R < right.size()) {
			T lE = left.get(L);
			T rE = right.get(R);
			
			// Comparison Counter;
			this.numberOfCmps++;
			
			if (lE.compareTo(rE) <= 0) {
				result.add(lE);
				L++;
			} else {
				result.add(rE);
				R++;
			}
		}
		result = addRest(left, result, L);
		result = addRest(right, result, R);
		return result;
	}

	
	private List<T> baseCase(List<T> array, Integer l, Integer r) {
		if (l > r)
			return new ArrayList<>();
		if (l.equals(r))
			return Arrays.asList(array.get(l));
		if (l.equals(r - 1)) {
			T f = array.get(l);
			T s = array.get(r);

			if (f.compareTo(s) > 0)
				return Arrays.asList(s, f);
			return Arrays.asList(f, s);
		}
		return null;
	}

	private List<T> addRest(List<T> right, List<T> result, Integer R) {
		while (R < right.size()) {
			T element = right.get(R);
			result.add(element);
			R++;
		}
		return result;
	}

	@Override
	public List<T> sort(List<T> testCase) {
		Integer l = 0;
		Integer r = testCase.size() - 1;
		this.numberOfCmps = 0;
		long startTime = System.currentTimeMillis();
		List<T> result =  mergeSort(testCase, l, r);
		long endTime = System.currentTimeMillis();
		this.timeTaken = endTime - startTime;
		
		return result;
		
	}

	@Override
	public long getSortingTime() {
		return this.timeTaken;
	}

	@Override
	public long getNumberOfComparisions() {
		return this.numberOfCmps;
	}

}
