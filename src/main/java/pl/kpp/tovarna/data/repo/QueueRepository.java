package pl.kpp.tovarna.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kpp.tovarna.data.classes.BuildState;
import pl.kpp.tovarna.data.entity.Queue;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Integer> {

    List<Queue> findByState(BuildState buildState);

    @Query("Select q from Queue q where state=0 or state=2")
    Iterable<Queue> lookForReadyToProcess();

    @Modifying
    @Query(value = "Truncate Table Queue", nativeQuery = true)
    void deleteAllUnrestricted();
}
