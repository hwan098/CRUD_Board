package com.study.paging;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class PagingResponse<T> {
	private List<T> list = new ArrayList<>();
	private Pagination pagination;	//계산된 페이지 정보를 담아 화면(HTML)으로 전달하는 용도로 사용
	
	public PagingResponse(List<T> list, Pagination pagination) {
		this.list = list;
		this.pagination = pagination;
	}

}
