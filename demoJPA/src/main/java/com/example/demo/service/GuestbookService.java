package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;

// ** JPA CRUD 구현
// => Entity 와 DTO 를 용도별로 분리해서 사용
// 		dtoToEntity() 와 entityToDto() 메서드 추가
// 		default 메서드로 작성
public interface GuestbookService {
	
	// ** JPA Pageable 을 이용한 Paging 기능
	PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO);
	
	
	// => JPA CRUD 구현
	List<Guestbook> selectList();
	Guestbook selectOne(Long gno);
	
	// JPA는 수정과 입력을 동시에 함 DB에 정보가 없으면 입력 있으면 수정임 (register)
	Long register(GuestbookDTO dto);	// insert, update 모두 사용
	
	void delete(Long gno);
	
	
	// => dtoToEntity() 와 entityToDto()
	// dtoToEntity()
	// update하거나 insert할때 필요함 그때는 시간이 필요없어서 regDate 랑 modDate를 하지 않았음 (들어갈 때 자주 사용된다는 말씀이신듯)
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		
		return entity;
	}
	
	// entityToDto()
	// 결과 출력시 주로 사용되므로 regDate 랑 modDate가 포함 됨 (나올 때 자주 사용된다는 말씀이신듯)
	default GuestbookDTO entityToDto(Guestbook entity) {
		return GuestbookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
	}
	
	
} // interface
