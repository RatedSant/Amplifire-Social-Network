package revature.controller.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import revature.model.User;

@Transactional
@Repository("userDAO")
public class UserDAOImpl{
	
	private SessionFactory sessionFactory;

	public void insertUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public List<User> selectAll() {
		// It has to be User -name of the class- don't you dare to try asn_user -name of
		// the table in DB- it will explode
		List<User> temp = sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
		
		for(User element: temp) {
//			element.setAsnUserPassword("");
		}
		
		return temp;
	}
	
	////////// The only usage of this method is to check if the asnUser exists in the DB 
	public boolean verifyAccount(User user) {
		
		/////////// ' username ' is the parameter name, it can be ANY name
		
		String hql = "from User where asnUser = :username";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
		query.setParameter("username", user.getAsnUser());
		
		if(query.uniqueResult() == null) {
			
			return true;
			
		}else if(!query.uniqueResult().getAsnUser().contentEquals(user.getAsnUser())) {
			
			return true;
		}
		return false;
		
	}
	
	public User selectUserByID(int id) {
		
		String hql = "from User where asnUserId = :id";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
		query.setParameter("id", id);
		
		if(query.uniqueResult() == null) {
			
			return null;
			
		}else if(query.uniqueResult().getAsnUserId() == id) {
			
			User temp = query.uniqueResult();
			
//			temp.setAsnUserPassword("");
			
			return temp;
		}
		return null;
		
	}
	
	public void updateAccount(User user) {
		
		sessionFactory.getCurrentSession().update(user);
	}

	public User selectUser(User user) {
		Query<User> query = sessionFactory.getCurrentSession().createQuery("from User where asnUser = :user and asnUserPassword = :password", User.class);
		query.setParameter("user", user.getAsnUser());
		query.setParameter("password", user.getAsnUserPassword());
		List<User> userList = query.list();
		if(userList.size() > 0) {
			User temp = query.list().get(0);
//			temp.setAsnUserPassword("");
			return temp;			
		}
		return null;	
	}
	
	public User selectUserByEmail(String email) {
		Query<User> query = sessionFactory.getCurrentSession().createQuery("from User where asnUserEmail = :email", User.class);
		query.setParameter("email", email);
		List<User> userList = query.list();
		if(userList.size() > 0) {
			User temp = query.list().get(0);
//			temp.setAsnUserPassword("");
			return temp;			
		}
		return null;	
	}
	
	public List<User> selectUsersBySearch(String input) {
		Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User "
				+ "WHERE lower(asn_user) LIKE lower(:input) "
				+ "OR lower(asn_user_firstname) LIKE lower(:input) "
				+ "OR lower(asn_user_lastname) LIKE lower(:input)", User.class);
		query.setParameter("input", input+"%");

		if(!query.list().isEmpty()) {
			List<User> temp = query.list();
			
			for(User element: temp) {
//				element.setAsnUserPassword("");
			}
			
			return temp;			
		}
		return null;	
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
	}
	
	public UserDAOImpl() {
		super();
	}

	public UserDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
