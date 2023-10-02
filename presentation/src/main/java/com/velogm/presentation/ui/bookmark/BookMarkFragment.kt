package com.velogm.presentation.ui.bookmark

import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingFragment
import com.velogm.domain.model.Bookmark
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentBookmarkBinding
import com.velogm.presentation.ui.bookmark.adapter.BookmarkAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BookMarkFragment : BindingFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {
    private val scrapMockList: MutableList<Bookmark> = mutableListOf()
    private val bookmarkAdapter: BookmarkAdapter = BookmarkAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("bookmark")
        initView()
    }

    private fun initView() {
        setMockData()
        initAdapter()
    }

    private fun setMockData() {
        var mock = Bookmark("스크랩0", "0개")
        for (i in 1..19) {
            scrapMockList.add(mock)
            mock = Bookmark("스크랩$i", "${i}개")
        }
    }

    private fun initAdapter() = with(binding) {

        rvScrap.run {
            adapter = bookmarkAdapter
//            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        bookmarkAdapter.submitList(scrapMockList)
    }
}
