package revature.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Container class for a Custom Composite Primary Key for Likes made from 
 * asnUserFk referencing a User and asnPostFk referencing a Post
 *
 */
@Embeddable
public class LikeID implements Serializable{	
	
	
	//Must be added to avoid warnings but will happen by default without it
	private static final long serialVersionUID = 1L;

	//Foreign Key for User
	@Column(name = "asn_user_fk")
	private Integer asnUserFk;
	
	//Foreign Key for Post
	@Column(name = "asn_post_fk")
	private Integer asnPostFk;

	//No Aurgs Constructor 
	public LikeID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//All Fields Constructor
	public LikeID(Integer asnUserFk, Integer asnPostFk) {
		super();
		this.asnUserFk = asnUserFk;
		this.asnPostFk = asnPostFk;
	}
	
	
	//GETTERS AND SETTERS
	
	public Integer getAsnUserFk() {
		return asnUserFk;
	}

	public void setAsnUserFk(Integer asnUserFk) {
		this.asnUserFk = asnUserFk;
	}

	public Integer getAsnPostFk() {
		return asnPostFk;
	}

	public void setAsnPostFk(Integer asnPostFk) {
		this.asnPostFk = asnPostFk;
	}

	//OVERRIDDEN METHODS
	
	@Override
	public int hashCode() {
		return Objects.hash(asnPostFk, asnUserFk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeID other = (LikeID) obj;
		return Objects.equals(asnPostFk, other.asnPostFk) && Objects.equals(asnUserFk, other.asnUserFk);
	}

	@Override
	public String toString() {
		return "LikeID [asnUserFk=" + asnUserFk + ", asnPostFk=" + asnPostFk + "]";
	}		
	
}