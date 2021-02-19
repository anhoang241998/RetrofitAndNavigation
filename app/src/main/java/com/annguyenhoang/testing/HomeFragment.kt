package com.annguyenhoang.testing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.annguyenhoang.testing.adapter.RecyclerAdapter
import com.annguyenhoang.testing.databinding.FragmentHomeBinding
import com.annguyenhoang.testing.models.Comments
import com.annguyenhoang.testing.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var listAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val input = binding.edtInput.text.toString()
            if (input.isNotEmpty()) {
                val action = HomeFragmentDirections.actionHomeFragment2ToNextFragment(input)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "vui long nhap gia tri vao", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        listAdapter = RecyclerAdapter()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getComments()
            } catch (e: IOException) {
                Log.e("loi", "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("loi", "HttpException, unexpected response")
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                listAdapter.comments = response.body()!!
            } else {
                Log.e("loi", "Response not successful")
            }

            binding.rvList.adapter = listAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}