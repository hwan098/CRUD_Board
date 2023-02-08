package com.study.domain.post;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    PostResponse findById(Long id);	 //특정 게시글을 조회하는 SELECT쿼리를 호출하는 메서드
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);	//게시글을 수정하는 UPDATE 쿼리를 호출하는 메서드

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);	//게시글을 삭제 처리하는 UPDATE 쿼리를 호출하는 메서드

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return 게시글 리스트
     */
    List<PostResponse> findAll();

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count();	//전체 게시글 수를 조회하는 SELECT쿼리를 호출하는 메서드--> 페이징 기능에서 사용

}