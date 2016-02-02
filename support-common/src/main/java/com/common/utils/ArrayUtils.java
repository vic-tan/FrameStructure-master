package com.common.utils;

/**
 * Array Utils
 * <ul>
 * <li>{@link #isEmpty(Object[])} 为空或长度为0 </li>
 * <li>{@link #getLast(Object[], Object, Object, boolean)} get last element of the target element, before the first one
 * that match the target element front to back</li>
 * <li>{@link #getNext(Object[], Object, Object, boolean)} get next element of the target element, after the first one
 * that match the target element front to back</li>
 * <li>{@link #getLast(Object[], Object, boolean)}</li>
 * <li>{@link #getLast(int[], int, int, boolean)}</li>
 * <li>{@link #getLast(long[], long, long, boolean)}</li>
 * <li>{@link #getNext(Object[], Object, boolean)}</li>
 * <li>{@link #getNext(int[], int, int, boolean)}</li>
 * <li>{@link #getNext(long[], long, long, boolean)}</li>
 * @author tanlifei
 * @date  2015-1-26
 * </ul>
 */
public class ArrayUtils {

	/**
	 * 为空或长度为0
	* @Title: isEmpty 
	* @Description: 用一句话描述该文件做什么
	* @param sourceArray 
	* @return boolean 返回类型  
	 */
	public static <V> boolean isEmpty(V[] sourceArray) {
		return (sourceArray == null || sourceArray.length == 0);
	}

	/**
	 *  得到目标元素的最后一个元素，第一个匹配目标元素从前到后的前
	 * <ul>
	 * <li>如果数组为空，则返回设置defaultValue</li>
	 * <li>如果目标元素不在数组中存在，则返回设置defaultValue</li>
	 * <li>如果目标元素存在于数组，其索引不为0，则返回最后一个元素</li>
	 * <li>>如果目标元素存在于数组，其索引为0，返回的最后一个数组，如果isCircle是真实的，否则返回设置defaultValue</li>
	 * </ul>
	* @param sourceArray
	* @param value
	* @param defaultValue
	* @param isCircle
	* @return V 返回类型  
	* @throws:throws
	 */
	public static <V> V getLast(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
		if (isEmpty(sourceArray)) {
			return defaultValue;
		}

		int currentPosition = -1;
		for (int i = 0; i < sourceArray.length; i++) {
			if (ObjectUtils.isEquals(value, sourceArray[i])) {
				currentPosition = i;
				break;
			}
		}
		if (currentPosition == -1) {
			return defaultValue;
		}

		if (currentPosition == 0) {
			return isCircle ? sourceArray[sourceArray.length - 1] : defaultValue;
		}
		return sourceArray[currentPosition - 1];
	}

	/**
	 * get next element of the target element, after the first one that match the target element front to back
	 * <ul>
	 * <li>if array is empty, return defaultValue</li>
	 * <li>if target element is not exist in array, return defaultValue</li>
	 * <li>if target element exist in array and not the last one in array, return the next element</li>
	 * <li>if target element exist in array and the last one in array, return the first one in array if isCircle is
	 * true, else return defaultValue</li>
	 * </ul>
	 * 
	 * @param <V>
	 * @param sourceArray
	 * @param value value of target element
	 * @param defaultValue default return value
	 * @param isCircle whether is circle
	 * @return
	 */
	public static <V> V getNext(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
		if (isEmpty(sourceArray)) {
			return defaultValue;
		}

		int currentPosition = -1;
		for (int i = 0; i < sourceArray.length; i++) {
			if (ObjectUtils.isEquals(value, sourceArray[i])) {
				currentPosition = i;
				break;
			}
		}
		if (currentPosition == -1) {
			return defaultValue;
		}

		if (currentPosition == sourceArray.length - 1) {
			return isCircle ? sourceArray[0] : defaultValue;
		}
		return sourceArray[currentPosition + 1];
	}

	/**
	 * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} defaultValue is null
	 */
	public static <V> V getLast(V[] sourceArray, V value, boolean isCircle) {
		return getLast(sourceArray, value, null, isCircle);
	}

	/**
	 * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} defaultValue is null
	 */
	public static <V> V getNext(V[] sourceArray, V value, boolean isCircle) {
		return getNext(sourceArray, value, null, isCircle);
	}

	/**
	 * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} Object is Long
	 */
	public static long getLast(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
		if (sourceArray.length == 0) {
			throw new IllegalArgumentException("The length of source array must be greater than 0.");
		}

		Long[] array = ObjectUtils.transformLongArray(sourceArray);
		return getLast(array, value, defaultValue, isCircle);

	}

	/**
	 * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} Object is Long
	 */
	public static long getNext(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
		if (sourceArray.length == 0) {
			throw new IllegalArgumentException("The length of source array must be greater than 0.");
		}

		Long[] array = ObjectUtils.transformLongArray(sourceArray);
		return getNext(array, value, defaultValue, isCircle);
	}

	/**
	 * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} Object is Integer
	 */
	public static int getLast(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
		if (sourceArray.length == 0) {
			throw new IllegalArgumentException("The length of source array must be greater than 0.");
		}

		Integer[] array = ObjectUtils.transformIntArray(sourceArray);
		return getLast(array, value, defaultValue, isCircle);

	}

	/**
	 * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} Object is Integer
	 */
	public static int getNext(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
		if (sourceArray.length == 0) {
			throw new IllegalArgumentException("The length of source array must be greater than 0.");
		}

		Integer[] array = ObjectUtils.transformIntArray(sourceArray);
		return getNext(array, value, defaultValue, isCircle);
	}
}
