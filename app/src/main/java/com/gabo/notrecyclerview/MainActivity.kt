package com.gabo.notrecyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gabo.notrecyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
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
                            val tv = TextView(this@MainActivity)
                            tv.text = "\n\ntext: ${tietFieldName.text.toString()}"
                            ll.addView(tv)
                        }
                        cbFieldIsNumeric.isChecked -> {
                            val tv = TextView(this@MainActivity)
                            tv.text = "\n\nNumeric: ${tietFieldName.text.toString()}"
                            ll.addView(tv)
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

    private fun clearField(tiet: TextInputEditText) {
        tiet.setText("")
    }
}