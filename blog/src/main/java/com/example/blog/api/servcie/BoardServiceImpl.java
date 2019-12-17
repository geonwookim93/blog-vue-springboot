package com.example.blog.api.servcie;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.blog.api.domain.Board;
import com.example.blog.api.domain.BoardDto;
import com.example.blog.api.repository.BoardRepository;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private BoardRepository boardRepository;

	@Transactional
	public void saveBoard(BoardDto boardDto) {
		boardRepository.save(boardDto.toEntity());
	}

	@Transactional
	public List<BoardDto> getBoardlist() {

		List<Board> boardEntities = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		for (Board board : boardEntities) {
			boardDtoList.add(entityToDto(board));
		}
		return boardDtoList;
	}

	@Transactional
	public BoardDto getBoard(Long bid) {
		Board board = boardRepository.findById(bid).get();
		return entityToDto(board);
	}

	@Transactional
	public void updateBoard(BoardDto boardDto, Long bid) throws NotFoundException {
		Board board = boardRepository.findById(bid).get();
		if (board == null) {
			throw new NotFoundException("id not founded");
		}
		if (boardDto.getTitle() != null) {
			board.setTitle(boardDto.getTitle());
		}
		if (boardDto.getContent() != null) {
			board.setContent(boardDto.getContent());
		}
		if (boardDto.getWriter() != null) {
			board.setWriter(boardDto.getWriter());
		}
		if (boardDto.getBoardType() != null) {
			board.setBoardType(boardDto.getBoardType());
		}
		boardRepository.save(board);
	}

	@Transactional
	public void deleteBoard(Long bid) throws NotFoundException {
		Board board = boardRepository.findById(bid).get();
		if (board == null) {
			throw new NotFoundException("id not founded");
		} else {
			boardRepository.delete(board);
		}

	}

	public BoardDto entityToDto(Board board) {
		BoardDto boardDto = BoardDto.builder().bid(board.getBid()).title(board.getTitle()).content(board.getContent())
				.writer(board.getWriter()).createdDate(board.getCreatedDate()).updatedDate(board.getUpdatedDate())
				.build();
		return boardDto;
	}

}
