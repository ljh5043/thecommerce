package thecommerce.toy.global.util.paging;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageUtil {

    public PageInfo getPageInfo(Page<?> pageList, int page) {
        long total = pageList.getTotalElements();
        int maxPage = pageList.getTotalPages();
        boolean next = pageList.hasPrevious();
        boolean prev = pageList.hasNext();

        return new PageInfo(total, maxPage, next, page, prev);
    }

}
