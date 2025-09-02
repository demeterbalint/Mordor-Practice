package hu.progmasters.mordor.service;

import hu.progmasters.mordor.domain.Horde;
import hu.progmasters.mordor.domain.Orc;
import hu.progmasters.mordor.dto.HordeForm;
import hu.progmasters.mordor.repository.HordeRepository;
import hu.progmasters.mordor.repository.OrcRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HordeService {

    private HordeRepository hordeRepository;
    private OrcRepository orcRepository;

    @Autowired
    public HordeService(HordeRepository hordeRepository, OrcRepository orcRepository) {
        this.hordeRepository = hordeRepository;
        this.orcRepository = orcRepository;
    }

    public void saveHorde(HordeForm hordeForm) {
        hordeRepository.save(new Horde(hordeForm));
    }

    public void addOrc(String hordeName, String name) {
        Horde horde = hordeRepository.findHordeByName(hordeName);
        horde.getOrcList().add(name);
        hordeRepository.save(horde);
    }

    public void changeOrcName(String hordeName, String oldName, String newName) {
        Horde horde = hordeRepository.findHordeByName(hordeName);
        horde.getOrcList().set(horde.getOrcList().indexOf(oldName), newName);
        hordeRepository.save(horde);
    }

    public void changeHorde(String originalHorde, String originalName, String newHorde, String newName) {
        Horde fromHorde = hordeRepository.findHordeByName(originalHorde);
        fromHorde.getOrcList().remove(originalName);
        Horde toHorde = hordeRepository.findHordeByName(newHorde);
        toHorde.getOrcList().add(newName);
        hordeRepository.save(fromHorde);
        hordeRepository.save(toHorde);
    }

    public void removeOrc(String hordeName, String orcName) {
        Horde horde = hordeRepository.findHordeByName(hordeName);
        horde.getOrcList().remove(orcName);
        hordeRepository.save(horde);
    }

    public void updateHorde(Integer id, HordeForm hordeForm) {
        Horde horde = hordeRepository.findHordeById(id);
        horde.setName(hordeForm.getName());
        horde.getOrcList().forEach(orcName -> {
            Orc orc = orcRepository.findOrcByName(orcName);
            orc.setHordeName(hordeForm.getName());
            orcRepository.save(orc);
        });
        hordeRepository.save(horde);
    }

    public void deleteHorde(Integer id) {
        Horde horde = hordeRepository.findHordeById(id);
        horde.getOrcList().forEach(orcName -> {
            Orc orc = orcRepository.findOrcByName(orcName);
            orc.setHordeName(null);
            orcRepository.save(orc);
        });
        hordeRepository.delete(horde);
    }
}
