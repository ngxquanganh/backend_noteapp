package anhnq.Repo;

import anhnq.Model.User_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repo extends JpaRepository<User_Model, Long> {
}
