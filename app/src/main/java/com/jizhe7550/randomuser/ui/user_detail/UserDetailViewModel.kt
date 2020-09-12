package com.jizhe7550.randomuser.ui.user_detail

import com.jizhe7550.randomuser.base.BaseViewModel
import com.jizhe7550.randomuser.model.User
import com.jizhe7550.randomuser.ui.user_detail.UserDetailEvent

class UserDetailViewModel(
    val user: User
) : BaseViewModel<UserDetailEvent>()