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

import com.alespero.expandablecardview.ExpandableCardView;
import com.squareup.picasso.Picasso;

import app.worldplay.rappi.R;
import app.worldplay.rappi.common.Constants;
import app.worldplay.rappi.model.Movies;
import app.worldplay.rappi.model.MoviesResponse;
import app.worldplay.rappi.network.Repository;
import app.worldplay.rappi.presenter.details.ContractMovie;
import app.worldplay.rappi.presenter.details.MoviePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;

public class DetailsView extends AppCompatActivity implements ContractMovie.ViewMovie {

    public static final String extra_movie = "extra_movie";
    private Movies movies;
    private String idvideo="";
    private MoviesResponse moviesR;
    private Repository repository;
    private TextView txtCardview;
    private ImageView imgCardview;
    @BindView(R.id.img_movie_back) ImageView banner;
    @BindView(R.id.img_movie_poster) ImageView poster;
    @BindView(R.id.fab_star)FloatingActionButton fab_star;

    @BindView(R.id.containerMovie) LinearLayout view;
    @BindView(R.id.movie_top) CollapsingToolbarLayout movie_top;

    @BindView(R.id.txt_movie_title) TextView title;
    @BindView(R.id.txt_movie_rated) TextView rated;
    @BindView(R.id.txt_movie_votes) TextView votes;
    @BindView(R.id.txt_movie_date) TextView date;

    @BindView(R.id.cardview_overview) ExpandableCardView cardViewOverViews;
    @BindView(R.id.cardview_genres) ExpandableCardView cardViewGenres;
    @BindView(R.id.cardview_languages) ExpandableCardView cardViewLanguages;

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
        cardViewOverViews.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                txtCardview = v.findViewById(R.id.txt_cardview);
                imgCardview = v.findViewById(R.id.img_cardview);
                txtCardview.setText(getOverview(moviesR));
                imgCardview.setImageDrawable(getResources().getDrawable(R.drawable.ic_overview));
            }
        });
        cardViewGenres.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                txtCardview = v.findViewById(R.id.txt_cardview);
                imgCardview = v.findViewById(R.id.img_cardview);
                txtCardview.setText(getGenres(moviesR));
                imgCardview.setImageDrawable(getResources().getDrawable(R.drawable.ic_genres));
            }
        });
        cardViewLanguages.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                txtCardview = v.findViewById(R.id.txt_cardview);
                imgCardview = v.findViewById(R.id.img_cardview);
                txtCardview.setText(getLanguages(moviesR));
                imgCardview.setImageDrawable(getResources().getDrawable(R.drawable.ic_languages));
            }
        });
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
            moviesR = info;
        }else{
            idvideo = info.getVideos().getResults().get(0).getKey();
            moviesR = info;
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

    public String getOverview(MoviesResponse info){
        String r = "";
        if(info.getOverview().isEmpty() || info!=null) {
            r = info.getOverview();
        } else {
            r = getResources().getString(R.string.error_msg_unknown);
        }

        return r;
    }

    public String getGenres(MoviesResponse info){
        String r = "";
        if(info.getGenres().size()!=0 || info!=null){
        for(int i = 0; i<info.getGenres().size(); i++){
            if(i==info.getGenres().size()-1){
                r = info.getGenres().get(i).name +".";
            } else{
                r = info.getGenres().get(i).name +", ";
            }
        }
        } else {
            r = getResources().getString(R.string.error_msg_unknown);
        }
        return r;
    }

    public String getLanguages(MoviesResponse info){
        String r = "";
        if(info.getGenres().size()!=0 || info!=null){
        for(int i = 0; i<info.getSpokenLanguages().size(); i++){
            if(i==info.getSpokenLanguages().size()-1){
                r = info.getSpokenLanguages().get(i).getName() +".";
            } else{
                r = info.getSpokenLanguages().get(i).getName() +", ";
            }
        }
        } else {
            r = getResources().getString(R.string.error_msg_unknown);
        }
        return r;
    }

}