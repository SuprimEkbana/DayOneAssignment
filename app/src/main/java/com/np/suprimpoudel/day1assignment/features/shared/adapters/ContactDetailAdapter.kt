package com.np.suprimpoudel.day1assignment.features.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.suprimpoudel.day1assignment.R
import com.np.suprimpoudel.day1assignment.features.`interface`.ContactListOnClick
import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail
import com.np.suprimpoudel.day1assignment.features.shared.view_holders.ContactDetailViewHolder

class ContactDetailAdapter(
    private val contactDetails: ArrayList<ContactDetail>,
    private val listener: ContactListOnClick
) :
    RecyclerView.Adapter<ContactDetailViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactDetailViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact_tile, parent, false)
        return ContactDetailViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ContactDetailViewHolder, position: Int) {
        holder.bind(contactDetails[position])
    }

    override fun getItemCount(): Int = contactDetails.size
}