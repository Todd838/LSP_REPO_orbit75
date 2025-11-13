package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for IntegerSet.
 * Tests all public methods of IntegerSet, including normal and edge cases.
 */
class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;

    /**
     * Sets up two IntegerSet instances before each test.
     */
    @BeforeEach
    void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(4);
        set2.add(5);
    }

    /** Tests clear() method. */
    @Test
    void testClear() {
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    /** Tests length() method. */
    @Test
    void testLength() {
        assertEquals(3, set1.length());
    }

    /** Tests equals(Object o) method. */
    @Test
    void testEquals() {
        IntegerSet set3 = new IntegerSet();
        set3.add(3); set3.add(1); set3.add(2);
        assertTrue(set1.equals(set3)); // same elements, different order
        set3.add(99);
        assertFalse(set1.equals(set3)); // extra element
    }

    /** Tests contains(int value) method. */
    @Test
    void testContains() {
        assertTrue(set1.contains(2));
        assertFalse(set1.contains(10));
    }

    /** Tests largest() method and exception for empty set. */
    @Test
    void testLargest() {
        assertEquals(3, set1.largest());
        set1.clear();
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }

    /** Tests smallest() method and exception for empty set. */
    @Test
    void testSmallest() {
        assertEquals(1, set1.smallest());
        set1.clear();
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }

    /** Tests add(int item) method and prevention of duplicates. */
    @Test
    void testAdd() {
        set1.add(4);
        assertTrue(set1.contains(4));
        int before = set1.length();
        set1.add(4); // duplicate ignored
        assertEquals(before, set1.length());
    }

    /** Tests remove(int item) method. */
    @Test
    void testRemove() {
        set1.remove(2);
        assertFalse(set1.contains(2));
        set1.remove(99); // nonexistent
        assertEquals(2, set1.length());
    }

    /** Tests union(IntegerSet other) method. */
    @Test
    void testUnion() {
        set1.union(set2);
        assertTrue(set1.contains(5));
        assertEquals(5, set1.length());
    }

    /** Tests intersect(IntegerSet other) method. */
    @Test
    void testIntersect() {
        set1.intersect(set2);
        assertTrue(set1.contains(3));
        assertEquals(1, set1.length());
    }

    /** Tests diff(IntegerSet other) method. */
    @Test
    void testDiff() {
        set1.diff(set2);
        assertFalse(set1.contains(3));
        assertEquals(2, set1.length());
    }

    /** Tests complement(IntegerSet other) method. */
    @Test
    void testComplement() {
        set1.complement(set2);
        assertEquals("[4, 5]", set1.toString());
    }

    /** Tests isEmpty() method. */
    @Test
    void testIsEmpty() {
        IntegerSet empty = new IntegerSet();
        assertTrue(empty.isEmpty());
        empty.add(1);
        assertFalse(empty.isEmpty());
    }

    /** Tests toString() method. */
    @Test
    void testToString() {
        assertEquals("[1, 2, 3]", set1.toString());
    }
}

