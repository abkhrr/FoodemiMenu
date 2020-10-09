package com.foodemi.foodemimenu.ui.view.feature.fragment.feedback

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentFeedbackBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.feedback.FeedbackViewModel
import javax.inject.Inject

class FragmentFeedback : CoreFragment<FragmentFeedbackBinding, FeedbackViewModel>(),
    AppNavigation {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedbackViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}