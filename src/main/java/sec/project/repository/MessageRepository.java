package sec.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public List<Message> findAllByOrderByIdAsc(); 
}
