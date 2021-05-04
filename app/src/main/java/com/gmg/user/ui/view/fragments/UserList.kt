package com.gmg.user.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmg.user.data.model.User
import com.gmg.user.databinding.FragmentUserListBinding
import com.gmg.user.ui.adapter.UserListRecyclerView
import com.gmg.user.ui.viewmodel.UserViewModel
import com.gmg.user.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserList : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListRecyclerView: UserListRecyclerView
    private val userListViewModel: UserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()

    }

    fun setupUI(){
        userListRecyclerView = UserListRecyclerView(binding.usersList, object : UserListRecyclerView.RendererListener {
            override fun onUserItem(user: User) {
                findNavController().navigate(UserListDirections.actionUserListToUserDetails(user))
            }

            override fun onLoadMore() {
                binding.loadingIndicator.visibility = View.VISIBLE
                userListViewModel.loadMore()
            }
        })

        binding.loadingIndicator.visibility = View.VISIBLE
    }

    fun setupObserver(){

        userListViewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.COMPLETED -> {
                    binding.loadingIndicator.visibility = View.GONE
                    binding.usersList.visibility = View.VISIBLE
                    it.data?.let { users ->   userListRecyclerView.render(users) }


                }
                Status.LOADING -> {
                    if(it.data == null){
                        binding.loadingIndicator.visibility = View.VISIBLE
                        binding.usersList.visibility = View.GONE
                    }else{
                        binding.loadingIndicator.visibility = View.VISIBLE
                    }

                }
                Status.ERROR -> {
                    //Handle Error
                    binding.loadingIndicator.visibility = View.GONE
                    Toast.makeText(requireActivity(),it.message, Toast.LENGTH_SHORT).show()
                }
                Status.EMPTY ->{
                    //show empty view holder
                }
                Status.CANCELED -> {
                    //handle in case request canceled
                }

            }
        })

    }


}