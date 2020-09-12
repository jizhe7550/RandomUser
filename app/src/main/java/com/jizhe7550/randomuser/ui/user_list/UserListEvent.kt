package com.jizhe7550.randomuser.ui.user_list

import com.jizhe7550.randomuser.base.BaseEvent
import com.jizhe7550.randomuser.model.User

sealed class UserListEvent : BaseEvent {
    class GoToUserDetail(val user: User, val position: Int) : UserListEvent()
    class DisplayError(val message: String) : UserListEvent()
}