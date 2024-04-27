package thecommerce.toy.global.util.paging;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfo {

    private long total;
    private int maxPage;

    private boolean next;
    private int page;
    private boolean prev;


}
