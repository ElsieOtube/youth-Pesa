package com.gichure.youthpesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostActivity extends AppCompatActivity {

    private EditText company, position, jobRequirements, duration, link, salary;
    private Button postBtn;


    private StorageReference mStorageRef;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

    private static final int GALLERY_REQUEST_CODE = 2;
    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);

        postBtn = findViewById(R.id.postBtn);
        company = findViewById(R.id.company);
        position = findViewById(R.id.position);
        jobRequirements = findViewById(R.id.jobRequirements);
        duration = findViewById(R.id.Duration);
        link = findViewById(R.id.link);
        salary = findViewById(R.id.salary);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        databaseRef = FirebaseDatabase.getInstance().getReference().child("JobRequests");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();



        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(PostActivity.this, "POSTING...", Toast.LENGTH_LONG).show();
                final String comp = company.getText().toString().trim();
                final String posi = position.getText().toString().trim();
                final String jobRe = jobRequirements.getText().toString().trim();
                final String dura = duration.getText().toString().trim();
                final String lin = link.getText().toString().trim();
                final String sal = salary.getText().toString().trim();

                java.util.Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate=  new SimpleDateFormat("dd-MM-yyyy");
                final String saveCurrentDate=currentDate.format(calendar.getTime());

                java.util.Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat currentTime=  new SimpleDateFormat("HH:mm");
                final String  saveCurrentTime=currentTime.format(calendar1.getTime());

                if(!TextUtils.isEmpty(comp) && !TextUtils.isEmpty(jobRe) && !TextUtils.isEmpty(dura)&& !TextUtils.isEmpty(lin) && !TextUtils.isEmpty(sal) && !TextUtils.isEmpty(posi )){

                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference = databaseRef.child(user_id);
                    databaseReference.child("Username").setValue(comp);

                }

            }
        });
    }


}