package hu.progmasters.mordor.repository;

import hu.progmasters.mordor.domain.Horde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HordeRepository extends JpaRepository<Horde,String> {
    Horde findHordeByName(String hordeName);

    Horde findHordeById(Integer id);

    @Query("SELECT h FROM Horde h WHERE :orcName in elements(h.orcList)")
    Horde findHordeByOrcName(@Param("orcName") String orcName);
}
