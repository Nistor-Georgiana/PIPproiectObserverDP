package pipPROIECT;

import java.util.ArrayList;
import java.util.List;

public class SubjectAccount implements Subject {

	private List<User> followers = new ArrayList<User>();
	List<Post> posts = new ArrayList<Post>();
	
	/**
	 * creates new post and adds it to the post list
	 * @param comm  new post content
	 */
	public void createNewPost(String comm)
	{	
		Post newpost = new Comment(comm);
		this.posts.add(newpost);
		this.notifyRegisteredObservers(newpost);
	}
	
	@Override
	public void registerObserver(User obs) {
		
		this.followers.add(obs);
	}

	@Override
	public void removeObserver(User obs) {
		followers.remove(obs);
		
	}

	// updates all followers when a new post is created
	@Override
	public void notifyRegisteredObservers(Post post) {
		for(Observer us:this.followers)
			us.update(post);
	}
	
	public List<User> getFollowers()
	{
		return this.followers;
	}

}
