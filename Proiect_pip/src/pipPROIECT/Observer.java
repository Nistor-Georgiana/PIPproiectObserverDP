package pipPROIECT;

public interface Observer {
	public void update(Post post);
	public void follow(Subject s);
	public void unfollow(Subject s);
}
