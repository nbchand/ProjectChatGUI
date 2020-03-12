import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ProjectChat{
    ProjectChat projectChat;
    JFrame frame1 = new JFrame("Chat Room 1");
    JFrame frame2 = new JFrame("Chat Room 2");
    JButton sendMessage1,sendMessage2,enterServer,room1,room2,newRoom1,newRoom2;
    JTextField messageBox1,messageBox2;
    JTextArea chatBox1,chatBox2;
    JTextField usernameSetter;
    JFrame preFrame;
    JFrame optionFrame = new JFrame("Select Chat Room"),optionFrame2 = new JFrame("Select Chat Room");
    String username;
    JMenuBar menuBar;
    JMenu options;
    JMenuItem clear1,clear2,leave1,leave2,quit1,quit2;
    int press1=0,press2=0,flag=0;

    //for setting username and entering server
    public void preDisplay(){
        frame1.setVisible(false);
        frame2.setVisible(false);
        optionFrame.setVisible(false);
        optionFrame2.setVisible(false);

        preFrame = new JFrame("Set your username!");

        usernameSetter = new JTextField(30);
        JLabel setUsernameLabel = new JLabel("Input a username:");
        setUsernameLabel.setForeground(Color.BLUE);
        setUsernameLabel.setFont(new Font("Serif", Font.BOLD, 25));

        JButton enterServer = new JButton("Enter Chat Server");
        JPanel prePanel = new JPanel(new GridBagLayout());
        prePanel.setBackground(Color.black);

        prePanel.add(setUsernameLabel);
        prePanel.add(usernameSetter);

        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.setVisible(true);
        preFrame.setSize(300,300);

        //did not work
        //enterServer.addActionListener(this);

        //calls actionPerformed() of enterServerButtonListener class
        enterServer.addActionListener(new EnterServerButtonListener());
        preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //for displaying chat rooms for first time
    public void display(){

        optionFrame.setSize(500,500);
        optionFrame.setLayout(null);
        optionFrame.setVisible(true);

        JLabel firstChatName = new JLabel("Enter Chat Room 1");
        firstChatName.setFont(new Font("Serif", Font.BOLD, 25));
        room1 = new JButton("Enter");

        JLabel secondChatName = new JLabel("Enter Chat Room 2");
        secondChatName.setFont(new Font("Serif", Font.BOLD, 25));
        room2 = new JButton("Enter");

        firstChatName.setBounds(100,150,275,20);
        room1.setBounds(100,175,100,40);

        secondChatName.setBounds(100,240,275,20);
        room2.setBounds(100,265,100,40);

        optionFrame.add(firstChatName);
        optionFrame.add(room1);
        optionFrame.add(secondChatName);
        optionFrame.add(room2);

        room1.addActionListener(new EnterChatRoom());
        room2.addActionListener(new EnterChatRoom());
        optionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    //for chat room 1
    public void chatRoom1(){
        press1++;

        frame1.setVisible(true);
        frame1.setSize(470, 300);
        JPanel southPanel = new JPanel();
        frame1.add(BorderLayout.SOUTH, southPanel);
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox1 = new JTextField(50);
        sendMessage1 = new JButton("Send Message");
        chatBox1 = new JTextArea();

        menuBar = new JMenuBar();
        frame1.add(BorderLayout.NORTH, menuBar);

        options = new JMenu("OPTIONS");
        menuBar.add(options);

        clear1 = new JMenuItem("CLEAR");
        leave1 = new JMenuItem("LEAVE");
        quit1 = new JMenuItem("QUIT");

        options.add(clear1);
        options.add(leave1);
        options.add(quit1);

        //for text color
        chatBox1.setForeground(Color.BLACK);

        //for making chatbox(textArea) uneditable as it is only for displaying message
        chatBox1.setEditable(false);

        //for setting text properties
        chatBox1.setFont(new Font("Serif", Font.BOLD, 25));

        //JScrollPane class lets the textArea scrollable vertically
        frame1.add(new JScrollPane(chatBox1), BorderLayout.CENTER);

        //it allows us to read the text in the textarea without horizontal scrolling
        chatBox1.setLineWrap(true);


        southPanel.add(messageBox1);
        southPanel.add(sendMessage1);

        //did not work
        //sendMessage.addActionListener(this);

        //calls actionPerformed() of sendMessageButtonListener class
        sendMessage1.addActionListener(new SendMessageButtonListener());
        leave1.addActionListener(new MenuFunction());
        quit1.addActionListener(new MenuFunction());
        clear1.addActionListener(new MenuFunction());

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //for chat room 2
    public void chatRoom2(){
        press2++;

        frame2.setVisible(true);
        frame2.setSize(470, 300);
        JPanel southPanel = new JPanel();
        frame2.add(BorderLayout.SOUTH, southPanel);
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox2 = new JTextField(50);
        sendMessage2 = new JButton("Send Message");

        menuBar = new JMenuBar();
        frame2.add(BorderLayout.NORTH, menuBar);

        options = new JMenu("OPTIONS");
        menuBar.add(options);

        clear2 = new JMenuItem("CLEAR");
        leave2 = new JMenuItem("LEAVE");
        quit2 = new JMenuItem("QUIT");

        options.add(clear2);
        options.add(leave2);
        options.add(quit2);

        chatBox2 = new JTextArea();
        chatBox2.setEditable(false);
        chatBox2.setFont(new Font("Serif", Font.BOLD, 25));
        chatBox2.setLineWrap(true);

        frame2.add(new JScrollPane(chatBox2), BorderLayout.CENTER);

        southPanel.add(messageBox2);
        southPanel.add(sendMessage2);

        sendMessage2.addActionListener(new SendMessageButtonListener());
        leave2.addActionListener(new MenuFunction());
        quit2.addActionListener(new MenuFunction());
        clear2.addActionListener(new MenuFunction());

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //for sending messages
    class SendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){

            if(event.getSource()==sendMessage1){
                if (messageBox1.getText().length() < 1){
                    // do nothing
                }

                //clears Frame if input is ".clear"
                else if(messageBox1.getText().equals(".clear")){

                    chatBox1.setText("Cleared all messages\n");
                    messageBox1.setText("");

                }

                //for sending message
                else {

                    /*setText() will replace the existing text with new text whereas append() will keep
                    the old text and concatenate it with new text*/

                    chatBox1.append("<" + username + ">:  " + messageBox1.getText() + "\n");
                    messageBox1.setText("");
                }
            }
            else if(event.getSource()==sendMessage2){
                if (messageBox2.getText().length() < 1){
                    // do nothing
                }

                //clears Frame if input is ".clear"
                else if(messageBox2.getText().equals(".clear")){

                    chatBox2.setText("Cleared all messages\n");
                    messageBox2.setText("");

                }

                //for sending message
                else {

                    /*setText() will replace the existing text with new text whereas append() will keep
                    the old text and concatenate it with new text*/

                    chatBox2.append("<" + username + ">:  " + messageBox2.getText() + "\n");
                    messageBox2.setText("");
                }
            }
        }
    }


    //for entering the server after entering the username
    class EnterServerButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            username = usernameSetter.getText();

            if (username.length() < 1){
                System.out.println("No!");
            }

            else {
                preFrame.dispose();
                display();
            }

        }

    }

    //for entering chatroom for first time
    class EnterChatRoom implements ActionListener{

        public void actionPerformed(ActionEvent event){
            optionFrame.setVisible(false);
            if(event.getSource()==room1){
                if (press1==0){
                    chatRoom1();
                }
                else{
                    frame1.setVisible(true);
                }
            }
            else{
                if (press2==0){
                    chatRoom2();
                }
                else{
                    frame2.setVisible(true);
                }
            }

        }
    }

    //for using buttons on the menu (clear and quit)
    class MenuFunction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==quit1||event.getSource()==quit2){
                System.exit(0);
            }
            else if(event.getSource()==leave1||event.getSource()==leave2){
                frame1.setVisible(false);
                frame2.setVisible(false);

                optionFrame.setVisible(true);
            }
            else{
                if(event.getSource()==clear1){
                    chatBox1.setText("Cleared all messages\n");
                }
                else{
                    chatBox2.setText("Cleared all messages\n");
                }

            }
        }
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
        ProjectChat project= new ProjectChat();
        project.preDisplay();
    }

}