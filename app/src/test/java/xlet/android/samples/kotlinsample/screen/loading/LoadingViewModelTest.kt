package xlet.android.samples.kotlinsample.screen.loading

import io.reactivex.disposables.Disposable
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.hamcrest.CoreMatchers.`is` as _is
import org.mockito.Mockito.`when` as _when

class LoadingViewModelTest {

    @Mock
    private val view = Mockito.mock(LoadingView::class.java)!!
    @Mock
    private val intervalDisposable = Mockito.mock(Disposable::class.java)!!

    @Test
    fun testCheckProgressDone() {
        MockitoAnnotations.initMocks(this)
        val mockLoadingViewModel = LoadingViewModel(100, view)

        mockLoadingViewModel.checkProgressDone(0, intervalDisposable)
        var executeTimes = 0
        Mockito.verify(view, Mockito.times(executeTimes)).startNextPage()
        Mockito.verify(intervalDisposable, Mockito.times(executeTimes)).dispose()

        mockLoadingViewModel.checkProgressDone(100, intervalDisposable)
        executeTimes = 1
        Mockito.verify(view, Mockito.times(executeTimes)).startNextPage()
        Mockito.verify(intervalDisposable, Mockito.times(executeTimes)).dispose()
    }

    @Test
    fun testHandleProgress() {
        MockitoAnnotations.initMocks(this)
        val maxCount = 10
        val mockLoadingViewModel = LoadingViewModel(maxCount, view)

        assertThat(mockLoadingViewModel.handleProgress(maxCount), _is(maxCount))
        assertThat(mockLoadingViewModel.handleProgress(-100), _is(0))
        assertThat(mockLoadingViewModel.handleProgress(200), _is(maxCount))
        for (givenCount in 0 until maxCount) {
            val nextCount = givenCount + 1
            assertThat(mockLoadingViewModel.handleProgress(givenCount), _is(nextCount))
        }
    }
}