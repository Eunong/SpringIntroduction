package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jdbc가 관리하는 Object
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동으로 PK를 생성하는 경우
    private Long id;

    @Column(name = "name") // DB의 컬럼명과 name 명칭과 일치시킴
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
