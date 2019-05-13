package app.worldplay.rappi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentAdapter extends Fragment {

    private int type = 0;
    private String pos = "";
    public FragmentAdapter() {
        // Required empty public constructor
    }

    public static FragmentAdapter newInstance(int type, String pos) {
        FragmentAdapter fragment = new FragmentAdapter();
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("pos", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
            pos = getArguments().getString("pos");
            Log.i("esto", type+pos);
         }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_adapter, container, false);

        return view;
}
}
