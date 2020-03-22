package com.milo.community.community.model;

import lombok.Data;

/**
 * @author milo
 * @date 2020/3/22
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
