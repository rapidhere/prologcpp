/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp;

import ranttu.rapid.prologcpp.exp.AssertError;

/**
 * utils
 *
 * @author rapidhere@gmail.com
 * @version $id: $.java, v0.1 2017/1/10 dongwei.dq Exp $
 */
public interface $ {
    // ~~~ common utils
    @SuppressWarnings("unchecked")
    static <T> T required(Object o) {
        return (T) o;
    }

    // ~~~ assert utils
    static <T> T notNull(T o) {
        if(o == null) {
            throw new AssertError("object should not be null");
        }

        return o;
    }

    static <T> T shouldNotReach() {
        throw new AssertError("should not reach this");
    }
}
