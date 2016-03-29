package com.lihonghao.weibo.entity;

public class LoginEntity {
    /**
     * username : demo
     * token : ce4e6f0d6319f8dd148bfd7ae1a768a9
     */

    private DataEntity data;
    /**
     * data : {"username":"demo","token":"ce4e6f0d6319f8dd148bfd7ae1a768a9"}
     * message :
     * status : 200
     */

    private String message;
    private int status;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public static class DataEntity {
        private String username;
        private String token;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return token;
        }
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
