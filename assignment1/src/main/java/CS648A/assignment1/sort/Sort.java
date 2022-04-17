package CS648A.assignment1.sort;

import java.util.List;

public interface Sort<T extends Comparable<T>> {

	
	public List<T> sort(List<T> testCase);

	public long getSortingTime();
	
	public long getNumberOfComparisions();

}
/*
 * Option 1 : GH Option 2 : Hall 1 Option 3 : Juice Shop Option 4 : DOAA Canteen
 * Option 5 : Any Hall / Canteen Option 6 : Campus Haat Option 7 : Swiggy Option
 * 8 : Kerela Kafe. Option 9 : Old Shop C
 */
