import com.bertholucci.domain.interactor.GetTracksByName
import com.bertholucci.domain.model.MusicDomain
import com.bertholucci.domain.repository.MusicRepository
import helpers.BaseTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTracksByNameTest : BaseTest<GetTracksByName>() {

    @RelaxedMockK
    private lateinit var repository: MusicRepository

    override fun init() {
        agent = GetTracksByName(repository)
    }

    @Test
    fun `get tracks by name and get the result list`() = runBlockingTest {
        coEvery { repository.getTracksByName(any()) } returns flow {
            emit(domainMock)
        }

        val request = Pair("Believer", 0)

        agent(request)

        agent.invoke(request).collect { result ->
            assertEquals(domainMock, result)
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
