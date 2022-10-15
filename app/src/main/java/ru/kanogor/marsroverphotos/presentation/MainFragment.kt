package ru.kanogor.marsroverphotos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.kanogor.marsroverphotos.R
import ru.kanogor.marsroverphotos.databinding.FragmentMainBinding
import ru.kanogor.marsroverphotos.entity.Photos
import ru.kanogor.marsroverphotos.presentation.recyclerView.MarsPhotoAdapter
import ru.kanogor.marsroverphotos.presentation.recyclerView.MarsPhotoLoadStateAdapter

private const val PARAM1 = "param1"

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val marsPhotosAdaptor = MarsPhotoAdapter { photo -> onItemClick(photo) }
        binding.recyclerMarsPhoto.adapter =
            marsPhotosAdaptor.withLoadStateFooter(MarsPhotoLoadStateAdapter())

        binding.swipeRefresh.setOnRefreshListener {
            marsPhotosAdaptor.refresh()
        }

        viewModel.pagedPhotos.onEach {
            marsPhotosAdaptor.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        marsPhotosAdaptor.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(photo: Photos) {
        val bundle = Bundle().apply {
            putString(PARAM1, photo.img_src)
        }
        findNavController().navigate(R.id.OpenPhotoFragment, args = bundle)
    }

}