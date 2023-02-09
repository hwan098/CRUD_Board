package com.study.paging;

import com.study.common.Dto.SearchDto;
import lombok.Getter;

@Getter
public class Pagination {
	private int totalRecordCount;	//전체 데이터 수
	private int totalPageCount;		//전체 페이지 수
	private int startPage;			//첫 페이지 번호
	private int endPage;			//끝 페이지 번호
	private int limitStart;			//LIMIT 시작 위치
	private boolean existPrevPage;  //이전 페이지 존재 여부 확인
	private boolean existNextPage;  //다음 페이지 존재 여부 확인
	
	public Pagination(int totalRecordCount, SearchDto params){
		if(totalRecordCount > 0) {
			this.totalRecordCount = totalRecordCount;
			this.calculation(params);
		}
	}
	
	private void calculation(SearchDto params) {
		
		//전체 페이지 수 계산-->데이터개수 / 한 페이지에 출력할 데이터 수
		totalPageCount = totalRecordCount / params.getRecordSize();
		
		//현재 페이지 번호가 전체 페이지 수보다 큰 경우,  현재 페이지 변호에 전체 페이지 수 저장
		if( params.getPage() > totalPageCount) {
			params.setPage(totalPageCount);
		}
		
		// 첫 페이지 번호 계산 --> (현재 페이지 번호 / 출력할 페이지 수) * 출력할 페이지 수 --> 5/10
		startPage = (params.getPage() / params.getPageSize()) * params.getPageSize();
		// 끝 페이지 번호 계산
		endPage = params.getPage() + params.getRecordSize();
		
		// 끝 페이지 번호가 전체 페이지 수보다 큰 경우, 끝 페이지번호에 전체 페이지 수 저장
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		//LIMIT 시작 위치 계산  --> 현재페이지-1 * 출력할 테이터 개수  (1 - 1) * 10 = 0  --> LIMIT 0, 10
		limitStart = (params.getPage() - 1) * params.getRecordSize();
		
		//이전 페이지 존재 여부 확인
		if(startPage >= 1) {
			existPrevPage = true;
		}
		
		//다음 페이지 존재 여부 확인
		if(totalRecordCount > (params.getRecordSize() * endPage)) {
			existNextPage = true;
		}
	}
}
