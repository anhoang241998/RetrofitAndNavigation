package com.annguyenhoang.testing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.annguyenhoang.testing.databinding.FragmentNextBinding

class NextFragment : Fragment(R.layout.fragment_next) {

    private var _binding: FragmentNextBinding? = null
    private val binding
        get() = _binding!!

    private val args: NextFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNextBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtChange.text = args.input

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}