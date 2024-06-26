package sg.edu.np.mad.madpractical3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import sg.edu.np.mad.madpractical2.R;

public class MainActivity extends AppCompatActivity {

    private Button btnFollow;
    private boolean isFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        User user = new User("John Doe", "MAD Developer", 1, false);

        //Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        btnFollow = findViewById(R.id.btnFollow);

        //Reading the random number
        Intent receivingEnd = getIntent();
        String number = receivingEnd.getStringExtra("randomNumber");

        //Set the TextViews with the User's name, description and default button message
        tvName.setText("MAD" + number);
        tvDescription.setText(user.description);
        btnFollow.setText("Follow");

        //Boolean to check if user is following
        isFollowing = false;

        //OnClick Event handler
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFollowing = !isFollowing;
                if(isFollowing){
                    btnFollow.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    btnFollow.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

        //Event listener for the Message button
        Button msgButton = findViewById(R.id.btnMessage);

        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMsgGroup = new Intent (MainActivity.this, MessageGroup.class);
                startActivity(goToMsgGroup);
            }
        });


    }
}