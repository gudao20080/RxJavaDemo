package com.wk.myapplication;

import java.util.List;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-01-28 17:09
 */
public class Student{
    private List<Course> mCourses;

    private String mName;
    private String mSex;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public List<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
            "mCourses=" + mCourses +
            ", mName='" + mName + '\'' +
            ", mSex='" + mSex + '\'' +
            '}';
    }
}
