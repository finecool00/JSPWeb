package com.coding404.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding404.board.model.BoardDAO;
import com.coding404.board.model.BoardVO;

public class BoardServiceImpl implements BoardService{

	@Override
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		
		//title, writer, content
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(writer, title, content);
		
	}

	@Override
	public List<BoardVO> getList(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardVO> list = dao.getList();
		
		return list;
	}

	@Override
	public BoardVO getContent(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		
		
		return null;
	}

}
