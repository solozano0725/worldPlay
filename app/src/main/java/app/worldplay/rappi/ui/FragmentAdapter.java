package app.worldplay.rappi.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.worldplay.rappi.R;

public class FragmentAdapter extends Fragment {

    private String type = "", filter = "";


    public FragmentAdapter() {
        // Required empty public constructor
    }

    public static FragmentAdapter newInstance(String type, String filter) {
        FragmentAdapter fragment = new FragmentAdapter();
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("filter", filter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(type);
            filter = getArguments().getString(filter);
            Log.i("esto", ""+type);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adapter, container, false);
        return view;
    }

}
