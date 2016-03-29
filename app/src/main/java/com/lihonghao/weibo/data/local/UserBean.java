package com.lihonghao.weibo.data.local;

public class UserBean {
    public String id;
    public String name;
    public String time;
    public String json;

    public UserBean() {
    }

    public UserBean(String id, String name, String time, String json) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.json = json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBean bean = (UserBean) o;

        if (id != null ? !id.equals(bean.id) : bean.id != null) return false;
        if (name != null ? !name.equals(bean.name) : bean.name != null) return false;
        if (time != null ? !time.equals(bean.time) : bean.time != null) return false;
        return !(json != null ? !json.equals(bean.json) : bean.json != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (json != null ? json.hashCode() : 0);
        return result;
    }
}
