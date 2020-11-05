package id.herdroid.moviecatalog.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import id.herdroid.moviecatalog.utils.DataDummy
import androidx.test.rule.ActivityTestRule
import id.herdroid.moviecatalog.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private val dataMovie = DataDummy.dummyMovies()
    private val dataTvShow = DataDummy.dummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dataMovie[0].title)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_release_date)).check(matches(withText(dataMovie[0].releaseDate)))
        onView(withId(R.id.movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail)).check(matches(withText(dataMovie[0].description)))
    }

    @Test
    fun loadTvShows(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTvShow.size))
    }

    @Test
    fun loadDetailTvShow(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dataTvShow[0].title)))
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_release_date)).check(matches(withText(dataTvShow[0].releaseDate)))
        onView(withId(R.id.movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail)).check(matches(withText(dataTvShow[10].description)))
    }

}