package com.sexyguys.suhang.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyAccountVO {
    private String target_email;
    private String target_password;
    private String new_email;
    private String new_password;
    private String new_school;
}
