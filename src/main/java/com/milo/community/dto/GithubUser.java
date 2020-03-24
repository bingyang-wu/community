package com.milo.community.dto;

import lombok.Data;

/**
 * @author jjjyy
 * @date 2020/3/20
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
