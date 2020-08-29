package com.app.trivia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.trivia.model.Model;

public class SecondFragment extends Fragment {

    private Model record = new Model();
    private RadioGroup cricketers;
    private Button next;
    private String name;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Bundle bundle = getArguments();
        if(bundle != null) {
            name = bundle.getString("Name");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cricketers = view.findViewById(R.id.cricketers_radio_group);
        next = view.findViewById(R.id.button_second);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ThirdFragment fragment = new ThirdFragment();
                record.setCricketer(getName(cricketers.getCheckedRadioButtonId()));
                Bundle args = new Bundle();
                args.putString("Name", name);
                args.putString("Cricketer", getName(cricketers.getCheckedRadioButtonId()));
                fragment.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }

    private String getName(int checkedId)
    {
        String retValue;
        switch (checkedId)
        {
            case(R.id.sachin) :
                retValue = getString(R.string.sachin);
                break;

            case(R.id.virat) :
                retValue =  getString(R.string.kohli);
                break;

            case(R.id.adam) :
                retValue =  getString(R.string.adam);
                break;
            case(R.id.kallis) :
                retValue =  getString(R.string.kallis);
                break;
            default:
                retValue = "none";
        }
        return retValue;
    }
}
