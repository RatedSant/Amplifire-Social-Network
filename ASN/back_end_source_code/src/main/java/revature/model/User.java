package revature.model;


import java.sql.Timestamp;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.hash.Hashing;

@JsonIgnoreProperties({"postList", "likeList", "commentList"})
@Entity
@Table(name = "asn_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asn_user_id")
	private int asnUserId;

	@Column(name = "asn_user", unique = true, nullable = false)
	private String asnUser;

	@Column(name = "asn_user_email", nullable = false)
	private String asnUserEmail;

	@Column(name = "asn_user_firstname")
	private String asnUserFirstname;

	@Column(name = "asn_user_lastname")
	private String asnUserLastname;

	@Column(name = "asn_user_profile_image_url")
	private String asnUserProfileImageUrl;

	@Column(name = "asn_user_password", nullable = false)
	private String asnUserPassword;
	
	@Column(name = "asn_profile_descript")
	private String asnProfileDescript;
	
	@Column(name = "asn_profile_hobby")
	private String asnProfileHobby;
	
	@Column(name = "asn_profile_sport")
	private String asnProfileSport;
	
	@Column(name = "asn_profile_food")
	private String asnProfileFood;
	
	@Column(name = "asn_created_at")
	@CreationTimestamp
	private Timestamp asnCreatedAt;

	@OneToMany(mappedBy = "asnUserFK", fetch = FetchType.LAZY)
	private List<Post> postList = new ArrayList<>();
	
	@OneToMany(mappedBy = "asnUserFK", fetch = FetchType.LAZY)
	private List<Like> likeList = new ArrayList<>();
	
	@OneToMany(mappedBy = "asnPostFK", fetch = FetchType.LAZY)
	private List<Comment> commentList = new ArrayList<>();

	
	public Timestamp getAsnCreatedAt() {
		return asnCreatedAt;
	}

	public void setAsnCreatedAt(Timestamp asnCreatedAt) {
		this.asnCreatedAt = asnCreatedAt;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public int getAsnUserId() {
		return asnUserId;
	}

	public void setAsnUserId(int asnUserId) {
		this.asnUserId = asnUserId;
	}

	public String getAsnUser() {
		return asnUser;
	}

	public void setAsnUser(String asnUser) {
		this.asnUser = asnUser;
	}

	public String getAsnUserEmail() {
		return asnUserEmail;
	}
	
	public String getAsnProfileDescript() {
		return asnProfileDescript;
	}

	public void setAsnProfileDescript(String asnProfileDescript) {
		this.asnProfileDescript = asnProfileDescript;
	}

	public void setAsnUserEmail(String asnUserEmail) {
		this.asnUserEmail = asnUserEmail;
	}

	public String getAsnUserFirstname() {
		return asnUserFirstname;
	}

	public void setAsnUserFirstname(String asnUserFirstname) {
		this.asnUserFirstname = asnUserFirstname;
	}

	public String getAsnUserLastname() {
		return asnUserLastname;
	}

	public void setAsnUserLastname(String asnUserLastname) {
		this.asnUserLastname = asnUserLastname;
	}

	public String getAsnUserProfileImageUrl() {
		return asnUserProfileImageUrl;
	}

	public String getAsnProfileHobby() {
		return asnProfileHobby;
	}

	public void setAsnProfileHobby(String asnProfileHobby) {
		this.asnProfileHobby = asnProfileHobby;
	}

	public String getAsnProfileSport() {
		return asnProfileSport;
	}

	public void setAsnProfileSport(String asnProfileSport) {
		this.asnProfileSport = asnProfileSport;
	}

	public String getAsnProfileFood() {
		return asnProfileFood;
	}

	public void setAsnProfileFood(String asnProfileFood) {
		this.asnProfileFood = asnProfileFood;
	}

	public void setAsnUserProfileImageUrl(String asnUserProfileImageUrl) {
		this.asnUserProfileImageUrl = asnUserProfileImageUrl;
	}

	public String getAsnUserPassword() {
		return asnUserPassword;
	}

	public void setAsnUserPassword(String asnUserPassword) {
		this.asnUserPassword = asnUserPassword;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public List<Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;

	}	

	

	public void hash() {
		this.asnUserPassword = Hashing.sha256().hashString(this.asnUserPassword, StandardCharsets.UTF_8).toString();
	}
	
    public String getRandomString()
    {
    	int n = 15;
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index= (int)(AlphaNumericString.length()* Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
  
        return sb.toString();
    }

	
	public User() {
		super();
	}
	
	
	

	public User(int asnUserId, String asnUser, String asnUserEmail, String asnUserFirstname, String asnUserLastname,
			String asnUserProfileImageUrl, String asnUserPassword, String asnProfileDescript, String asnProfileHobby,
			String asnProfileSport, String asnProfileFood, Timestamp asnCreatedAt, List<Post> postList,
			List<Like> likeList, List<Comment> commentList) {
		super();
		this.asnUserId = asnUserId;
		this.asnUser = asnUser;
		this.asnUserEmail = asnUserEmail;
		this.asnUserFirstname = asnUserFirstname;
		this.asnUserLastname = asnUserLastname;
		this.asnUserProfileImageUrl = asnUserProfileImageUrl;
		this.asnUserPassword = asnUserPassword;
		this.asnProfileDescript = asnProfileDescript;
		this.asnProfileHobby = asnProfileHobby;
		this.asnProfileSport = asnProfileSport;
		this.asnProfileFood = asnProfileFood;
		this.asnCreatedAt = asnCreatedAt;
		this.postList = postList;
		this.likeList = likeList;
		this.commentList = commentList;
	}

	public User(String asnUser, String asnUserEmail, String asnUserFirstname, String asnUserLastname,
			String asnUserProfileImageUrl, String asnUserPassword, String asnProfileDescript, String asnProfileHobby,
			String asnProfileSport, String asnProfileFood, Timestamp asnCreatedAt, List<Post> postList,
			List<Like> likeList, List<Comment> commentList) {
		super();
		this.asnUser = asnUser;
		this.asnUserEmail = asnUserEmail;
		this.asnUserFirstname = asnUserFirstname;
		this.asnUserLastname = asnUserLastname;
		this.asnUserProfileImageUrl = asnUserProfileImageUrl;
		this.asnUserPassword = asnUserPassword;
		this.asnProfileDescript = asnProfileDescript;
		this.asnProfileHobby = asnProfileHobby;
		this.asnProfileSport = asnProfileSport;
		this.asnProfileFood = asnProfileFood;
		this.asnCreatedAt = asnCreatedAt;
		this.postList = postList;
		this.likeList = likeList;
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "User [asnUserId=" + asnUserId + ", asnUser=" + asnUser + ", asnUserEmail=" + asnUserEmail
				+ ", asnUserFirstname=" + asnUserFirstname + ", asnUserLastname=" + asnUserLastname
				+ ", asnUserProfileImageUrl=" + asnUserProfileImageUrl + ", asnUserPassword=" + asnUserPassword
				+ ", asnProfileDescript=" + asnProfileDescript + ", asnProfileHobby=" + asnProfileHobby
				+ ", asnProfileSport=" + asnProfileSport + ", asnProfileFood=" + asnProfileFood + ", asnCreatedAt="
				+ asnCreatedAt + ", postList=" + postList + ", likeList=" + likeList + ", commentList=" + commentList
				+ "]";
	}

	


}