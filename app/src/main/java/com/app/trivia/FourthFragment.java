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
import android.widget.TextView;

import com.app.trivia.model.Model;


public class FourthFragment extends Fragment implements View.OnClickListener{

    private Model record;
    private Button finished, history;

    private TextView name, cricketer, colors;
    private String strName, strCric, strColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            strName = bundle.getString("Name");
            strCric = bundle.getString("Cricketer");
            strColor = bundle.getString("Colors");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        finished = view.findViewById(R.id.btn_finish);
        history = view.findViewById(R.id.btn_history);
        name = view.findViewById(R.id.name);
        cricketer = view.findViewById(R.id.cricket_answer);
        colors = view.findViewById(R.id.color_answer);

        name.setText(strName);
        cricketer.setText(strCric);
        colors.setText(strColor);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_finish)
        {
            NavHostFragment.findNavController(FourthFragment.this)
                    .navigate(R.id.action_FourthFragment_to_FirstFragment);
        }

        if(v.getId() == R.id.btn_history)
        {

        }
    }
}
