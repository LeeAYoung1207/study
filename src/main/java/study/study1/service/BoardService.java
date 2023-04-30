package study.study1.service;

import study.study1.dto.BoardDTO;
import study.study1.dto.PageRequestDTO;
import study.study1.dto.PageResultDTO;
import study.study1.entity.Board2;
import study.study1.entity.Member2;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO boardDTO);

    default Board2 dtoToEntity(BoardDTO dto){

        Member2 member = Member2.builder()
                .email(dto.getWriterEmail())
                .build();


        Board2 board = Board2.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDTO(Board2 board, Member2 member, Long replyCount){
        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();
        return dto;
    }


}
