package junit.cua;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayVsLinked {
	public static void main(String[] args) {

		System.out.println("Temps ArrayList: " + arrayListProcess());
		System.out.println("Temps LinkedList: " + linkedListProcess());
	}

	public static long arrayListProcess() {
		long start = System.currentTimeMillis();
		List<Integer> array = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			array.add(i);
		}
		for (int i = 0; i < 100000; i++) {
			array.remove(0);
		}
		long finish = System.currentTimeMillis();
		return finish - start;
	}

	public static long linkedListProcess() {
		long start = System.currentTimeMillis();
		List<Integer> linked = new LinkedList<>();
		for (int i = 0; i < 100000; i++) {
			linked.add(i);
		}
		for (int i = 0; i < 100000; i++) {
			linked.remove(0);
		}
		long finish = System.currentTimeMillis();
		return finish - start;
	}
}
