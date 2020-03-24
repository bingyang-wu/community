package com.milo.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();
    private List<QuestionDTO> questions;

    public void setPagination(int count, int page, int size) {

        if (page < 1) {
            page = 1;
        }

        int totalPage = 0;
        totalPage = count / size;
        if (count % size != 0) {
            totalPage++;
        }

        this.totalPage = totalPage;

        if (page > totalPage) {
            page = totalPage;
        }

        this.page = page;


        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        // 是否展示上一页
        if (page != 1) {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page != totalPage) {
            showNext = true;
        }

        // 是否展示第一页
        if (!pages.contains(1)) {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (!pages.contains(totalPage)) {
            showEndPage = true;
        }
    }
}
