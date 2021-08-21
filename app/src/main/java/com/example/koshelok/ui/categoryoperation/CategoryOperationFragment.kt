package com.example.koshelok.ui.categoryoperation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.koshelok.R
import com.example.koshelok.databinding.FragmentCategoryOperationTransactionBinding
import com.example.koshelok.ui.model.CategoryModel
import com.example.koshelok.ui.sumoperation.SumOperationFragmentArgs

class CategoryOperationFragment : Fragment(R.layout.fragment_category_operation_transaction),
    CategoryItemClickListener {

    private val binding by viewBinding(FragmentCategoryOperationTransactionBinding::bind)
    private val viewModel: CategoryViewModel by viewModels()

    private val args by navArgs<SumOperationFragmentArgs>()
    private val transaction by lazy { args.transaction }

    private lateinit var adapterCategory: AdapterCategory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        clickBackButton()
        isSelectedCategory()
        binding.addSumOperationButton.setOnClickListener {
            launchAddTransactionFragment()
        }
        transaction.let { viewModel.setSelectCategory(it) }
    }

    private fun setupRecycler() {
        adapterCategory = AdapterCategory(this@CategoryOperationFragment)
        binding.categoryRecyclerView.adapter = adapterCategory
        viewModel.listCategoryModel.observe(
            viewLifecycleOwner,
            Observer { data: List<CategoryModel>? ->
                if (data != null) {
                    adapterCategory.submitList(data)
                }
            })
    }

    private fun isSelectedCategory() {
        if (viewModel.isSelect()) {
            binding.addSumOperationButton.isEnabled = true
        }
    }

    private fun launchAddTransactionFragment() {
        transaction.categoryModel = viewModel.getEnableCategory()
        transaction.date = System.currentTimeMillis()
        findNavController().navigate(
            CategoryOperationFragmentDirections.actionCategoryOperationFragmentToAddOperationFragment(
                transaction
            )
        )
    }

    private fun clickBackButton() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onClickItem(position: Int, item: CategoryModel) {
        viewModel.changeEnableState(position, item)
        adapterCategory.notifyDataSetChanged()
        isSelectedCategory()
    }
}