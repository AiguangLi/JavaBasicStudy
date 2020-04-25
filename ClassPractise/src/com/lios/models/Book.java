package com.lios.models;

/**
 * @author liaiguang
 */
public class Book implements AutoCloseable{
    boolean checkedOut = false;
    public Book(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public void checkIn() {
        checkedOut = false;
    }

    @Override
    public void close() throws Exception {
        if (checkedOut) {
            System.out.println("Book closed when checkedOut is true!");
            throw new Exception("Book closed when checkedOut is true!");
        }

        System.out.println("--close--");
    }
}
