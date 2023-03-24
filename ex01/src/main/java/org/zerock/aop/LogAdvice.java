package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	//실행 전 후에 처리를 선언해서 동작 시키는 것 의미 : @Around
	@Around("execution(* org.zerock.*.service.*ServiceImpl.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info("\nStart=======================================================");
		//실행되는 Class
		log.info("* 실행객체 : " + pjp.getTarget());
		log.info("* 실행메서드 : " + pjp.getSignature());
		//parameter
		log.info("* 전달 데이터 : " + Arrays.deepToString(pjp.getArgs()));
		
		
		//처리내용
		
		//처리결과 저장객체
		
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		log.info("* 처리결과 데이터 : " + result);
		long end = System.currentTimeMillis();
		
		log.info("TIME : " + (end-start));
		log.info("\nEnd=========================================================");
		
		return result;
	}
	
}
