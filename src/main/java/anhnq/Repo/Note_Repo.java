package anhnq.Repo;

import anhnq.Model.Notes_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Note_Repo extends JpaRepository<Notes_Model, Long> {
        List<Notes_Model> findByIdUser(Long idUser);
}
