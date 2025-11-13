package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that represents a mathematical set of integers with standard set operations.
 * Duplicates are not allowed. The implementation uses an ArrayList internally.
 * 
 * @author 
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * @return number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set and the specified object contain the same elements.
     * Order does not matter.
     * @param o the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;

        IntegerSet other = (IntegerSet) o;
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /**
     * Returns true if the set contains the given value.
     * @param value the value to check
     * @return true if the value is present, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     * @return largest integer
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     * @return smallest integer
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if present.
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Modifies this set to contain all unique elements that are in either this or the other set.
     * @param other another IntegerSet
     */
    public void union(IntegerSet other) {
        for (int val : other.set) {
            if (!set.contains(val)) {
                set.add(val);
            }
        }
    }

    /**
     * Modifies this set to contain only the elements found in both sets.
     * @param other another IntegerSet
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Removes all elements from this set that are also in the other set.
     * @param other another IntegerSet
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Modifies this set to become the complement of itself relative to another set.
     * That is, this = (other \ this)
     * @param other another IntegerSet
     */
    public void complement(IntegerSet other) {
        List<Integer> complement = new ArrayList<Integer>(other.set);
        complement.removeAll(this.set);
        this.set = complement;
    }

    /**
     * Checks whether the set is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in square brackets.
     * @return formatted string of elements
     */
    @Override
    public String toString() {
        return set.toString();
    }
}
