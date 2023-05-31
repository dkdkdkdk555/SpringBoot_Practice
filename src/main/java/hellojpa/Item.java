package hellojpa;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="DIS_TYPE")
public abstract class Item { // 추상클래스로 만들어야 한다. 일반클래스로 만들면 상속과 상관없이 해당 클래스를 독단적으로 쓴다는 얘기여서 JPA에서 테이블을 생성한다.

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
