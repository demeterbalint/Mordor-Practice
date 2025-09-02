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

    private List<String> orcList;

    public Horde(HordeForm hordeForm) {
        this.name = hordeForm.getName();
        this.orcList = new ArrayList<>();
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
}
