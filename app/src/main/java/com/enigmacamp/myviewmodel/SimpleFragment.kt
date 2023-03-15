package com.enigmacamp.myviewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myviewmodel.util.db


/**
 * A simple [Fragment] subclass.
 * Use the [SimpleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimpleFragment : Fragment() {
    private lateinit var viewModel: SimpleFragmentVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //    Di fragment, lifecycle yang paling aman, untuk inisialisasi
//    viewmodel, apalagi kalau kita menggunakan savedstatehandle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Untuk shared viewmodel, gunakan owner nya requireActivity
//        viewModel = ViewModelProvider(requireActivity())[SimpleFragmentVM::class.java]
        viewModel = ViewModelProvider(this)[SimpleFragmentVM::class.java]
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnTotalCustomer = view.findViewById<Button>(R.id.btnTotalCustomer)
        btnTotalCustomer.setOnClickListener {
            viewModel.addTotalCustomer()
            Log.d("Simple-Fragment", viewModel.totalCustomer.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SimpleFragment()
    }
}