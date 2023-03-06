package pipPROIECT;

import java.util.Random;

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
	
	class Photo extends Post
	{
		int n, m;
		int[][] img;	
		
		Photo(int a, int b) {
			super(2);
			this.m=a;
			this.n=b;
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					img[i][j]=new Random().nextInt(255);
		}
	}
	
}