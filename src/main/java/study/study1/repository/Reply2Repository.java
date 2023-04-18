package study.study1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import study.study1.entity.Reply2;

public interface Reply2Repository extends JpaRepository<Reply2, Long> {

    @Modifying
    @Query("delete from Reply2 r where r.board.bno =:bno")
    void deleteByBno(Long bno);
}
