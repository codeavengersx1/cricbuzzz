package com.code.avengers.cricbuzzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.txtRunWicket)
    TextView txtRunWicket;

    @BindView(R.id.txtOvers)
    TextView txtOvers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Attach ButterKnife to this Activity
        * */
        ButterKnife.bind(this);

        /*
        * Read from Database
        * */
        DatabaseReference root = FirebaseDatabase.getInstance()
                .getReference();
        root.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                DataSnapshot runWicketData = dataSnapshot.child("run_wicket");
                String updatedRuns = runWicketData.getValue().toString();
                txtRunWicket.setText(updatedRuns);

                String updatedOvers = dataSnapshot.child("overs").getValue().toString();
                txtOvers.setText(updatedOvers);

                // System.out.println(dataSnapshot.toString());
                Log.d("DataSnapshot", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
