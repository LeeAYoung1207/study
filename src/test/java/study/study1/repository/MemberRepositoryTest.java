package study.study1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.study1.entity.Member2;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private Member2Repository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1,50).forEach(i -> {
            Member2 member = Member2.builder()
                    .email("test" + i + "@test.com")
                    .name("ay")
                    .password("0000")
                    .build();

            memberRepository.save(member);
        });
    }



}
