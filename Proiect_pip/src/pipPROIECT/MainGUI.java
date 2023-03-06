package pipPROIECT;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


public class MainGUI {

	private JFrame frame;
	
	List<User> usersList = new ArrayList<User>(); 
	SubjectAccount subAcc = new SubjectAccount(); // Subject
	User current_user = null;
	String current_username = null;
	Integer nr_users=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 448, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// user tab for adding new users
		JPanel user = new JPanel(); 
		user.setLayout(null);

		// tab with informations about Subject 
		//(create & send new post, posts number, followers number)
		JPanel subject =new JPanel(); 
		subject.setLayout(null);
		
		JTabbedPane tp=new JTabbedPane();
		tp.setBounds(10,11,414,544);
		tp.add("User",user);
		tp.setDisabledIconAt(0, null);
		tp.add("Subject",subject);
 
		JTextField newUserField = new JTextField(); 
		newUserField.setBounds(21, 39, 205, 20);
		user.add(newUserField);
		newUserField.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Posts");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(131, 27, 73, 23);
		subject.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Followers");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(248, 27, 73, 23);
		subject.add(lblNewLabel_1);
		
		// label gets udated when another post is created
		JLabel nr_posts = new JLabel("");
		nr_posts.setBounds(129, 68, 46, 14);
		subject.add(nr_posts);
		
		// label gets udated when Subject gets followed/unfollowed
		JLabel nr_followers = new JLabel("");
		nr_followers.setBounds(247, 68, 46, 14);
		subject.add(nr_followers);
		
		// label for username warnings (already exists, is invalid)
		JLabel warningmsgname = new JLabel("");
		warningmsgname.setBounds(21, 11, 205, 14);
		user.add(warningmsgname);
		
		DefaultListModel<String> userlist = new DefaultListModel<>();
		
		// ----- Enter button ----- // adds new user to the user list

		JButton enter_button = new JButton("Enter new user");

		enter_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ??? nu functioneaza sa caute in lista de followeri
				
				// checks if a user with the same username already exists
				if(userlist.contains(newUserField.getText().toString())) 
				{
					warningmsgname.setText("!! username already exists");
				}
				else
				{
					// checks if the usename introduced is null
					if(newUserField.getText().toString().length()==0) 
					{
						warningmsgname.setText("!! username invalid");
					}
					else	// adds new user to the user list
					{
						User newuser = new User(newUserField.getText().toString());
						usersList.add(newuser);
						userlist.addElement(newuser.getUsername());
						newUserField.setText("");
						warningmsgname.setText("");					
					}		
				}
			}
		});
		enter_button.setBounds(236, 38, 123, 23);
		user.add(enter_button);
		
		JList<String> guilist = new JList<>(userlist);
		guilist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guilist.setLayoutOrientation(JList.VERTICAL);
		guilist.setBounds(21, 70, 205, 361);
		
		//user.add(scrollPane);
		
		// label that displays the username status when pressing follow/unfollow
		JLabel followwarning = new JLabel("");
		followwarning.setBounds(193, 127, 138, 14);
		subject.add(followwarning);
		
		// displays the selected user
		JLabel currentuserL = new JLabel("current user");
		currentuserL.setBounds(43, 476, 320, 29);
		subject.add(currentuserL);
		
		// selects a user from the user list
		guilist.addListSelectionListener(new ListSelectionListener() {
			  public void valueChanged(ListSelectionEvent evt) {
			    if (!evt.getValueIsAdjusting()) {
			    	current_username = guilist.getSelectedValue();
			    	currentuserL.setText("current user:  @" + current_username);
			    	followwarning.setText("");
			    }
			  }
		});
		user.add(guilist);

		// ----- Follow button ----- // adds the selected user to the follower list of the Subject
		JButton follow_button = new JButton("Follow");

		follow_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				current_user= new User(current_username);
				
				if(current_username==null)
				{
					followwarning.setText("! no user selected");
				}
				else
				{
					// checking if the selected user is in the follower list already
					int ok = 0;
					for(User u:subAcc.getFollowers())
					{
						if(u.getUsername().equals(current_username))
						{
							ok=1;
							break;
						}
					}
					
					if(ok==1)   //(!subAcc.getFollowers().contains(new User(current_username))) ??
					{
						followwarning.setText("already following!");
					}
					else
					{
						// adding the selected user to the follower list
						current_user.follow(subAcc);
						Integer i =subAcc.getFollowers().size(); 
						nr_followers.setText(i.toString()); // update followers number
				    	followwarning.setText("");
					}
				}
				}
		});
		subject.setLayout(null);
		follow_button.setBounds(131, 93, 73, 23);
		subject.add(follow_button);
		
		
		// ----- Unfollow button ----- // removes selected user from the follower list of the Subject
		
		JButton unfollow_button = new JButton("Unfollow");
		unfollow_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(current_username==null)
				{
					followwarning.setText("! no user selected");
				}
				else
				{
					// checking if the selected user is in the follower list if not display warning
					String nr_flwrs = nr_followers.getText();
					for(User u:subAcc.getFollowers())
					{
						if(u.getUsername().equals(current_username))
						{
							u.unfollow(subAcc); // fix this
							Integer i =subAcc.getFollowers().size(); // update followers number
							nr_followers.setText(i.toString());
							break;
						}
					}
					
					if(nr_followers.getText().toString().equals(nr_flwrs))
						followwarning.setText("user not following sub");
				}			
			}
		});
		unfollow_button.setBounds(248, 93, 83, 23);
		subject.add(unfollow_button);
		
		JLabel lblNewLabel_3 = new JLabel("Account");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(10, 33, 97, 84);
		subject.add(lblNewLabel_3);
		
		// field where the Subject types the posts
		JTextField postField = new JTextField();
		postField.setBounds(63, 176, 230, 63);
		subject.add(postField);
		postField.setColumns(10);
		
		// warning label for post content
		JLabel postfieldwarning = new JLabel("");
		postfieldwarning.setBounds(86, 250, 187, 14);
		subject.add(postfieldwarning);
		
		// ----- Send button ----- // check the post field content, creates & adds a new post to the post list
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//checks if the post content is null
				if(postField.getText().toString().equals("")) 
				{
					postfieldwarning.setText("type something !");
				}
				else
				{
					// creates & adds a new post to the post list
					String comm = postField.getText().toString();
					subAcc.createNewPost(comm);
					postField.setText("");
					Integer i =subAcc.posts.size(); // update posts number
					nr_posts.setText(i.toString());
					postfieldwarning.setText("");
				}		
			}
		});
		btnNewButton.setBounds(297, 196, 89, 23);
		subject.add(btnNewButton);		
		
		JLabel lblNewLabel_2 = new JLabel("create post:");
		lblNewLabel_2.setBounds(63, 151, 97, 14);
		subject.add(lblNewLabel_2);
		frame.getContentPane().add(tp);
	}
}
