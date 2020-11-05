package id.herdroid.moviecatalog.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.adapter.MovieAdapter
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.viewmodel.MovieViewModel
import id.herdroid.moviecatalog.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private var type: TypeData = TypeData.MOVIES

    companion object {
        const val MOVIE_TYPE = "type"

        @JvmStatic
        fun newInstance(type: TypeData) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_TYPE, type.ordinal)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            type = TypeData.values()[it.getInt(MOVIE_TYPE)]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProviders.of(this).get(
                if (type == TypeData.MOVIES) MovieViewModel::class.java
                else TvShowViewModel::class.java)
            val listData = when (viewModel) {
                is MovieViewModel -> viewModel.getMovies()
                else -> listOf()
            }
            val recyclerView = rv_movie
            context?.run {
                recyclerView.adapter = MovieAdapter(this, listData , type)
                recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                recyclerView.setHasFixedSize(true)
            }
        }
    }
}
