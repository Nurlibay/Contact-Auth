package uz.unidev.contactauth.presentation.main.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.unidev.contactauth.data.source.remote.response.ContactResponse
import uz.unidev.contactauth.databinding.ItemContactBinding

class ContactAdapter :
    ListAdapter<ContactResponse, ContactAdapter.ContactViewHolder>(DiffUtilCallBack) {

    object DiffUtilCallBack : DiffUtil.ItemCallback<ContactResponse>() {
        override fun areItemsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ContactResponse,
            newItem: ContactResponse
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.phone == newItem.phone
        }
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.tvContactName.text = data.name
            binding.tvContactPhone.text = data.phone
            binding.ivRemove.setOnClickListener {
                onDeleteIconClick?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.ivEdit.setOnClickListener {
                onUpdateIconClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }
    }

    private var onUpdateIconClick: ((ContactResponse) -> Unit?)? = null
    fun setOnUpdateIconClickListener(block: (ContactResponse) -> Unit) {
        onUpdateIconClick = block
    }

    private var onDeleteIconClick: ((ContactResponse) -> Unit?)? = null
    fun setOnDeleteIconClickListener(block: (ContactResponse) -> Unit) {
        onDeleteIconClick = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind()
    }
}