package com.leslie.dream.mxzlw.base;

import java.io.Serializable;

/**
 * model 基类
 *
 * @Author dzl on 2016/10/31.
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean base_select;
    private int base_current;

    public boolean isBase_select() {
        return base_select;
    }

    public void setBase_select(boolean base_select) {
        this.base_select = base_select;
    }

    public int getBase_current() {
        return base_current;
    }

    public void setBase_current(int base_current) {
        this.base_current = base_current;
    }
}
