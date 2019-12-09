package com.bluesky.zhz.web.controller.async;

import java.util.Objects;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/9 11:45
 **/
public class TestLcc {
    private int i;
    private String s;
    private int i1;

    public TestLcc(int i, String s, int i1) {
        this.i = i;
        this.s = s;
        this.i1 = i1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestLcc testLcc = (TestLcc) o;
        return i == testLcc.i &&
                i1 == testLcc.i1 &&
                Objects.equals(s, testLcc.s);
    }

    @Override
    public int hashCode() {

        return Objects.hash(i, s, i1);
    }
}
