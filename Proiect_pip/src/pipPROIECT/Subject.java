package pipPROIECT;

public interface Subject {
	public void registerObserver(User obs);
	public void removeObserver(User obs);
	public void notifyRegisteredObservers(Post p);
}
