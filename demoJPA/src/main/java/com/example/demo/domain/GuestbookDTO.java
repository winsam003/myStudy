package com.example.demo.domain;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestbookDTO {

	private long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;			// 작성시간
	private LocalDateTime modDate;			// 수정시간
	
}
