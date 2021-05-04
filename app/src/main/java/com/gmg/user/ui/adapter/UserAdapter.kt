package com.gmg.user.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmg.user.R
import com.gmg.user.data.model.User
import com.gmg.user.databinding.LoadingHolderBinding
import com.gmg.user.databinding.UserHolderBinding
import com.gmg.user.utils.CircleTransform

import com.squareup.picasso.Picasso

private enum class Type { USER, LOADING_MORE }

private val type: Array<Type> = Type.values()

internal class UserListRecyclerView(private val recyclerView: RecyclerView, private val listener: RendererListener) {

    private var users: List<User>? = null

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SeriesAdapter()
        }
    }

    fun render(users: List<User>) {
        if (this.users != null){
            addMore(users)
        }else{
            this.users = users
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }

    fun addMore(moreUsers: List<User>) {
        val currentUsers = users!!
        val startPosition = currentUsers.size
        this.users = currentUsers + moreUsers
        recyclerView.adapter?.notifyItemRangeInserted(startPosition, moreUsers.size)
    }

    private inner class SeriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (type[viewType]) {
            Type.USER -> ShowHolder(
                UserHolderBinding.bind(LayoutInflater.from(parent.context).inflate(
                R.layout.user_holder, parent, false)))
            Type.LOADING_MORE -> LoadingHolder(
                LoadingHolderBinding.bind(
                    LayoutInflater.from(parent.context).inflate(R.layout.loading_holder, parent, false)
                )
            )
        }

        override fun getItemCount(): Int = users?.size?.run { this + 1 } ?: 0

        override fun getItemId(position: Int): Long = position.toLong()

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (type[holder.itemViewType]) {
                Type.USER -> users?.run { (holder as ShowHolder).render(this[position]) }
                Type.LOADING_MORE -> listener.onLoadMore()
            }
        }

        override fun getItemViewType(position: Int) = when (users?.size) {
            position -> Type.LOADING_MORE.ordinal
            else -> Type.USER.ordinal
        }
    }

    private inner class LoadingHolder(private val binding: LoadingHolderBinding) : RecyclerView.ViewHolder(binding.root)

    inner class ShowHolder(private val binding: UserHolderBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var userItem: User

        init {
            itemView.setOnClickListener { listener.onUserItem(userItem) }
        }

        fun render(user: User) {
            this.userItem = user
            Picasso.get().load(user.picture.medium).transform(CircleTransform()).into(binding.avatar)
            binding.name.text = user.name.first +" "+user.name.last
            binding.gender.text = user.gender
        }
    }


    interface RendererListener {
        fun onUserItem(userItem: User)
        fun onLoadMore()
    }
}