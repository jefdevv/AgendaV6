package br.com.jef.agendav6.ui.utils.listener

import br.com.jef.agendav6.ui.model.User

interface GetUser {

    fun userList(user: List<User>)
    fun onClick(user: User)


}