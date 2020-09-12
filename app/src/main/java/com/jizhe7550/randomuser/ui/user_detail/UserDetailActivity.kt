package com.jizhe7550.randomuser.ui.user_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.jizhe7550.randomuser.R
import com.jizhe7550.randomuser.base.BaseActivity
import com.jizhe7550.randomuser.databinding.ActivityUserDetailBinding
import com.jizhe7550.randomuser.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailActivity : BaseActivity<UserDetailEvent, UserDetailViewModel>() {

    private lateinit var binding: ActivityUserDetailBinding
    override val viewModel: UserDetailViewModel by viewModel(parameters = {
        parametersOf(intent.getParcelableExtra(USER_EXTRA))
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        enableFadeTransition()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val USER_EXTRA = "UserDetailActivity.USER"

        fun getIntent(context: Context, user: User): Intent =
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(USER_EXTRA, user)
            }
    }
}
