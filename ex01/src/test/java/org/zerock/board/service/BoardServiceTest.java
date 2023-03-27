package org.zerock.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//테스트 프로그램을 지정
@RunWith(SpringJUnit4ClassRunner.class)
//설정파일을 불러와서 적용
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private BoardServiceImpl service;
	
	@Test
	public void testExist() {
		//not null 확인하는 메서드
//		assertNotNull(service);
		log.info("\n\nBoard 서비스 테스트-------------------------");
		log.info(service);
//		log.info(service.getDao());
		log.info("\nBoard 서비스 종료-------------------------\n\n");
	}
	
}
