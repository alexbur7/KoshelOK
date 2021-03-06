package com.example.koshelok.ui.wallet.titlewallet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.koshelok.R
import com.example.koshelok.databinding.FragmentAddTitleWalletBinding
import com.example.koshelok.domain.Currency
import com.example.koshelok.ui.util.entity.CreateWalletEntity
import com.example.koshelok.ui.util.extentions.hideKeyboard
import com.example.koshelok.ui.util.extentions.showKeyboard

class AddTitleWalletFragment : Fragment(R.layout.fragment_add_title_wallet) {

    private val binding by viewBinding(FragmentAddTitleWalletBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAddTextChangedListener()
        setOnBackPressedListener()
        binding.titleWalletEditText.showKeyboard()
        binding.fartherButton.setOnClickListener {
            launchEditWalletFragment()
        }
    }

    override fun onStop() {
        super.onStop()
        binding.titleWalletEditText.hideKeyboard()
    }

    private fun setOnBackPressedListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun launchEditWalletFragment() {
        val title = binding.titleWalletEditText.text.toString().trim()
        findNavController().navigate(
            AddTitleWalletFragmentDirections.actionAddTitleWalletFragmentToEditWalletFragment(
                CreateWalletEntity(null, null, title, Currency.RUB)
            )
        )
    }

    private fun setAddTextChangedListener() {
        binding.titleWalletEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                Unit

            override fun afterTextChanged(str: Editable) {
                if (str.toString().trim() > "") {
                    with(binding.fartherButton) {
                        isEnabled = true
                    }
                } else {
                    with(binding.fartherButton) {
                        isEnabled = false
                    }
                }
            }
        })
    }
}
