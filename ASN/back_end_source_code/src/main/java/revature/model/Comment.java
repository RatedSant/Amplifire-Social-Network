package revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asn_comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="asn_comment_id")
	private int asnCommentId;
	
	@Column(name = "asn_comment_description", nullable = false, columnDefinition = "TEXT")
	private String asnCommentDescription;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="asn_post_FK")
	private Post asnPostFK;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="asn_user_FK")
	private User asnUserFK;

	public int getAsnCommentId() {
		return asnCommentId;
	}

	public void setAsnCommentId(int asnCommentId) {
		this.asnCommentId = asnCommentId;
	}

	public String getAsnCommentDescription() {
		return asnCommentDescription;
	}

	public void setAsnCommentDescription(String asnCommentDescription) {
		this.asnCommentDescription = asnCommentDescription;
	}

	public Post getAsnPostFK() {
		return asnPostFK;
	}

	public void setAsnPostFK(Post asnPostFK) {
		this.asnPostFK = asnPostFK;
	}

	public User getAsnUserFK() {
		return asnUserFK;
	}

	public void setAsnUserFK(User asnUserFK) {
		this.asnUserFK = asnUserFK;
	}

	public Comment(int asnCommentId, String asnCommentDescription, Post asnPostFK, User asnUserFK) {
		super();
		this.asnCommentId = asnCommentId;
		this.asnCommentDescription = asnCommentDescription;
		this.asnPostFK = asnPostFK;
		this.asnUserFK = asnUserFK;
	}

	public Comment(String asnCommentDescription, Post asnPostFK, User asnUserFK) {
		super();
		this.asnCommentDescription = asnCommentDescription;
		this.asnPostFK = asnPostFK;
		this.asnUserFK = asnUserFK;
	}

	public Comment() {
		super();
	}

	@Override
	public String toString() {
		return "Comment [asnCommentId=" + asnCommentId + ", asnCommentDescription=" + asnCommentDescription
				+ ", asnPostFK=" + asnPostFK + ", asnUserFK=" + asnUserFK + "]";
	}
	
	
}