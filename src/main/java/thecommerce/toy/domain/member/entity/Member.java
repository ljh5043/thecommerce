package thecommerce.toy.domain.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thecommerce.toy.domain.member.payload.request.ModifyMemberInfoRequest;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;                                        //  회원 PK

    @Column(unique = true)
    private String loginId;                                 //  로그인ID

    private String password;                                //  비밀번호

    @Column(unique = true)
    private String nickName;                                //  닉네임

    private String name;                                    //  이름

    private String phone;                                   //  전화번호

    private String email;                                   //  이메일주소

    private boolean active;                                 //  활성

    public Member(SaveNewMemberRequest request){
        this.loginId = request.getLoginId();
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.name = request.getName();
        this.phone = request.getPhone();
        this.email = request.getEmail();
    }

    public void completeSave(){
        this.active = true;
    }

    public void update(ModifyMemberInfoRequest request) {
        this.phone = request.getPhone();
        this.email = request.getEmail();
    }
}