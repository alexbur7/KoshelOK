package com.example.koshelok.ui.transactions.typeoperation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.koshelok.R
import com.example.koshelok.databinding.FragmentTypeOperationTransactionBinding
import com.example.koshelok.domain.TypeOperation
import com.example.koshelok.ui.main.appComponent
import com.example.koshelok.ui.transactions.sumoperation.SumOperationFragmentArgs
import com.example.koshelok.ui.util.factory.ViewModelFactory
import javax.inject.Inject

class TypeOperationFragment : Fragment(R.layout.fragment_type_operation_transaction) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by viewBinding(FragmentTypeOperationTransactionBinding::bind)
    private val viewModel:TypeOperationViewModel by viewModels { viewModelFactory }
    private val args by navArgs<SumOperationFragmentArgs>()
    private val transaction by lazy { args.transaction }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        setOnBackPressedListener()

        transaction.let { viewModel.setSelectTransaction(it) }
        transaction.type?.let {
            checkChoose(it)
            viewModel.setSelectType(it)
        }

        viewModel.typeOperation.observe(viewLifecycleOwner) {
            checkChoose(it)
        }
    }

    private fun setClickListener() {
        binding.incomeLayout.setOnClickListener {
            viewModel.setSelectType(TypeOperation.SELECT_INCOME)
        }
        binding.expenseLayout.setOnClickListener {
            viewModel.setSelectType(TypeOperation.SELECT_EXPENSE)
        }
        binding.addTypeOperationButton.setOnClickListener {
            launchCategoryFinishedFragment()
        }
    }

    private fun launchCategoryFinishedFragment() {
        transaction.type = viewModel.typeOperation.value
        findNavController().navigate(
            TypeOperationFragmentDirections
                .actionTypeOperationFragmentToCategoryOperationFragment(transaction)
        )
    }

    private fun checkChoose(selected: TypeOperation) {
        when (selected) {
            TypeOperation.SELECT_INCOME -> {
                binding.incomeImageView.visibility = View.VISIBLE
                binding.expenseImageView.visibility = View.INVISIBLE
            }
            TypeOperation.SELECT_EXPENSE -> {
                binding.incomeImageView.visibility = View.INVISIBLE
                binding.expenseImageView.visibility = View.VISIBLE
            }
        }
        setStateButton()
    }

    private fun setStateButton() {
        with(binding.addTypeOperationButton) {
            isEnabled = true
        }
    }

    private fun setOnBackPressedListener() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}
