package study.study1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.study1.entity.Board2;

import java.util.List;

public interface Board2Repository extends JpaRepository<Board2, Long> {
    @Query("select b, w from Board2 b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("SELECT b, r from Board2 b Left join Reply2 r on r.board = b where b.bno =:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w, count(r) " +
    "from Board2 b left join b.writer w left join Reply2 r on r.board = b group by b",
    countQuery ="select count(b) from Board2 b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w, count(r) " +
            "from Board2 b left join b.writer w " +
            "left outer join Reply2 r on r.board = b " +
            "where b.bno =:bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
