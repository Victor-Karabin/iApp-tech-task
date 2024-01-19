package com.iapps.domain.cats

import com.iapps.boundary.cats.CatPhotosRepo
import com.iapps.interactor.cats.GetCatsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import kotlin.properties.Delegates.notNull

@OptIn(ExperimentalCoroutinesApi::class)
class GetCatsUseCaseTests {

    private var usecase by notNull<GetCatsUseCase>()

    private val photoFactory = CatPhotoFactory()
    private val photo1 = photoFactory.create("2024-01-18T21:33:21Z")
    private val photo2 = photoFactory.create("2024-01-18T21:33:19Z")
    private val photo3 = photoFactory.create("2024-01-18T21:33:23Z")
    private val photos = listOf(photo1, photo2, photo3)

    private val expected = listOf(
        photo2.toCat(),
        photo1.toCat(),
        photo3.toCat()
    )

    @Before
    fun before() {
        openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        val repo = mock(CatPhotosRepo::class.java)
            .stub {
                onBlocking { this.photos() }
                    .doReturn(Result.success(photos))
            }

        usecase = GetCatsUseCaseImpl(repo)
    }

    @Test
    fun `result-list is sorted by published date`() = runTest {
        val result = usecase()
        assert(result.isSuccess) { "unexpected failure result" }
        assertEquals(expected, result.getOrNull())
    }

    @After
    fun terDown() {
        Dispatchers.resetMain()
    }
}