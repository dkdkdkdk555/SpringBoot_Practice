package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain3_ {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /*
             단방향 매핑
             */
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member4 member = new Member4();
//            member.setUsername("member1");
////            member.setTeamId(team.getId());  키로 조인 -> 객체로 참조
//            member.setTeam(team);
//            em.persist(member);

//            Member4 findMember = em.find(Member4.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam= " + findTeam.getName());

            // 맴버의 팀(외래키)을 바꾸고 싶을때
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam); // db의 외래키값이 없데이트됨

            /*
             양방향 매핑
             */
//            Member4 findMember = em.find(Member4.class, member.getId());
//            List<Member4> members = findMember.getTeam().getMembers();
//
//            for(Member4 m : members) {
//                System.out.println("m = " + m.getUsername());
//            }

            /*
             양방향 매핑시 가장 많이 하는 실수
             */
            // 저장
//            Member4 member4 = new Member4();
//            member4.setUsername("member4");
//            em.persist(member4);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member4);  // 역방향만 연관관계 설정 --> 읽기전용이여서 안됨다.
//          m team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member4 = new Member();
//            member4.setUsername("member1");
//            member4.setTeam(team); // **
//            em.persist(member4);
//
////            team.getMembers().add(member4); // **
//
////            em.flush(); // db에 반영
////            em.clear(); // 영속성컨텍스트 내용 지움
//
//            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시에서 내용 가져옴
//            List<Member> members = findTeam.getMembers();
//
//            System.out.println("=============");
//            for(Member m : members){
//                System.out.println("m = " + m.getUsername());
//            }
//            System.out.println("=============");  em.persist(team);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
