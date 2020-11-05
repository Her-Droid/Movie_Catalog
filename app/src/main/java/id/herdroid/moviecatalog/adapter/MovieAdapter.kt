package id.herdroid.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.data.MovieEntity
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.list_movie.view.*

class MovieAdapter(private val context: Context, private var listMovie: List<MovieEntity>, private val type: TypeData):
        RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(movies: MovieEntity) {
            itemView.run {
                movies.also {
                    Glide.with(itemView.context).load(movies.imagePath)
                            .into(img_movie)
                    movie_title.text = it.title
                    movie_description.text = it.description
                    movie_date.text = itemView.resources.getString(R.string.release_date, movies.releaseDate)
                }
                setOnClickListener {
                    context.startActivity(

                    Intent(context, DetailActivity::class.java)
                        .putExtra(DetailActivity.EXTRA_MOVIE, movies.movieId)
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies =listMovie[position]
        holder.bindItem(movies)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}

