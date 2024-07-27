package web.PP_3_1_2.dao;





import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.PP_3_1_2.model.User;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
        ;
    }

    @Override
    public void deleteUser(User user) {
        String hql = "delete from User where id=:id";
        entityManager.createQuery(hql).setParameter("id", user.getId()).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        String hql = "update User "
                + "SET name = :name "
                + ", surname = :surname "
                + ", age = :age "
                + ", email =: email"
                + " where id = :id";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("age", user.getAge());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }

    @Override
    public User showUser(int id) {
        String hql = "select u from User u where id=:id";
        return entityManager.createQuery(hql, User.class).setParameter("id", id).getSingleResult();
    }
}
