package kr.co.hta.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hta.board.dao.BoardDao;
import kr.co.hta.board.dao.UserDao;
import kr.co.hta.board.exception.SimpleBoardException;
import kr.co.hta.board.vo.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Board> getAllBoards() {
		return boardDao.getBoards();
	}
	@Override
	public Board getBoardDetail(int boardNo) {
		return boardDao.getBoardByNo(boardNo);
	}
	@Override
	public void addNewBoard(Board board) {
		boardDao.addBoard(board);
		// 새 게시글 등록시 포인트 5점 부여
//		 User user = userDao.getUserById(board.getNick());
//		 user.setPoint(user.getPoint() + 5);
//		 userDao.updateUser(user);
	}
	@Override
	public void deleteBoard(int boardNo, String userId) {
		Board board = boardDao.getBoardByNo(boardNo);
		if (!userId.equals(board.getNick())) {
			throw new SimpleBoardException("본인이 작성한 글만 삭제 할 수 있습니다.");
		}
		boardDao.deleteBoardByNo(boardNo);
	}
	
}
