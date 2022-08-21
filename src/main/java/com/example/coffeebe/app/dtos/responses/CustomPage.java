package com.example.coffeebe.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {
    private List<T> data;
    private Metadata metadata;

    public CustomPage(Page<T> page) {
        data = page.getContent();
        metadata = new Metadata(page);
    }
    public CustomPage() {
    }

    @Data
    @AllArgsConstructor
    public static class Metadata {
        private int page = 0;
        private int size = 10;
        private long total = 0;
        private int totalPage = 0;

        public <T> Metadata(Page<T> page) {
            size = page.getSize();
            this.page = page.getNumber();
            this.total = page.getTotalElements();
            this.totalPage = page.getTotalPages();
        }
    }
}
