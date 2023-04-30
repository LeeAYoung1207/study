package study.study1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import study.study1.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import study.study1.dto.PageRequestDTO;
import study.study1.dto.PageResultDTO;
import study.study1.entity.Board2;
import study.study1.entity.Member2;
import study.study1.repository.Board2Repository;
import study.study1.repository.Reply2Repository;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final Board2Repository boardRepository;

    private final Reply2Repository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);

        Board2 board2 = dtoToEntity(dto);

        boardRepository.save(board2);

        return board2.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn =(en -> entityToDTO((Board2)en[0],(Member2) en[1], (Long)en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board2)arr[0],(Member2) arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);

    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board2> findBoard = boardRepository.findById(boardDTO.getBno());
        if (findBoard.isPresent()){
            Board2 board = findBoard.get();

            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            boardRepository.save(board);
        }

    }
}
