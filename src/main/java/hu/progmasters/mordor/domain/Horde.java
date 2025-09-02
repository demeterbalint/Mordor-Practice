package hu.progmasters.mordor.domain;

import hu.progmasters.mordor.dto.HordeForm;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Horde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ElementCollection
    private List<String> orcList;

    private int headcount;

    public Horde(HordeForm hordeForm) {
        this.name = hordeForm.getName();
        this.orcList = new ArrayList<>();
        this.headcount = 0;
    }

    public Horde() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrcList() {
        return orcList;
    }

    public int getHeadcount() {
        return headcount;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }
}
