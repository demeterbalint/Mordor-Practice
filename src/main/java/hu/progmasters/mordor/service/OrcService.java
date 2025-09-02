package hu.progmasters.mordor.service;

import hu.progmasters.mordor.domain.Orc;
import hu.progmasters.mordor.domain.OrcRaceType;
import hu.progmasters.mordor.domain.WeaponType;
import hu.progmasters.mordor.dto.OrcDetails;
import hu.progmasters.mordor.dto.OrcForm;
import hu.progmasters.mordor.dto.OrcListItem;
import hu.progmasters.mordor.repository.OrcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrcService {

    private OrcRepository orcRepository;
    private HordeService hordeService;

    @Autowired
    public OrcService(OrcRepository orcRepository, HordeService hordeService) {
        this.orcRepository = orcRepository;
        this.hordeService = hordeService;
    }

    public List<OrcListItem> findAll() {
        List<Orc> byOrderByKillCountDesc = orcRepository.findByOrderByKillCountDesc();
        return byOrderByKillCountDesc
                .stream()
                .map(OrcListItem::new)
                .collect(Collectors.toList());
    }

    public OrcDetails findById(Integer orcId) {
        Orc orcById = findOrcById(orcId);
        return new OrcDetails(orcById);

    }

    public void save(OrcForm orcForm) {
        Orc orcToSave = new Orc(orcForm);
        orcRepository.save(orcToSave);
    }

    public void remove(Integer orcId) {
        Orc orc = findOrcById(orcId);
        orcRepository.delete(orc);
    }

    public void update(Integer orcId, OrcForm orcForm) {
        Orc orcToUpdate = findOrcById(orcId);
        updateOrcFields(orcToUpdate, orcForm);
    }

    private void updateOrcFields(Orc orcToUpdate, OrcForm orcForm) {
        if (orcToUpdate.getHordeName() == null && orcForm.getHordeName() == null) {
            updateOrcAndHorde(orcToUpdate, orcForm);
        } else if (orcToUpdate.getHordeName() == null && orcForm.getHordeName() != null) {
            hordeService.addOrc(orcForm.getHordeName(), orcForm.getName());
            updateOrcAndHorde(orcToUpdate, orcForm);
        } else if (orcForm.getHordeName() == null) {
            hordeService.removeOrc(orcToUpdate.getHordeName(), orcToUpdate.getName());
            updateOrcAndHorde(orcToUpdate, orcForm);
        } else if (!orcToUpdate.getHordeName().equals(orcForm.getHordeName())) {
            hordeService.changeHorde(orcToUpdate.getHordeName(), orcToUpdate.getName(), orcForm.getHordeName(), orcForm.getName());
            updateOrcAndHorde(orcToUpdate, orcForm);
        } else if (!orcToUpdate.getName().equals(orcForm.getName())) {
            hordeService.changeOrcName(orcForm.getHordeName(), orcToUpdate.getName(), orcForm.getName());
            updateOrcAndHorde(orcToUpdate, orcForm);
        } else {
            updateOrcAndHorde(orcToUpdate, orcForm);
        }

    }

    private void updateOrcAndHorde(Orc orcToUpdate, OrcForm orcForm) {
        orcToUpdate.setKillCount(orcForm.getKillCount());
        orcToUpdate.setName(orcForm.getName());
        orcToUpdate.setOrcRaceType(OrcRaceType.valueOf(orcForm.getOrcRaceType()));
        orcToUpdate.getWeapons().clear();
        for (String weapon : orcForm.getWeapons()) {
            orcToUpdate.getWeapons().add(WeaponType.valueOf(weapon));
        }
        orcToUpdate.setHordeName(orcForm.getHordeName());
        orcRepository.save(orcToUpdate);
    }

    private Orc findOrcById(Integer id) {
        return orcRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Orc findOrcByName(String name) {
        return orcRepository.findByName(name);
    }
}
