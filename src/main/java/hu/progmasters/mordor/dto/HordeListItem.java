package hu.progmasters.mordor.dto;

import hu.progmasters.mordor.domain.Horde;

public class HordeListItem {

    private Integer id;
    private String name;
    private Integer headcount;

    public HordeListItem(Horde horde) {
        this.id = horde.getId();
        this.name = horde.getName();
        this.headcount = horde.getOrcList().size();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getHeadcount() {
        return headcount;
    }
}
