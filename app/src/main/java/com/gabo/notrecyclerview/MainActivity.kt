package com.gabo.notrecyclerview

import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import androidx.appcompat.app.AppCompatActivity
import com.gabo.notrecyclerview.databinding.ActivityMainBinding
import com.gabo.notrecyclerview.extension.addFieldsAsNumeric
import com.gabo.notrecyclerview.extension.addFieldsAsText
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            cbFieldIsNumeric.setOnCheckedChangeListener { compoundButton, b ->
                if (cbFieldIsNumeric.isChecked) {
                    clearField(tietFieldName)
                    tietFieldName.inputType = TYPE_CLASS_NUMBER
                } else {
                    clearField(tietFieldName)
                    tietFieldName.inputType = TYPE_CLASS_TEXT
                }
            }
            btnAddField.setOnClickListener {
                if (tietFieldName.text.toString().isNotEmpty()) {
                    when {
                        !cbFieldIsNumeric.isChecked -> {
                            tvList.text = tvList.text.toString()
                                .addFieldsAsText(tietFieldName.text.toString())
                        }
                        cbFieldIsNumeric.isChecked -> {
                            tvList.text = tvList.text.toString()
                                .addFieldsAsNumeric(tietFieldName.text.toString())
                        }
                    }
                } else {
                    Snackbar.make(
                        binding.root, "there is no point to add empty name to list",
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(binding.tvList).show()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("list", binding.tvList.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvList.text = savedInstanceState.getString("list")
    }

    private fun clearField(tiet: TextInputEditText) {
        tiet.setText("")
    }
}