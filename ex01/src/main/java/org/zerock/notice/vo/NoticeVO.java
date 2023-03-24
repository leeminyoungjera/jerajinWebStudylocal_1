package org.zerock.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeVO {
	
	private long no;
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 바로 아래줄만 적용됨.
	private Date startDate, endDate;
	private Date writeDate;
	private Date updateDate;
	
}
