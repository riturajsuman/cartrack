package com.retrofit.cartrack.landingUI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.retrofit.cartrack.R
import com.retrofit.cartrack.landingUI.model.UserItem

class CustomAdapter(val UserList: ArrayList<UserItem>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return UserList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.address_city.text = UserList[position].address.city
        holder.street.text = UserList[position].address.street
        holder.zipcode.text = UserList[position].address.zipcode
        holder.company_name.text = UserList[position].company.name
        holder.name.text = UserList[position].name
        holder.phone.text = UserList[position].phone

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val address_city: TextView = itemView.findViewById(R.id.address_city) as TextView
        val street: TextView = itemView.findViewById(R.id.street) as TextView
        val zipcode: TextView = itemView.findViewById(R.id.zipcode) as TextView
        val company_name: TextView = itemView.findViewById(R.id.company_name) as TextView
        val name: TextView = itemView.findViewById(R.id.name) as TextView
        val phone: TextView = itemView.findViewById(R.id.phone) as TextView

    }
}