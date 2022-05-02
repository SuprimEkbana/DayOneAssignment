package com.np.suprimpoudel.day1assignment.features

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.np.suprimpoudel.day1assignment.R
import com.np.suprimpoudel.day1assignment.databinding.ActivityMainBinding
import com.np.suprimpoudel.day1assignment.databinding.DialogContactAddEditBinding
import com.np.suprimpoudel.day1assignment.features.`interface`.ContactListOnClick
import com.np.suprimpoudel.day1assignment.features.profile.ContactPresenter
import com.np.suprimpoudel.day1assignment.features.profile.ContactView
import com.np.suprimpoudel.day1assignment.features.shared.adapters.ContactDetailAdapter
import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail
import com.np.suprimpoudel.day1assignment.utils.AppConstants

class MainActivity : MvpActivity<ContactView, ContactPresenter>(), ContactView, ContactListOnClick {

    //Bindings
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogContactAddEditBinding

    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var alertDialog: AlertDialog
    private lateinit var nameInput: TextInputEditText
    private lateinit var phoneInput: TextInputEditText
    private lateinit var addressInput: TextInputEditText

    private var contactDetailAdapter: ContactDetailAdapter? = null

    //Activity Overridden Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding for Main Activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactRecyclerView = binding.recyclerView

        presenter.showContactList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addIcon -> showCustomDialog(AppConstants.ADD_ACTION, -1)
        }
        return super.onOptionsItemSelected(item)
    }


    //Custom Dialog
    private fun showCustomDialog(action: String, position: Int) {
        val builder = AlertDialog.Builder(this)
        val view = View.inflate(this, R.layout.dialog_contact_add_edit, null)
        dialogBinding = DialogContactAddEditBinding.bind(view)

        nameInput = dialogBinding.edtContactName
        addressInput = dialogBinding.edtContactAddress
        phoneInput = dialogBinding.edtContactPhone

        showDialogBox(builder, view, action, position)

        initListenerForDialog(action, position)
    }

    //MVP Overridden Methods
    override fun createPresenter(): ContactPresenter = ContactPresenter()

    override fun setDataIntoDialog(contactDetail: ContactDetail) {
        presenter.showDataIntoDialog(contactDetail, nameInput, addressInput, phoneInput)
    }

    override fun setDataIntoRecyclerView(contactDetails: ArrayList<ContactDetail>) {
        contactDetailAdapter = ContactDetailAdapter(contactDetails, this)
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
        contactRecyclerView.adapter = contactDetailAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun refreshScreen() {
        contactDetailAdapter?.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    //Recycler View Click Listener
    override fun onDeleteIconClicked(position: Int) {
        presenter.removeContact(position)
    }

    override fun onContactTileClicked(position: Int) {
        showCustomDialog(action = AppConstants.UPDATE_ACTION, position = position)
    }


    private fun showDialogBox(
        builder: AlertDialog.Builder,
        view: View,
        action: String,
        position: Int
    ) {
        builder.setView(view)
        alertDialog = builder.create()
        alertDialog.window?.setDimAmount(0.8f)
        alertDialog.setCancelable(true)
        alertDialog.show()
        alertDialog.window?.setGravity(Gravity.CENTER)

        if (action == AppConstants.UPDATE_ACTION) {
            dialogBinding.btnAddEdit.text = AppConstants.UPDATE_ACTION
            presenter.getSingleContactDetail(position)
        } else {
            dialogBinding.btnAddEdit.text = AppConstants.ADD_ACTION
        }
    }

    private fun initListenerForDialog(action: String, position: Int) {
        dialogBinding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        dialogBinding.btnAddEdit.setOnClickListener {
            addEditContactDetail(action, position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addEditContactDetail(action: String, position: Int) {

        val name = nameInput.text.toString().trim()
        val address = addressInput.text.toString().trim()
        val phone = phoneInput.text.toString().trim()

        if (name.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()) {
            if (action == AppConstants.ADD_ACTION) {
                presenter.addContactList(
                    ContactDetail(
                        name = name,
                        address = address,
                        phoneNumber = phone
                    )
                )
            } else {
                presenter.updateContactList(
                    name = name,
                    address = address,
                    phoneNumber = phone,
                    position = position
                )
            }
        }

        alertDialog.dismiss()
    }
}