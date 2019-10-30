package net.mtgsaber.lib.algorithms.trees.linkedlist;

import java.util.*;

public class SingleLinkedList<T> implements Iterable<T> {
    private SingleLinkNode<T> head, tail;
    private int count;

    public SingleLinkedList(List<T> initVals) {
        if (initVals != null && initVals.size() > 0) {
            boolean first = true;
            head = new SingleLinkNode<>(initVals.get(0));
            tail = head;
            for (T item : initVals) {
                if (first) {
                    first = false;
                    count++;
                    continue;
                }
                tail.next = new SingleLinkNode<>(item);
                tail = tail.next;
                count++;
            }
        }
    }

    public void prepend(T val) {
        SingleLinkNode<T> n = new SingleLinkNode<>(val);
        if (head == null)
            tail = n;
        else
            n.next = head;
        head = n;
        count++;
    }

    public void append(T val) {
        if (this.head == null) {
            this.head = new SingleLinkNode<>(val);
            tail = head;
            count++;
            return;
        }
        tail.next = new SingleLinkNode<>(val);
        tail = tail.next;
        count++;
    }

    public boolean remove(Object val) {
        if (head != null) {
            if (head.next == null) {
                if (head.DATA == val) {
                    head = null;
                    tail = null;
                    count = 0;
                    return true;
                }
            } else {
                if (head.DATA == val) {
                    head = head.next;
                    count--;
                    return true;
                }
                SingleLinkNode<T> precur = head, cur;
                for (cur = head.next; cur != null; cur = cur.next) {
                    if (cur.DATA == val) {
                        if (cur == tail)
                            tail = precur;
                        precur.next = cur.next;
                        count--;
                        return true;
                    } else
                        precur = cur;
                }
            }
        }
        return false;
    }

    public T[] toArray(T[] base) {
        T[] ret = Arrays.copyOf(base, count);
        if (count == 0)
            return ret;
        int i = 0;
        SingleLinkNode<T> cur;
        for (cur = head; cur != null; cur = cur.next)
            ret[i] = cur.DATA;
        return ret;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new SLLIterator(head);
    }

    public void sortBubble(Comparator<T> comparator, boolean ascending) {
        System.out.println("[ start bubble sort ]");
        // the empty list and a single-element list are always vacuously sorted
        if (head == null || head.next == null)
            return;
        // used as a placeholder for swap operations
        SingleLinkNode<T> temp;
        // sort first two nodes
        if (ascending ^ (comparator.compare(head.DATA, head.next.DATA) <= 0)) {
            temp = head.next.next;
            head.next.next = head;
            head = head.next;
            head.next.next = temp;
        }
        // if there are only two nodes, then the list is sorted
        if (count == 2)
            return;

        // sort the rest of the list
        SingleLinkNode<T> pre, left, right;
        boolean needToPass = true;
        int passCount, checkCount;
        for (passCount = 0; passCount < count - 3 && needToPass; passCount++) {
            needToPass = false;
            pre = head;
            left = head.next;
            right = head.next.next;
            System.out.println("[" + passCount + "] ");
            for (checkCount = 0; checkCount < count - 2 - passCount; checkCount++) {
                System.out.print("  [" + checkCount + "]");
                if (!ascending ^ comparator.compare(left.DATA, right.DATA) <= 0) {
                    needToPass = true;

                    // swap
                    temp = right.next;
                    pre.next = right;
                    right.next = left;
                    left.next = temp;
                }

                // move up one position
                pre = left;
                left = right;
                right = right.next;
            }
            System.out.println();
        }
    }

    public String toString() {
        if (head == null)
            return "[]";
        if (head.next == null)
            return "[" + head.DATA.toString() + "]";
        StringBuilder s = new StringBuilder("[");
        SingleLinkNode<T> cur;
        for (cur = head; cur.next != null; cur = cur.next) {
            s.append(cur.DATA.toString());
            s.append(", ");
        }
        s.append(cur.DATA.toString());
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        // inits
        SingleLinkedList<Integer> ints = new SingleLinkedList<>(Collections.emptyList());
        SingleLinkedList<Double> doubles = new SingleLinkedList<>(Collections.singletonList(Math.PI));
        SingleLinkedList<Character> characters = new SingleLinkedList<>(Arrays.asList(
                'a', 'b'
        ));
        SingleLinkedList<Byte> bytes = new SingleLinkedList<>(Arrays.asList(new Byte[]{
                0x4F, 0x2C, 0x4F, 0x13, 0x09, 0x2E, 0x7F, 0x5A
        }));
        System.out.println(
                "Initial Lists:"
                        + "\n\tEmpty List: " + ints.toString()
                        + "\n\tSingle-Element List: " + doubles.toString()
                        + "\n\tTwo-Element List: " + characters.toString()
                        + "\n\tLarge List: " + bytes.toString()
        );

        // appends
        System.out.println("==========================================");
        ints.append(2);
        doubles.append(4.5);
        characters.append('c');
        bytes.append(((byte) 0x5F));
        System.out.println(
                "Appended Lists:"
                        + "\n\tEmpty List: " + ints.toString()
                        + "\n\tSingle-Element List: " + doubles.toString()
                        + "\n\tTwo-Element List: " + characters.toString()
                        + "\n\tLarge List: " + bytes.toString()
        );

        // prepends
        System.out.println("==========================================");
        ints.prepend(7);
        doubles.prepend(4.6);
        characters.prepend('z');
        bytes.prepend(((byte) 0x23));
        System.out.println(
                "Prepended Lists:"
                        + "\n\tEmpty List: " + ints.toString()
                        + "\n\tSingle-Element List: " + doubles.toString()
                        + "\n\tTwo-Element List: " + characters.toString()
                        + "\n\tLarge List: " + bytes.toString()
        );

        // removals
        System.out.println("==========================================");
        ints.remove(7);
        doubles.remove(4.6);
        characters.remove('z');
        bytes.remove(((byte) 0x23));
        System.out.println(
                "Item-Removed Lists:"
                        + "\n\tEmpty List: " + ints.toString()
                        + "\n\tSingle-Element List: " + doubles.toString()
                        + "\n\tTwo-Element List: " + characters.toString()
                        + "\n\tLarge List: " + bytes.toString()
        );

        // for-each
        System.out.print("==========================================");
        System.out.print("\niterably printed int list: ");
        for (int i : ints)
            System.out.print(" " + i);
        System.out.println("\niterably printed double list: ");
        for (double d : doubles)
            System.out.print(" " + d);
        System.out.println("\niterably printed char list: ");
        for (char c : characters)
            System.out.print(" " + c);
        System.out.println("\niterably printed byte list: ");
        for (byte b : bytes)
            System.out.print(" " + b);
        System.out.println();

        // sorts
        System.out.println("==========================================");
        ints.sortBubble(Integer::compare, true);
        doubles.sortBubble(Double::compare, false);
        characters.sortBubble(Character::compare, true);
        bytes.sortBubble(Byte::compare, false);
        System.out.println(
                "Sorted Lists:"
                        + "\n\tEmpty List: " + ints.toString()
                        + "\n\tSingle-Element List: " + doubles.toString()
                        + "\n\tTwo-Element List: " + characters.toString()
                        + "\n\tLarge List: " + bytes.toString()
        );

        System.out.println("==========================================");
        System.out.println("\t\tTESTS COMPLETE");
    }

}

