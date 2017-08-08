package com.cxmax.library.utils;

import java.util.Comparator;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/7/31.
 */

public class Test implements Comparator {

    class Student implements Comparable{
        private String name;
        private int score;

        public Student(String name, int score){
            this.name = name;
            this.score = score;
        }

        public String toString(){
            return this.name + ":" + this.score;
        }

        public int compareTo(Object o){
            Student s = (Student)(o);
            return this.score - s.score;
        }
    }
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
