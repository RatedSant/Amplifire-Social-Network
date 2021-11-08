package revature.controller.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import revature.model.Like;
import revature.model.LikeID;

/**
 * DAO class for Likes. It is responsible for all Database accesses directly
 * referencing Like(s)
 *
 */
@Transactional
@Repository("likeDAO")
public class LikeDAOImpl {
	// Private Access for SessionFactory
	private SessionFactory sessionFactory;

	/**
	 * insert Builds a custom insert into query using Hibernate and set it
	 * parameters Using the values of the new LikeID
	 * 
	 * @param newLike Primary Key ID of a new Like object that doesn't exist in the
	 *                Database yet.
	 */
	public void insert(LikeID newLike) {
		Query<?> query = sessionFactory.getCurrentSession()
				.createSQLQuery("INSERT INTO asn_like (asn_user_fk, asn_post_fk) VALUES ( :asnUserFk, :asnPostFk)");
		query.setParameter("asnUserFk", newLike.getAsnUserFk());
		query.setParameter("asnPostFk", newLike.getAsnPostFk());
		query.executeUpdate();
	}

	/**
	 * delete Builds a custom Delete FROm query using Hibernate and set it
	 * parameters Using the values of the existing LikeID
	 * 
	 * @param currLike Primary Key ID of a Like object that currently exist in the
	 *                 Database yet.
	 */
	public void delete(LikeID currLike) {
		Query<?> query = sessionFactory.getCurrentSession()
				.createSQLQuery("DELETE FROM asn_like WHERE asn_user_fk = :asnUserFk AND asn_post_fk = :asnPostFk");
		query.setParameter("asnUserFk", currLike.getAsnUserFk());
		query.setParameter("asnPostFk", currLike.getAsnPostFk());
		query.executeUpdate();

	}

	/**
	 * selectAll returns a list of all the LikeIDs after extracting them from the
	 * list of all Likes
	 * 
	 * @return List of LikeID Front-end only requires the info contained in the
	 *         LikeID so no need to send entire Object's info
	 */
	public List<LikeID> selectAll() {

		List<Like> temp = sessionFactory.getCurrentSession().createQuery("from Like", Like.class).list();

		List<LikeID> result = new ArrayList<>();

		for (Like element : temp) {
			result.add(element.getId());
		}

		return result;
	}

	/**
	 * selectByUserId returns list of all LikeIDs tied to a given User ID
	 * after extracting them from the list of all Likes tied to a given USER ID
	 * @param userId The ID of an existing User
	 * @return List of LikeID Front-end only requires the info contained in the
	 *         LikeID so no need to send entire Object's info
	 */
	public List<LikeID> selectByUserId(int userId) {
		Query<Like> query = sessionFactory.getCurrentSession().createQuery("from Like where asn_user_fk = :userId",
				Like.class);
		query.setParameter("userId", userId);

		List<Like> temp = query.list();

		List<LikeID> result = new ArrayList<>();

		for (Like element : temp) {
			result.add(element.getId());
		}

		return result;
	}
	
	/**
	 * selectByPostId returns list of all LikeIDs tied to a given Post ID
	 * after extracting them from the list of all Likes tied to a given Post ID
	 * @param postId The ID of an existing Post
	 * @return List of LikeID Front-end only requires the info contained in the
	 *         LikeID so no need to send entire Object's info
	 */
	public List<LikeID> selectByPostId(int postId) {
		Query<Like> query = sessionFactory.getCurrentSession().createQuery("from Like where asn_post_fk = :postId",
				Like.class);
		query.setParameter("postId", postId);

		List<Like> temp = query.list();

		List<LikeID> result = new ArrayList<>();

		for (Like element : temp) {
			result.add(element.getId());
		}

		return result;
	}
	
	/**
	 * getHasLiked returns a boolean value of if a given User has liked a given Post.
	 * @param userId The ID of an existing User
	 * @param postId The ID of an existing Post
	 * @return True for if user has liked that post and false if they have not
	 */
	
	public boolean getHasLiked(int userId, int postId) {
		Query<Like> query = sessionFactory.getCurrentSession()
				.createQuery("from Like where asn_user_fk = :userId AND asn_post_fk = :postId", Like.class);
		query.setParameter("userId", userId);
		query.setParameter("postId", postId);
		return !query.list().isEmpty();
	}

	/**
	 * getLikeCount builds a custom query for a view built on the POSTGRESQL side
	 * and returns an Integer of how many likes a given post has
	 * @param postId ID of an existing Post
	 * @return Integer of number of likes 
	 */
	public Integer getLikeCount(Integer postId) {
		Query<?> query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT asn_like_count FROM asn_like_view WHERE asn_post_id = :postId");
		query.setParameter("postId", postId);
		return ((BigInteger) query.getSingleResult()).intValue();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public LikeDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public LikeDAOImpl() {
		super();
	}
}
