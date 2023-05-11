package edu.lsbf.task1;

public class Hashtable {
    //@ spec_public
    private Object[] h;
    //@ spec_public
    private final int capacity;
    //@ spec_public
    private int size = 0;
    // The size field is never negative, and always ≤ capacity.
    //@ public invariant size >= 0 && size <= capacity;
    // The capacity should be the same value as h.length.
    //@ public invariant capacity == h.length;
    // The array h cannot be null.
    //@ public invariant h != null;
    // There should be space for at least one element in the hash table.
    //@ public invariant capacity > 0;
    // The number of elements stored in array h (i.e., the number of array cells whose content is not null) is size.
    //@ public invariant size == (\num_of int i; 0 <= i && i < capacity; h[i] != null);

    Hashtable(int capacity) {
        h = new Object[capacity];
        this.capacity = capacity;
    }

    //@ public normal_behavior
    //@   requires val > 0;
    //@   ensures \result >= 0 && \result < capacity;
    //@ pure
    public int hash_function(int val) {
        return val % capacity;
    }

    // If the size is strictly smaller than capacity, then all of the following must hold:
    // – add terminates normally
    // – add increases size by one
    // – After add(obj,key), the object obj is stored in h at some index i.
    // If the size has reached capacity, add will throw an FullHashtableException, and the state does not change. In addition:
    // - Write assignable clauses where appropriate.
    // - Add JML modifiers where necessary.
    // TODO: there has a bug in OpenJML compiler, I can't put h[*] in assignable clause
    //@ public normal_behavior
    //@   requires size < capacity;
    //@   assignable h, size;
    //@   ensures size == \old(size) + 1;
    //@   ensures (\exists int i; 0 <= i && i < capacity; h[i] == obj);
    //@ also public exceptional_behavior
    //@   requires size >= capacity;
    //@   assignable \nothing;
    //@   signals_only FullHashtableException;
    public void add(Object obj, int key) {
        if (size < capacity) {
            int i = hash_function(key);
            if (h[i] == null) {
                h[i] = obj;
                size++;
            } else {
                while (h[i] != null) {
                    if (i == capacity - 1) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                h[i] = obj;
                size++;
            }

        } else throw new FullHashtableException();
    }
}

class FullHashtableException extends RuntimeException {
    FullHashtableException() {
        super("Hashtable is full");
    }
}
