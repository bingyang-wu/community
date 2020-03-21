package com.milo.community.community.dto;

import lombok.Data;

/**
 * @author milo
 * @date 2020/3/20
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
