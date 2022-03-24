package com.tjoeun.util;

import java.util.List;
import java.util.Map;

public class PageUtil {

	private List<Map> list;
	private int rows;
	private int currPage;
	private int totalPages;
	private int totalRows;
	private int navCount;

	
	public void setList(List<Map> list) {
		
		this.list = list;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCurrPage() {
		return currPage;
	}


	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}


	public List<Map> getList() {
		return list;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public int getTotalRows() {
		return totalRows;
	}


	public int getNavCount() {
		return navCount;
	}

	public void setRowsPerScreen(int rows) {
		
		this.rows = rows;
	}

	public void setCurrentPage(int currPage) {
		
		this.currPage = currPage;
		
	}

	public void setTotalPages(int totalPages) {
		
		this.totalPages = totalPages;
		
	}

	public void setTotalRows(int totalRows) {
		
		this.totalRows = totalRows;
		
	}

	public void setNavCount(int navCount) {
		
		this.navCount = navCount;
		
	}
	
	public int[] getNavLinks() {
		
//		5/2 = 2.5 -> 2.0 -> 2 다른언어
//		(navCount/2)
		int low = (currPage - (navCount/2)) < 1 ? 1 : currPage - (navCount/2);
		int high = (low+4)>totalPages ? totalPages : low+4;
		low = high<=5 ? 1 : high-4;
		
		int[] links = new int[ (high-low) + 1 ];
		
		for(int n=low, i=0; n<=high; n++) {
			links[i++] = n;
		}
		
		return links;
	}
	
	
}
