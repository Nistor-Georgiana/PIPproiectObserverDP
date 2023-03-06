package pipPROIECT;

public class User implements Observer {

	private String username;
	
	User(String name)
	{
		this.username = name;
	}
	
	// updates followers when there is a new post (called in SubjectAcount.notifyRegisteredObservers)
	@Override
	public void update(Post post) {
		System.out.println( this.getUsername() + ", you have a new notification!" );
		post.printPost();
	}
	
	public String getUsername()
	{
		return this.username;
	}

	@Override
	public void follow(Subject s) {
		s.registerObserver(this);		
	}

	@Override
	public void unfollow(Subject s) {
		s.removeObserver(this);
	}

}
