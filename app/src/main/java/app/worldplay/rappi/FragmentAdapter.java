package app.worldplay.rappi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.worldplay.rappi.model.Result;
import app.worldplay.rappi.service.MoviesServices;
import app.worldplay.rappi.service.RetrofitClienInstance;
import retrofit2.Retrofit;


public class FragmentAdapter extends Fragment {

    private int type = 0;
    private String pos = "";
    private RecyclerView rv;
    private SearchView sv;
    private MoviesServices moviesServices;
    private static Retrofit client;
    private RecyclerViewAdapter recyclerViewAdapter;
    public FragmentAdapter() {    }

    public static FragmentAdapter newInstance(int type) {
        FragmentAdapter fragment = new FragmentAdapter();
        Bundle args = new Bundle();
        args.putInt("type", type);
        client = RetrofitClienInstance.getRetrofitInstance();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
            Log.i("esto", ""+type);
         }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_adapter, container, false);
        return view;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sv = view.findViewById(R.id.searchView);
        rv = view.findViewById(R.id.recyclerView);

        switch(type){
            case 0:
                moviesServices.getMoviesPopular(new OnGetMoviesCallback() {
                    @Override
                    public void onSuccess(List<Result> movies) {
                        recyclerViewAdapter = new RecyclerViewAdapter(movies);
                        rv.setAdapter(recyclerViewAdapter);
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });
            case 1:
        }
    }
}
