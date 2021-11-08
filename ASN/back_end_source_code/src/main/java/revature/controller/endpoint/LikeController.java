package revature.controller.endpoint;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import revature.controller.dao.LikeDAOImpl;
import revature.model.ASNHttpResponse;
import revature.model.LikeID;



/**
 * Controller class for Likes. It is responsible for all end-points that directly reference Like(s)
 *
 */
@RestController
@RequestMapping("/like")
@CrossOrigin(origins={"http://localhost:4200","http://127.0.0.1:4200", "http://34.125.179.241:9015", "http://35.236.194.104:8080"}, allowCredentials = "true")
public class LikeController {
	
	//Private Instance of DAO class for Likes
	private LikeDAOImpl likeDAO;

	/**
	 * addLike takes in a new Primary key from Front end and sends it the DAO layer
	 * to be inserted into Like table
	 * @param id New Primary Key value inside of a LikeID Object 
	 * @return Returns a custom HTTPResponse for front end to know the insert was performed
	 */
	@PostMapping(value = "/add")
	@ResponseStatus(value = HttpStatus.CREATED) // status code 201
	public @ResponseBody ASNHttpResponse addLike(@RequestBody LikeID id) {
		likeDAO.insert(id);
		return new ASNHttpResponse(201, "inserted");
	}
	
	/**
	 * delete like takes in an existing Primary key from Front end and sends it the DAO layer
	 * to delete the related Like from the Like table
	 * @param id Existing Primary Key value inside of a LikeID Object 
	 * @return Returns a custom HTTPResponse for front end to know the delete was performed
	 */
	@DeleteMapping(value = "/delete")
	public @ResponseBody ASNHttpResponse delete(@RequestBody LikeID id) {
		likeDAO.delete(id);
		return new ASNHttpResponse(200, "Successful Delete");
	}
	
	/**
	 * getAllLikes returns a list of the Primary key of all existing likes.
	 * Was used for testing during development but has no
	 * practical use case and should have been replaced by
	 * getLikeByUserId(int) or getLikeByPostId(int) 
	 * @return List of LikeID Front-end only requires the info contained in 
	 * 						the LikeID so no need to send entire Object's 
	 * 						info
	 */
	@Deprecated
	@GetMapping(value = "/all")
	public @ResponseBody List<LikeID> getAllLike() {
		return likeDAO.selectAll();
	}

	
	/**
	 * getLikeByUserId returns a list of the Primary Key of all likes by a User
	 * tied to the given ID
	 * @param id The ID of an existing user 
	 * @return List of LikeID Front-end only requires the info contained in 
	 * 						the LikeID so no need to send entire Object's 
	 * 						info
	 */
	@GetMapping(value = "/byuser/{id}")
	public @ResponseBody List<LikeID> getLikeByUserId(@PathVariable("id") int id) {
		return likeDAO.selectByUserId(id);
	}
	
	
	/**
	 * getLikeByPostId returns a list of the Primary Key of all likes by a Post
	 * tied to the given ID
	 * @param id The ID of an existing Post
	 * @return List of LikeID Front-end only requires the info contained in 
	 * 						the LikeID so no need to send entire Object's 
	 * 						info
	 */
	@GetMapping(value = "/bypost/{id}")
	public @ResponseBody List<LikeID> getLikeByPostId(@PathVariable("id") int id) {
		return likeDAO.selectByPostId(id);
	}
	
	
	/**
	 * getLikeCount returns the total number of likes for a given Post ID in a Map object
	 * @param id The ID of an existing Post
	 * @return A map object of String, Integer that will be automatically converted to a 
	 * 		   JSON for ease of use in the Front end 
	 */
	@GetMapping(value = "/count/{id}")
	public @ResponseBody Map<String, Integer> getLikeCount(@PathVariable("id") int id) {
		//Easiest way I found to get a custom built JSON Object sent back to Front end
		Map<String, Integer> temp = new LinkedHashMap<>();
		temp.put("count", likeDAO.getLikeCount(id));
		
		return temp;
	}
	
	
	/**
	 * getHasLiked returns if the currently signed in user has like a given Post ID
	 * @param pid The ID of an existing Post
	 * @param uid The ID of the currently logged in User
	 * @return Returns a Map object of String, Boolean that will be automatically converted to a 
	 * 		   JSON for ease of use in the Front end 
	 */
	@GetMapping(value = "/{uid}/{pid}")
	public @ResponseBody Map<String, Boolean> getHasLiked(@PathVariable("uid") int uid, @PathVariable("pid") int pid) {
		Map<String, Boolean> temp = new LinkedHashMap<>();
		temp.put("result", likeDAO.getHasLiked(uid, pid));
		return temp;
	}

	//GETTER AND SETTER
	
	public LikeDAOImpl getLikeDAO() {
		return likeDAO;
	}

	public void setLikeDAO(LikeDAOImpl likeDAO) {
		this.likeDAO = likeDAO;
	}
	
	
	@Autowired
	public LikeController(LikeDAOImpl likeDAO) {
		super();
		this.likeDAO = likeDAO;
	}
	
	
	// No AURGS Constructor
	
	public LikeController() {
		super();
	}
}

