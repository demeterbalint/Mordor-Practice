package hu.progmasters.mordor.dto;

import hu.progmasters.mordor.domain.Orc;
import hu.progmasters.mordor.domain.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class OrcDetails {

    private Integer id;
    private String name;
    private String orcRaceType;
    private Integer killCount;
    private List<String> weapons;

    public OrcDetails(Orc orc) {
        this.id = orc.getId();
        this.name = orc.getName();
        this.orcRaceType = orc.getOrcRaceType().getDisplayName();
        this.killCount = orc.getKillCount();
        this.weapons = new ArrayList<>();
        for (WeaponType weaponType : orc.getWeapons()) {
            this.weapons.add(weaponType.getDisplayName());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrcRaceType() {
        return orcRaceType;
    }

    public void setOrcRaceType(String orcRaceType) {
        this.orcRaceType = orcRaceType;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public void setKillCount(Integer killCount) {
        this.killCount = killCount;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }
}
