package com.cos.photogramstart.domain.imgae;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //DB에 테이블을 생성
public class Image { // N
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String caption; 
	private String postImageUrl; // 사진을 전송 받아서 그 사진을 서버 특정 폴더에 저장 - DB엔 그 저장된 경로를 INSERT
	
	@JoinColumn(name = "userId")
	@ManyToOne // 1
	private User user; // 누가 업로드 했는지
	
	// 이미지 좋아요
	// 댓글
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	/* 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않게 함.
	 * @Override public String toString() { return "Image [id=" + id + ", caption="
	 * + caption + ", postImageUrl=" + postImageUrl + ", createDate=" + createDate +
	 * "]"; }
	 */
	
	
}
