package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] array;
    private Predicate<T> filter;
    
    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>(){
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return this.array.toString();
    }

    class ArrayIterator implements Iterator<T> {
        int current = 0;
        @Override
        public boolean hasNext() {
            while(current < array.length && !filter.test(array[current])) {
                current++;
            }
            return array.length > current; 
        }

        @Override
        public T next() {
            if(hasNext()){
                return array[current++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
