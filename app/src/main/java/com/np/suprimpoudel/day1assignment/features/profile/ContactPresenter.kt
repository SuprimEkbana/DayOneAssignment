package com.np.suprimpoudel.day1assignment.features.profile

import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail

class ContactPresenter : MvpBasePresenter<ContactView>() {
    private val contactInteractor = ContactInteractor()

    fun showContactList() {
        ifViewAttached { view ->
            view.setDataIntoRecyclerView(contactInteractor.showAllContactDetail())
        }
    }

    fun removeContact(position: Int) {
        ifViewAttached { view ->
            contactInteractor.removeContactDetail(position)
            view.refreshScreen()
            view.showMessage("Deleted Successfully")
        }
    }

    fun addContactList(contactDetail: ContactDetail) {
        ifViewAttached { view ->
            contactInteractor.addContactDetail(contactDetail)
            view.refreshScreen()
            view.showMessage("Added Successfully")
        }
    }

    fun updateContactList(name: String, address: String, phoneNumber: String, position: Int) {
        ifViewAttached { view ->
            contactInteractor.updateContactDetail(name, address, phoneNumber, position)
            view.refreshScreen()
            view.showMessage("Updated Successfully")
        }
    }

    fun getSingleContactDetail(position: Int) {
        ifViewAttached { view ->
            val contactDetail = contactInteractor.getSingleContactDetail(position)
            view.setDataIntoDialog(contactDetail)
        }
    }

    fun showDataIntoDialog(
        contactDetail: ContactDetail,
        nameInput: TextInputEditText,
        addressInput: TextInputEditText,
        phoneInput: TextInputEditText
    ) {
        nameInput.setText(contactDetail.name)
        addressInput.setText(contactDetail.address)
        phoneInput.setText(contactDetail.phoneNumber)
    }
}