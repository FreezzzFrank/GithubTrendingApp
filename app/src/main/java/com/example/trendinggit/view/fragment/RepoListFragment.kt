package com.example.trendinggit.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendinggit.APP
import com.example.trendinggit.databinding.FragmentRepoListBinding
import com.example.trendinggit.view.adapter.RepoListAdapter
import com.example.trendinggit.viewmodel.RepoListViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.jetbrains.anko.longToast


class RepoListFragment : Fragment() {
    private lateinit var databinding: FragmentRepoListBinding
    private lateinit var adapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = FragmentRepoListBinding.inflate(inflater,container,false).apply {
            viewmodel = ViewModelProvider.AndroidViewModelFactory(APP.instance).create(RepoListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        // Inflate the layout for this fragment
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databinding.viewmodel?.fetchRepoData()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        databinding.viewmodel?.repoListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateRepoList(it)
        })

        databinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = databinding.viewmodel
        if (viewModel != null) {
            adapter = RepoListAdapter(viewModel)
            val layoutManager = LinearLayoutManager(activity)
            repo_list_rv.layoutManager = layoutManager
            repo_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            repo_list_rv.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RepoListFragment().apply {}
    }
}