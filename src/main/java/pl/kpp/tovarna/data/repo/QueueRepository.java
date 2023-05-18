package pl.kpp.tovarna.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kpp.tovarna.data.entity.Queue;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Integer> {

}
