package com.example.hongPark.aop;

import com.example.hongPark.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언 : 부가 기능을 주입하는 클래스
@Component // IoC 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect {
    // 대상 메소드 선택 : api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.hongPark.api.*.*(..))")
    public void cut() {}

    // 실행 시점 설정 : cut()의 대상이 수행되기 이전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { // cut()의 대상 메소드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        // 입력값 logging 하기
        // CommentService#create()의 입력값 => 5
        // CommentService#craete()의 입력값 => CommentDto(id=null, ...)
        for(Object  obj : args) {
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }
    // 실행 시점 설정 : cut()에 지정된 대상 호출 성공 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, // cut()의 대상 메소드
                                   Object returnObj) { // 리턴값
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        // 반환값 주입
        // CommentService#create()의 반환값 => CommentDto(id=10, ...)
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
