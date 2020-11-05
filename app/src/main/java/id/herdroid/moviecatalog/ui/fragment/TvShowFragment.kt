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
import id.herdroid.moviecatalog.adapter.TvShowAdapter
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.viewmodel.MovieViewModel
import id.herdroid.moviecatalog.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvShowFragment :Fragment(){
    private var type: TypeData = TypeData.TV_SHOWS

    companion object {
        const val TVSHOW_TYPE = "type"

        @JvmStatic
        fun newInstance(type: TypeData) =
                TvShowFragment().apply {
                    arguments = Bundle().apply {
                        putInt(TVSHOW_TYPE, type.ordinal)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            type = TypeData.values()[it.getInt(TVSHOW_TYPE)]
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProviders.of(this).get(
                    if (type == TypeData.TV_SHOWS) TvShowViewModel::class.java
                    else MovieViewModel::class.java)
            val listData = when (viewModel) {
                is TvShowViewModel -> viewModel.getTvShows()

                else -> listOf()
            }
            val recyclerView = rv_tvShow
            context?.run {
                recyclerView.adapter = TvShowAdapter(this, listData , type)
                recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                recyclerView.setHasFixedSize(true)
            }
        }
    }
}
