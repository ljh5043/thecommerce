package thecommerce.toy.domain.member.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import thecommerce.toy.domain.member.enums.SortTypeForFindAll;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class FindAllByPagingRequest {

    @Positive(message = "page는 1보다 커야합니다.")
    @Schema(description = "페이지", example = "1")
    private Integer page;

    @Positive(message = "page size는 1보다 커야합니다.")
    @Schema(description = "사이즈", example = "2")
    private Integer pageSize;

    @NotNull
    private SortTypeForFindAll sort;

    public FindAllByPagingRequest(Integer page, Integer pageSize, SortTypeForFindAll sort){
        this.page = (page == null || page < 1) ? 1 : page;
        this.pageSize = (pageSize == null || pageSize < 1) ? 10 : pageSize;
        this.sort = (sort == null) ? SortTypeForFindAll.CREATED_AT : sort;
    }
}
