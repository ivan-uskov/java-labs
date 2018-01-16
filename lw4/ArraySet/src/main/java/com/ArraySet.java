package com;

import java.util.*;
import java.util.stream.Collectors;

class ArraySet<E extends Comparable<E>> extends AbstractSet<E> implements NavigableSet<E> {
    private final List<E> data;

    ArraySet(E[] items) {
        data = Arrays.stream(items).filter(Objects::nonNull).distinct().sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    private ArraySet(List<E> d) {
        data = d;
    }

    private class ArraySetIterator<T> implements Iterator<T> {
        private final boolean isAscOrder;
        private final List<T> list;
        private int position;

        ArraySetIterator(List<T> l, boolean asc) {
            list = l;
            isAscOrder = asc;
            position = isAscOrder ? 0 : list.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return isAscOrder ? position < list.size() : position >= 0;
        }

        @Override
        public T next() {
            return list.get(isAscOrder ? position++ : position--);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public E lower(E e) {
        int index = Collections.binarySearch(data, e);
        if (index < 0) {
            index = Math.abs(index + 1);
        }

        return indexExists(index - 1) ? data.get(index - 1) : null;
    }

    @Override
    public E floor(E e) {
        int index = Collections.binarySearch(data, e);
        if (index >= 0) {
            return indexExists(index) ? data.get(index) : null;
        }

        index = Math.abs(index + 1);
        return indexExists(index - 1) ? data.get(index - 1) : null;
    }

    @Override
    public E ceiling(E e) {
        int index = Collections.binarySearch(data, e);
        if (index < 0) {
            index = Math.abs(index + 1);
        }

        return indexExists(index) ? data.get(index) : null;
    }

    @Override
    public E higher(E e) {
        int index = Collections.binarySearch(data, e);
        if (index >= 0) {
            return indexExists(index + 1) ? data.get(index + 1) : null;
        }

        index = Math.abs(index + 1);
        return indexExists(index) ? data.get(index) : null;
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException("poll first on immutable set");
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException("poll last on immutable set");
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return new ArraySet<>(new ArrayList<E>(data){
            @Override
            public E get(int index) {
                boolean exists = index >= 0 && index < size();
                return data.get(exists ? size() - index - 1 : index);
            }
        });
    }

    @Override
    public Iterator<E> iterator() {
        return new ArraySetIterator<>(data, true);
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new ArraySetIterator<>(data, false);
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return tailSet(fromElement, fromInclusive).headSet(toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        if (toElement == null) {
            throw new NullPointerException("toElement is null");
        } else if (isEmpty()) {
            return this;
        }

        int index = Collections.binarySearch(data, toElement);
        int last = (index >= 0 ? (inclusive ? index + 1 : index) : Math.abs(index + 1));

        return new ArraySet<>(last <= 0 ? new ArrayList<>() : data.subList(0, last));
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        if (fromElement == null) {
            throw new NullPointerException("fromElement is null");
        } else if (isEmpty()) {
            return this;
        }

        int index = Collections.binarySearch(data, fromElement);
        int first = (index >= 0 ? (inclusive ? index : index + 1) : Math.abs(index + 1));

        return new ArraySet<>(first >= size() ? new ArrayList<>() : data.subList(first, size()));
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public Comparator<? super E> comparator() {
        return E::compareTo;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("set is empty");
        }

        return data.get(0);
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("set is empty");
        }

        return data.get(size() - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    private boolean indexExists(int index) {
        return index >= 0 && index < size();
    }
}
