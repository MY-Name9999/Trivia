package com.app.trivia;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.app.trivia.database.Connector;
import com.app.trivia.model.Model;

import java.util.ArrayList;
import java.util.List;


public class ThirdFragment extends Fragment{

    private Button next;
    private CheckBox yellow, white, orange, green;
    private List<String> colors = new ArrayList<>();
    private Model record = new Model();
    private String name, cricketer;

    private Connector db = new Connector(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null) {
            name = bundle.getString("Name");
            cricketer = bundle.getString("Cricketer");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        next = view.findViewById(R.id.button_third);
        yellow = view.findViewById(R.id.yellow);
        white = view.findViewById(R.id.white);
        orange = view.findViewById(R.id.orange);
        green = view.findViewById(R.id.green);

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                FourthFragment fragment = new FourthFragment();
                record.setName(name);
                record.setCricketer(cricketer);
                record.setColors(getChecked());
                Bundle args = new Bundle();
                args.putString("Name", name);
                args.putString("Cricketer", cricketer);
                args.putString("Colors", getChecked());
                fragment.setArguments(args);
                //db.addRecord(record); //Persisting the data


                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getChecked()
    {
        if(yellow.isChecked()){ colors.add(yellow.getTag().toString()); }
        if(white.isChecked()){ colors.add(white.getTag().toString()); }
        if(orange.isChecked()){ colors.add(orange.getTag().toString()); }
        if(green.isChecked()){ colors.add(green.getTag().toString()); }

        return String.join(",", colors);
    }
}
