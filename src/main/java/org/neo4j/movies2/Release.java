package org.neo4j.movies2;

public class Release {
    private String name;
    private Integer year;


    public Release(String name, Integer year) {
        this.name = name;
        this.year = year;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
