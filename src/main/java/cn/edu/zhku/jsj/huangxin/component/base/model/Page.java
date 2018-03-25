package cn.edu.zhku.jsj.huangxin.component.base.model;

import java.util.List;

public class Page<T> {
	
	private int curPage;
	private int totalPages;
	private int totalRows;
	private int pageSize;
	private List<T> data;
	
	public int getCurPage(){
		return curPage;
	}
	public void setCurPage(int curPage){
		this.curPage=curPage;
	}
	public int getTotalPages(){
		return totalPages;
	}
	public void setTotalPages(int totalPages){
		this.totalPages=totalPages;
	}
	public int getTotalRows(){
		return totalRows;
	}
	public void setTotalRows(int totalRows){
		this.totalRows=totalRows;
	}
	public int getPageSize(){
		return pageSize;
	}
	public void setPageSize(int pageSize){
		this.pageSize=pageSize;
	}
	public List<T> getData(){
		return data;
	}
	public void setData(List<T> data){
		this.data=data;
	}
}
