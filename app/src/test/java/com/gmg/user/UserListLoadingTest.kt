package com.gmg.user

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gmg.user.data.model.User
import com.gmg.user.ui.viewmodel.UserViewModel
import com.gmg.user.utils.LiveDataState
import com.gmg.user.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.*
import org.mockito.Mockito.timeout
import org.mockito.Mockito.times
import testApp



@ExperimentalCoroutinesApi
class UserListLoadingTest : KoinTest {

    val testDispatcher = TestCoroutineDispatcher()
    val userViewModel : UserViewModel by inject()




    @Mock
    lateinit var userListObserverA : Observer<LiveDataState<List<User>>>

    @Mock
    lateinit var userListObserverB : Observer<LiveDataState<List<User>>>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<LiveDataState<List<User>>>

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        startKoin{
            androidContext(Mockito.mock(Context::class.java))
            loadKoinModules(testApp)
        }


    }

    @After
    fun after(){
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        //unloadKoinModules(testApp)
    }

    @Test
    fun testUserListLoading(){


        userViewModel.users.observeForever(userListObserverA)
        //userViewModel.loadMore()

        Mockito.verify(userListObserverA).onChanged(argumentCaptor.capture())
        val value  = argumentCaptor.value
        Assert.assertEquals(Status.LOADING,value.status)



    }


    @Test
    fun testUserListLoadingCompleted(){

        userViewModel.users.observeForever(userListObserverB)
        userViewModel.loadFirstPage()
        Mockito.verify(userListObserverB, timeout(5000).times(4)).onChanged(argumentCaptor.capture())
        print(argumentCaptor.value)
        val values = argumentCaptor.value
        Assert.assertEquals(Status.COMPLETED,values.status)

    }





}