package com.coding404.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coding404.user.model.UserDAO;
import com.coding404.user.model.UserVO;

public class UserServiceImp1 implements UserService {

	@Override
	public int join(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		//아이디 중복검사
		//싱글톤 객체를 얻는 방법
		UserDAO dao = UserDAO.getInstance();
		int result = dao.idCheck(id);
		
		if(result == 1) { //중복 O
			return 1;
		} else { //가입처리
			UserVO vo = new UserVO(id, pw, name, email, gender, null);
			dao.join(vo);
			
			return 0;
		}
	}

	@Override
	public UserVO login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//로그인
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.login(id, pw);
		
		return vo;
	}

	@Override
	public UserVO getInfo(HttpServletRequest request, HttpServletResponse response) {
		
		//회원아이디(상황에따라 택일!) - 이전화면에서 필요한 값을 넘겨주거나 VS 회원아이디는 세션에 존재
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		//dao호출
		  UserDAO dao = UserDAO.getInstance();
	      UserVO vo = dao.getInfo(id);
		
		
		
		return vo;
	}

	@Override
	public int updateInfo(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * 서비스와 DAO영역을 작성
		 * 1. 아이디 기반으로 회원정보를 수정합니다.
		 * 2. 성공실패 여부를 컨트롤러 반환 받습니다.
		 * 3. 수정 성공시 -> mypage로 리다이렉트, 실패시 -> modify로 리다이렉트
		 */
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		UserVO vo = new UserVO(id, pw, name, email, gender, null);
		
		//dao객체
		UserDAO dao = UserDAO.getInstance();
		int result = dao.updateInfo(vo);
		return result;
	}

	
	
}
