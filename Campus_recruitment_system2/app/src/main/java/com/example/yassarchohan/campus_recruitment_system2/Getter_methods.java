package com.example.yassarchohan.campus_recruitment_system2;

/**
 * Created by Yassar chohan on 1/26/2017.
 */
public class Getter_methods {

    public String com_name;
    public String postedby;
    public String desige;
    public String nodeKey;
    public String current;


    public void setNodeKey(String nodeKey) {

        this.nodeKey = nodeKey;
    }

    public Getter_methods() {
    }

    public Getter_methods(String com_name, String postedby, String desige, String nodeKey, String current) {
        this.com_name = com_name;
        this.postedby = postedby;
        this.desige = desige;
        this.nodeKey = nodeKey;
        this.current = current;
    }

    public Getter_methods(String com_name, String postedby, String desige, String nodeKey) {
        this.com_name = com_name;
        this.postedby = postedby;
        this.desige = desige;
        this.nodeKey = nodeKey;
    }

    public String getCom_name() {
        return com_name;
    }

    public String getPostedby() {
        return postedby;
    }

    public String getDesige() {
        return desige;
    }

    public String getNodeKey() {
        return nodeKey;
    }
}
