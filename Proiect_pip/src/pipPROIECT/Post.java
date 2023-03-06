package pipPROIECT;

// Post class implemented to add new posts types (ex: photo with image and caption)

public class Post {
	int post_type;
	
	Post(int p)
	{
		this.post_type=p;
	}
	
	void printPost() {};
	
}

class Comment extends Post
{
	String comment = new String();
	
	Comment(String comm)
	{	
		super(1);
		this.comment=comm;	
	}
	
	void printPost()
	{
		System.out.println("New post! Subject commented: ");
		System.out.println('"' + this.comment + '"');
	}
	
}