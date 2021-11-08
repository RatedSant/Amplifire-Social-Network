package revature.controller.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import revature.model.Comment;
import revature.model.Post;

@Transactional
@Repository("commentDAO")
public class CommentDAOImpl {
	
	private SessionFactory sessionFactory;
	
	public void insertComment(Comment comment) {
		//sessionFactory.getCurrentSession().merge(comment);
		Query<?> query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("INSERT INTO asn_comment (asn_comment_description , asn_user_fk, asn_post_fk) VALUES ( :description, :asnUserFk, :asnPostFk)");
		query.setParameter("description", comment.getAsnCommentDescription());
		query.setParameter("asnUserFk", comment.getAsnUserFK());
		query.setParameter("asnPostFk", comment.getAsnPostFK());
		query.executeUpdate();
	}
	
	public List<Comment> selectByPostId(int postId) {	
		Query<Comment> query = sessionFactory.getCurrentSession().createQuery("from Comment where asn_post_fk = :postId", Comment.class);
		query.setParameter("postId", postId);	
		return query.list();
	}	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public CommentDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	
	
	

}
