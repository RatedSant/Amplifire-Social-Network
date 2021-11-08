package revature.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"likeList", "commentsList"})
@Entity
@Table(name="asn_post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="asn_post_id")
	private int asnPostId;
	
	@Column(name="asn_post_description", columnDefinition="TEXT")
	private String asnPostDescription;
	
	@Column(name="asn_post_image_url", columnDefinition = "TEXT")
	private String asnPostImageUrl;
	
	@Column(name = "asn_created_at")
	@CreationTimestamp
	private Timestamp asnCreatedAt;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="asn_user_FK")
	private User asnUserFK;
	
	@OneToMany(mappedBy = "asnPostFK", fetch = FetchType.LAZY)
	private List<Comment> commentsList = new ArrayList<>();

	@OneToMany(mappedBy = "asnPostFK", fetch = FetchType.LAZY)
	private List<Like> likeList = new ArrayList<>();

	public int getAsnPostId() {
		return asnPostId;
	}

	public void setAsnPostId(int asnPostId) {
		this.asnPostId = asnPostId;
	}

	public String getAsnPostDescription() {
		return asnPostDescription;
	}

	public void setAsnPostDescription(String asnPostDescription) {
		this.asnPostDescription = asnPostDescription;
	}

	public String getAsnPostImageUrl() {
		return asnPostImageUrl;
	}

	public void setAsnPostImageUrl(String asnPostImageUrl) {
		this.asnPostImageUrl = asnPostImageUrl;
	}

	public Timestamp getAsnCreatedAt() {
		return asnCreatedAt;
	}

	public void setAsnCreatedAt(Timestamp asnCreatedAt) {
		this.asnCreatedAt = asnCreatedAt;
	}

	public User getAsnUserFK() {
		return asnUserFK;
	}

	public void setAsnUserFK(User asnUserFK) {
		this.asnUserFK = asnUserFK;
	}

	public List<Comment> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	public List<Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}

	public Post(int asnPostId, String asnPostDescription, String asnPostImageUrl, Timestamp asnCreatedAt,
			User asnUserFK, List<Comment> commentsList, List<Like> likeList) {
		super();
		this.asnPostId = asnPostId;
		this.asnPostDescription = asnPostDescription;
		this.asnPostImageUrl = asnPostImageUrl;
		this.asnCreatedAt = asnCreatedAt;
		this.asnUserFK = asnUserFK;
		this.commentsList = commentsList;
		this.likeList = likeList;
	}

	public Post(String asnPostDescription, String asnPostImageUrl, Timestamp asnCreatedAt, User asnUserFK,
			List<Comment> commentsList, List<Like> likeList) {
		super();
		this.asnPostDescription = asnPostDescription;
		this.asnPostImageUrl = asnPostImageUrl;
		this.asnCreatedAt = asnCreatedAt;
		this.asnUserFK = asnUserFK;
		this.commentsList = commentsList;
		this.likeList = likeList;
	}

	@Override
	public String toString() {
		return "Post [asnPostId=" + asnPostId + ", asnPostDescription=" + asnPostDescription + ", asnPostImageUrl="
				+ asnPostImageUrl + ", asnCreatedAt=" + asnCreatedAt + ", asnUserFK=" + asnUserFK + ", commentsList="
				+ commentsList + ", likeList=" + likeList + "]";
	}

	public Post() {
		super();
	}
	
	

	
}