package io.simpolor.aop.aop;

/**
 * 포인트 컷에서 자주사용되는 정의
 * - execution(public * *(..)) : public 메소드 실행
 * - execution(* set*(..)) : 이름이 set으로 시작하는 모든 메소드명 실행
 * - execution(* set*(..)) : 이름이 set으로 시작하는 모든 메소드명 실행
 * - execution(* com.xyz.service.AccountService.*(..)) : AccountService 인터페이스의 모든 메소드 실행
 * - execution(* com.xyz.service.*.*(..)) : service 패키지의 모든 메소드 실행
 * - execution(* com.xyz.service..*.*(..)) : service 패키지와 하위 패키지의 모든 메소드 실행
 * - within(com.xyz.service.*) : service 패키지 내의 모든 결합점
 * - within(com.xyz.service..*)	: service 패키지 및 하위 패키지의 모든 결합점
 * - this(com.xyz.service.AccountService) : AccountService 인터페이스를 구현하는 프록시 개체의 모든 결합점
 * - target(com.xyz.service.AccountService)	: AccountService 인터페이스를 구현하는 대상 객체의 모든 결합점
 * - args(java.io.Serializable)	: 하나의 파라미터를 갖고 전달된 인자가 Serializable인 모든 결합점
 * - @target(org.springframework.transaction.annotation.Transactional) : 대상 객체가 @Transactional 어노테이션을 갖는 모든 결합점
 * - @within(org.springframework.transaction.annotation.Transactional) : 대상 객체의 선언 타입이 @Transactional 어노테이션을 갖는 모든 결합점
 * - @annotation(org.springframework.transaction.annotation.Transactional) : 실행 메소드가 @Transactional 어노테이션을 갖는 모든 결합점
 * - @args(com.xyz.security.Classified) : 단일 파라미터를 받고, 전달된 인자 타입이 @Classified 어노테이션을 갖는 모든 결합점
 * - bean(accountRepository) : “accountRepository” 빈
 * - !bean(accountRepository) : “accountRepository” 빈을 제외한 모든 빈
 * - bean(*) : 모든 빈
 * - bean(account*)	: 이름이 'account'로 시작되는 모든 빈
 * - bean(*Repository)	: 이름이 “Repository”로 끝나는 모든 빈
 * - bean(accounting/*)	: 이름이 “accounting/“로 시작하는 모든 빈
 * - bean(*dataSource) || bean(*DataSource)	: 이름이 “dataSource” 나 “DataSource” 으로 끝나는 모든 빈
 */