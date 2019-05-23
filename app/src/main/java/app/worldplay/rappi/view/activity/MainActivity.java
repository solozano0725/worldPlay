package app.worldplay.rappi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.worldplay.rappi.R;
import app.worldplay.rappi.common.Constants;
import app.worldplay.rappi.model.Movies;
import app.worldplay.rappi.model.MoviesList;
import app.worldplay.rappi.network.Repository;
import app.worldplay.rappi.presenter.home.ContractHome;
import app.worldplay.rappi.presenter.home.HomePresenter;
import app.worldplay.rappi.view.adapter.MoviesAdapter;
import app.worldplay.rappi.view.adapter.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;
import es.dmoral.toasty.Toasty;

import static app.worldplay.rappi.view.activity.DetailsView.extra_movie;


public class MainActivity extends AppCompatActivity implements ContractHome.View {

    @BindView(R.id.recyclerView)
    android.support.v7.widget.RecyclerView recyclerView;
    @BindView(R.id.searchView)SearchView searchView;
    @BindView(R.id.container)RelativeLayout view;
    @BindView(R.id.progressView)RelativeLayout progressView;

    private HomePresenter presenter;
    private MoviesList movies;
    private List<Movies> moviesList = new ArrayList<>();
    private BottomNavigationView navView;
    private int pos = 0;
    private MoviesAdapter moviesAdapter;
    private Context context;
    private Repository repository;
    private boolean filter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.presenter = new HomePresenter(this);
        movies = new MoviesList();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }

        this.context = this;
        initVista();
        pullRecyclerView();
    }

    private void initVista(){

        navView = findViewById(R.id.navView);
        navView.setSelectedItemId(R.id.nav_popular);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    filterList(newText);
                    filter = true;
                    getMoviesFilter(newText, context);
                } else {
                    moviesList.clear();
                    moviesAdapter = new MoviesAdapter(moviesList, context);
                    moviesAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(moviesAdapter);
                    filter = false;
                }
                return true;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(!repository.isConnectionOn()){
                Toasty.warning(getApplicationContext(), getString(R.string.check_internet), Toast.LENGTH_SHORT, true).show();
            }
            switch (item.getItemId()) {
                case R.id.nav_popular:
                    moviesList.clear();
                    moviesAdapter.notifyDataSetChanged();
                    pos = 1;
                    getMovies(Constants.popular,1, context);
                    searchView.setEnabled(true);
                    return true;
                case R.id.nav_toprated:
                    moviesList.clear();
                    moviesAdapter.notifyDataSetChanged();
                    pos = 2;
                    getMovies(Constants.topRated,1, context);
                    searchView.setEnabled(true);
                    return true;
                case R.id.nav_upcoming:
                    moviesList.clear();
                    moviesAdapter.notifyDataSetChanged();
                    pos = 3;
                    getMovies(Constants.upComing,1, context);
                    searchView.setEnabled(true);
                    return true;
                case R.id.nav_search:
                    moviesList.clear();
                    moviesAdapter.notifyDataSetChanged();
                    pos = 4;
                    if(repository.isConnectionOn()){
                        searchView.setEnabled(true);
                    }else{
                        searchView.setEnabled(false);
                        Toasty.warning(getApplicationContext(), getString(R.string.use_internet_search), Toast.LENGTH_SHORT, true).show();
                    }
                    return true;
            }
            return false;
        }
    };

    private void pullRecyclerView(){

        repository = new Repository(this);
        moviesAdapter = new MoviesAdapter(moviesList, context);

        if(repository.isConnectionOn()){
            getMovies(Constants.popular,1,context);
        }else{
            getMovies(Constants.popular,1,context);
            getMoviesError(getResources().getString(R.string.check_internet));
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerView(getApplicationContext(), recyclerView, new RecyclerView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movies movie;
                if(filter){
                    movie = moviesList.get(position);
                }else{
                    movie = moviesList.get(position);
                }

                Intent intent = new Intent(context, DetailsView.class);
                intent.putExtra(extra_movie,movie);
                context.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void filterList(String t){
        moviesList.clear();
        for (Movies m: moviesList){
            if(m.getOriginalLanguage().toLowerCase().contains(t.toLowerCase())){
                moviesList.add(m);
            }
        }

        moviesAdapter = new MoviesAdapter(moviesList, context);
        moviesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeLoading() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void getMovies(String type, int page, Context context) {
        presenter.getMovies(type, page, context);
    }

    @Override
    public void getMoviesList(MoviesList movies) {
        switch (pos){
            case 1:
                this.movies = movies;
                break;
            case 2:
                this.movies = movies;
                break;
            case 3:
                this.movies = movies;
                break;
        }
        for(Movies m: movies.movies){
            moviesList.add(m);
        }
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMoviesFilter(String txt, Context context) {
        presenter.getMoviesFilter(txt, context);
    }

    @Override
    public void getMoviesFilterList(MoviesList movies) {
        moviesList.clear();
        moviesList = movies.movies;
        moviesAdapter = new MoviesAdapter(moviesList, context);
        moviesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void getMoviesError(String txt) {
        Snacky.builder()
                .setView(view)
                .setText(txt)
                .setDuration(Snacky.LENGTH_LONG)
                .setActionText(android.R.string.ok)
                .error()
                .show();
    }

}