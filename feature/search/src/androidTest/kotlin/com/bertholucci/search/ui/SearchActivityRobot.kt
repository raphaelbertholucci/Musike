package com.bertholucci.search.ui

import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.testing.FragmentScenario
import com.bertholucci.domain.interactor.GetTracksByName
import com.bertholucci.domain.model.ImageDomain
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.search.Check
import com.bertholucci.search.Execute
import com.bertholucci.search.R
import com.bertholucci.search.Setup
import com.bertholucci.search.extensions.checkRecyclerViewItem
import com.bertholucci.search.extensions.click
import com.bertholucci.search.extensions.hasText
import com.bertholucci.search.extensions.isTextDisplayed
import com.bertholucci.search.extensions.typeText
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun setupView(func: MainActivityRobot.() -> Unit) =
    MainActivityRobot().apply(func)

class MainActivityRobot : Setup<MainActivityRobotExecute, MainActivityRobotCheck> {

    private val useCase: GetTracksByName = mockk()

    init {
        startKoin {
            modules(
                listOf(
                    module { viewModel { SearchViewModel(useCase) } },
                )
            )
        }
    }

    override fun executeCreator() = MainActivityRobotExecute()

    override fun checkCreator() = MainActivityRobotCheck()

    override fun launch() {
        FragmentScenario.launchInContainer(
            fragmentClass = SearchFragment::class.java,
            fragmentArgs = null,
            themeResId = R.style.Theme_Musike
        )
    }

    fun mockTracks() {
        coEvery {
            useCase(any())
        } returns flow {
            emit(mock)
        }
    }
}

class MainActivityRobotExecute : Execute<MainActivityRobotCheck> {
    override fun checkCreator() = MainActivityRobotCheck()

    fun typeTrack(text: String) {
        R.id.et_search.typeText(text)
    }

    fun clickOnPopularity() {
        R.id.tv_sort.click()
    }
}

class MainActivityRobotCheck : Check {

    fun checkStaticViews() {
        R.id.tv_sort.hasText(R.string.search_popularity)
        R.id.tv_hint.hasText(R.string.search_label_hint)
    }

    fun checkTracks() {
        checkRecyclerItem(0, "One More Time", "Daft Punk", "1420701")
        checkRecyclerItem(1, "One Step Closer", "Linkin Park", "1138819")
    }

    private fun checkRecyclerItem(position: Int, title: String, artist: String, listeners: String) {
        R.id.rv_tracks.checkRecyclerViewItem<AppCompatTextView>(position, R.id.tv_title) { view ->
            view.text == title
        }
        R.id.rv_tracks.checkRecyclerViewItem<AppCompatTextView>(position, R.id.tv_artist) { view ->
            view.text == artist
        }
        R.id.rv_tracks.checkRecyclerViewItem<AppCompatTextView>(
            position,
            R.id.tv_listeners
        ) { view ->
            view.text == listeners
        }
    }

    fun checkPopularityDialogTexts() {
        "Sort popularity by:".isTextDisplayed()
        "Crescent".isTextDisplayed()
        "Descending".isTextDisplayed()
        "None".isTextDisplayed()
    }
}

val mock = listOf(
    TrackDomain(
        name = "One More Time",
        artist = "Daft Punk",
        listeners = "1420701",
        url = "https://picsum.photos/200",
        image = listOf(ImageDomain(image = "https://picsum.photos/200", size = ""))
    ),
    TrackDomain(
        name = "One Step Closer",
        artist = "Linkin Park",
        listeners = "1138819",
        url = "https://picsum.photos/200",
        image = listOf(ImageDomain(image = "https://picsum.photos/200", size = ""))
    )
)