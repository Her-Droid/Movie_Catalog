package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.data.MovieEntity
import id.herdroid.moviecatalog.data.TvShowEntity
import id.herdroid.moviecatalog.utils.DataDummy

class DetailViewModel :ViewModel() {
    private lateinit var movId: String

    fun setSelectedData(courseId: String) {
        this.movId = courseId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.dummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowEntities = DataDummy.dummyTvShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvShowId == movId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }

}
