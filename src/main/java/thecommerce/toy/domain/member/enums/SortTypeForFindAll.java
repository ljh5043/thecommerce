package thecommerce.toy.domain.member.enums;

public enum SortTypeForFindAll {

    CREATED_AT("가입일 순"),
    NAME("이름순");

    private final String sortType;

    SortTypeForFindAll(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

}
