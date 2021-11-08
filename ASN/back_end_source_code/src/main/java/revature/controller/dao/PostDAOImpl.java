package revature.controller.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import revature.model.Post;


@Transactional
@Repository("postDAO")
public class PostDAOImpl {
	
	private SessionFactory sessionFactory;
	
	
	public void insert(Post newPost) {
		sessionFactory.getCurrentSession().save(newPost);
	}	
	
	public List<Post> selectAll() {		
		 //All this to retrieve Posts in desc order, that way see newest on top... should include Timestamp save date and time of Post creation eventually
		 CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		 CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
		 Root<Post> root = criteriaQuery.from(Post.class);
		 criteriaQuery.select(root);
		 criteriaQuery.orderBy(builder.desc(root.get("asnPostId")));
		 Query<Post> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);		 
		 List<Post> list = query.getResultList();
		 return list;
		
		//return sessionFactory.getCurrentSession().createQuery("from Post", Post.class).list();
	}
	
	public List<Post> selectByUserId(int userId) {	
		Query<Post> query = sessionFactory.getCurrentSession().createQuery("from Post where asn_user_fk = :userId ORDER BY asn_post_id DESC", Post.class);
		query.setParameter("userId", userId);	
		return query.list();
	}	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public PostDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public PostDAOImpl() {
		super();
	}
	
	

}
