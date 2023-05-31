package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain_ {

    public static void main(String[] args) {
        // META-INF/persistence.xml에서 설정정보를 가져오고 EntityMangerFactory를 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager 생성
        EntityManager em = emf.createEntityManager();

        // db 수정은 하나의 트랜젝션 안에서 이루어져야한다. ->  트랜잭션을 얻어야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜젝션 시작

        try{
            // insert
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("정이삭");
//
//            em.persist(member);

            // update
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("이삭킹"); /*
//                    JPA를 통해서 엔티티를 가져오면 JPA가 관리하고, 데이터가 바뀌었는지 트렌젝션 내에서 체크하기때문에
//                    커밋할때 엔티티가 바뀐데로 수정하기 때문이다..
//            */

            // JPQL 여러개조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//            // jpql로 짜놓으면 sql방언에 맞게 변형됨
//            for(Member member : result){
//                System.out.println("Member.name = " + member.getName());
//            }

            // [앤티티의 생명주기] 비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HElllo");
//
            // [앤티티의 생명주기] 영속
//            System.out.println("====Before====");
//            em.persist(member); // JPA 영속성 컨텍스트에 의해 관리됨 이때부터 영속성을 가짐
//            System.out.println("====After===="); /* 트랜젝션을 커밋하는 시점에 영속성 컨텍스트에서 관리되는 객체의 쿼리가 실행됨
//                    그래서 println찍은것들 뒤에 쿼리가 실행됨
//            */
//            // em.detach(member); [앤티티의 생명주기] 영속성컨텍스트에서 분리, 준영속 상태
//            // em.remove(member); [앤티티의 생명주기] 객체를 삭제
//
//            Member findMember = em.find(Member.class, 100L);
//            System.out.println("findMember.id= " + findMember.getId());
//            System.out.println("findMember.name= " + findMember.getName()); // 인설트 되기전에 출력된다 ->  영속성컨텍스트 1차캐시에 저장된걸 가져와서

            // 영속 엔티티의 동일성 보장 _ 1차캐시 덕에 가능
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = " +  (findMember1 == findMember2)); // true
//
//
            // 엔티티 등록 - 트랜잭션을 지원하는 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("===============================");
//            // 커밋하는 순간에 db로 insert쿼리를 보내기 때문에 println이 더 먼저뜨는 지연이 일어난다.

            // 엔티티 수정 - 변경 감지
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZ");
//
//            System.out.println("===============================");

            // 플러시 직접 호출하는 경우 - 커밋되기 전에 미리 결과가 보고싶다.
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush();
//            System.out.println("===============================");

            // 준영속
//            Member member = em.find(Member.class, 150L);
//            member.setName("weij");
//
////            em.detach(member); //  영속성을 제거해서 수정은 되지 않는다.
//            em.clear(); // 영속성컨텍스트를 통째로 다 지워버리는
//
//            System.out.println("===============");
//
//            // JPA 목적이 자바 컬렉션 사용하듯 db를 다룰려고 만들어진거다.
//            tx.commit(); // 트랜젝션 커밋 --> flush()가 일어남.
            /*
            *   플러시
            *   - 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
            *   - 변경 감지
            *   - 수정된 엔티티를 쓰기지연 sql저장소에 등록
            *   - 쓰기지연 sql저장소에 쌓아놨던 쿼리들을 데이터베이스에 전송(crud)
            *   - jpql 쿼리실행시는 플러시가 자동으로 된다. 그래서 커밋이 맨 뒤에 있더라도 jpql 앞에서 persist한 것들을 조회해볼 수 있는거다.
            *   - 영속성 컨텍스트를 비우지 않는다.
            *   - 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
            *   - 트랜잭션이라는 작업 단위가 중요 -> 커밋직전에만 동기화하면 된다.
            * */
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // db 작업후 닫아준다.
        }

        emf.close(); // db 작업후 닫아준다.



        System.out.println("JpaMain.main");
    }
}
