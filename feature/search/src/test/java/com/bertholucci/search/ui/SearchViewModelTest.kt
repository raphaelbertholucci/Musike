package com.bertholucci.search.ui

import com.bertholucci.domain.interactor.GetTracksByName
import com.bertholucci.domain.model.MusicDomain
import com.bertholucci.search.mapper.MusicMapper
import com.bertholucci.search.ui.helpers.BaseTest
import com.bertholucci.search.ui.helpers.success
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest : BaseTest<SearchViewModel>() {

    @RelaxedMockK
    private lateinit var getTracks: GetTracksByName

    override fun init() {
        agent = SearchViewModel(getTracks)
    }

    @Test
    fun `get tracks by name and get the result list`() = runBlockingTest {
        coEvery { getTracks(any()) } returns flow {
            emit(domainMock)
        }

        agent.getTracksByName("Believer", 0)

        agent.tracks.observeForever { result ->
            assertEquals(success(MusicMapper().mapFromDomainList(domainMock)), result)
        }
    }
}

val domainMock = listOf(
    MusicDomain(
        name = "Believer",
        artist = "Imagine Dragons",
        listeners = "357290",
        url = "",
        image = listOf()
    )
)

val domainMock2 = listOf(
    MusicDomain(
        name = "Believ2222er",
        artist = "Imag2222ine Dragons",
        listeners = "357290",
        url = "",
        image = listOf()
    )
)