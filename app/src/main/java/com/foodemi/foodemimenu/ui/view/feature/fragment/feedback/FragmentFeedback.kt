package com.foodemi.foodemimenu.ui.view.feature.fragment.feedback

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.data.model.response.ModelFeedback
import com.foodemi.foodemimenu.databinding.FragmentFeedbackBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.navigation.FeedbackNavigator
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.feedback.FeedbackViewModel
import com.foodemi.foodemimenu.utils.constant.Const
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FragmentFeedback : CoreFragment<FragmentFeedbackBinding, FeedbackViewModel>(),
    FeedbackNavigator {

    @Inject
    lateinit var factory: ViewModelFactory

    private var feedbackViewModel: FeedbackViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_feedback

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: FeedbackViewModel
        get() {
            feedbackViewModel = ViewModelProvider(this, factory).get(FeedbackViewModel::class.java)
            return feedbackViewModel as FeedbackViewModel
        }

    private var tableNumber: String?      = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            tableNumber  = arguments?.getString(Const.TABLE_NUMBER_PICK)
        }
        feedbackViewModel?.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        setupEmoji()
        setupButtonDone()
    }

    @SuppressLint("SetTextI18n")
    private fun setupEmoji() {
        with(getViewDataBinding()) {
            groupOfRadioFeedback.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {

                    R.id.radio_emoji_angry -> {
                        radioEmojiAngry.isChecked    = true
                        radioEmojiSad.isChecked      = false
                        radioEmojiHappy.isChecked    = false
                        radioEmojiLove.isChecked     = false

                        emojiText.text = "Very Bad"
                    }

                    R.id.radio_emoji_sad -> {
                        radioEmojiAngry.isChecked    = false
                        radioEmojiSad.isChecked      = true
                        radioEmojiHappy.isChecked    = false
                        radioEmojiLove.isChecked     = false

                        emojiText.text = "Bad"
                    }

                    R.id.radio_emoji_happy -> {
                        radioEmojiAngry.isChecked    = false
                        radioEmojiSad.isChecked      = false
                        radioEmojiHappy.isChecked    = true
                        radioEmojiLove.isChecked     = false

                        emojiText.text = "Good"
                    }

                    R.id.radio_emoji_love -> {
                        radioEmojiAngry.isChecked    = false
                        radioEmojiSad.isChecked      = false
                        radioEmojiHappy.isChecked    = false
                        radioEmojiLove.isChecked     = true

                        emojiText.text = "Excellent"
                    }

                }
            }
        }
    }

    private fun setupButtonDone(){
        with(getViewDataBinding()){
            viewBtnActionToThankyou.setOnClickListener {
                val tNumber            = getNonNullString(tableNumber)
                val emojiFeels         = emojiText.text.toString()
                val feedbackText       = viewTextCatatanFeedback.text.toString()

                val now              = Calendar.getInstance()
                val feedbackDate       = SimpleDateFormat("dd-MM-yyyy HHmm", Locale.getDefault()).format(now.time).toString()

                val body = ModelFeedback(tNumber, emojiFeels, feedbackText, feedbackDate)
                feedbackViewModel?.sendFeedback(body)
            }
        }
    }

    private fun getNonNullString(t: String?): String{
        var realString = ""
        t?.let {
            realString = it
        }
        return realString
    }

    override fun handleFeedbackError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

    override fun handleFeedbackSuccess(data: Boolean) {
        getNavController().navigate(R.id.feedback_to_thankyou)
    }

}