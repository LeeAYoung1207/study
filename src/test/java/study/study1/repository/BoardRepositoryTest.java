package study.study1.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import study.study1.entity.Board2;
import study.study1.entity.Member2;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private Board2Repository boardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1,50).forEach(i->{
            Member2 member = Member2.builder().email("test" + i + "@test.com").build();
            Board2 board = Board2.builder()
                    .title("제목" + i)
                    .content("내용: " + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });

    }

    @Test
    public void findAll(){
        boardRepository.findAll().stream().forEach(i-> System.out.println(i));
    }

    @Transactional
    @Test
    public void readTest(){
        Board2 board = boardRepository.findById(5L).get();

        System.out.println(board);

        System.out.println(board.getWriter());
    }

    @Test
    public void testQuery(){
        Object result = boardRepository.getBoardWithWriter(5L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testQuery2(){
        List<Object[]> result = boardRepository.getBoardWithReply(5L);

        for(Object[] arr: result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testQuery3(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[])row;
            System.out.println(Arrays.toString(arr));
        });

    }

    @Test
    public void testQuery4(){
        Object result = boardRepository.getBoardByBno(5L);
        Object[] arr =(Object[]) result;
        System.out.println(arr);
        System.out.println("-------------------------------------");
        System.out.println(Arrays.toString(arr));
    }

}
