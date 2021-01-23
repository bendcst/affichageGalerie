package galerie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import galerie.entity.Artiste;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring 

public interface ArtisteRepository extends JpaRepository<Artiste, Integer> {
    @Query(value = "SELECT * FROM PERSONNE WHERE DTYPE = 'Artiste'",
            nativeQuery = true)
    Collection<Artiste> findAllAuteur();
}
