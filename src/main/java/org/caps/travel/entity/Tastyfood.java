package org.caps.travel.entity;

import javax.persistence.*;

@Table(name = "tastyfood")
public class Tastyfood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String describes;

    private String commend;

    private String pic;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return describe
     */
    public String getDescribe() {
        return describes;
    }

    /**
     * @param describe
     */
    public void setDescribe(String describe) {
        this.describes = describe;
    }

    /**
     * @return commend
     */
    public String getCommend() {
        return commend;
    }

    /**
     * @param commend
     */
    public void setCommend(String commend) {
        this.commend = commend;
    }

    /**
     * @return pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
}