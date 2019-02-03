package org.caps.travel.entity;

import javax.persistence.*;

@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer fid;

    private String name;

    private String explain;

    private Double dprice;

    private Double mprice;

    private Scenic scenic;

    public Scenic getScenic() {
        return scenic;
    }

    public void setScenic(Scenic scenic) {
        this.scenic = scenic;
    }

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
     * @return fid
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * @param fid
     */
    public void setFid(Integer fid) {
        this.fid = fid;
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
     * @return explain
     */
    public String getExplain() {
        return explain;
    }

    /**
     * @param explain
     */
    public void setExplain(String explain) {
        this.explain = explain;
    }

    /**
     * @return dprice
     */
    public Double getDprice() {
        return dprice;
    }

    /**
     * @param dprice
     */
    public void setDprice(Double dprice) {
        this.dprice = dprice;
    }

    /**
     * @return mprice
     */
    public Double getMprice() {
        return mprice;
    }

    /**
     * @param mprice
     */
    public void setMprice(Double mprice) {
        this.mprice = mprice;
    }
}