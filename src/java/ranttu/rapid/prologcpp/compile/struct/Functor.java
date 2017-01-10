/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.struct;

/**
 * a prolog functor
 *
 * @author rapidhere@gmail.com
 * @version $id: Functor.java, v0.1 2017/1/10 dongwei.dq Exp $
 */
public class Functor {
    private int    size;
    private String id;

    /**
     * the number of this functor in prolog module
     */
    private int    itemCount;

    public Functor(String id, int size) {
        this.id = id;
        this.size = size;
        this.itemCount = 0;
    }

    public String getId() {
        return id;
    }

    public void incItemCount() {
        itemCount++;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getSize() {
        return size;
    }
}
