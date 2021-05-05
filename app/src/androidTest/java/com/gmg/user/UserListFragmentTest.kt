package com.gmg.user

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gmg.user.data.model.Name
import com.gmg.user.data.model.Picture
import com.gmg.user.data.model.User
import com.gmg.user.ui.view.fragments.UserList
import com.gmg.user.ui.view.fragments.UserListDirections
import org.junit.Test
import org.junit.runner.RunWith

import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {


    @Test
    fun testUserDetailNavigation(){

        //Test Navigation Host Controller
        val navigationController = TestNavHostController(ApplicationProvider.getApplicationContext())

        //Graphical fragment scenario
        val userListScenario = launchFragmentInContainer<UserList>()

        userListScenario.onFragment{ userListFragment ->

            //setting the graph to test navigation host
            navigationController.setGraph(R.navigation.nav_graph)

            //making the NavController available via the findNavController() APIs
            Navigation.setViewNavController(userListFragment.requireView(), navigationController)

        }


        //clicking on the list item and verifying that performing a click changes the Navigation controller
        //onData(anything()).atPosition(1).perform(click())

        //onView(withId(R.id.usersList)).perform(RecyclerViewActions.actionOnItemAtPosition<UserListRecyclerView.ShowHolder>(2, click()))


        //Mock User Object for the navigation testing

        InstrumentationRegistry.getInstrumentation().runOnMainSync{

            navigationController.navigate(UserListDirections.actionUserListToUserDetails(mockUser))
        }


        assertThat(navigationController.currentDestination?.id).isEqualTo(R.id.userDetails)


    }

    private val mockUser = User(
        "male",
        Name("Mr","First Name","Last Name"),
        "email@email.com",
        "0561243452",
        "0561243452",
        Picture("https://images.unsplash.com/photo-1619266411584-5daa1a11d912?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
        "https://images.unsplash.com/photo-1619266411584-5daa1a11d912?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
            "https://images.unsplash.com/photo-1619266411584-5daa1a11d912?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"),
        "Pakistani"
    )

}