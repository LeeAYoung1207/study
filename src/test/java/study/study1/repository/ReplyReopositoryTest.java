package study.study1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.study1.entity.Board2;
import study.study1.entity.Reply2;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyReopositoryTest {

    @Autowired
    private Reply2Repository replyRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1,150).forEach(i->{
            long bno = (long)(Math.random() * 50) + 1;

            Board2 board = Board2.builder().bno(bno).build();

            Reply2 reply = Reply2.builder()
                    .board(board)
                    .text("댓글" + i)
                    .replier("guest")
                    .build();
            replyRepository.save(reply);
        });

    }
}
