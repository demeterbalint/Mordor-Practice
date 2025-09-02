package hu.progmasters.mordor.repository;

import hu.progmasters.mordor.domain.Horde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HordeRepository extends JpaRepository<Horde,String> {
    Horde findHordeByName(String hordeName);

    Horde findHordeById(Integer id);
}
