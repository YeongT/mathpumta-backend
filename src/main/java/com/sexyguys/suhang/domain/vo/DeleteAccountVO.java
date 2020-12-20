package com.sexyguys.suhang.domain.vo;

import lombok.Getter;
import lombok.Setter;

// POJO 방식으로 VO 객체 생성
@Getter
@Setter
public class DeleteAccountVO {
    private String email;
    private String password;
}
