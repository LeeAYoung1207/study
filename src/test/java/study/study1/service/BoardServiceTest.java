package study.study1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.study1.dto.BoardDTO;
import study.study1.dto.PageRequestDTO;
import study.study1.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void registerTest(){
        BoardDTO dto = BoardDTO.builder()
                .title("test")
                .content("register")
                .writerEmail("test1@test.com")
                .build();

        Long register = boardService.register(dto);
        System.out.println(register);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void getTest(){
        BoardDTO boardDTO = boardService.get(10L);
        System.out.println(boardDTO);
    }

    @Test
    public void removeTest(){
        boardService.removeWithReplies(4L);
    }
}
