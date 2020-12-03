package com.udacity.shoestore.views

import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import com.google.android.material.textfield.TextInputEditText

@BindingMethods(
    BindingMethod(
        type = ShoeSizeEditText::class,
        attribute = "app:shoeSize",
        method = "setShoeSize"
    )
)

@InverseBindingMethods(
    InverseBindingMethod(
        type = ShoeSizeEditText::class,
        attribute = "app:shoeSize",
        method = "getShoeSize",
        event = "android:textAttrChanged"
    )
)
class ShoeSizeEditText : TextInputEditText {
    constructor(
        context: Context
    ) : super(context)

    constructor(
        context: Context,
        attributeSet: AttributeSet?
    ) : super(context, attributeSet)

    constructor(
        context: Context,
        attributeSet: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attributeSet, defStyleAttr)

    fun setShoeSize(size: Double) {
        this.setText(size.toString())
    }

    fun getShoeSize(): Double {
        val sizeAsString = this.text?.toString() ?: "0.0"
        return sizeAsString.toDouble()
    }
}
