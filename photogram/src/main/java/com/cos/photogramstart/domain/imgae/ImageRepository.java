package com.cos.photogramstart.domain.imgae;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer>{

	@Query(value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY id DESC", nativeQuery=true)
	Page<Image> mStroy(int principalId, Pageable pageable);
	
	@Query(value = "SELECT i.* \r\n"
			+ "FROM image i INNER JOIN \r\n"
			+ "(SELECT imageId, COUNT(imageId) likeCount \r\n"
			+ "FROM likes \r\n"
			+ "GROUP BY imageId) c\r\n"
			+ "on i.id = c.imageId\r\n"
			+ "ORDER BY likeCount DESC", nativeQuery = true)
	List<Image> mPopular();
	
}
