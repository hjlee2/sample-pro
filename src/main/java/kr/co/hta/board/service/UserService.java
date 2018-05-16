package kr.co.hta.board.service;

import java.util.List;

import kr.co.hta.board.vo.User;

public interface UserService {

	void addNewUser(User user);
	User getUserDetail(String userId);
}
