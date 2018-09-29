package com.liang.demo.algorithm;


import java.util.*;

/**
 * @description:
 * @author: haofeiL
 * @createDate: 2018/9/13 15: 22
 * @version: v1.0
 */
public class DeepIterator implements Iterator {

    private Iterator iter;
    private Map<Integer, Iterator> map = new HashMap();
    private int deep = 0;


    public DeepIterator(Iterable iterable) {
        this.iter = iterable.iterator();
        map.put(deep, iter);
    }

    @Override
    public boolean hasNext() {
        if (!iter.hasNext()) {
            if (deep > 0) {
                deep--;
                iter = map.get(deep);
                return iter.hasNext();
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public Integer next() {
        Object next = iter.next();
        if (next instanceof Integer) {
            return (Integer) next;
        } else if (next instanceof Iterable) {
            deep++;
            Iterator iterator = ((Iterable) next).iterator();
            map.put(deep, iterator);
            iter = iterator;
            return this.next();
        } else if (next == null) {
            deep--;
            iter = map.get(deep);
            return this.next();
        }
        return 0;
    }

    @Override
    public void remove() {
//         iter.iterator().remove();
    }

    public static void main(String[] args) {
        List l1 = new LinkedList();
        l1.add(1);
        l1.add(2);

        List l2 = new LinkedList<Integer>();
        l2.add(3);
        l2.add(4);
        l1.add(l2);

        List l3 = new LinkedList<Integer>();
        l3.add(5);
        l2.add(l3);

        l2.add(6);
        l2.add(7);
        l2.add(8);
        l1.add(9);
        l1.add(10);

        DeepIterator deepIter = new DeepIterator(l1);
        while (deepIter.hasNext()) {
            System.out.println(deepIter.next());
        }
    }
}
