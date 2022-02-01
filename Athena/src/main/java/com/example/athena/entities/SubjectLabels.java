package com.example.athena.entities;

import java.util.*;

import static java.util.Collections.emptyList;

public enum SubjectLabels
{
    COMPUTER_SCIENCE,
    CALCULUS_1,
    GEOMETRY ;

    public static String printValue(SubjectLabels enumVal)
    {
        switch(enumVal)
        {
            case COMPUTER_SCIENCE:
                return "Computer science" ;

            case CALCULUS_1:
                return "Calculus 1" ;

            case GEOMETRY:
                return "Geometry" ;

            default:
                return "" ;
        }
    }

    public static List<String> getAllLabels()
    {
        List<String> retVal = new List<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {

                return  (T[]) emptyList().toArray();


            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public String get(int index) {
                return null;
            }

            @Override
            public String set(int index, String element) {
                return null;
            }

            @Override
            public void add(int index, String element) {

            }

            @Override
            public String remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @Override
            public ListIterator<String> listIterator(int index) {
                return null;
            }

            @Override
            public List<String> subList(int fromIndex, int toIndex) {
                return emptyList() ;
            }
        };

        for(SubjectLabels label: SubjectLabels.values())
        {
            retVal.add(SubjectLabels.printValue(label)) ;
        }

        return retVal ;
    }
}
