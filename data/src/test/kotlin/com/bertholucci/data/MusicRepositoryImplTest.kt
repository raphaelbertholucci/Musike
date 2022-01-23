package com.bertholucci.data

import helpers.BaseTest
import com.bertholucci.data.mapper.MusicResponseMapper
import com.bertholucci.data.model.MusicMatches
import com.bertholucci.data.model.MusicResponse
import com.bertholucci.data.model.MusicResults
import com.bertholucci.data.model.MusicTracks
import com.bertholucci.data.repository.MusicRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class MusicRepositoryImplTest : BaseTest<MusicRepositoryImpl>() {

    @RelaxedMockK
    private lateinit var api: MusikeApi

    override fun init() {
        agent = MusicRepositoryImpl(api)
    }

    @Test
    fun getUpcomingMovies() = runBlockingTest {
        coEvery { api.getTracksByName(any(), any(), any(), any(), any()) } returns movieListMock()

        agent.getTracksByName("Believer").collect {
            assertEquals(
                MusicResponseMapper().mapToDomainList(movieListMock().getTracks()),
                it
            )
        }
    }

    private fun movieListMock() = MusicResults(
        MusicMatches(
            MusicTracks(
                listOf(
                    MusicResponse(
                        name = "Believer",
                        artist = "Imagine Dragons",
                        listeners = "357290",
                        url = "",
                        image = listOf()
                    )
                )
            )
        )
    )
}