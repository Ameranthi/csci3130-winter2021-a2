package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";

    FirebaseDatabase database = null;
    DatabaseReference userNameRef = null;
    DatabaseReference emailRef = null;
    DatabaseReference mDatabase = null;


    @IgnoreExtraProperties
    public class User {

        public String username;
        public String email;

        public User() {
        }

        public User(String username, String email) {
            this.username = getUserName();
            this.email = getEmailAddress();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attaching the event handler
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        //initiating the Firebase
        initializeDatabase();

    }

    protected void initializeDatabase() {
        //initialize your database and related fields here
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://a2-3130-cc88e-default-rtdb.firebaseio.com/");


        //mDatabase = DatabaseReference.getInstance("https://a2-3130-cc88e-default-rtdb.firebaseio.com/");

    }

    protected String getUserName() {
        EditText userName = findViewById(R.id.userName);
        return userName.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailAddress);
        return emailAddress.getText().toString().trim();
    }

    protected boolean isEmptyUserName(String userName) {
        return userName.isEmpty();
    }

    protected boolean isAlphanumericUserName(String userName) {
        if(userName.matches("^[a-zA-Z0-9]+$")){
            return true;
        }
        return false;
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        //your business logic goes here!
        if(emailAddress.contains("@dal.ca")){ //only accept dal student emails
            return true;
        }
        return false;
    }

    protected void switch2WelcomeWindow(String userName, String emailAddress) {
        //your business logic goes here!
        if (isValidEmailAddress(emailAddress) == true && isAlphanumericUserName(userName) == true) {
            Intent switchIntent = new Intent(this, WelcomeActivity.class);
//            switchIntent.putExtra(WELCOME_MESSAGE, );
            startActivity(switchIntent);
        }
    }

    protected void saveUserNameToFirebase(String userName) {
        //save user name to Firebase
        User u = new User(userName, getEmailAddress());
        mDatabase.child("users").child(String.valueOf(System.currentTimeMillis())).setValue(u);

    }

    protected void saveEmailToFirebase(String emailAddress) {
        //save email to Firebase
        //save user name to Firebase
        User u = new User(getUserName(), emailAddress);
        mDatabase.child("users").child(String.valueOf(System.currentTimeMillis())).setValue(u);

    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    @Override
    public void onClick(View view) {

        String userName = getUserName();
        String emailAddress = getEmailAddress();
        String errorMessage = new String();


        if (isEmptyUserName(userName)){
            errorMessage = getResources().getString(R.string.EMPTY_USER_NAME);
        }

        if(!(isAlphanumericUserName(userName))) {
            errorMessage = getResources().getString(R.string.NON_ALPHA_NUMERIC_USER_NAME);
        }
        if (!(isValidEmailAddress(emailAddress))){
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS);
        }

        //check for valid user name and valid email email address

        if (errorMessage.isEmpty()) {
            //no errors were found!
            //much of the business logic goes here!

            if(isAlphanumericUserName(userName)){
                setStatusMessage(getResources().getString(R.string.EMPTY_STRING));

            }
            if(isValidEmailAddress(emailAddress)){
                setStatusMessage(getResources().getString((R.string.EMPTY_STRING)));
            }

        } else {
            setStatusMessage(errorMessage);
        }
        saveEmailToFirebase(emailAddress);
        saveUserNameToFirebase(userName);
        //switch2WelcomeWindow(userName, emailAddress);//COMMENT THIS OUT TO RUN THE REST OF THE TESTs because the set status message function doesnt carry over to the next screen.


    }
}