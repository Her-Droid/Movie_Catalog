package id.herdroid.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.data.TvShowEntity
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.list_tvshow.view.*

class TvShowAdapter  (private val context: Context, private var listTvShow: List<TvShowEntity>, private val type: TypeData):
        RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(tvShow: TvShowEntity) {
            itemView.run {
                tvShow.also {
                    Glide.with(itemView.context).load(tvShow.imagePath)
                            .into(img_tv)
                    tv_title.text = it.title
                    tv_description.text = it.description
                    tv_date.text = itemView.resources.getString(R.string.release_date, tvShow.releaseDate)
                }
                setOnClickListener {
                    context.startActivity(
                            Intent(context, DetailActivity::class.java)
                                    .putExtra(DetailActivity.EXTRA_TVSHOW, tvShow.tvShowId)
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_tvshow, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bindItem(tvShow)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }


}

