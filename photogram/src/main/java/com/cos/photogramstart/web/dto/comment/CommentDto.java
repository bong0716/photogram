package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

// @NotNull : Null값 체크
// @NotEmpty : 반값이거나 null 체크
// @NotBlank : 빈값과 null, 빈공백(스페이스) 체크 

@Data
public class CommentDto {
	@NotBlank 
	private String content;
	@NotNull // (int는 NotBlack, NotNull 불가능, Integer는 NotBlack 불가능)
	private Integer imageId;
}
