package spbu.sem2.hw6.task1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Generic class for StackSet (implements Stack, based on Stack).
 *
 * @param <E> type of StackSet' elements
 */
class StackSet<E> implements Set<E> {
    private Stack<E> stackSet = new Stack<E>();

    @Override
    public int size() {
        return stackSet.size();
    }

    @Override
    public boolean isEmpty() {
        return stackSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return stackSet.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return stackSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return stackSet.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return stackSet.toArray(a);
    }

    @Override
    public boolean add(E t) {
        if (stackSet.contains(t))
            return false;
        return stackSet.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return stackSet.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return stackSet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;

        for (Iterator<? extends E> i = c.iterator(); i.hasNext();) {
            if (add(i.next())) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return stackSet.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return stackSet.removeAll(c);
    }

    @Override
    public void clear() {
        stackSet.clear();
    }
}
