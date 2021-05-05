package com.gmg.user.ui.view.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gmg.user.databinding.FragmentUserDetailsBinding
import com.gmg.user.utils.CircleTransform

import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class UserDetails : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: UserDetailsArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.user.apply {
            Picasso.get().load(picture.large).transform(CircleTransform()).into(binding.avatar)
            binding.name.text = "${name.title} ${name.first} ${name.last}"
            binding.gender.text = gender
            binding.email.text = email
            binding.phone.text = phone
            binding.cell.text = cell
            binding.natinality.text = nat
        }

    }



}