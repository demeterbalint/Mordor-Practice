package hu.progmasters.mordor.dto;

import java.util.List;

public class OrcForm {

    private String name;
    private String orcRaceType;
    private Integer killCount;
    private List<String> weapons;

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
