package kr.co.hta.board.web.advice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.co.hta.board.exception.SimpleBoardException;

@ControllerAdvice
public class SimpleBoardControllerAdvice {
//public class GlobalExceptionHandler {		>> 대신 사용 가능하다

	@ExceptionHandler(SimpleBoardException.class)
	public String handleException(SimpleBoardException ex) {
		ex.printStackTrace();
		return "error/500.jsp";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		ex.printStackTrace();
		return "error/500.jsp";
	}
	
	
}
