package com.np.suprimpoudel.day1assignment.features.profile

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail

interface ContactView : MvpView {
    fun setDataIntoDialog(contactDetail: ContactDetail)
    fun setDataIntoRecyclerView(contactDetails: ArrayList<ContactDetail>)
    fun refreshScreen()
    fun showMessage(message: String)
}