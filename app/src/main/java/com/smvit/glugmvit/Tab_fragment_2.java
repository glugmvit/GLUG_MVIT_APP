package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 * class for the ideabag section
 * Edited by vibhor on 8/15/2017
 * Finalized by Susmit and Shreejeeth on 17/09/17
 */

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

public class Tab_fragment_2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0_tab_2,container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        final EditText NameField = (EditText) view.findViewById(R.id.name);
        final EditText USNField = (EditText) view.findViewById(R.id.usn);
        final EditText IdeaField = (EditText) view.findViewById(R.id.idea);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = Email(NameField.getText().toString(), USNField.getText().toString(), IdeaField.getText().toString());
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:sreejeeth.ramprasad1996@gmail.com"));   //susmit , add your email id and mine  here.

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Idea !");
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                try {
                    startActivity(emailIntent);
                }catch(ActivityNotFoundException e) {
                    Toast.makeText(getActivity(),"Looks like you do not have any email app installed. Please install one and try again.",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
    private String Email(String name, String USN, String Idea) {

        String priceMessage= "Name: "+name + "\n\n" + "USN: "+USN+ "\n\n" +"Description:\n"+Idea;
        return priceMessage;
    }
}
