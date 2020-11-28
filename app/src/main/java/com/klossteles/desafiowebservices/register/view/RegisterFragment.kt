package com.klossteles.desafiowebservices.register.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.klossteles.desafiowebservices.R

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register(view)
        checkNameChanged(view)
        checkEmailChanged(view)
        checkPasswordChanged(view)
        setBackNavigation(view)
    }

    private fun setBackNavigation(view: View) {
        view.findViewById<ImageView>(R.id.imgBackRegister).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun register(view: View) {
        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            var success = true
            val name = view.findViewById<TextInputEditText>(R.id.edtNameRegister)?.text.toString()
            if (name.isEmpty()) {
                view.findViewById<TextInputLayout>(R.id.txtNameRegister).error =
                    getString(R.string.insert_name)
                success = false
            }
            val email = view.findViewById<TextInputEditText>(R.id.edtEmailRegister)?.text.toString()
            if (email.isEmpty()) {
                view.findViewById<TextInputLayout>(R.id.txtEmailRegister).error = getString(R.string.insert_email)
                success = false
            }

            val password = view.findViewById<TextInputEditText>(R.id.edtPasswordRegister)?.text.toString()
            success = checkPassword(password, view, success)

            if (success) {
                val navController = findNavController()
//                navController.navigate(R.id.action_registerFragment_to_restaurantsListFragment)
            }
        }
    }

    private fun checkPassword(password: String, view: View, success: Boolean): Boolean {
        var isOk = success
        if (password.isEmpty()) {
            view.findViewById<TextInputLayout>(R.id.txtPasswordRegister).error =
                getString(R.string.password_cannot_be_null)
            isOk = false
        } else if (password.length < 6) {
            view.findViewById<TextInputLayout>(R.id.txtPasswordRegister).error =
                getString(R.string.must_contain_at_least_6_characters)
            isOk = false
        }
        return isOk
    }

    private fun checkPasswordChanged(view: View) {
        view.findViewById<TextInputEditText>(R.id.edtPasswordRegister)
            .addTextChangedListener(object :
                TextWatcher {
                override fun afterTextChanged(s: Editable?) { }

                override fun beforeTextChanged( s: CharSequence?, start: Int, count: Int, after: Int ) { }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    view.findViewById<TextInputLayout>(R.id.txtPasswordRegister).error = ""
                }
            })
    }

    private fun checkEmailChanged(view: View) {
        view.findViewById<TextInputEditText>(R.id.edtEmailRegister)
            .addTextChangedListener(object :
                TextWatcher {
                override fun afterTextChanged(s: Editable?) { }

                override fun beforeTextChanged( s: CharSequence?, start: Int, count: Int, after: Int ) { }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    view.findViewById<TextInputLayout>(R.id.txtEmailRegister).error = ""
                }
            })
    }

    private fun checkNameChanged(view: View) {
        view.findViewById<TextInputEditText>(R.id.edtNameRegister)
            .addTextChangedListener(object :
                TextWatcher {
                override fun afterTextChanged(s: Editable?) { }

                override fun beforeTextChanged( s: CharSequence?, start: Int, count: Int, after: Int ) { }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    view.findViewById<TextInputLayout>(R.id.txtNameRegister).error = ""
                }
            })
    }
}