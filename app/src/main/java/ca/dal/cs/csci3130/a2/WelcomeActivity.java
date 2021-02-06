package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    static String extractedUserName=new String();
    static String extractedEmailAddress=new String();
    FirebaseDatabase database = null;
    DatabaseReference userNameRef;
    DatabaseReference emailRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //attaching the event handler to retrieve button
        Button retrieveButton = findViewById(R.id.accessDBButton);
        retrieveButton.setOnClickListener(this);

        //initializing the database instance and others!
        this.initializeDatabase();

//        Intent myIntent = getIntent();
//        setStatusMessage(myIntent.getExtras(MainActivity.WELCOME_MESSAGE));
    }


    protected void initializeDatabase() {
        //code for database initialization and accessing the credentials
    }


    @Override
    public void onClick(View view) {
        String message=extractedUserName+"\t"+extractedEmailAddress;
        //show the message with Toast or AlertDialog
    }
}