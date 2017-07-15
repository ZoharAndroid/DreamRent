package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

/**
 * Created by zzh on 2017/7/13.
 *
 * 管理者Admin
 */

public class Admin extends BaseModel {
    private String admin_headpic;
    private String admin_phone;
    private float admin_overallrating;
    private int admin_id;
    private int admin_servicenum;
    private String admin_nickname;

    public String getAdmin_headpic() {
        return admin_headpic;
    }

    public void setAdmin_headpic(String admin_headpic) {
        this.admin_headpic = admin_headpic;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public float getAdmin_overallrating() {
        return admin_overallrating;
    }

    public void setAdmin_overallrating(float admin_overallrating) {
        this.admin_overallrating = admin_overallrating;
    }

    public int getAdmin_servicenum() {
        return admin_servicenum;
    }

    public void setAdmin_servicenum(int admin_servicenum) {
        this.admin_servicenum = admin_servicenum;
    }

    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public void setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
    }
}
