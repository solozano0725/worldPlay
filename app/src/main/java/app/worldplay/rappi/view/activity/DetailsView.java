package app.worldplay.rappi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import app.worldplay.rappi.R;
import app.worldplay.rappi.common.Constants;
import app.worldplay.rappi.model.Movies;
import app.worldplay.rappi.model.MoviesResponse;
import app.worldplay.rappi.network.Repository;
import app.worldplay.rappi.presenter.detalle.ContractMovie;
import app.worldplay.rappi.presenter.detalle.MoviePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;

public class DetailsView extends AppCompatActivity implements ContractMovie.ViewMovie {

    public static final String extra_movie = "extra_movie";
    private Movies movies;
    private String idvideo="";
    private Repository repository;

    @BindView(R.id.img_movie_back) ImageView banner;
    @BindView(R.id.img_movie_poster) ImageView poster;
    @BindView(R.id.fab_star)FloatingActionButton fab_star;

    @BindView(R.id.containerMovie) LinearLayout view;
    @BindView(R.id.movie_top) CollapsingToolbarLayout movie_top;

    @BindView(R.id.txt_movie_title) TextView title;
    @BindView(R.id.txt_movie_rated) TextView rated;
    @BindView(R.id.txt_movie_votes) TextView votes;
    @BindView(R.id.txt_movie_date) TextView date;

    @BindView(R.id.txt_movie_overview)TextView overview;
    @BindView(R.id.txt_movie_genres)TextView genres;
    @BindView(R.id.txt_movie_languages)TextView languages;

    private MoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        repository = new Repository();
        displayHomeAsUpEnabled();
        presenter = new MoviePresenter(this);
        getExtrasFromIntent();
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        movies = (Movies) getIntent().getSerializableExtra(extra_movie);
        getMovieInfo(movies.id+"", this);
        title.setText(movies.title);
        votes.setText(String.valueOf(movies.voteCount));
        date.setText(movies.releaseDate);
        rated.setText(movies.voteAverage+"/10");
        overview.setText(movies.overview);
        genres.setText(movies.genreIds.toString());
        languages.setText(movies.overview);
        Picasso.with(getApplicationContext()).load(Constants.imageUrl+movies.backdropPath).into(banner);
        Picasso.with(getApplicationContext()).load(Constants.imageUrl+movies.posterPath).into(poster);
    }

    @Override
    public void getMovieInfo(String id, Context context) {
        presenter.getMovieInfo(id,context);
    }

    @Override
    public void setInfoMovieWarning(String txt) {
        Snacky.builder()
                .setView(view)
                .setText(txt)
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText(android.R.string.ok)
                .warning()
                .show();
    }

    @Override
    public void setInfoMovieError(String txt) {
        Snacky.builder()
                .setView(view)
                .setText(txt)
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText(android.R.string.ok)
                .error()
                .show();
    }

    @Override
    public void setInfoMovie(MoviesResponse info) {
        if(info.getVideos().getResults().size()==0){
            setInfoMovieWarning(getString(R.string.no_video));
        }else{
            idvideo = info.getVideos().getResults().get(0).getKey();
        }
    }

    public void showTrailer(View view){
        if(idvideo.isEmpty()){
            setInfoMovieWarning(getString(R.string.consult_video));
        }else{
            Intent i = new Intent(this, Video.class);
            i.putExtra("video", idvideo);
            startActivity(i);
        }
    }
}