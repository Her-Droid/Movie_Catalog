package id.herdroid.moviecatalog.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.data.MovieEntity
import id.herdroid.moviecatalog.data.TvShowEntity
import id.herdroid.moviecatalog.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedData(movieId)
                viewModel.getMovie()?.let { item ->
                    populateMovie(item)
                }
            }
            else{
                if (extras != null) {
                    val tvId = extras.getString(EXTRA_TVSHOW)
                    if (tvId != null) {
                        viewModel.setSelectedData(tvId)
                        viewModel.getTvShow()?.let { item ->
                            populateTv(item)
                        }
                    }
                }
            }
        }
    }

    private fun populateMovie(data: MovieEntity) {
        detail_title.text = data.title
        movie_detail.text = data.description
        movie_release_date.text = data.releaseDate
        Glide.with(this).load(data.imagePath)
            .into(imgPoster)
        title = data.title
    }

    private fun populateTv(data: TvShowEntity) {
        detail_title.text = data.title
        movie_detail.text = data.description
        movie_release_date.text = data.releaseDate
        Glide.with(this).load(data.imagePath)
                .into(imgPoster)

        title = data.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
