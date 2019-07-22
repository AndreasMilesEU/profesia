package meambitoprofesia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class App {

    meambitoprofesia.UserDetails userDetails = getUserDetails();

    private JButton button1;
    private JPanel panelMain;
    private JLabel emailLabel;
    private JTextField emailText = new JTextField(userDetails.getEmail());
    private JLabel passwordLabel;
    private JTextField passwordText = new JTextField(userDetails.getPassword());
    private JLabel positionsLabel;
    private JList positionsText;
    private JList positionsList;
    private JLabel scheduler;
    private JComboBox schedulerCombo;
    private JButton addJob;

    public App(JTextField passwordText) throws IOException {
        this.emailText = emailText;
        this.passwordText = passwordText;
        this.positionsText = positionsList;
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Dickhead");
            }
        });
    }

    public App() throws IOException {

    }

    public static void main(String[] args) throws Exception {
        // print to screen first, then cast to form
        printSetUserDetails();
        printGetUserDetails();
        printSetDomainProfesia();
/*
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setSize(350, 300);
        frame.setVisible(true);*/
    }

    public static void printSetUserDetails() throws IOException {
        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // create a post
        meambitoprofesia.UserDetails userDetails = new UserDetails();
        userDetails.setEmail("someUser@domain.com");
        userDetails.setPassword("DemoPassword321");
        userDetails.setName("Jozko");
        userDetails.setSurname("Mrkvicka");
        userDetails.setLastUpdatedAt(new Date());
        userDetails.setHost("host");
        userDetails.setPort(3306);
        userDetails.setDbName("dbName");
        userDetails.setCollName("collection_name");

        // create some predefined tags
        Set< Tag > tags = new HashSet<Tag>();
        Tag java = new Tag((long) 1, "Java");
        Tag python = new Tag((long) 2, "Python");
        Tag javaScript = new Tag((long) 3, "JavaScript");
        Tag testing = new Tag((long) 4, "Testing");
        Tag securityAnalyst = new Tag((long) 5, "Security Analyst");
        tags.add(java);
        tags.add(python);
        tags.add(javaScript);
        tags.add(testing);
        tags.add(securityAnalyst);

        // set tags to post
        userDetails.setTags(tags);

        // create some predefined applications
        Set< Applications > applications = new HashSet< Applications>();
        Applications itDeployment = new Applications(
                "1",
                "https://www.profesia.sk/praca/slovenska-sporitelna/O3720727",
                "IT Infrastructure Deploy", "applied");
        applications.add(itDeployment);

        Applications javaDeveloper = new Applications(
                 "2",
                "https://www.profesia.sk/praca/develogics-k-s/O3720668",
                "JavaDeveloper", "applied");
        applications.add(javaDeveloper);

        // set tags to post
        userDetails.setApplications(applications);

        // Convert object to JSON string
        String postJson = mapper.writeValueAsString(userDetails);
        System.out.println(postJson);

        // Save JSON string to file
        FileOutputStream fileOutputStream = new FileOutputStream("UserDetails.json");
        mapper.writeValue(fileOutputStream, userDetails);
        fileOutputStream.close();


    }

    public static void printSetDomainProfesia() throws IOException {
        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // create domain JSON for Profesia.sk, setting "names" of elements,
        // allowing for creation of consistent 2 x parameter method
        // methodName (WebElement name, String
        meambitoprofesia.Domain profesia = new Domain();
        profesia.setUrl("https://www.profesia.sk/login_person.php");
        profesia.setEmail("login"); // name
        profesia.setPassword("password"); // name
        profesia.setLoginButton("submitbutton[1]"); // name
        profesia.setSearch("offer-search-link"); // id
        profesia.setNextPage("next"); // class

        // Convert object to JSON string
        String postJson = mapper.writeValueAsString(profesia);
        System.out.println(postJson);

        // Save JSON string to file
        FileOutputStream fileOutputStream = new FileOutputStream("Profesia.json");
        mapper.writeValue(fileOutputStream, profesia);
        fileOutputStream.close();

    }

    public static void printGetUserDetails() throws IOException {

        // print post object
        meambitoprofesia.UserDetails userDetails = getUserDetails();
        System.out.println("Printing post details");
        System.out.println(userDetails.getEmail());
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getEmail());
        System.out.println(userDetails.getLastUpdatedAt());

        // print tags for the user
        System.out.println("Printing tag details of post :: " + userDetails.getEmail());
        for (Iterator< Tag > iterator = userDetails.getTags().iterator(); iterator.hasNext();) {
            Tag tag = (Tag) iterator.next();

            System.out.println(tag.getId());
            System.out.println(tag.getName());

        }

        // print applications for the user
        System.out.println("Printing application details for this user :: " + userDetails.getEmail());
        for (Iterator< Applications > iterator = userDetails.getApplications().iterator(); iterator.hasNext();) {
            Applications applications = (Applications) iterator.next();

            System.out.println(applications.getId());
            System.out.println(applications.getLink());

        }
    }

    public static meambitoprofesia.UserDetails getUserDetails() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //Read JSON file and convert to a java object
        InputStream fileInputStream = new FileInputStream("UserDetails.json");
        UserDetails userDetails = mapper.readValue(fileInputStream, UserDetails.class);
        fileInputStream.close();

        return userDetails;
    }

    public static meambitoprofesia.Domain getDomain(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //Read JSON file and convert to a java object
        InputStream fileInputStream = new FileInputStream(file);
        Domain domain = mapper.readValue(fileInputStream, Domain.class);
        fileInputStream.close();

        return domain;
    }

}
