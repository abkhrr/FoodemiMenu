package com.foodemi.foodemimenu.ui.view.feature.activity

import android.animation.AnimatorInflater
import android.animation.StateListAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.foodemi.foodemimenu.BR
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.ActivityMainBinding
import com.foodemi.foodemimenu.ui.view.base.CoreActivity
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.FragmentMenuList
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.activity.ActivityMainViewModel
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : CoreActivity<ActivityMainBinding, ActivityMainViewModel>(),
    HasAndroidInjector {

    @Inject
    lateinit var factory: ViewModelFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: ActivityMainViewModel
        get() = ViewModelProvider(this, factory).get(ActivityMainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDestinationChanged()
    }

    private fun setupDestinationChanged(){
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.pickTable -> {
                    hideToolbarBackButton()
                    view_toolbar_title.text = ""
                    appBarLayoutMain.stateListAnimator = hideElevationAppBar()
                }
                R.id.menuList -> {
                    showToolbar()
                    hideToolbarBackButton()
                    view_toolbar_title.text = "MENU FOODEMI"
                    appBarLayoutMain.stateListAnimator = backToElevation()
                }
                R.id.menuDetails -> {
                    showToolbar()
                    actionBarBackButton()
                    view_toolbar_title.text = "DETAIL MENU"
                    appBarLayoutMain.stateListAnimator = backToElevation()
                }
            }
        }
    }



    private fun actionBarBackButton(): ActionBar?{
        return supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun hideToolbarBackButton(): ActionBar?{
        return supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun showToolbar(){
        view_toolbar_main.visibility = View.VISIBLE
        appBarLayoutMain.visibility  = View.VISIBLE
    }

    private fun backToElevation(): StateListAnimator {
        return AnimatorInflater.loadStateListAnimator(this, R.animator.appbar_always_elevated)
    }

    private fun hideElevationAppBar(): StateListAnimator {
        return AnimatorInflater.loadStateListAnimator(this, R.animator.appbar_no_elevated)
    }

}