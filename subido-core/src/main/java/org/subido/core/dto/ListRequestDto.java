package org.subido.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
public class ListRequestDto {

    private Integer offset;
    private Integer limit;
    private List<Sort> sortList;

    public Pageable getPageable() {
        int offset = this.offset == null ? 0 : this.offset;
        int limit = this.limit == 0 ? 1 : this.limit;
        // TODO: implement
        Sort sort = Sort.unsorted();
        return PageRequest.of(offset * limit, limit + 1, sort);
    }
}
