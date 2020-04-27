package com.nickgua.demo1.toppage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nickgua.demo1.*
import com.nickgua.demo1.common.PageEvent
import com.nickgua.demo1.common.PageStatus
import com.nickgua.demo1.common.isListOf
import com.nickgua.demo1.common.showOrGone
import com.nickgua.demo1.navigation.Navigator
import com.nickgua.model.data.WeatherTime
import kotlinx.android.synthetic.main.fragment_top_page.*
import kotlinx.android.synthetic.main.view_error_default.*
import kotlinx.android.synthetic.main.view_loading.*

class TopPageFragment : Fragment() {

    private val viewModel by viewModels<TopPageViewModel> { ViewModelFactory() }

    private val adapter: TopPageAdapter = TopPageAdapter()

    private lateinit var navigator: Navigator

    private val pageStatusObserver = Observer<PageStatus> { status ->
        when(status) {
            is PageStatus.Loading -> {
                loadingView.showOrGone(true)
            }
            is PageStatus.Success<*> -> {
                loadingView.showOrGone(false)
                errorView.showOrGone(false)

                status.data?.isListOf<WeatherTime>()?.let {
                    updateList(it)
                }
            }
            is PageStatus.Error -> {
                loadingView.showOrGone(false)
                errorView.showOrGone(true)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = Navigator(this)
        viewModel.loadWeatherData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopPageList()
        initSwipeRefresh()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.pageStatus.observe(viewLifecycleOwner, pageStatusObserver)

        viewModel.pageEvent.observe(viewLifecycleOwner, Observer { event ->
            when(event) {
                is PageEvent.Navigation -> {
                    navigator.navigate(event.direction)
                }
            }
        })
    }

    private fun initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }
    }

    private fun initTopPageList() {
        adapter.onWeatherTimeClickListener = viewModel.onWeatherTimeClickListener
        topPageList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@TopPageFragment.adapter
        }
    }

    private fun updateList(weatherTimes: List<WeatherTime>) {
        adapter.setWeatherTimes(weatherTimes)
    }
}