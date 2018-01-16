package com;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArraySetTest {
    @Test
    void firstLastOfEmptySetThrowsException() {
        assertThrows(NoSuchElementException.class, (new ArraySet<>(new Integer[]{}))::first);
        assertThrows(NoSuchElementException.class, (new ArraySet<>(new Integer[]{}))::last);
    }

    @Test
    void firstReturnsFirstElement() {
        assertEquals(new Integer(1), new ArraySet<>(new Integer[]{1, 2, 3, 4}).first());
    }

    @Test
    void lastReturnsLastElement() {
        assertEquals(new Integer(4), new ArraySet<>(new Integer[]{1, 2, 3, 4}).last());
    }

    @Test
    void sizeReturnsCollectionSize() {
        assertEquals(4, new ArraySet<>(new Integer[]{1, 2, 3, 4}).size());
    }

    @Test
    void iteratorTest() {
        Iterator<Integer> i = new ArraySet<>(new Integer[]{1, 2, 3, 4}).iterator();
        assertEquals(new Integer(1), i.next());
        assertThrows(UnsupportedOperationException.class, i::remove);
        assertEquals(new Integer(2), i.next());
        assertEquals(new Integer(3), i.next());
        assertEquals(new Integer(4), i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void descendingIteratorTest() {
        Iterator<Integer> i = new ArraySet<>(new Integer[]{1, 2, 3, 4}).descendingIterator();
        assertEquals(new Integer(4), i.next());
        assertThrows(UnsupportedOperationException.class, i::remove);
        assertEquals(new Integer(3), i.next());
        assertEquals(new Integer(2), i.next());
        assertEquals(new Integer(1), i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void lowerTest() {
        assertEquals(new Integer(2), (new ArraySet<>(new Integer[]{2, 3, 4, 5})).lower(3));
        assertEquals(new Integer(4), (new ArraySet<>(new Integer[]{1, 2, 3, 4})).lower(5));
        assertEquals(new Integer(6), (new ArraySet<>(new Integer[]{2, 4, 6, 8})).lower(7));
        assertEquals(null, (new ArraySet<>(new Integer[]{2, 4, 6, 8})).lower(2));
    }

    @Test
    void floorTest() {
        assertEquals(new Integer(3), (new ArraySet<>(new Integer[]{2, 3, 4, 5})).floor(3));
        assertEquals(new Integer(4), (new ArraySet<>(new Integer[]{1, 2, 3, 4})).floor(5));
        assertEquals(new Integer(6), (new ArraySet<>(new Integer[]{2, 4, 6, 8})).floor(7));
        assertEquals(null, (new ArraySet<>(new Integer[]{2, 4, 6, 8})).floor(1));
    }

    @Test
    void ceilingTest() {
        assertEquals(new Integer(3), (new ArraySet<>(new Integer[]{2, 3, 4, 5})).ceiling(3));
        assertEquals(new Integer(1), (new ArraySet<>(new Integer[]{1, 2, 3, 4})).ceiling(0));
        assertEquals(new Integer(8), (new ArraySet<>(new Integer[]{2, 4, 6, 8})).ceiling(7));
        assertEquals(null, (new ArraySet<>(new Integer[]{2, 4, 6, 8})).ceiling(9));
    }

    @Test
    void higherTest() {
        assertEquals(new Integer(4), (new ArraySet<>(new Integer[]{2, 3, 4, 5})).higher(3));
        assertEquals(new Integer(1), (new ArraySet<>(new Integer[]{1, 2, 3, 4})).higher(0));
        assertEquals(new Integer(8), (new ArraySet<>(new Integer[]{2, 4, 6, 8})).higher(7));
        assertEquals(null, (new ArraySet<>(new Integer[]{2, 4, 6, 8})).higher(8));
    }

    @Test
    void pollThrowsException() {
        assertThrows(UnsupportedOperationException.class, (new ArraySet<>(new String[]{})::pollFirst));
        assertThrows(UnsupportedOperationException.class, (new ArraySet<>(new String[]{})::pollLast));
    }

    @Test
    void headSetTest() {
        ArraySet<Integer> s = new ArraySet<>(new Integer[]{1, 2, 4, 6});
        assertTrue(new ArraySet<>(new Integer[]{1, 2}).equals(s.headSet(3, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2}).equals(s.headSet(3, false)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4}).equals(s.headSet(4, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2}).equals(s.headSet(4, false)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.headSet(7, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.headSet(7, false)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(s.headSet(1, false)));
        assertTrue(new ArraySet<>(new Integer[]{1}).equals(s.headSet(1, true)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(s.headSet(0, true)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(s.headSet(0, false)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(new ArraySet<>(new Integer[]{}).headSet(0)));

        assertThrows(NullPointerException.class, () -> s.headSet(null));
    }

    @Test
    void tailSetTest() {
        ArraySet<Integer> s = new ArraySet<>(new Integer[]{1, 2, 4, 6});
        assertTrue(new ArraySet<>(new Integer[]{4, 6}).equals(s.tailSet(3, true)));
        assertTrue(new ArraySet<>(new Integer[]{4, 6}).equals(s.tailSet(3, false)));
        assertTrue(new ArraySet<>(new Integer[]{4, 6}).equals(s.tailSet(4, true)));
        assertTrue(new ArraySet<>(new Integer[]{6}).equals(s.tailSet(4, false)));
        assertTrue(new ArraySet<>(new Integer[]{6}).equals(s.tailSet(6, true)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(s.tailSet(6, false)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(s.tailSet(7, true)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(s.tailSet(7, false)));
        assertTrue(new ArraySet<>(new Integer[]{2, 4, 6}).equals(s.tailSet(1, false)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.tailSet(1, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.tailSet(0, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.tailSet(0, false)));
        assertTrue(new ArraySet<>(new Integer[]{}).equals(new ArraySet<>(new Integer[]{}).tailSet(0)));

        assertThrows(NullPointerException.class, () -> s.tailSet(null));
    }

    @Test
    void subSetTest() {
        ArraySet<Integer> s = new ArraySet<>(new Integer[]{1, 2, 4, 6});
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.subSet(1, true, 6, true)));
        assertTrue(new ArraySet<>(new Integer[]{2, 4}).equals(s.subSet(1, false, 6, false)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4}).equals(s.subSet(1, true, 6, false)));
        assertTrue(new ArraySet<>(new Integer[]{2, 4, 6}).equals(s.subSet(1, false, 6, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.subSet(0, true, 7, true)));
        assertTrue(new ArraySet<>(new Integer[]{1, 2, 4, 6}).equals(s.subSet(0, false, 7, false)));

        assertThrows(NullPointerException.class, () -> s.subSet(null, 1));
        assertThrows(NullPointerException.class, () -> s.subSet(1, null));
    }

    @Test
    void comparatorNotNull() {
        ArraySet<Integer> s = new ArraySet<>(new Integer[]{1, 2, 4, 6});
        assertNotNull(s.comparator());
    }

    @Test
    void descendingSetTest() {
        ArraySet<Integer> s = new ArraySet<>(new Integer[]{1, 2, 4, 6});
        NavigableSet<Integer> ds1 = s.descendingSet();
        Iterator<Integer> i1 = ds1.iterator();
        assertEquals(new Integer(6), i1.next());
        assertThrows(UnsupportedOperationException.class, i1::remove);
        assertEquals(new Integer(4), i1.next());
        assertEquals(new Integer(2), i1.next());
        assertEquals(new Integer(1), i1.next());
        assertFalse(i1.hasNext());

        NavigableSet<Integer> ds2 = ds1.descendingSet();
        Iterator<Integer> i2 = ds2.iterator();
        assertEquals(new Integer(1), i2.next());
        assertThrows(UnsupportedOperationException.class, i2::remove);
        assertEquals(new Integer(2), i2.next());
        assertEquals(new Integer(4), i2.next());
        assertEquals(new Integer(6), i2.next());
        assertFalse(i1.hasNext());
    }
}