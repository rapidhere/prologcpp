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
final public class $ {
    // forbidden constructor
    private $() {
    }

    // ~~~ common utils
    @SuppressWarnings("unchecked")
    public static <T> T required(Object o) {
        return (T) o;
    }

    // ~~~ assert utils
    public static <T> T notNull(T o) {
        if(o == null) {
            throw new AssertError("object should not be null");
        }

        return o;
    }

    public static <T> T should(boolean b, String message) {
        if(! b) {
            throw new AssertError(message);
        }

        return null;
    }

    public static <T> T shouldNotReach() {
        throw new AssertError("should not reach this");
    }
}
