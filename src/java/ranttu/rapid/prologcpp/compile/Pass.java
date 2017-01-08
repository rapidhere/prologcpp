/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile;

/**
 * a pass in compiling
 *
 * @author rapidhere@gmail.com
 * @version $id: Pass.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public interface Pass {
    /**
     * process a pass
     */
    void process(CompilingContext context);
}
