package br.com.jef.agendav6.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jef.agendav6.R
import br.com.jef.agendav6.ui.model.User
import br.com.jef.agendav6.ui.utils.listener.GetUser

class Adapter(val listUser: List<User>, val getUserTest: GetUser) :
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ItemViewHolder((view))


    }

    override fun getItemCount() = listUser.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = listUser[position]

        holder.txtName.text = user.name
        holder.txtTelType.text = user.typeTel
        holder.txtTelephone.text = user.telephone
        holder.txtBirhDate.text = user.birthDate
        holder.txtGender.text = user.gender

        holder.itemView.setOnClickListener(){
            getUserTest.onClick(user)
        }

    }
}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtName = view.findViewById<TextView>(R.id.txtNameRow)
    val txtTelType = view.findViewById<TextView>(R.id.txtTelTypeRow)
    val txtTelephone = view.findViewById<TextView>(R.id.txtTelephoneRow)
    val txtBirhDate = view.findViewById<TextView>(R.id.txtBirthDateRow)
    val txtGender = view.findViewById<TextView>(R.id.txtGenderRow)


}
