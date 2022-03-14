package com.tjoeun.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.vo.BoardVO;

public class BoardDAO 
{
	private static final String fpath = "C:/test/bbs.txt";

	public boolean save(BoardVO board) 
	{	
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath,true));
			pw.println(board);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public List<BoardVO> getList() {
		List<BoardVO> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			while((line=br.readLine())!=null) {
				String[] token = line.split("\\|");
				BoardVO vo = new BoardVO();
				vo.setNum(Integer.parseInt(token[0]));
				vo.setAuthor(token[1]);
				vo.setTitle(token[2]);
				vo.setContents(token[3]);
				vo.setDate(java.sql.Date.valueOf(token[4]));
				list.add(vo);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BoardVO getBoard(String strNum) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			while((line=br.readLine())!=null) {
				String[] token = line.split("\\|");
				if(token[0].equals(strNum)) {
					BoardVO board = new BoardVO();
					board.setNum(Integer.parseInt(token[0]));
					board.setAuthor(token[1]);
					board.setTitle(token[2]);
					board.setContents(token[3]);
					board.setDate(java.sql.Date.valueOf(token[4]));
					br.close();
					return board;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(BoardVO board) {
		//파일데이터 메모리에 로드, 동일한 레코드 검색, 새 데이터로 갱신, 
		//갱신된 리스트를 파일에 덮어쓰기
		List<BoardVO> list = getList();
		int idx = list.indexOf(board); // 동일성 검사
		if(idx<0) {
			return false;
		}
		list.get(idx).setTitle(board.getTitle());
		list.get(idx).setContents(board.getContents());
		return overwrite(list);
	}

	private boolean overwrite(List<BoardVO> list) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fpath));
			for(int i=0;i<list.size();i++) {
				out.println(list.get(i));
			}
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int num) {
		//파일로드, 메모리에서 해당 글 삭제, 변경된 리스트를 파일에 덮어쓰기
		List<BoardVO> list = getList();
		BoardVO board = new BoardVO();
		board.setNum(num);

		boolean deleted = list.remove(board);
		
		if (deleted) return overwrite(list);
		else return false;
	}

}
