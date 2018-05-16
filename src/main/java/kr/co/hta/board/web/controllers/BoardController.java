package kr.co.hta.board.web.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ko.co.hta.board.web.form.BoardForm;
import kr.co.hta.board.annotation.LoginUser;
import kr.co.hta.board.service.BoardService;
import kr.co.hta.board.vo.Board;
import kr.co.hta.board.vo.User;
import kr.co.hta.board.web.views.DownloadView;

@Controller
@RequestMapping("/board")
public class BoardController {

	// 파일 업로드 처리
	private String directory = "c:/upload/formfile";
	
	@Autowired
	private DownloadView downloadView;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		
		return "board/list.jsp";
	}
	
	@RequestMapping("/detail.do")
	public String detail(@RequestParam("no") int boardNo, Model model) {
		model.addAttribute("board", boardService.getBoardDetail(boardNo));
		return "board/detail.jsp";
	}
	
	@RequestMapping("/del.do")
	public String delete(@RequestParam("no") int boardNo, @LoginUser User user) {
//	public String delete(@RequestParam("no") int boardNo, HttpSession session) {
//		User loginUser = (User) session.getAttribute("LOGIN_USER");
		boardService.deleteBoard(boardNo, user.getId());
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/form.do")
	public String form() {
		return "board/form.jsp";
	}
	
	@RequestMapping("/add.do")
	public String add(BoardForm boardForm, HttpSession session) throws Exception {
//		public String add(BoardForm boardForm, @LoginUser User user) throws Exception {
		
		User user = (User) session.getAttribute("LOGIN_USER");
		
		Board board = new Board();
		board.setTitle(boardForm.getTitle());
		board.setNick(user.getId());
		board.setContents(boardForm.getContents());
		
		MultipartFile upFile = boardForm.getUpfile();
		if (!upFile.isEmpty()) {
			String filename = upFile.getOriginalFilename();
			board.setFilename(upFile.getOriginalFilename());
			
			FileCopyUtils.copy(upFile.getBytes(), new File(directory, filename));
		}
		boardService.addNewBoard(board);
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/down.do")
	public ModelAndView download(@RequestParam("no") int boardNo) {
		ModelAndView mav = new ModelAndView();
		
		Board board = boardService.getBoardDetail(boardNo);
		mav.addObject("directory", directory);
		mav.addObject("filename", board.getFilename());
		mav.setView(downloadView);
		
		return mav;
	}
}
