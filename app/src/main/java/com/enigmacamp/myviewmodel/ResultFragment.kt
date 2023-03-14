package com.enigmacamp.myviewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private lateinit var viewModel: SimpleFragmentVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //        Untuk shared viewmodel, gunakan owner nya requireActivity
//        viewModel = ViewModelProvider(requireActivity())[SimpleFragmentVM::class.java]
        viewModel = ViewModelProvider(this)[SimpleFragmentVM::class.java]
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnGetTotalCustomer = view.findViewById<Button>(R.id.btnGetTotalCustomer)
        btnGetTotalCustomer.setOnClickListener {
            Log.d("Result-Fragment", viewModel.totalCustomer.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }
}