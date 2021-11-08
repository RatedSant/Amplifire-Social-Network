package revature.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


/**
 * Data model class for likes and container for Like table's relationships
 * 
 */
@Entity
@Table(name="asn_like")
public class Like {
	
	//Primary Key
	@EmbeddedId
	private LikeID id;
	
	//Foreign Key Referencing User
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("asnUserFk")
	@JoinColumn(name="asn_user_fk")
	private User asnUserFK;
	
	//Foreign Key Referencing Post
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("asnPostFk")
	@JoinColumn(name="asn_post_fk")
	private Post asnPostFK;
	
	
	//TODO: ADDITIONAL VARRIABLES FOR EHANCED LIKE FEATURES GO HERE

	
	//No Aurgs Constructor
	public Like() {
		super();

	}

	//All Fields Constructor should be for most interactions.
	public Like(LikeID id, User asnUserFK, Post asnPostFK) {
		super();
		this.id = id;
		this.asnUserFK = asnUserFK;
		this.asnPostFK = asnPostFK;
	}	
	

	//GETTERS AND SETTERS
	
	public LikeID getId() {
		return id;
	}

	public void setId(LikeID id) {
		this.id = id;
	}

	public User getAsnUserFK() {
		return asnUserFK;
	}

	public void setAsnUserFK(User asnUserFK) {
		this.asnUserFK = asnUserFK;
	}

	public Post getAsnPostFK() {
		return asnPostFK;
	}

	public void setAsnPostFK(Post asnPostFK) {
		this.asnPostFK = asnPostFK;
	}

	//OVERRIDDEN METHODS
	
	@Override
	public String toString() {
		return "Like [asnUserFK=" + asnUserFK.getAsnUserId() + ", asnPostFK=" + asnPostFK.getAsnPostId() + "]";
	}

}
