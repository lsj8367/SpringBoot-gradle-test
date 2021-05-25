package com.lsj8367.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Auditing 기능을 포함시킴
public abstract class BaseTimeEntity {
    //모든 Entity의 상위 클래스가 되어 Entity들의 createDate, modifiedDate를 자동으로 관리하는 역할을 하는 클래스

    @CreatedDate //Entity가 생성되어 저장될때 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한Entity가 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
}
