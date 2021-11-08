package revature.controller.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import revature.controller.dao.CommentDAOImpl;
import revature.controller.dao.PostDAOImpl;
import revature.model.ASNHttpResponse;
import revature.model.Comment;
import revature.model.Post;


@RestController
@RequestMapping("/post")
@CrossOrigin(origins={"http://localhost:4200", "http://127.0.0.1:4200", "http://34.125.179.241:9015", "http://35.236.194.104:8080"}, allowCredentials = "true")

public class PostController {
	
	private PostDAOImpl postDAO;
	private CommentDAOImpl commentDAO;
	
	@PostMapping(value = "/add")
	@ResponseStatus(value = HttpStatus.CREATED) // status code 201
	public ASNHttpResponse addPost(@RequestBody Post newPost) {
		postDAO.insert(newPost);
		return new ASNHttpResponse(201, "inserted");
	}
	
	@PostMapping(value = "/comment")
	@ResponseStatus(value = HttpStatus.CREATED) // status code 201
	public ASNHttpResponse addComment(@RequestBody Comment newComment) {
		commentDAO.insertComment(newComment);
		return new ASNHttpResponse(201, "inserted");
	}
	
	@GetMapping(value = "/comment/{id}")
	public @ResponseBody List<Comment> getCommentByPostId(@PathVariable("id") int id) {
		return commentDAO.selectByPostId(id);
	}
	
	@GetMapping(value = "/all")
	public @ResponseBody List<Post> getAllPost() {
		return postDAO.selectAll();
	}
	
	@GetMapping(value = "/user/{id}")
	public @ResponseBody List<Post> getPostByUserId(@PathVariable("id") int id) {
		return postDAO.selectByUserId(id);
	}

	public PostDAOImpl getPostDAO() {
		return postDAO;
	}

	public void setPostDAO(PostDAOImpl postDAO) {
		this.postDAO = postDAO;
	}

	@Autowired
	public PostController(PostDAOImpl postDAO, CommentDAOImpl commentDAO) {
		super();
		this.postDAO = postDAO;
		this.commentDAO = commentDAO;
	}

	public PostController() {
		super();
	}	

}